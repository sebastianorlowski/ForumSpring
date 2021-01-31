package pl.orlowski.sebastian.forumspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.orlowski.sebastian.forumspring.dto.InscriptionDto;
import pl.orlowski.sebastian.forumspring.dto.TopicDto;
import pl.orlowski.sebastian.forumspring.inscription.Inscription;
import pl.orlowski.sebastian.forumspring.repository.UserRepository;
import pl.orlowski.sebastian.forumspring.service.InscriptionService;
import pl.orlowski.sebastian.forumspring.service.TopicService;
import pl.orlowski.sebastian.forumspring.service.UserService;
import pl.orlowski.sebastian.forumspring.topic.Topic;

import java.util.List;

@Controller
public class InscriptionController {

    private InscriptionService inscriptionService;
    private TopicService topicService;
    private UserService userService;

    @Autowired
    public InscriptionController(InscriptionService inscriptionService,
                                 TopicService topicService,
                                 UserService userService) {
        this.inscriptionService = inscriptionService;
        this.topicService = topicService;
        this.userService = userService;
    }

    @GetMapping("/topic/{idTopic}/inscription")
    public String getNewInscriptionWindow(@PathVariable Long idTopic, Model model) {
        Topic topic = topicService.findOne(idTopic);
        model.addAttribute("inscription", topic);
        return "inscription";
    }

    //    Add inscription
    @PostMapping("/topic/{idTopic}")
    public String addNewInscription(@PathVariable Long idTopic,
                                    InscriptionDto inscriptionDto,
                                    Authentication auth) {
        Topic topic = topicService.findOne(idTopic);
        Inscription inscription = new Inscription();
        inscription.setText(inscriptionDto.getText());
        inscription.setTopic(topic);
        inscription.setUser(userService.findByLogin(auth.getName()));

        inscriptionService.save(inscription);

        return "redirect:/topic/" + idTopic;
    }

//    Inscription window
    @GetMapping("/inscription/edit/{id}")
    public String editInscription(@PathVariable Long id, Model model,
                                  Authentication auth) {
        Inscription inscription = inscriptionService.findOne(id);
        if (inscription.getUser() != userService.findByLogin(auth.getName())) {
            return "redirect:/topic/" + inscription.getTopic().getId();
        }
        model.addAttribute("inscription", inscription);

        return "inscriptionEdit";
}

    @PostMapping("/inscription/update/{id}")
    public String updateInscription(@PathVariable Long id, String text) {
        Inscription inscription = inscriptionService.findOne(id);
        inscription.setId(id);
        inscription.setUser(inscription.getUser());
        inscription.setTopic(inscription.getTopic());
        inscription.setText(text);

        inscriptionService.save(inscription);

        return "redirect:/topic/" + inscription.getTopic().getId();
    }

//    Delete inscription
    @GetMapping("inscription/delete/{id}")
    public String deleteInscription(@PathVariable Long id,
                                    Authentication auth) {
        Inscription inscription = inscriptionService.findOne(id);
        Topic topic = inscription.getTopic();
        if (inscription.getUser() != userService.findByLogin(auth.getName())) {
            return "redirect:/topic/" + topic.getId();
        }
        inscriptionService.delete(id);

        return "redirect:/topic/" + topic.getId();
    }

}

package pl.orlowski.sebastian.forumspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.orlowski.sebastian.forumspring.dto.InscriptionDto;
import pl.orlowski.sebastian.forumspring.inscription.Inscription;
import pl.orlowski.sebastian.forumspring.repository.UserRepository;
import pl.orlowski.sebastian.forumspring.service.InscriptionService;
import pl.orlowski.sebastian.forumspring.service.TopicService;
import pl.orlowski.sebastian.forumspring.topic.Topic;
import pl.orlowski.sebastian.forumspring.user.User;

@Controller
public class InscriptionController {

    private InscriptionService inscriptionService;
    private TopicService topicService;
    private UserRepository userRepository;

    @Autowired
    public InscriptionController(InscriptionService inscriptionService,
                                 TopicService topicService,
                                 UserRepository userRepository) {
        this.inscriptionService = inscriptionService;
        this.topicService = topicService;
        this.userRepository = userRepository;
    }

//    Edit inscription
    @GetMapping("inscription/{id}")
    public String editInscription(@PathVariable Long id, Model model,
                                  Authentication auth) {
        Inscription inscription = inscriptionService.findOne(id);
        if (inscription.getUser() != userRepository.findByLogin(auth.getName())) {
            return "redirect:/topic/" + inscription.getTopic();
        }
        model.addAttribute("inscription", inscription);

        return "inscriptionEdit";
}

//    Add inscription
    @PostMapping("topic/{idTopic}/inscription")
    public String addNewInscription(@PathVariable Long idTopic,
                                    InscriptionDto inscriptionDto,
                                    Authentication auth) {
        Topic topic = topicService.findOne(idTopic);
        Inscription inscription = new Inscription();
        inscription.setText(inscriptionDto.getText());
        inscription.setTopic(topic);
        inscription.setUser(userRepository.findByLogin(auth.getName()));

        inscriptionService.save(inscription);

        return "redirect:/topic/" + idTopic;
    }

//    Delete inscription
    @GetMapping("inscription/delete/{id}")
    public String deleteInscription(@PathVariable Long id,
                                    Authentication auth) {
        Inscription inscription = inscriptionService.findOne(id);
        Long topicId = inscription.getId();

        if (inscription.getUser() != userRepository.findByLogin(auth.getName())) {
            return "redirect :/topic/" + id;
        }
        inscriptionService.delete(id);

        return "redirect:/topic/" + topicId;
    }
}

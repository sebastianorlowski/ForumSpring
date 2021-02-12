package pl.orlowski.sebastian.forumspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.orlowski.sebastian.forumspring.dto.NewInscription;
import pl.orlowski.sebastian.forumspring.inscription.Inscription;
import pl.orlowski.sebastian.forumspring.service.InscriptionService;
import pl.orlowski.sebastian.forumspring.service.TopicService;
import pl.orlowski.sebastian.forumspring.service.UserService;
import pl.orlowski.sebastian.forumspring.topic.Topic;

import javax.validation.Valid;

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

    @GetMapping("/topic/{topicId}/inscription")
    public String createInscription(@PathVariable Long topicId, Model model) {

        if (topicService.existById(topicId)) {
            model.addAttribute("inscription", new NewInscription());
            return "inscription";
        } else {
            return "index";
        }
    }

    @PostMapping("/topic/{topicId}")
    public String addNewInscription(@Valid @ModelAttribute("inscription")NewInscription newInscription,
                                    BindingResult bindingResult,
                                    @PathVariable Long topicId,
                                    Authentication auth,
                                    Model model) {

        if (bindingResult.hasErrors()) {
            return "inscription";
        }

        Topic topic = topicService.findOne(topicId);

        Inscription inscription = new Inscription();
        inscription.setText(newInscription.getText());
        inscription.setTopic(topic);
        inscription.setUser(userService.findByLogin(auth.getName()));

        inscriptionService.save(inscription);

        return "redirect:/topic/" + topicId;
    }

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
    @PostMapping("/inscription/delete/{id}")
    public String deleteInscription(@RequestParam Long id,
                                    Authentication auth) {

        boolean hasUserRole = auth.getAuthorities().stream()
                    .anyMatch(r -> r.getAuthority().equals("ADMIN"));
        if (inscriptionService.existById(id)) {
            Inscription inscription = inscriptionService.findOne(id);
            Topic topic = topicService.findOne(inscription.getId());
            inscriptionService.delete(id);
            return "redirect:/admin?deleteinscriptionsuccess";
        }
        return "redirect:/admin";
    }

}

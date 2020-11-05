package pl.orlowski.sebastian.forumspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.orlowski.sebastian.forumspring.dto.InscriptionDto;
import pl.orlowski.sebastian.forumspring.inscription.Inscription;
import pl.orlowski.sebastian.forumspring.repository.UserRepository;
import pl.orlowski.sebastian.forumspring.service.InscriptionService;
import pl.orlowski.sebastian.forumspring.service.TopicService;
import pl.orlowski.sebastian.forumspring.topic.Topic;
import pl.orlowski.sebastian.forumspring.user.User;

@Controller
@RequestMapping("/topic/")
public class TopicController {

    private TopicService topicService;
    private InscriptionService inscriptionService;
    private UserRepository userRepository;

    @Autowired
    public TopicController(TopicService topicService,
                           InscriptionService inscriptionService,
                           UserRepository userRepository) {
        this.topicService = topicService;
        this.inscriptionService = inscriptionService;
        this.userRepository = userRepository;
    }

    @ModelAttribute("inscription")
    public InscriptionDto inscriptionDto() {
        return new InscriptionDto();
    }

    @GetMapping("{id}")
    public String getTopicById(@PathVariable Long id, Model model) {
        Topic topic = topicService.findOne(id);
        model.addAttribute("topic", topic);
        return "topic";
    }

    @GetMapping("{id}/inscription")
    public String addInscription(@PathVariable Long id, Model model) {
        model.addAttribute("inscription", new InscriptionDto());
        return "inscription";
    }

    @PostMapping("{id}")
    public String createNewInscription(@PathVariable Long id,
                                       InscriptionDto inscriptionDto,
                                       Authentication auth) {
        Topic topic = topicService.findOne(id);
        Inscription inscription = new Inscription();
        inscription.setTopic(topic);
        inscription.setText(inscriptionDto.getText());
        inscription.setUser(userRepository.findByLogin(auth.getName()));

        inscriptionService.save(inscription);

        return "redirect:/topic/" + id;
    }

    }




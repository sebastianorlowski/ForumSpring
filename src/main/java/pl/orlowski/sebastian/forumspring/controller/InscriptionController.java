package pl.orlowski.sebastian.forumspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.orlowski.sebastian.forumspring.service.TopicService;
import pl.orlowski.sebastian.forumspring.topic.Topic;

@Controller
@RequestMapping("/topic")
public class InscriptionController {

    private TopicService topicService;

    @Autowired
    public InscriptionController(TopicService topicService) {
        this.topicService = topicService;
    }

    @GetMapping("/{id}/inscription")
    public String getInscription(@PathVariable Long id, Model model) {
        Topic topic = topicService.findOne(id);
        model.addAttribute("inscription");
        return "inscription";
    }


}

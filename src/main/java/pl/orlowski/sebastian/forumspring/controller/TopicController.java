package pl.orlowski.sebastian.forumspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.orlowski.sebastian.forumspring.inscription.Inscription;
import pl.orlowski.sebastian.forumspring.service.TopicService;
import pl.orlowski.sebastian.forumspring.topic.Topic;

@Controller
@RequestMapping("/topic")
public class TopicController {

    @Autowired
    private TopicService topicService;

    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    @GetMapping("/{id}")
    public String getTopicById(@PathVariable Long id, Model model) {
        Topic topic = topicService.findOne(id);
        model.addAttribute("topic", topic);
        return "topic";
    }

    }




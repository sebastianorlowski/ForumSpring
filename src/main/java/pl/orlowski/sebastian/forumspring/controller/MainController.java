package pl.orlowski.sebastian.forumspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.orlowski.sebastian.forumspring.service.TopicService;
import pl.orlowski.sebastian.forumspring.topic.Topic;

@Controller
@RequestMapping("/")
public class MainController {

    private TopicService topicService;

    @Autowired
    public MainController(TopicService topicService) {
        this.topicService = topicService;
    }

    @ModelAttribute("topic")
    public Topic topic() {
        return new Topic();
    }

    @GetMapping
    public String topicList(Model model) {
        model.addAttribute("topic", new Topic());
        return "index";
    }

}

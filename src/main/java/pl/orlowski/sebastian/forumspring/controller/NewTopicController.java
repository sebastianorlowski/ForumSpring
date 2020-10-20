package pl.orlowski.sebastian.forumspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.orlowski.sebastian.forumspring.service.TopicService;
import pl.orlowski.sebastian.forumspring.topic.Topic;

@Controller
@RequestMapping("/newTopic")
public class NewTopicController {

    private TopicService topicService;

    @Autowired
    public NewTopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    @ModelAttribute("topic")
    public Topic topic() {
        return new Topic();
    }

    @GetMapping
    public String newTopic(Model model) {
        model.addAttribute("topic", new Topic());
        return "newTopic";
    }

    @PostMapping
    public String createNewTopic(@ModelAttribute("topic") Topic topic) {
        topicService.save(topic);
        return "redirect:/newTopic?success";
    }
}
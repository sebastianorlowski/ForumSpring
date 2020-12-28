package pl.orlowski.sebastian.forumspring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.orlowski.sebastian.forumspring.service.TopicService;
import pl.orlowski.sebastian.forumspring.topic.Topic;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/search")
public class SearchController {

    private TopicService topicService;

    public SearchController(TopicService topicService) {
        this.topicService = topicService;
    }

    @GetMapping
    public String search(Model model) {
        int size = topicService.findAll().size();
        model.addAttribute("topicsize", size);
        return "search";
    }

    @GetMapping("/{topicName}")
    public String topicsByRegex(@PathVariable String topicName, Model model) {
        List<Topic> topicsByTopicTitle = topicService.findTopicsByRegex(topicName);

        if (topicsByTopicTitle != null) {
            model.addAttribute("searchResult", topicsByTopicTitle);
            return "searchList";
        } else {
            return "search";
        }
    }

    @PostMapping("/{topicName}")
    public String findTopicByRegex(@RequestParam String topicName) {
        return "redirect:/search/" + topicName;
    }
}

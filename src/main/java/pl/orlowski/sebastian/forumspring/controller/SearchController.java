package pl.orlowski.sebastian.forumspring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/search")
public class SearchController {

    @GetMapping
    public String search() {
        return "search";
    }

//    @PostMapping("/search={topicName}")
//    public String findTopicByName(@PathVariable) {
//
//    }
}

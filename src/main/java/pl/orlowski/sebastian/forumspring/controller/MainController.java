package pl.orlowski.sebastian.forumspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.orlowski.sebastian.forumspring.service.InscriptionService;
import pl.orlowski.sebastian.forumspring.service.TopicService;

@Controller
public class MainController {

    private TopicService topicService;
    private InscriptionService inscriptionService;

    @Autowired
    public MainController(TopicService topicService,
                          InscriptionService inscriptionService) {
        this.topicService = topicService;
        this.inscriptionService = inscriptionService;
    }

    @GetMapping("/")
    public String topicList(Model model) {
        model.addAttribute("newInscriptionList", inscriptionService.findInscriptionsByDate());
        model.addAttribute("newTopicList", topicService.findTopicsByDate());
        return "index";
    }

}

package pl.orlowski.sebastian.forumspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.orlowski.sebastian.forumspring.repository.TopicRepository;
import pl.orlowski.sebastian.forumspring.repository.UserRepository;
import pl.orlowski.sebastian.forumspring.service.InscriptionService;
import pl.orlowski.sebastian.forumspring.service.TopicService;
import pl.orlowski.sebastian.forumspring.topic.Topic;

@Controller
@RequestMapping("/topic/")
public class TopicController {

    private TopicService topicService;
    private UserRepository userRepository;
    private InscriptionService inscriptionService;
    private TopicRepository topicRepository;

    @Autowired
    public TopicController(TopicService topicService,
                           UserRepository userRepository,
                           InscriptionService inscriptionService,
                           TopicRepository topicRepository) {

        this.topicService = topicService;
        this.userRepository = userRepository;
        this.inscriptionService = inscriptionService;
        this.topicRepository = topicRepository;
    }

    /* find topic by id */
    @GetMapping("{id}")
    public String getTopicWindow(@PathVariable Long id, Model model) {
        Topic topic = topicService.findOne(id);
        if (topicRepository.existsById(id)) {
            model.addAttribute("topic", topic);
            return "topic";
        }
        return "redirect:/";
    }

//    edit topic text
    @GetMapping("/edit/{id}")
    public String showEditTopicWindow(@PathVariable Long id,
                                      Authentication auth,
                                      Model model) {
        Topic topic = topicService.findOne(id);
        if (topic.getUser() != userRepository.findByLogin(auth.getName())) {
            return "redirect:/topic/" + id;
        }
        model.addAttribute("topic", topic);
        return "topicEdit";
    }

//  delete topic
    @GetMapping("/delete/{id}")
    public String deleteTopic(@PathVariable Long id,
                              Authentication auth) {
        Topic topic = topicService.findOne(id);
        if (topic.getUser() != (userRepository.findByLogin(auth.getName()))) {
            return "redirect:/topic/" + id;
        }
        topicService.delete(id);

        return "redirect:/";
    }

}

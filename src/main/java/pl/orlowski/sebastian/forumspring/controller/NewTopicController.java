package pl.orlowski.sebastian.forumspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.orlowski.sebastian.forumspring.repository.UserRepository;
import pl.orlowski.sebastian.forumspring.service.TopicService;
import pl.orlowski.sebastian.forumspring.service.UserService;
import pl.orlowski.sebastian.forumspring.topic.NewTopic;
import pl.orlowski.sebastian.forumspring.topic.Topic;

@Controller
@RequestMapping("/newTopic")
public class NewTopicController {

    private TopicService topicService;

    private UserService userService;

    @Autowired
    public NewTopicController(TopicService topicService,
                              UserService userService) {
        this.topicService = topicService;
        this.userService = userService;
    }

    @GetMapping
    public String newTopic(Model model) {
        model.addAttribute("newTopic", new NewTopic());
        return "newTopic";
    }

    @PostMapping
    public String createNewTopic(NewTopic newTopic, Authentication auth) {
        Topic topic = new Topic();
        topic.setUser(userService.findByLogin(auth.getName()));
        topic.setTitle(newTopic.getTitle());
        topic.setText(newTopic.getText());

        topicService.save(topic);
        return "redirect:/topic/" + topic.getId();
    }

}
package pl.orlowski.sebastian.forumspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.orlowski.sebastian.forumspring.inscription.Inscription;
import pl.orlowski.sebastian.forumspring.service.InscriptionService;
import pl.orlowski.sebastian.forumspring.service.TopicService;
import pl.orlowski.sebastian.forumspring.service.UserService;
import pl.orlowski.sebastian.forumspring.user.User;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    private UserService userService;
    private InscriptionService inscriptionService;
    private TopicService topicService;

    @Autowired
    public UserController(UserService userService, InscriptionService inscriptionService, TopicService topicService) {
        this.userService = userService;
        this.inscriptionService = inscriptionService;
        this.topicService = topicService;
    }

    @GetMapping("/{userLogin}")
    public String getInscriptionsByUser(@PathVariable("userLogin") String userLogin, Model model) {
        User user = userService.findByLogin(userLogin);

        if (user != null) {
            model.addAttribute("user", user);
            model.addAttribute("inscriptions", inscriptionService.getInscriptionsByUser(user));
            model.addAttribute("topic", topicService.findTopicsByUser(user));

            return "userInfo";
        } else {
            return "admin";
        }
    }

    @PostMapping("/{userLogin}")
    public String findUserByLogin(@RequestParam String userLogin) {
        return "redirect:/user/" + userLogin;
    }

    @PostMapping("/delete/{userLogin}")
    public String deleteUserByLogin(@RequestParam String userLogin) {

        if (userService.userIsExist(userService.findByLogin(userLogin))) {
            userService.deleteByLogin(userLogin);

            return "redirect:/admin?deleteusersuccess";
        }
        return "redirect:/admin";
    }
}

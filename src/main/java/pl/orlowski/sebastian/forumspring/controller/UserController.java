package pl.orlowski.sebastian.forumspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.orlowski.sebastian.forumspring.service.InscriptionService;
import pl.orlowski.sebastian.forumspring.service.TopicService;
import pl.orlowski.sebastian.forumspring.service.UserService;
import pl.orlowski.sebastian.forumspring.user.User;

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

    @GetMapping
    public String search() {
        return "searchUser";
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

            return "searchUser";
        }
    }

    @PostMapping("/{userLogin}")
    public String findUserByLogin(@RequestParam String userLogin) {
        return "redirect:/user/" + userLogin;
    }

    @PostMapping("/delete/{userLogin}")
    public String deleteUserByLogin(@RequestParam String userLogin, Authentication auth) {

        boolean hasUserRole = auth.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("ADMIN"));

        if (userService.userIsExist(userService.findByLogin(userLogin)) || hasUserRole) {
            userService.enabledUser(userLogin, false);

            return "redirect:/admin?deleteusersuccess";
        }
        return "redirect:/admin";
    }

    @PostMapping("/enable/{userLogin}")
    public String enableUserByLogin(@RequestParam String userLogin, Authentication auth) {

        boolean hasUserRole = auth.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("ADMIN"));

        if (userService.userIsExist(userService.findByLogin(userLogin)) || hasUserRole) {
            userService.enabledUser(userLogin, true);

            return "redirect:/admin?enableusersuccess";
        }
        return "redirect:/admin";
    }
}

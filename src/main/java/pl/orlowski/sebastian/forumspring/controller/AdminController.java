package pl.orlowski.sebastian.forumspring.controller;

import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.orlowski.sebastian.forumspring.inscription.Inscription;
import pl.orlowski.sebastian.forumspring.repository.RoleRepository;
import pl.orlowski.sebastian.forumspring.service.InscriptionService;
import pl.orlowski.sebastian.forumspring.service.TopicService;
import pl.orlowski.sebastian.forumspring.service.UserService;
import pl.orlowski.sebastian.forumspring.topic.Topic;
import pl.orlowski.sebastian.forumspring.user.User;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private UserService userService;
    private TopicService topicService;
    private InscriptionService inscriptionService;
    private RoleRepository roleRepository;

    @Autowired
    public AdminController(UserService userService, TopicService topicService, InscriptionService inscriptionService) {
        this.userService = userService;
        this.topicService = topicService;
        this.inscriptionService = inscriptionService;
    }

    @GetMapping()
    public String adminController() {
        return "admin";
    }

    /* find users and give option to delete and show inscription with id*/
    @GetMapping("/getusers")
    public String getAllUsers(Model model) {
        return findPaginatedUser(1, model);
    }

    /* Pagination users */

    @GetMapping("/getusers/page/{pageNumber}")
    public String findPaginatedUser(@PathVariable("pageNumber") int pageNumber, Model model) {
        int pageSize = 10;

        Page<User> page = userService.findPaginated(pageNumber, pageSize);
        List<User> allUsers = page.getContent();

        model.addAttribute("pageNo", page.getNumber());
        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalElements", page.getTotalElements());
        model.addAttribute("userList",  allUsers);
        model.addAttribute("inscription", inscriptionService);
        model.addAttribute("role", userService);
        model.addAttribute("topic", topicService);

        return "allUsers";
    }

    @PostMapping("/getusers/page/{pageNumber}")
    public String goToPage(@RequestParam("pageNumber") int pageNumber) {
        return "redirect:/admin/getusers/page/" + pageNumber;
    }


//    @GetMapping()
//    public String deleteUserByLogin(String login) {
//        User user = userService.findByLogin(login);
//
//        userService.delete(user.getId());
//
//        return "redirect:/admin/getusers";
//    }

}


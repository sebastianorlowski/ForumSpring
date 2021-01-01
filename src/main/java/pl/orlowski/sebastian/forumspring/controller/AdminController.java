package pl.orlowski.sebastian.forumspring.controller;

import org.dom4j.rule.Mode;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.orlowski.sebastian.forumspring.inscription.Inscription;
import pl.orlowski.sebastian.forumspring.service.InscriptionService;
import pl.orlowski.sebastian.forumspring.service.TopicService;
import pl.orlowski.sebastian.forumspring.service.UserService;
import pl.orlowski.sebastian.forumspring.topic.Topic;
import pl.orlowski.sebastian.forumspring.user.User;

import java.util.List;

@Controller("/admin")
public class AdminController {

    private UserService userService;
    private TopicService topicService;
    private InscriptionService inscriptionService;

    public AdminController(UserService userService, TopicService topicService, InscriptionService inscriptionService) {
        this.userService = userService;
        this.topicService = topicService;
        this.inscriptionService = inscriptionService;
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

        return "allUsers";
    }

    @PostMapping("/getusers/page/{pageNumber}")
    public String goToPage(@RequestParam("pageNumber") int pageNumber) {
        return "redirect:/admin/getusers/page/" + pageNumber;
    }

    @GetMapping("/user/{userLogin}")
    public String getInscriptionsByUser(String login, Model model) {
        User user = userService.findByLogin(login);
        List<Inscription> allInscriptions = inscriptionService.getInscriptionsByUser(user);

        model.addAttribute("inscriptionList", allInscriptions);

        return "allInscriptions";
    }

    @GetMapping()
    public String deleteUserByLogin(String login) {
        User user = userService.findByLogin(login);

        userService.delete(user.getId());

        return "redirect:/admin/getusers";
    }

}


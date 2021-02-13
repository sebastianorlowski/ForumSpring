package pl.orlowski.sebastian.forumspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.orlowski.sebastian.forumspring.dto.TopicDto;
import pl.orlowski.sebastian.forumspring.inscription.Inscription;
import pl.orlowski.sebastian.forumspring.service.InscriptionService;
import pl.orlowski.sebastian.forumspring.service.TopicService;
import pl.orlowski.sebastian.forumspring.service.UserService;
import pl.orlowski.sebastian.forumspring.topic.Topic;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/topic")
public class TopicController {

    private TopicService topicService;
    private InscriptionService inscriptionService;
    private UserService userService;

    @Autowired
    public TopicController(TopicService topicService,
                           InscriptionService inscriptionService,
                           UserService userService) {
        this.topicService = topicService;
        this.inscriptionService = inscriptionService;
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public String getTopicWindow(@PathVariable Long id, Model model) {

        Topic topic = topicService.findOne(id);

        if (topicService.existById(id)) {
            model.addAttribute("topic", topic);
            model.addAttribute("inscriptions", inscriptionService.getInscriptionsByTopicId(id));
            return findPaginatedTopic(1, id, model);
        }
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String showEditTopicWindow(@PathVariable Long id,
                                      Authentication auth,
                                      Model model) {
        Topic topic = topicService.findOne(id);

        if (topicService.existById(id) && topic.getUser() == userService.findByLogin(auth.getName())) {
            model.addAttribute("topic", topic);
            return "topicEdit";
        }
        return "redirect:/topic/" + id;
    }

    @PostMapping("/update/{id}")
    public String updateTopic(@Valid @ModelAttribute("topic") TopicDto topicDto,
                              BindingResult bindingResult, @PathVariable Long id, String text) {

        if (bindingResult.hasErrors()) {
            return "topicEdit";
        }

        Topic topic = topicService.findOne(id);
        topic.setId(id);
        topic.setText(text);

        topicService.save(topic);

        return "redirect:/topic/" + topic.getId();
    }

    @PostMapping("/delete/{topicId}")
    public String deleteTopicById(@RequestParam Long topicId, Authentication auth) {

        boolean hasUserRole = auth.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("ADMIN"));

        if (topicService.existById(topicId) || hasUserRole) {

            inscriptionService.deleteInscriptionsByTopic(topicService.findOne(topicId));
            topicService.delete(topicId);
            return "redirect:/admin?deletetopicsuccess";
        }
        return "redirect:/admin";
    }

    /* Get Topic list */
    @GetMapping()
    public String getAllTopics(Model model) {
        return findPaginated(1, model);
    }

    /* Pagination topic list */
    @GetMapping("/page/{pageNumber}")
    public String findPaginated(@PathVariable("pageNumber") int pageNumber, Model model) {
        int pageSize = 10;

        Page<Topic> page = topicService.findPaginated(pageNumber, pageSize);
        List<Topic> listTopic = page.getContent();

        model.addAttribute("pageNo", page.getNumber());
        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalElements", page.getTotalElements());
        model.addAttribute("topicList",  listTopic);

        return "allTopics";
    }

    @PostMapping("/page/{pageNumber}")
    public String goToPage(@RequestParam("pageNumber") int pageNumber) {
        return "redirect:/topic/page/" + pageNumber;
    }

    /* Pagination inscription list */
    @GetMapping("/{topicId}/page/{pageNumber}")
    public String findPaginatedTopic(@PathVariable("pageNumber") int pageNumber, @PathVariable("topicId") Long topicId, Model model) {
        int pageSize = 10;

        Page<Inscription> page = inscriptionService.findPaginated(pageNumber, pageSize, topicId);
        List<Inscription> listInscription = page.getContent();
        Topic topic = topicService.findOne(topicId);

        model.addAttribute("pageNo", page.getNumber());
        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalElements", page.getTotalElements());
        model.addAttribute("inscriptionList",  listInscription);
        model.addAttribute("topic", topic);

        return "topic";
    }

    @PostMapping("/{topicId}/page/{pageNumber}")
    public String goToPageTopic(@RequestParam("pageNumber") int pageNumber,
                                @PathVariable("topicId") Long topicId) {
        return "redirect:/topic/" + topicId + "/page/" + pageNumber;
    }
}

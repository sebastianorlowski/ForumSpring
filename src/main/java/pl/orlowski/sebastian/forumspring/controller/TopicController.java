package pl.orlowski.sebastian.forumspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.orlowski.sebastian.forumspring.repository.TopicRepository;
import pl.orlowski.sebastian.forumspring.repository.UserRepository;
import pl.orlowski.sebastian.forumspring.service.InscriptionService;
import pl.orlowski.sebastian.forumspring.service.TopicService;
import pl.orlowski.sebastian.forumspring.topic.Topic;

import java.util.List;

@Controller
@RequestMapping("/topic")
public class TopicController {

    private TopicService topicService;
    private TopicRepository topicRepository;
    private InscriptionService inscriptionService;
    private UserRepository userRepository;

    @Autowired
    public TopicController(TopicService topicService,
                           TopicRepository topicRepository,
                           InscriptionService inscriptionService,
                           UserRepository userRepository) {
        this.topicService = topicService;
        this.topicRepository = topicRepository;
        this.inscriptionService = inscriptionService;
        this.userRepository = userRepository;
    }

    /* find topic by id */
    @GetMapping("/{id}")
    public String getTopicWindow(@PathVariable Long id, Model model) {
        Topic topic = topicService.findOne(id);
        if (topicRepository.existsById(id)) {
            model.addAttribute("topic", topic);
            model.addAttribute("inscriptions", inscriptionService.getInscriptionsByTopicId(id));
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

    @PostMapping("/update/{id}")
    public String updateTopic(@PathVariable Long id, String text) {
        Topic topic = topicRepository.findById(id).get();
        topic.setId(id);
        topic.setTitle(topic.getTitle());
        topic.setUser(topic.getUser());
        topic.setText(text);

        topicRepository.save(topic);

        return "redirect:/topic/" + topic.getId();
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

    /* Get Topic list */
    @GetMapping("/page")
    public String getAllTopics(Model model) {
        return findPaginated(1, model);
    }

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
}

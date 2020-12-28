package pl.orlowski.sebastian.forumspring.topic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.orlowski.sebastian.forumspring.inscription.Inscription;
import pl.orlowski.sebastian.forumspring.repository.TopicRepository;
import pl.orlowski.sebastian.forumspring.service.TopicService;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class TopicServiceImpl implements TopicService {

    private TopicRepository topicRepository;

    @Autowired
    public TopicServiceImpl(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    @Override
    public Topic save(Topic topic) {
        return topicRepository.save(topic);
    }

    @Override
    public List<Topic> findAll() {
        return topicRepository.findAll();
    }

    @Override
    public Topic findOne(Long id) {
        return topicRepository.getOne(id);
    }

    @Override
    public void delete(Long id) {
        topicRepository.delete(findOne(id));
    }

    @Override
    public Set<Topic> findTopicsByDate() {
        return topicRepository.findTop5ByOrderByCreatedAtDesc();
    }

    @Override
    public List<Topic> findAllByDateAsc() {
        return topicRepository.findAllByOrderByCreatedAtDesc();
    }

    @Override
    public Page<Topic> findPaginated(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, Sort.by("createdAt").descending());
        return this.topicRepository.findAll(pageable);
    }

    @Override
    public boolean existById(Long id) {
        return topicRepository.existsById(id);
    }

    @Override
    public List<Topic> findTopicsByRegex(String regex) {
        List<Topic> topicRegex = new ArrayList<>();
        String pattern = ".*" + regex.toLowerCase() + ".*";
        for (Topic t : findAll()) {
            if (t.getTitle().toLowerCase().matches(pattern)) {
                topicRegex.add(t);
            }
        }
        return topicRegex;
    }

}

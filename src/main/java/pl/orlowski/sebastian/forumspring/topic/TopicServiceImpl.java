package pl.orlowski.sebastian.forumspring.topic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.orlowski.sebastian.forumspring.inscription.Inscription;
import pl.orlowski.sebastian.forumspring.repository.TopicRepository;
import pl.orlowski.sebastian.forumspring.service.TopicService;

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
    public Set<Topic> findTop5ByOrderByCreatedAtDesc() {
        return topicRepository.findTop5ByOrderByCreatedAtDesc();
    }
}

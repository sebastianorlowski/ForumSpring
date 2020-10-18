package pl.orlowski.sebastian.forumspring.topic;

import org.springframework.stereotype.Service;
import pl.orlowski.sebastian.forumspring.repository.TopicRepository;
import pl.orlowski.sebastian.forumspring.service.TopicService;

import java.util.List;

@Service
public class TopicServiceImpl implements TopicService {

    private TopicRepository topicRepository;

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
}

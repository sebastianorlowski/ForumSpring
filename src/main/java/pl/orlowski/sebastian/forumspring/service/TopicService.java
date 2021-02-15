package pl.orlowski.sebastian.forumspring.service;

import org.springframework.data.domain.Page;
import pl.orlowski.sebastian.forumspring.topic.Topic;
import pl.orlowski.sebastian.forumspring.user.User;

import java.util.List;
import java.util.Set;

public interface TopicService {

    Topic save(Topic topic);

    List<Topic> findAll();

    Set<Topic> findTopicsByDate();

    Topic findOne(Long id);

    void delete(Long id);

    boolean existById(Long id);

    Page<Topic> findPaginated(int pageNumber, int pageSize);
    
    List<Topic> findTopicsByRegex(String regex);

    List<Topic> findTopicsByUser(User user);

}

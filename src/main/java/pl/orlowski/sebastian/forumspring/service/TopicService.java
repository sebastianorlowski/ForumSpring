package pl.orlowski.sebastian.forumspring.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.orlowski.sebastian.forumspring.topic.Topic;
import java.util.List;
import java.util.Set;

public interface TopicService {

    Topic save(Topic topic);

    List<Topic> findAll();

    Set<Topic> findTopicsByDate();

    Topic findOne(Long id);

    void delete(Long id);

    boolean existById(Long id);

    List<Topic> findAllByDateAsc();

    Page<Topic> findPaginated(int pageNumber, int pageSize);

}

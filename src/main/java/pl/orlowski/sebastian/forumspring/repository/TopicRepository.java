package pl.orlowski.sebastian.forumspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.orlowski.sebastian.forumspring.topic.Topic;

public interface TopicRepository extends JpaRepository<Topic, Long> {
    Topic findByName(String name);

}

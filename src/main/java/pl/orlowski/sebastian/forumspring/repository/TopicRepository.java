package pl.orlowski.sebastian.forumspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.orlowski.sebastian.forumspring.topic.Topic;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {

}
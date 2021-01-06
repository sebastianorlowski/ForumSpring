package pl.orlowski.sebastian.forumspring.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.orlowski.sebastian.forumspring.topic.Topic;
import pl.orlowski.sebastian.forumspring.user.User;

import java.util.List;
import java.util.Set;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {

    List<Topic> findAllByOrderByCreatedAtDesc();

    Set<Topic> findTop5ByOrderByCreatedAtDesc();

    List<Topic> getTopicsByUser(User user);
}

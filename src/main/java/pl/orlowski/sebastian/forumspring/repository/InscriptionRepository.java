package pl.orlowski.sebastian.forumspring.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.orlowski.sebastian.forumspring.inscription.Inscription;
import pl.orlowski.sebastian.forumspring.topic.Topic;
import pl.orlowski.sebastian.forumspring.user.User;

import java.util.List;
import java.util.Set;

@Repository
public interface InscriptionRepository extends JpaRepository<Inscription, Long> {

    Set<Inscription> findAllByTopicId(Long id);

    Set<Inscription> findTop1ByOrderByCreatedAtDesc();

    Page<Inscription> findByTopicId(Long id, Pageable pageable);

    Set<Inscription> findAllByUser(User user);

    void deleteInscriptionsByTopic(Topic topic);

}

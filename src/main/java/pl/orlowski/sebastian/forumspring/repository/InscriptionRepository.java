package pl.orlowski.sebastian.forumspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.orlowski.sebastian.forumspring.inscription.Inscription;
import pl.orlowski.sebastian.forumspring.topic.Topic;

import java.util.Set;

@Repository
public interface InscriptionRepository extends JpaRepository<Inscription, Long> {

    Set<Inscription> findAllByTopicId(Long id);
    Set<Inscription> findTop5ByOrderByCreatedAtDesc();

}

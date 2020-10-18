package pl.orlowski.sebastian.forumspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.orlowski.sebastian.forumspring.inscription.Inscription;

public interface InscriptionRepository extends JpaRepository<Inscription, Long> {
    Inscription findByTopicAndId(String name, Long id);
}

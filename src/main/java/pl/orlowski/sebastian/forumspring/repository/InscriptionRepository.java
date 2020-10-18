package pl.orlowski.sebastian.forumspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.orlowski.sebastian.forumspring.inscription.Inscription;

@Repository
public interface InscriptionRepository extends JpaRepository<Inscription, Long> {
}

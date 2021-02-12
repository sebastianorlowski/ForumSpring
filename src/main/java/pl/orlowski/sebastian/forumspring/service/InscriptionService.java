package pl.orlowski.sebastian.forumspring.service;

import org.springframework.data.domain.Page;
import pl.orlowski.sebastian.forumspring.inscription.Inscription;
import pl.orlowski.sebastian.forumspring.topic.Topic;
import pl.orlowski.sebastian.forumspring.user.User;

import java.util.List;
import java.util.Set;

public interface InscriptionService {

    Inscription save(Inscription inscription);

    List<Inscription> findAll();

    Inscription findOne(Long id);

    void delete(Long id);

    Set<Inscription> getInscriptionsByTopicId(Long id);

    Set<Inscription> findInscriptionsByDate();

    Page<Inscription> findPaginated(int pageNumber, int pageSize, Long topicId);

    Set<Inscription> getInscriptionsByUser(User user);

    void deleteInscriptionsByTopic(Topic topic);

    boolean existById(Long id);
}

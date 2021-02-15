package pl.orlowski.sebastian.forumspring.inscription;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.orlowski.sebastian.forumspring.repository.InscriptionRepository;
import pl.orlowski.sebastian.forumspring.service.InscriptionService;
import pl.orlowski.sebastian.forumspring.topic.Topic;
import pl.orlowski.sebastian.forumspring.user.User;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Service
public class InscriptionServiceImpl implements InscriptionService {

    private InscriptionRepository inscriptionRepository;

    @Autowired
    public InscriptionServiceImpl(InscriptionRepository inscriptionRepository) {
        this.inscriptionRepository = inscriptionRepository;
    }

    @Override
    public Inscription save(Inscription inscription) {
        return inscriptionRepository.save(inscription);
    }

    @Override
    public List<Inscription> findAll() {
        return inscriptionRepository.findAll();
    }

    @Override
    public Inscription findOne(Long id) {
        return inscriptionRepository.getOne(id);
    }

    @Override
    public void delete(Long id) {
        inscriptionRepository.deleteById(id);
    }

    @Override
    public Set<Inscription> getInscriptionsByTopicId(Long id) {
        return inscriptionRepository.findAllByTopicId(id);
    }

    @Override
    public Set<Inscription> findInscriptionsByDate() {
        return inscriptionRepository.findTop1ByOrderByCreatedAtDesc();
    }

    @Override
    public Page<Inscription> findPaginated(int pageNumber, int pageSize, Long topicId) {
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, Sort.by("createdAt").ascending());
        return this.inscriptionRepository.findByTopicId(topicId, pageable);
    }

    @Override
    public Set<Inscription> getInscriptionsByUser(User user) {
        return inscriptionRepository.findAllByUser(user);
    }

    @Override
    @Transactional
    public void deleteInscriptionsByTopic(Topic topic) {
        inscriptionRepository.deleteInscriptionsByTopic(topic);
    }

    @Override
    public boolean existById(Long id) {
        return inscriptionRepository.existsById(id);
    }
}


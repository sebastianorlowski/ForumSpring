package pl.orlowski.sebastian.forumspring.inscription;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.orlowski.sebastian.forumspring.repository.InscriptionRepository;
import pl.orlowski.sebastian.forumspring.service.InscriptionService;

import java.util.List;

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
        inscriptionRepository.delete(findOne(id));
    }
}

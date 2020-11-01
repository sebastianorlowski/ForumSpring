package pl.orlowski.sebastian.forumspring.service;

import org.springframework.stereotype.Service;
import pl.orlowski.sebastian.forumspring.dto.InscriptionDto;
import pl.orlowski.sebastian.forumspring.inscription.Inscription;

import java.util.List;

public interface InscriptionService {

    Inscription save(Inscription inscription);

    List<Inscription> findAll();

    Inscription findOne(Long id);

    void delete(Long id);
}

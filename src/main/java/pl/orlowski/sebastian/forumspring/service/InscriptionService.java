package pl.orlowski.sebastian.forumspring.service;

import pl.orlowski.sebastian.forumspring.inscription.Inscription;

import java.util.List;

public interface InscriptionService {

    Inscription save(Inscription inscription);

    List<Inscription> findAll();

    Inscription findOne(Long id);

    void delete(Long id);
}

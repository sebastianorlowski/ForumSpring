package pl.orlowski.sebastian.forumspring.service;

import pl.orlowski.sebastian.forumspring.topic.Topic;

import java.util.List;

public interface TopicService {

    Topic save(Topic topic);

    List<Topic> findAll();

    Topic findOne(Long id);

    void delete(Long id);
}

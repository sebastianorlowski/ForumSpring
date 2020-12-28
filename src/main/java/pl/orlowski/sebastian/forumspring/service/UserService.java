package pl.orlowski.sebastian.forumspring.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.orlowski.sebastian.forumspring.dto.UserRegistrationDto;
import pl.orlowski.sebastian.forumspring.topic.Topic;
import pl.orlowski.sebastian.forumspring.user.User;

@Service
public interface UserService extends UserDetailsService {
    User save(UserRegistrationDto userRegistrationDto);

    String getUserLoginByTopic(Long topicId);

    User findByLogin(String login);
}

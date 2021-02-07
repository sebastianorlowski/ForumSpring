package pl.orlowski.sebastian.forumspring.service;

import org.springframework.data.domain.Page;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.orlowski.sebastian.forumspring.dto.UserRegistrationDto;
import pl.orlowski.sebastian.forumspring.topic.Topic;
import pl.orlowski.sebastian.forumspring.user.User;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

@Service
public interface UserService extends UserDetailsService {
    User save(UserRegistrationDto userRegistrationDto);

    String getUserLoginByTopic(Long topicId);

    User findByLogin(String login);

    Page<User> findPaginated(int pageNumber, int pageSize);

    void deleteByLogin(String login);

    String getUserDetails(User user);

    boolean userIsExist(User user);
}

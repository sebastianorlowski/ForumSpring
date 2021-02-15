package pl.orlowski.sebastian.forumspring.service;

import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import pl.orlowski.sebastian.forumspring.dto.UserRegistrationDto;
import pl.orlowski.sebastian.forumspring.user.User;

@Service
public interface UserService extends UserDetailsService{

    User save(UserRegistrationDto userRegistrationDto);

    User findByLogin(String login);

    Page<User> findPaginated(int pageNumber, int pageSize);

    void enabledUser(String login, boolean isEnabled);

    boolean userIsExist(User user);

    boolean emailExist(String email);
}

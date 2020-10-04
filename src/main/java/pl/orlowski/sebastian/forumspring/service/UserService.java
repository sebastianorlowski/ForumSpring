package pl.orlowski.sebastian.forumspring.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import pl.orlowski.sebastian.forumspring.dto.UserRegistrationDto;
import pl.orlowski.sebastian.forumspring.user.User;

public interface UserService extends UserDetailsService {
    User save(UserRegistrationDto userRegistrationDto);

}

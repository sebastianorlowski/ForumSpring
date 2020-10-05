package pl.orlowski.sebastian.forumspring.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import pl.orlowski.sebastian.forumspring.dto.UserRegistrationDto;
import pl.orlowski.sebastian.forumspring.user.User;

public interface UserService extends UserDetailsService {
    User save(UserRegistrationDto userRegistrationDto);
}

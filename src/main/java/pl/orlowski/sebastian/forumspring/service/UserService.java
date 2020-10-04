package pl.orlowski.sebastian.forumspring.service;

import pl.orlowski.sebastian.forumspring.dto.UserRegistrationDto;
import pl.orlowski.sebastian.forumspring.user.User;

public interface UserService {
    User save(UserRegistrationDto userRegistrationDto);

}

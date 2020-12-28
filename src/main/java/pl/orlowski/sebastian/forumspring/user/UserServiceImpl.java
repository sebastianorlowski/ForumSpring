package pl.orlowski.sebastian.forumspring.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.orlowski.sebastian.forumspring.dto.UserRegistrationDto;
import pl.orlowski.sebastian.forumspring.repository.UserRepository;
import pl.orlowski.sebastian.forumspring.service.TopicService;
import pl.orlowski.sebastian.forumspring.service.UserService;
import pl.orlowski.sebastian.forumspring.topic.Topic;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private TopicService topicService;

//    Encode password into save
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, TopicService topicService) {
        this.userRepository = userRepository;
        this.topicService = topicService;
    }

    /* Save user to db */
    @Override
    public User save(UserRegistrationDto userRegistrationDto) {
        User user = new User(userRegistrationDto.getLogin(), passwordEncoder.encode(userRegistrationDto.getPassword()),
                userRegistrationDto.getEmail(), Arrays.asList(new Role("USER")));

        return userRepository.save(user);
    }

    public String getUserLoginByTopic(Long topicId) {
        Topic topic = topicService.findOne(topicId);
        User user = topic.getUser();
        return user.getLogin();
    }

    /* Login user */
    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(login);
        if (user == null) {
            throw new UsernameNotFoundException("Unknown user");
        }
            return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    /* Transfer roles to authorities */
    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
//        Put roles into stream then map the role, we put role to the security provide class
//        simplegrantedauthority and we pass roles name to this object and finally we collected stream to the list
       return roles.stream().map(role -> new SimpleGrantedAuthority(role.getRole())).collect(Collectors.toList());
    }

    @Override
    public User findByLogin(String login) {
        return userRepository.findByLogin(login);
    }
}

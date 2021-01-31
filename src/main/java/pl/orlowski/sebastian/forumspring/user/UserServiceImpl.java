package pl.orlowski.sebastian.forumspring.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.orlowski.sebastian.forumspring.dto.UserRegistrationDto;
import pl.orlowski.sebastian.forumspring.repository.RoleRepository;
import pl.orlowski.sebastian.forumspring.repository.UserRepository;
import pl.orlowski.sebastian.forumspring.service.TopicService;
import pl.orlowski.sebastian.forumspring.service.UserService;
import pl.orlowski.sebastian.forumspring.topic.Topic;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private TopicService topicService;
    private RoleRepository roleRepository;

//    Encode password into save
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, TopicService topicService, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.topicService = topicService;
        this.roleRepository = roleRepository;
    }

    /* Save user to db */
    @Override
    public User save(UserRegistrationDto userRegistrationDto) {
        User user = new User();
        user.setLogin(userRegistrationDto.getLogin());
        user.setPassword(passwordEncoder.encode(userRegistrationDto.getPassword()));
        user.setEmail(userRegistrationDto.getEmail());
        user.setRoles(Arrays.asList(roleRepository.findByName("USER")));

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
            org.springframework.security.core.userdetails.User userDetails = new org.springframework.security.core.userdetails.User
                    (user.getLogin(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));

        return userDetails;
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

    @Override
    public Page<User> findPaginated(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, Sort.by("id").descending());
        return this.userRepository.findAll(pageable);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public String getUserDetails(User user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();


        return "lelel";
    }
}

package pl.orlowski.sebastian.forumspring.user;

import com.fasterxml.jackson.databind.module.SimpleAbstractTypeResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.orlowski.sebastian.forumspring.dto.UserRegistrationDto;
import pl.orlowski.sebastian.forumspring.repository.UserRepository;
import pl.orlowski.sebastian.forumspring.service.UserService;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
//    Encode password into save
    private BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /* Save user to db */
    @Override
    public User save(UserRegistrationDto userRegistrationDto) {
        User user = new User(userRegistrationDto.getLogin(), userRegistrationDto.getEmail(),
                passwordEncoder.encode(userRegistrationDto.getPassword()), Arrays.asList(new Role("USER")));

        return userRepository.save(user);
    }

    /* Login user */
    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(login);
        if (user != null) {
            return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
        } throw new UsernameNotFoundException("Unknown user");
    }

    /* Transfer roles to authorities */
    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
//        Put roles into stream then map the role, we put role to the security provide class
//        simplegrantedauthority and we pass roles name to this object and finally we collected stream to the list
       return roles.stream().map(role -> new SimpleGrantedAuthority(role.getRole())).collect(Collectors.toList());
    }
}

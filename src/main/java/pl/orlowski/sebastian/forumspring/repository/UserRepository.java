package pl.orlowski.sebastian.forumspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.orlowski.sebastian.forumspring.user.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByLogin(String login);

    User findByEmail(String email);
}

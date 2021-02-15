package pl.orlowski.sebastian.forumspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.orlowski.sebastian.forumspring.user.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);
}

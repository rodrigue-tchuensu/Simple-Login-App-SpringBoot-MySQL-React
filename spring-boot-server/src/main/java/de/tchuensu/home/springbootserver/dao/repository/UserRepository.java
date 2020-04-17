package de.tchuensu.home.springbootserver.dao.repository;

import de.tchuensu.home.springbootserver.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 *
 * @autor  Rodrigue Tchuensu P.
 */

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAll();

    User findUserById(Long id);

    User findUserByUsername(String username);

    User findUserByEmail(String email);

    User save(User user);

}

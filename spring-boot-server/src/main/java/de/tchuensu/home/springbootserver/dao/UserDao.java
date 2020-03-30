package de.tchuensu.home.springbootserver.dao;

import de.tchuensu.home.springbootserver.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * @autor  Rodrigue Tchuensu P.
 */

@Repository
public interface UserDao extends JpaRepository<User, Long> {

    List<User> findAll();

    User findUserById(Long id);

    User findUserByUsername(String username);

    //User findByUsernameAndPassword(String username, String password);
}

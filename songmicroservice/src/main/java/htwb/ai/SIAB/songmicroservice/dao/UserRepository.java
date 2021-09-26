package htwb.ai.SIAB.songmicroservice.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import htwb.ai.SIAB.songmicroservice.domain.User;

/**
 * UserRepository Interface Class
 *
 */
@Repository
public interface UserRepository extends JpaRepository<User, String> {

    /**
     * Find user by userId
     * @param userId userId of user
     * @return User object
     */
    User findByUserId(String userId);

}

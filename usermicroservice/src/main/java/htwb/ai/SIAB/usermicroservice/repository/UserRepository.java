package htwb.ai.SIAB.usermicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import htwb.ai.SIAB.usermicroservice.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    User findByUserId(String userId);

}

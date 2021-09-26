package htwb.ai.SIAB.songmicroservice.service;

import htwb.ai.SIAB.songmicroservice.dao.UserRepository;
import htwb.ai.SIAB.songmicroservice.domain.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.UUID;

import java.util.Optional;

/**
 * UserDAO Class
 *
 */
@Component
public class UserDAO implements IUserDAO {

    @Autowired
    UserRepository userRepository;


    @Override
    public User getUserByUserId(String userId) {
        return userRepository.findById(userId).get();
    }

    @Override
    public String generateToken(){
        String token = UUID.randomUUID().toString();
        return token;
    }

    @Override
    public void save(User user){

        User u = new User();
        u.setUserId(user.getUserId());
        u.setFirstname(user.getFirstname());
        u.setLastname(user.getLastname());
        u.setPassword(user.getPassword());
        userRepository.save(u);
    }


    /**
     * Get user
     * @param userId userId of user
     * @return User object
     */
    public Optional<User> getOptionalUser(String userId){
        return userRepository.findById(userId);
    }

}

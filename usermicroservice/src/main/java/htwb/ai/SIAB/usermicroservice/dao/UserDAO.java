package htwb.ai.SIAB.usermicroservice.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import htwb.ai.SIAB.usermicroservice.model.User;
import htwb.ai.SIAB.usermicroservice.repository.UserRepository;

import java.util.UUID;

import javax.persistence.NoResultException;

import java.util.List;
import java.util.Optional;

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
    
    @Override
    public List<User> getAllUsers() throws NoResultException {

        List<User> userList = userRepository.findAll();
        return userList;
       }


    public Optional<User> getOptionalUser(String userId){
        return userRepository.findById(userId);
    }

//    public String getloggedusername(){
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        return authentication.getName();
//    }
//    
//    public User getloggeduser(){
//        String username = getloggedusername();
//        return  getUserByUserId(username);
//    }
}

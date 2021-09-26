package htwb.ai.SIAB.usermicroservice.dao;

import java.util.List;

import htwb.ai.SIAB.usermicroservice.model.User;

public interface IUserDAO {

    public User getUserByUserId (String userId);
    public String generateToken();
    public void save(User user);
    public List<User> getAllUsers();

}

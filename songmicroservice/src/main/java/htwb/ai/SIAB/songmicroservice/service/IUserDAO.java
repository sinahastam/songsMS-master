package htwb.ai.SIAB.songmicroservice.service;

import htwb.ai.SIAB.songmicroservice.domain.User;

/**
 * UserDAO Interface Class
 *
 */
public interface IUserDAO {

    /**
     * Gets user by userId
     * @param userId UserId of user
     * @return User object
     */
    public User getUserByUserId (String userId);
    
    /**
     * Generates a new token
     * @return Token string
     */
    public String generateToken();
    
    /**
     * Saves a new user
     * @param user new user
     */
    public void save(User user);

}

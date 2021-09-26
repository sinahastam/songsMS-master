package htwb.ai.SIAB.usermicroservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import htwb.ai.SIAB.usermicroservice.dao.IUserDAO;
import htwb.ai.SIAB.usermicroservice.model.User;
import htwb.ai.SIAB.usermicroservice.repository.UserRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    UserRepository userRepository;

    private IUserDAO userDAO;
    
    @Autowired
    public ArrayList<String> authList;
    
    @Autowired
    public HashMap<String, User> authMap;

    public UserController(IUserDAO userDAO) {
        this.userDAO = userDAO;
    }


    @Autowired
    public void setUserDAO(IUserDAO userDAO){
        this.userDAO=userDAO;
    }
    
    /**
     * Checks if auth token is valid
     * @param token Auth token
     * @return Authorized status
     */
    @GetMapping("/{token}")
    public ResponseEntity<?> ckeckToken(@PathVariable("token") String token){

		try {
			if(authMap.containsKey(token)) {
				return new ResponseEntity<String>("Authorized", HttpStatus.ACCEPTED);
			}else {
	            return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
			}
		} catch (Exception e) {
			return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
		}
    }
    
    /**
     * Checks if auth token is valid and gives user info
     * @param token Auth token
     * @return Authorized status + user info
     */
    @GetMapping("/{token}/user")
    public ResponseEntity<?> getUserFromToken(@PathVariable("token") String token){

		try {
			if(authMap.containsKey(token)) {
				return new ResponseEntity<User>(authMap.get(token), HttpStatus.ACCEPTED);
			}else {
	            return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
			}
		} catch (Exception e) {
			return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
		}
    }
    
    
    @PostMapping
    public ResponseEntity<?> auth(@RequestBody User user){
    	try {
			System.out.println("========================== [ POST ] ==========================");
			System.out.println(user.getFirstname());
			System.out.println(user.getLastname());
			System.out.println(user.getUserId());
			System.out.println(user.getPassword());
			
			System.out.println("========================== [ SQL ] ==========================");
			User userDB = userDAO.getUserByUserId(user.getUserId());
			System.out.println(userDB.getFirstname());
			System.out.println(userDB.getLastname());
			System.out.println(userDB.getUserId());
			System.out.println(userDB.getPassword());
			
			if(user.getUserId().equals(userDB.getUserId()) && user.getPassword().equals(userDB.getPassword())) {
				String authToken = UUID.randomUUID().toString();
				System.out.println("TOKEN: " +authToken);
				authMap.put(authToken, userDB);
				System.out.println("AUTHLIST: ");
				System.out.println(this.authMap.toString());
				return new ResponseEntity<String>(authToken, HttpStatus.ACCEPTED);
			}else {
				return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ERROR!");
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
    
    }



}



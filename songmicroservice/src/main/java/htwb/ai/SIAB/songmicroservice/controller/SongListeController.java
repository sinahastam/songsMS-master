package htwb.ai.SIAB.songmicroservice.controller;

import htwb.ai.SIAB.songmicroservice.service.SongListeDAO;
import htwb.ai.SIAB.songmicroservice.service.UserDAO;
import htwb.ai.SIAB.songmicroservice.domain.Song;
import htwb.ai.SIAB.songmicroservice.domain.SongListe;
import htwb.ai.SIAB.songmicroservice.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

/**
 * SongListe Controller Class
 *
 */
@RestController
@RequestMapping(value = "/songLists")
public class SongListeController {


    @Autowired
    UserDAO userDAO;

    @Autowired
    private SongListeDAO songListeDAO;
    
	@Autowired
	private RestTemplate restTemplate;


    /**
     * Gets all SongLists from a specific user
     * @param userId SongList owner
     * @param authToken Auth token from header
     * @return List of Songlists
     */
    @GetMapping
    public ResponseEntity<List<SongListe>> getall(@RequestParam String userId, @RequestHeader(name = "Authorization") String authToken) {
    	
		try {

	    	String authStatus = restTemplate.getForObject("http://localhost:8099/api/userms/auth/" +authToken, String.class);
	    	if(authStatus.toLowerCase().equals("authorized")) {
	    		User user = restTemplate.getForObject("http://localhost:8099/api/userms/auth/" +authToken +"/user", User.class);
	            if (user.getUserId().equals(userId)) {

	                return new ResponseEntity<>(songListeDAO.getallListUser(userId), HttpStatus.OK);
	            } else {
	                try {
	                	List <SongListe> liste = songListeDAO.getpublicListUser(userId);
	                	if(liste.isEmpty()) {
	                		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	                	}else {
	                		return new ResponseEntity<>(liste, HttpStatus.OK);
	                	}
	                }catch (Exception e) {
	                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	                }
	            }
	    	}else if (authStatus.toLowerCase().equals("unauthorized")) {
	            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	    	}
			
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
        
    }


    
    /**
     * Gets a Songlist by the songlist id
     * @param id id of songlist
     * @param authToken Auth token from header
     * @return Songlist
     */
    @GetMapping("/{id}")
    public ResponseEntity<SongListe> getSongListeById(@PathVariable("id") int id, @RequestHeader(name = "Authorization") String authToken){
  	
		try {

	    	String authStatus = restTemplate.getForObject("http://localhost:8099/api/userms/auth/" +authToken, String.class);
	    	if(authStatus.toLowerCase().equals("authorized")) {
	    		try {
	    			User user = restTemplate.getForObject("http://localhost:8099/api/userms/auth/" +authToken +"/user", User.class);
	    			SongListe songListe = songListeDAO.getList((long)id, user.getUserId());
	                return new ResponseEntity<SongListe>(songListe, HttpStatus.FOUND);
	    		} catch (Exception e) {
	    			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
	    		}
	    	}else if (authStatus.toLowerCase().equals("unauthorized")) {
	            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	    	}
			
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

    }
    
    /**
     * Creates a new songlist
     * @param songListe New songlist
     * @param ucBuilder
     * @param authToken Auth token from header
     * @return Location/id of new songlist
     */
    @PostMapping
    public ResponseEntity<?> createNewSongListe(@RequestBody SongListe songListe, UriComponentsBuilder ucBuilder, @RequestHeader(name = "Authorization") String authToken){

		try {

	    	String authStatus = restTemplate.getForObject("http://localhost:8099/api/userms/auth/" +authToken, String.class);
	    	if(authStatus.toLowerCase().equals("authorized")) {
	        	try {
	            	if(songListe == null) {
	            		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	            	}else {
	            		User user = restTemplate.getForObject("http://localhost:8099/api/userms/auth/" +authToken +"/user", User.class);
	                    songListe.setOwnerId(user.getUserId());
	            		songListeDAO.saveList(songListe);
	            		
	                	//location header
	                    HttpHeaders headers = new HttpHeaders();
	                    headers.setLocation(ucBuilder.path("/songLists/{id}").buildAndExpand(songListe.getId()).toUri());
	                    return new ResponseEntity<>(headers, HttpStatus.CREATED);
	            	}
	        	} catch (Exception e) {
	        		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	        	}
	    	}else if (authStatus.toLowerCase().equals("unauthorized")) {
	            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	    	}
			
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
    	
    }
    
    /**
     * Updates an existing songlist
     * @param songListe new/updated songlist
     * @param ucBuilder
     * @param authToken Auth token from header
     * @return Location/id of new/updated songlist
     */
    @PutMapping
    public ResponseEntity<?> updateSongListe(@RequestBody SongListe songListe, UriComponentsBuilder ucBuilder, @RequestHeader(name = "Authorization") String authToken){

		try {

	    	String authStatus = restTemplate.getForObject("http://localhost:8099/api/userms/auth/" +authToken, String.class);
	    	if(authStatus.toLowerCase().equals("authorized")) {
	        	try {
	            	if(songListe == null) {
	            		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	            	}else {
	            		User user = restTemplate.getForObject("http://localhost:8099/api/userms/auth/" +authToken +"/user", User.class);
	            		if(songListe.getOwnerId().equals(user.getUserId())) {
	            			songListeDAO.updateList(songListe.getId(), songListe, user.getUserId());
	            		}
	            		
	                	//location header
	                    HttpHeaders headers = new HttpHeaders();
	                    headers.setLocation(ucBuilder.path("/songLists/{id}").buildAndExpand(songListe.getId()).toUri());
	                    return new ResponseEntity<>(headers, HttpStatus.OK);
	            	}
	        	} catch (Exception e) {
	        		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	        	}
	    	}else if (authStatus.toLowerCase().equals("unauthorized")) {
	            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	    	}
			
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
    	
    }
    
    /**
     * Deletes an existing songlist
     * @param id songlist id
     * @param authToken auth token from header
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSongListeById(@PathVariable("id") int id, @RequestHeader(name = "Authorization") String authToken){
    	
		try {

	    	String authStatus = restTemplate.getForObject("http://localhost:8099/api/userms/auth/" +authToken, String.class);
	    	if(authStatus.toLowerCase().equals("authorized")) {
	        	try {
	        		User user = restTemplate.getForObject("http://localhost:8099/api/userms/auth/" +authToken +"/user", User.class);
	        		songListeDAO.deleteList((long) id, user.getUserId());
	        		return new ResponseEntity<>(HttpStatus.OK);

	    		} catch (Exception e) {
	    			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    		}
	    	}else if (authStatus.toLowerCase().equals("unauthorized")) {
	            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	    	}
			
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
    	
    }
    
    
}

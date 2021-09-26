package htwb.ai.SIAB.songmicroservice.controller;

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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import htwb.ai.SIAB.songmicroservice.domain.Song;
import htwb.ai.SIAB.songmicroservice.service.ISongDAO;
import htwb.ai.SIAB.songmicroservice.service.Songs;

import javax.servlet.ServletException;

import java.sql.SQLException;
import java.util.List;


/**
 * Song Controller Class
 *
 */
@RestController
@RequestMapping(value = "/songs")
public class SongController {
	
	@Autowired
	private RestTemplate restTemplate;

    private ISongDAO songDao;

    /**
     * Sets song Dao
     * @param songDao SongDao
     */
    @Autowired
    public void setSongDao(ISongDAO songDao){
        this.songDao = songDao;
    }
    
    /**
     * Get the song info by id
     * @param id Id of song entry
     * @return Song entry
     */
    @GetMapping("/{id}")
    public ResponseEntity<Song> getSongsById(@PathVariable("id") int id, @RequestHeader(name = "Authorization") String authToken){

		try {

	    	String authStatus = restTemplate.getForObject("http://localhost:8099/api/userms/auth/" +authToken, String.class);
	    	if(authStatus.toLowerCase().equals("authorized")) {
				Song song = songDao.getSong(id);
	            return new ResponseEntity<Song>(song, HttpStatus.FOUND);
	    	}else if (authStatus.toLowerCase().equals("unauthorized")) {
	            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	    	}
			
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

    }
    
    /**
     * Creates a new song
     * @param song Song data
     * @param ucBuilder
     * @return Location of new song in headers
     */
    @PostMapping
    public ResponseEntity<?> createSong(@RequestBody Song song, UriComponentsBuilder ucBuilder, @RequestHeader(name = "Authorization") String authToken){
    	
		try {

	    	String authStatus = restTemplate.getForObject("http://localhost:8099/api/userms/auth/" +authToken, String.class);
	    	if(authStatus.toLowerCase().equals("authorized")) {
	        	if(song.getTitle() == "" || song.getTitle() == null) {
	        		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	        	}else {
	        		songDao.saveSong(song);
	        		
	            	//location header
	                HttpHeaders headers = new HttpHeaders();
	                headers.setLocation(ucBuilder.path("/songs/{id}").buildAndExpand(song.getId()).toUri());
	                return new ResponseEntity<>(headers, HttpStatus.CREATED);
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
     * Updates an existing song entry
     * @param id Id of song entry
     * @param song New/updated song data
     * @return
     */
    @PutMapping("/{id}")
    public  ResponseEntity<?> updateSong(@PathVariable int id, @RequestBody Song song, @RequestHeader(name = "Authorization") String authToken) {
    	
		try {

	    	String authStatus = restTemplate.getForObject("http://localhost:8099/api/userms/auth/" +authToken, String.class);
	    	if(authStatus.toLowerCase().equals("authorized")) {
	            if(song.getId() == id) {
	                try {
	                    if (song.getTitle() == null || song.getTitle() == ""){
	                        throw new SQLException() ;
	                    }
	                    int idsong = songDao.updateSong(id, song);
	                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	                }
	                catch (Exception e){
	                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	                }
	                }else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	    	}else if (authStatus.toLowerCase().equals("unauthorized")) {
	            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	    	}
			
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}


    }

    
    /**
     * Deletes song entry by id
     * @param id Id of song entry
     * @return
     */
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteSong(@PathVariable("id") int id, @RequestHeader(name = "Authorization") String authToken){
    	
		try {

	    	String authStatus = restTemplate.getForObject("http://localhost:8099/api/userms/auth/" +authToken, String.class);
	    	if(authStatus.toLowerCase().equals("authorized")) {
				songDao.deleteSong(id);
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    	}else if (authStatus.toLowerCase().equals("unauthorized")) {
	            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	    	}
			
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
    	
    }


    /**
     * Gets songs in json format
     * @return List of all songs
     * @throws ServletException
     * @throws SQLException
     */
    @GetMapping(produces = "application/json")
    public List<Song> getSongsJson(@RequestHeader(name = "Authorization") String authToken) throws ServletException, SQLException {
    	
		try {

	    	String authStatus = restTemplate.getForObject("http://localhost:8099/api/userms/auth/" +authToken, String.class);
	    	if(authStatus.toLowerCase().equals("authorized")) {
	            List<Song> songs = songDao.getallsong();
	            return songs;
	    	}else if (authStatus.toLowerCase().equals("unauthorized")) {
	            return null;
	    	}
			
            return null;
		} catch (Exception e) {
			return null;
		}

    }

    /**
     * Get songs in xml format
     * @return List of all songs
     * @throws ServletException
     * @throws SQLException
     */
    @GetMapping(produces ="application/xml")
    public List<Song> getSongsXml(@RequestHeader(name = "Authorization") String authToken) throws ServletException, SQLException {
		try {

	    	String authStatus = restTemplate.getForObject("http://localhost:8099/api/userms/auth/" +authToken, String.class);
	    	if(authStatus.toLowerCase().equals("authorized")) {
	            List<Song> songs = songDao.getallsong();

	            var mysongs = new Songs();
	            mysongs.setSongs(songs);
	            return mysongs.getSongs();
	    	}else if (authStatus.toLowerCase().equals("unauthorized")) {
	            return null;
	    	}
			
            return null;
		} catch (Exception e) {
			return null;
		}
    	
    }


}

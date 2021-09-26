package htwb.ai.SIAB.lyricsmicroservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;

/**
 * Lyrics Controller Class
 *
 */
@RestController
@RequestMapping(value = "/lyrics")
public class LyricsController {
	
	@Autowired
	private RestTemplate restTemplate;

//    /**
//     * Gets Song lyrics
//     * @param artist Artist name
//     * @param title Title of song
//     * @return Lyrics text
//     */
//    @GetMapping("/{artist}/{title}")
//    public ResponseEntity<?> getSongLyrics(@PathVariable("artist") String artist, @PathVariable("title") String title, @RequestHeader(name = "Authorization") String authToken){
//		
//    	try {
//
//	    	String authStatus = restTemplate.getForObject("http://localhost:8099/api/userms/auth/" +authToken, String.class);
//	    	if(authStatus.toLowerCase().equals("authorized")) {
//	        	String songLyrics = restTemplate.getForObject("https://api.lyrics.ovh/v1/"+artist+"/"+title, String.class);
//	        	System.out.println(songLyrics);
//	    		return new ResponseEntity<String>(songLyrics, HttpStatus.ACCEPTED);
//	    	}else if (authStatus.toLowerCase().equals("unauthorized")) {
//	            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
//	    	}
//			
//            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
//		} catch (Exception e) {
//			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//		}
//
//    }
	
    /**
     * Gets Song lyrics and saves to a file
     * @param artist Artist name
     * @param title Title of song
     * @return Lyrics text
     */
    @GetMapping("/{artist}/{title}")
    public ResponseEntity<?> getSongLyrics(@PathVariable("artist") String artist, @PathVariable("title") String title, @RequestHeader(name = "Authorization") String authToken){
		
    	try {

	    	String authStatus = restTemplate.getForObject("http://localhost:8099/api/userms/auth/" +authToken, String.class);
	    	if(authStatus.toLowerCase().equals("authorized")) {
	        	String songLyrics = restTemplate.getForObject("https://api.lyrics.ovh/v1/"+artist+"/"+title, String.class);
            	songLyrics = songLyrics.replace("\n", " ");
            	songLyrics = songLyrics.replace("\r", " ");
            	songLyrics = songLyrics.replace("\\r", " ");
            	songLyrics = songLyrics.replace("\\n", " ");
            	System.out.println(songLyrics);
	        	
	            File myObj = new File("" +title +"-" +artist +".txt");
	            if (myObj.createNewFile()) {
	            	System.out.println("File created: " + myObj.getName());
		            
	            	FileWriter myWriter = new FileWriter("" +title +"-" +artist +".txt");
	            	PrintWriter printWriter = new PrintWriter(myWriter, true);
	            	printWriter.printf(songLyrics);
	            	printWriter.close();
		            System.out.println("Successfully wrote to the file.");
	            } else {
	              System.out.println("File already exists.");
	            }
	            
	        	
	        	return new ResponseEntity<String>(songLyrics, HttpStatus.ACCEPTED);
	    	}else if (authStatus.toLowerCase().equals("unauthorized")) {
	            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	    	}
			
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

    }
    

}

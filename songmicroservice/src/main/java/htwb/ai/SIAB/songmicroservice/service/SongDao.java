package htwb.ai.SIAB.songmicroservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import htwb.ai.SIAB.songmicroservice.dao.SongRepository;
import htwb.ai.SIAB.songmicroservice.domain.Song;

import javax.persistence.*;
import javax.servlet.ServletException;
import java.sql.SQLException;
import java.util.List;


/**
 * SongDAO Class
 *
 */
@Component
public class SongDao implements ISongDAO{

    @Autowired
    SongRepository songRepository ;



    public int saveSong(Song song) {

        songRepository.save(song);
        return song.getId();

    }

    public Song getSong(int id)  throws ServletException, SQLException {

         Song s = songRepository.findById(id).get();
         return s;

    }


    public List<Song> getallsong() throws  NoResultException {

     List<Song> songList = songRepository.findAll();
     return songList;
    }

    public void deleteSong(int id){

     Song song = songRepository.findById(id).get();
     songRepository.delete(song);

    }

    public int updateSong(int id, Song s){

     Song song = songRepository.findById(id).get();
     song.setArtist(s.getArtist());
     song.setLabel(s.getLabel());
     song.setReleased(s.getReleased());
     song.setTitle(s.getTitle());
     songRepository.save(song);
     return song.getId();

    }

}
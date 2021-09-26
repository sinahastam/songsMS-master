package htwb.ai.SIAB.songmicroservice.service;

import javax.servlet.ServletException;

import htwb.ai.SIAB.songmicroservice.domain.Song;

import java.sql.SQLException;
import java.util.List;

/**
 * SongDAO Interface Class
 *
 */
public interface ISongDAO {

    /**
     * Saves a song
     * @param song Song
     * @return Id of song entry
     */
    public int saveSong(Song song);
    /**
     * Get song info by Id
     * @param id Id of song entry
     * @return Song entry
     * @throws ServletException
     * @throws SQLException
     */
    public Song getSong(int id) throws ServletException, SQLException;
    /**
     * Gets all song entries
     * @return List of all songs
     */
    public List<Song> getallsong();
    /**
     * Deletes a song by id
     * @param id Id of song
     */
    public void deleteSong(int id);
    /**
     * Updates existing song entry
     * @param id Id of song
     * @param s New/updated song
     * @return Id of song
     */
    public int updateSong(int id, Song s);

}

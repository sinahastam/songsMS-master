package htwb.ai.SIAB.songmicroservice.service;


import java.lang.reflect.Array;
import java.util.List;

import htwb.ai.SIAB.songmicroservice.domain.SongListe;

/**
 * SongListeDAO Interface Class
 *
 */
public interface ISongListeDAO {
	
    /**
     * Saves a new songlist
     * @param songListe new songlist
     */
    public void saveList(SongListe songListe);
    
    /**
     * Deletes an existing songlist
     * @param id Id of songlist
     * @param userId userId of method caller
     * @throws Exception
     */
    public void  deleteList(Long id, String userId) throws Exception;
    
    /**
     * Gets songlist from id
     * @param id id of songlist
     * @param userId userId of method caller
     * @return Songlist entry
     * @throws Exception
     */
    public SongListe getList(Long id, String userId) throws Exception;
    
    /**
     * Gets all Songlists from a specific user
     * @param userId userId of songlist owner
     * @return List of songlists
     */
    public List<SongListe> getallListUser(String userId);
    
    /**
     * Gets all public songlists from a specific user
     * @param userId userId of songlist owner
     * @return List of songlists
     */
    public List<SongListe> getpublicListUser(String userId);
    
    /**
     * Gets all songlists
     * @return List of songlists
     */
    public List<SongListe> getallList();
    
    /**
     * Gets songlists from user by userId
     * @param songListe Songlist
     * @return list of array
     */
    public List<Array> getListwithUserId(SongListe songListe);

}

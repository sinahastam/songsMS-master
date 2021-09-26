package htwb.ai.SIAB.songmicroservice.service;


import htwb.ai.SIAB.songmicroservice.domain.SongListe;
import htwb.ai.SIAB.songmicroservice.dao.SongListeRepository;
//import htwb.ai.SIAB.songmicroservice.dao.SongRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.util.*;


/**
 * SongListeDAO Class
 *
 */
@Component
public class SongListeDAO implements ISongListeDAO {


    @Autowired
    SongListeRepository songListeRepository;

    @Autowired
    UserDAO userDAO;

    public void saveList(SongListe songListe){
        songListeRepository.save(songListe);
    }

    
    public void deleteList(Long id, String userId) throws Exception {
            if (songListeRepository.findById(id).get().getOwnerId().equals(userId)) {
                System.out.println(songListeRepository.findById(id).get().getOwnerId());
                songListeRepository.delete(songListeRepository.findById(id).get());
            } else {
                Exception ex = new Exception();
                throw ex;
            }

    }
    
    
    public void updateList(Long id, SongListe neueListe, String userId) throws Exception {
          if (songListeRepository.findById(id).get().getOwnerId().equals(userId)) {
              System.out.println(songListeRepository.findById(id).get().getOwnerId());
              
              SongListe l = neueListe;
              songListeRepository.save(l);
              
          } else {
              Exception ex = new Exception();
              throw ex;
          }

  }

    public SongListe getList(Long id, String userId) throws Exception {

            SongListe songListe = songListeRepository.findById(id).get();
            try {
                System.out.println(userId);
                System.out.println(songListe.getOwnerId());

                if (songListe.getIsPrivate() == false) {
                    return songListe;
                } else if (songListe.getOwnerId().equals(userId) && songListe.getIsPrivate() == true) {
                    return songListe;
                } else {  Exception ex = new Exception();
                throw ex;
                }
            }
            catch (Exception e){
                throw e;
            }
    }

    public List<SongListe> getallListUser(String userId){
        List<SongListe> songListes = songListeRepository.findByOwnerId(userId);
        return songListes;
    }

    public List<SongListe> getpublicListUser(String userId){
        List<SongListe> songListes = songListeRepository.findByOwnerId(userId);
        List<SongListe> publicList = new ArrayList<>();
        for (SongListe songListe: songListes)
        {
            if (songListe.getIsPrivate() == false){
                publicList.add(songListe);
            }
        }
        return publicList;

    }
    public List<SongListe> getallList(){

        List<SongListe> songListes = songListeRepository.findAll();
        return songListes;
    }

    @Override
    public List<Array> getListwithUserId(SongListe songListe) {
        List<SongListe> arrayList = new ArrayList<>();
        arrayList.add(songListe);
        return null;
    }
}

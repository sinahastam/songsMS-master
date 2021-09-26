package htwb.ai.SIAB.songmicroservice.dao;

import htwb.ai.SIAB.songmicroservice.domain.SongListe;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/**
 * SongListeRepository Interface Class
 *
 */
public interface SongListeRepository extends JpaRepository<SongListe, Long> {

//  List<SongListe> findByUser(User user);
	
    /**
     * Finds songlists by ownerId(userId)
     * @param userId UserId of owner
     * @return SongLists of specific user
     */
    List<SongListe> findByOwnerId(String userId);

}

package htwb.ai.SIAB.songmicroservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import htwb.ai.SIAB.songmicroservice.domain.Song;

/**
 * SongRepository Interface Class
 *
 */
@Repository
public interface SongRepository extends JpaRepository<Song, Integer> {

}

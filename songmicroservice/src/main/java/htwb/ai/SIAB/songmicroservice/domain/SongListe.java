package htwb.ai.SIAB.songmicroservice.domain;


import javax.persistence.*;
import java.util.Set;

/**
 * SongListe Model Class
 *
 */
@Entity
public class SongListe {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private boolean isPrivate;

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String owner) {
        this.ownerId = owner;
    }

    public String ownerId;

/*
    @ManyToOne
    @JoinColumn(name="OwnerId")
    @NotNull
    private  User user;
*/


    @ManyToMany
    private Set<Song> songList;


/*    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }*/

    public Set<Song> getSongList() {
        return songList;
    }

    public void setSongList(Set<Song> songs) {
        this.songList = songs;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getIsPrivate() {
        return isPrivate;
    }

    public void setIsPrivate(boolean isPrivate) {
        this.isPrivate = isPrivate;
    }




}

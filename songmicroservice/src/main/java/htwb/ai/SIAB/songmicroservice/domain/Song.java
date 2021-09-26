package htwb.ai.SIAB.songmicroservice.domain;

import com.sun.istack.NotNull;

import javax.persistence.*;


/**
 * Song Model Class
 *
 */
@Entity
@Table(name = "song")
public class Song {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @NotNull
    private String title;


    private String artist;


    private String label;



    private int released;


    public Song() {
    }

    public Song(String title, String artist, String label, int released){
        this.title=title;
        this.artist= artist;
        this.label= label;
        this.released=released;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getReleased() {
        return released;
    }

    public void setReleased(int released) {
        this.released = released;
    }

    @Override
    public String toString() {
        return "{title=" + title + ", artist=" + artist + ", label=" + label + ", released="
                + released + "}";
    }
}
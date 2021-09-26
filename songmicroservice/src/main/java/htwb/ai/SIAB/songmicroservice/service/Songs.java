package htwb.ai.SIAB.songmicroservice.service;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import htwb.ai.SIAB.songmicroservice.domain.Song;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * Songs xml class
 *
 */
@JacksonXmlRootElement
public class Songs implements Serializable {

    private static final long serialVersionUID = 22L;

    @JacksonXmlProperty(localName = "Song")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<Song> songs = new ArrayList<>();

    /**
     * Gets all songs
     * @return List of songs
     */
    public List<Song> getSongs() {
        return songs;
    }

    /**
     * Sets songs list
     * @param songs Song list
     */
    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }
}



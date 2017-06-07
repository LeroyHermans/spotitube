package nl.han.dea.oose.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 22-3-17.
 */
public class Playlist {

    private String owner;
    private String name;

    private List<Track> tracks = new ArrayList<>();

    public Playlist(){}

    public Playlist(String owner, String name){
        this.owner = owner;
        this.name = name;
    }

    public Playlist(String owner, String name, List<Track> tracks){
        this.owner = owner;
        this.name = name;
        this.tracks = tracks;
    }

    public void addTrack(Track track){
        tracks.add(track);
    }

    public List<Track> getTracks(){
        return tracks;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float calculateDuration(){
        float duration = 0;
        for(Track track : tracks){
          duration += track.getDuration();
        }
        return duration;
    }
}

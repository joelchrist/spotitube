package nl.joelchrist.spotitube.playlists.rest;

import nl.joelchrist.spotitube.tracks.domain.Track;
import nl.joelchrist.spotitube.tracks.rest.RestTrack;

import java.util.List;

public class RestPlaylist {
    private Integer id;
    private String name;
    private String owner;
    private List<RestTrack> tracks;

    public RestPlaylist() {
    }

    public RestPlaylist(Integer id, String name, String owner, List<RestTrack> tracks) {
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.tracks = tracks;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public List<RestTrack> getTracks() {
        return tracks;
    }

    public void setTracks(List<RestTrack> tracks) {
        this.tracks = tracks;
    }
}

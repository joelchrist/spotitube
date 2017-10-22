package nl.joelchrist.spotitube.playlists.domain;

import nl.joelchrist.spotitube.tracks.domain.Track;

import java.util.List;

public class Playlist {
    private Integer id;
    private String name;
    private Boolean owner;
    private List<Track> tracks;

    public Playlist(List<Track> tracks) {
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

    public Boolean getOwner() {
        return owner;
    }

    public void setOwner(Boolean owner) {
        this.owner = owner;
    }

    public List<Track> getTracks() {
        return tracks;
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }
}

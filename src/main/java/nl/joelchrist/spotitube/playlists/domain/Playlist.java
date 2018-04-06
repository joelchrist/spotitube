package nl.joelchrist.spotitube.playlists.domain;

import nl.joelchrist.spotitube.dao.domain.Document;
import nl.joelchrist.spotitube.tracks.domain.Track;
import org.bson.types.ObjectId;

import java.util.List;

public class Playlist extends Document{
    private String name;
    private String owner;
    private List<Track> tracks;

    public Playlist() {
    }

    public Playlist(ObjectId id, String name, String owner, List<Track> tracks) {
        setId(id);
        this.name = name;
        this.owner = owner;
        this.tracks = tracks;
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

    public List<Track> getTracks() {
        return tracks;
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }
}

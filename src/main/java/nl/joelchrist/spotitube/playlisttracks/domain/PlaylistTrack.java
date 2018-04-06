package nl.joelchrist.spotitube.playlisttracks.domain;

import org.bson.types.ObjectId;

public class PlaylistTrack {
    private ObjectId trackId;
    private ObjectId playlistId;

    public PlaylistTrack(ObjectId trackId, ObjectId playlistId) {
        this.trackId = trackId;
        this.playlistId = playlistId;
    }

    public PlaylistTrack() {

    }

    public ObjectId getTrackId() {
        return trackId;
    }

    public void setTrackId(ObjectId trackId) {
        this.trackId = trackId;
    }

    public ObjectId getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(ObjectId playlistId) {
        this.playlistId = playlistId;
    }
}

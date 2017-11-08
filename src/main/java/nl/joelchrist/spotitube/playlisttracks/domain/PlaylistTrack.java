package nl.joelchrist.spotitube.playlisttracks.domain;

public class PlaylistTrack {
    private Integer trackId;
    private Integer playlistId;

    public PlaylistTrack(Integer trackId, Integer playlistId) {
        this.trackId = trackId;
        this.playlistId = playlistId;
    }

    public Integer getTrackId() {
        return trackId;
    }

    public void setTrackId(Integer trackId) {
        this.trackId = trackId;
    }

    public Integer getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(Integer playlistId) {
        this.playlistId = playlistId;
    }
}

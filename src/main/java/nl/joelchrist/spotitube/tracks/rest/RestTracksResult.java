package nl.joelchrist.spotitube.tracks.rest;

import java.util.List;

public class RestTracksResult {
    private List<RestTrack> tracks;

    public RestTracksResult(List<RestTrack> tracks) {
        this.tracks = tracks;
    }

    public RestTracksResult() {
    }

    public List<RestTrack> getTracks() {
        return tracks;
    }

    public void setTracks(List<RestTrack> tracks) {
        this.tracks = tracks;
    }
}

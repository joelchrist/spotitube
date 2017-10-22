package nl.joelchrist.spotitube.playlists.domain.rest;

import nl.joelchrist.spotitube.playlists.domain.Playlist;
import nl.joelchrist.spotitube.tracks.domain.Track;

import java.util.List;

public class RestPlaylistResponse {
    private List<Playlist> playlists;
    private Integer playListLength;

    public RestPlaylistResponse() {
    }

    public RestPlaylistResponse(List<Playlist> playlists) {
        this.playlists = playlists;
        this.playListLength = playlists.stream().map(Playlist::getTracks).reduce((tracks, tracks2) -> {
            tracks.addAll(tracks2);
            return tracks;
        }).map(tracks -> tracks.stream().mapToInt(Track::getDuration).sum()).orElse(0);
    }

    public List<Playlist> getPlaylist() {
        return playlists;
    }

    public void setPlaylist(List<Playlist> playlists) {
        this.playlists = playlists;
    }

    public Integer getPlayListLength() {
        return playListLength;
    }

    public void setPlayListLength(Integer playListLength) {
        this.playListLength = playListLength;
    }
}

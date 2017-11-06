package nl.joelchrist.spotitube.playlists.rest;

import nl.joelchrist.spotitube.playlists.domain.Playlist;

import java.util.List;

public class RestPlaylistResult {
    private List<RestPlaylist> playlists;
    private Integer length;

    public RestPlaylistResult() {
    }

    public RestPlaylistResult(List<RestPlaylist> playlists, Integer length) {
        this.playlists = playlists;
        this.length = length;
    }

    public List<RestPlaylist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(List<RestPlaylist> playlists) {
        this.playlists = playlists;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer playListLength) {
        this.length = playListLength;
    }
}

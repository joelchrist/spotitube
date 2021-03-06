package nl.joelchrist.spotitube.playlisttracks.managers;

import nl.joelchrist.spotitube.playlisttracks.repositories.PlaylistTrackRepository;
import nl.joelchrist.spotitube.playlisttracks.domain.PlaylistTrack;

import javax.inject.Inject;
import java.util.List;

public class PlaylistTrackManager {

    @Inject
    private PlaylistTrackRepository playlistTrackRepository;

    public List<PlaylistTrack> getPlaylistTracks() {
        return playlistTrackRepository.findAll();
    }

    public List<PlaylistTrack> getPlaylistTracksByPlaylistId(Integer playlistId) {
        return playlistTrackRepository.findByPlaylistId(playlistId);
    }


    public void removeTrackFromPlaylist(Integer playlistId, Integer trackId) {
        playlistTrackRepository.removeTrackFromPlaylist(playlistId, trackId);
    }

    public void addTrackToPlaylist(PlaylistTrack playlistTrack) {
        playlistTrackRepository.addPlaylistTrack(playlistTrack);
    }
}

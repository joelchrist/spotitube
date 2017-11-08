package nl.joelchrist.spotitube.playlisttracks.repositories;

import nl.joelchrist.spotitube.playlisttracks.domain.PlaylistTrack;

import java.util.List;
import java.util.Optional;

public interface PlaylistTrackRepository {
    List<PlaylistTrack> findAll();
    List<PlaylistTrack> findByPlaylistId(Integer playlistId);
    Optional<PlaylistTrack> findByTrackId(Integer trackId);
    void removeTrackFromPlaylist(Integer playlistId, Integer trackId);
    void addPlaylistTrack(PlaylistTrack playlistTrack);
}

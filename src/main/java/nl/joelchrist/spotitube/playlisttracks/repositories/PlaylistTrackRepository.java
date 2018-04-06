package nl.joelchrist.spotitube.playlisttracks.repositories;

import nl.joelchrist.spotitube.playlisttracks.domain.PlaylistTrack;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Optional;

public interface PlaylistTrackRepository {
    List<PlaylistTrack> findAll();
    List<PlaylistTrack> findByPlaylistId(ObjectId playlistId);
    Optional<PlaylistTrack> findByTrackId(ObjectId trackId);
    void removeTrackFromPlaylist(ObjectId playlistId, ObjectId trackId);
    void addPlaylistTrack(PlaylistTrack playlistTrack);
}

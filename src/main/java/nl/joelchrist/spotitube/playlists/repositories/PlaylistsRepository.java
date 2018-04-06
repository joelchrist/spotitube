package nl.joelchrist.spotitube.playlists.repositories;

import nl.joelchrist.spotitube.playlists.domain.Playlist;
import org.bson.types.ObjectId;

import java.util.List;

public interface PlaylistsRepository {
    List<Playlist> findAll();

    void deletePlaylist(ObjectId playlistId);

    void addPlaylist(Playlist playlist);

    void updateName(ObjectId playlistId, String name);
}

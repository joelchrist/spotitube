package nl.joelchrist.spotitube.playlists.repositories;

import nl.joelchrist.spotitube.playlists.domain.Playlist;

import java.util.List;

public interface PlaylistsRepository {
    List<Playlist> findAll();

    void deletePlaylist(Integer playlistId);

    void addPlaylist(Playlist playlist);
}

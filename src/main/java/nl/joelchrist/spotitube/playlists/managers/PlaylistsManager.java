package nl.joelchrist.spotitube.playlists.managers;

import nl.joelchrist.spotitube.playlists.domain.Playlist;
import nl.joelchrist.spotitube.playlists.repositories.PlaylistsRepository;

import javax.inject.Inject;
import java.util.List;

public class PlaylistsManager {
    @Inject
    private PlaylistsRepository playlistsRepository;

    public List<Playlist> getPlaylists() {
        return playlistsRepository.findAll();
    }

    public void deletePlaylist(Integer playlistId) {
        playlistsRepository.deletePlaylist(playlistId);
    }

    public void addPlaylist(Playlist playlist) {
        playlistsRepository.addPlaylist(playlist);
    }

    public void updateName(Integer playlistId, String name) {
        playlistsRepository.updateName(playlistId, name);
    }
}

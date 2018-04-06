package nl.joelchrist.spotitube.playlists.repositories;

import nl.joelchrist.spotitube.dao.Repository;
import nl.joelchrist.spotitube.playlists.domain.Playlist;
import org.bson.types.ObjectId;
import org.jongo.MongoCollection;

import java.util.List;

import static nl.joelchrist.spotitube.dao.util.MongoUtil.cursorToList;

public class MongoPlaylistRepository extends Repository implements PlaylistsRepository {
    private MongoCollection playlistCollection = getCollection("playlists");

    @Override
    public List<Playlist> findAll() {
        return cursorToList(playlistCollection.find().as(Playlist.class));
    }

    @Override
    public void deletePlaylist(ObjectId playlistId) {
        playlistCollection.remove(playlistId);
    }

    @Override
    public void addPlaylist(Playlist playlist) {
        playlistCollection.save(playlist);
    }

    @Override
    public void updateName(ObjectId playlistId, String name) {
        Playlist playlist = playlistCollection.findOne(playlistId).as(Playlist.class);
        playlist.setName(name);
        playlistCollection.update(playlistId).with(playlist);
    }
}

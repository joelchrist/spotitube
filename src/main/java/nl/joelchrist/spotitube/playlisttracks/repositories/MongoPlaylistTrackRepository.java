package nl.joelchrist.spotitube.playlisttracks.repositories;

import nl.joelchrist.spotitube.dao.Repository;
import nl.joelchrist.spotitube.playlisttracks.domain.PlaylistTrack;
import org.bson.types.ObjectId;
import org.jongo.MongoCollection;

import javax.enterprise.inject.Default;
import java.util.List;
import java.util.Optional;

import static nl.joelchrist.spotitube.dao.util.MongoUtil.cursorToList;

@Default
public class MongoPlaylistTrackRepository extends Repository implements PlaylistTrackRepository {
    private MongoCollection playlistTrackCollection = getCollection("playlistTracks");

    @Override
    public List<PlaylistTrack> findAll() {
        return cursorToList(playlistTrackCollection.find().as(PlaylistTrack.class));
    }

    @Override
    public List<PlaylistTrack> findByPlaylistId(ObjectId playlistId) {
        return cursorToList(playlistTrackCollection.find("{playlistId: #}", playlistId).as(PlaylistTrack.class));
    }

    @Override
    public Optional<PlaylistTrack> findByTrackId(ObjectId trackId) {
        return Optional.empty();
    }

    @Override
    public void removeTrackFromPlaylist(ObjectId playlistId, ObjectId trackId) {
        playlistTrackCollection.remove("{playlistId: #, trackId: #}", playlistId, trackId);
    }

    @Override
    public void addPlaylistTrack(PlaylistTrack playlistTrack) {
        playlistTrackCollection.save(playlistTrack);
    }
}

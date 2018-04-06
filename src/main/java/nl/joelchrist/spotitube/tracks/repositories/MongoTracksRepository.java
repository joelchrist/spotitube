package nl.joelchrist.spotitube.tracks.repositories;

import nl.joelchrist.spotitube.dao.Repository;
import nl.joelchrist.spotitube.tracks.domain.Track;
import org.jongo.MongoCollection;

import java.util.List;

import static nl.joelchrist.spotitube.dao.util.MongoUtil.cursorToList;

public class MongoTracksRepository extends Repository implements TracksRepository {
    private MongoCollection tracksCollection = getCollection("tracks");

    @Override
    public List<Track> findAll() {
        return cursorToList(tracksCollection.find().as(Track.class));
    }
}

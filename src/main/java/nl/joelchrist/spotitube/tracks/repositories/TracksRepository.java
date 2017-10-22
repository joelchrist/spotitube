package nl.joelchrist.spotitube.tracks.repositories;

import nl.joelchrist.spotitube.tracks.domain.Track;

import java.util.List;

public interface TracksRepository {
    List<Track> getTracks();
}

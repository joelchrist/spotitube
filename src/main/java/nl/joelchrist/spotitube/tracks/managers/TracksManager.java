package nl.joelchrist.spotitube.tracks.managers;

import nl.joelchrist.spotitube.exceptions.EntityNotFoundException;
import nl.joelchrist.spotitube.tracks.domain.Track;
import nl.joelchrist.spotitube.tracks.repositories.TracksRepository;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

public class TracksManager {
    @Inject
    private TracksRepository tracksRepository;

    public List<Track> getTracks() throws EntityNotFoundException {
        Optional<List<Track>> tracksOptional = tracksRepository.getTracks();
        return tracksOptional.orElseThrow(() -> new EntityNotFoundException(Track.class));
    }

}

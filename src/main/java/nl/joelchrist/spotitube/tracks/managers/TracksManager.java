package nl.joelchrist.spotitube.tracks.managers;

import nl.joelchrist.spotitube.playlisttracks.helpers.PlaylistTrackHelper;
import nl.joelchrist.spotitube.tracks.domain.Track;
import nl.joelchrist.spotitube.tracks.repositories.TracksRepository;
import org.bson.types.ObjectId;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class TracksManager {
    @Inject
    private TracksRepository tracksRepository;

    @Inject
    private PlaylistTrackHelper playlistTrackHelper;


    public List<Track> getTracks() {
        return tracksRepository.findAll();
    }

    public List<Track> getTracksNotInPlaylist(ObjectId playlistId) {
        List<Track> tracks = tracksRepository.findAll();
        return tracks.stream().filter(track -> !playlistTrackHelper.isTrackInPlaylist(track, playlistId)).collect(Collectors.toList());
    }

    public List<Track> getTracksInPlaylist(ObjectId playlistId) {
        List<Track> tracks = tracksRepository.findAll();
        return tracks.stream().filter(track -> playlistTrackHelper.isTrackInPlaylist(track, playlistId)).collect(Collectors.toList());
    }
}

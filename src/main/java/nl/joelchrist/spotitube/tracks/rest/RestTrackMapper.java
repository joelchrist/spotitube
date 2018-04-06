package nl.joelchrist.spotitube.tracks.rest;

import nl.joelchrist.spotitube.tracks.domain.Track;
import nl.joelchrist.spotitube.util.RestMapper;


public class RestTrackMapper implements RestMapper<RestTrack, Track> {

    @Override
    public RestTrack toRest(Track nonRest) {
        return new RestTrack(
                nonRest.getId().toHexString(),
                nonRest.getTitle(),
                nonRest.getPerformer(),
                nonRest.getDuration(),
                nonRest.getAlbum(),
                nonRest.getPlayCount(),
                nonRest.getPublicationDate().toString(),
                nonRest.getDescription(),
                nonRest.getOfflineAvailable()
        );
    }

    @Override
    public Track fromRest(RestTrack rest) {
        // noop
        return new Track();
    }
}

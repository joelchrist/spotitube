package nl.joelchrist.spotitube.tracks.endpoints;

import nl.joelchrist.spotitube.auth.config.Authenticated;
import nl.joelchrist.spotitube.tracks.domain.Track;
import nl.joelchrist.spotitube.tracks.managers.TracksManager;
import nl.joelchrist.spotitube.tracks.rest.RestTrack;
import nl.joelchrist.spotitube.tracks.rest.RestTrackMapper;
import nl.joelchrist.spotitube.tracks.rest.RestTracksResult;

import javax.inject.Inject;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import java.util.List;
import java.util.stream.Collectors;

@Path("/tracks")
public class TracksEndpoint {
    @Inject
    private TracksManager tracksManager;

    @Inject
    private RestTrackMapper restTrackMapper;


    @GET
    @Produces("application/json")
    @Authenticated
    public RestTracksResult getTracks(@QueryParam("forPlaylist") @DefaultValue("0") Integer playListId) {
        List<Track> tracks;
        if (playListId.equals(0)) {
            tracks = tracksManager.getTracks();
        }
        else {
            tracks = tracksManager.getTracksNotInPlaylist(playListId);
        }
        List<RestTrack> restTracks = tracks.stream().map(restTrackMapper::toRest).collect(Collectors.toList());
        return new RestTracksResult(restTracks);
    }

}

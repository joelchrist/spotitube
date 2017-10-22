package nl.joelchrist.spotitube.tracks.endpoints;

import nl.joelchrist.spotitube.tracks.domain.Track;
import nl.joelchrist.spotitube.tracks.managers.TracksManager;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import java.util.List;

@Path("/tracks")
public class TracksEndpoint {
    @Inject
    TracksManager tracksManager;

    @GET
    public List<Track> getTracks(@QueryParam("forPlaylist") Integer forPlaylist) {
        return null;
    }


    private List<Track> getAllTracks(){
        return null;
    }

    private List<Track> getTracksNotInPlaylist(Integer playListId) {
        return null;
    }

}

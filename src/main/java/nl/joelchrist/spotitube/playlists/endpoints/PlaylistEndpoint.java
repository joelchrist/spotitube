package nl.joelchrist.spotitube.playlists.endpoints;

import nl.joelchrist.spotitube.auth.config.Authenticated;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("/playlists")
public class PlaylistEndpoint {

    @GET
    @Path("/{playlistId}/tracks")
    @Produces("application/json")
    public String getTracksInPlaylist(@PathParam("playlistId") Integer playlistId) {
        return "hey";
    }


    @GET
    @Produces("application/json")
    @Authenticated
    public String getPlaylists() {
        return "{\"hello\", \"hello\"}";
    }
}

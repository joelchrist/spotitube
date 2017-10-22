package nl.joelchrist.spotitube.playlists.endpoints;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/playlists")
public class PlaylistEndpoint {

    @GET
    public String getAllPlaylists() {
        return "TODO: iets";
    }
}

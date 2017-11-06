package nl.joelchrist.spotitube.playlists.endpoints;

import nl.joelchrist.spotitube.auth.config.Authenticated;
import nl.joelchrist.spotitube.playlists.domain.Playlist;
import nl.joelchrist.spotitube.playlists.managers.PlaylistsManager;
import nl.joelchrist.spotitube.playlists.rest.PlaylistRequest;
import nl.joelchrist.spotitube.playlists.rest.RestPlaylistResult;
import nl.joelchrist.spotitube.playlists.rest.RestPlaylistResultMapper;
import nl.joelchrist.spotitube.tracks.domain.Track;
import nl.joelchrist.spotitube.tracks.managers.TracksManager;
import nl.joelchrist.spotitube.tracks.rest.RestTrack;
import nl.joelchrist.spotitube.tracks.rest.RestTrackMapper;
import nl.joelchrist.spotitube.tracks.rest.RestTracksResult;
import nl.joelchrist.spotitube.users.domain.User;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;
import java.util.List;
import java.util.stream.Collectors;

@Path("/playlists")
public class PlaylistsEndpoint {

    @Inject
    private PlaylistsManager playlistsManager;

    @Inject
    private TracksManager tracksManager;

    @Inject
    private RestPlaylistResultMapper restPlaylistResultMapper;

    @Inject
    private RestTrackMapper restTrackMapper;

    @GET
    @Path("/{playlistId}/tracks")
    @Produces("application/json")
    public RestTracksResult getTracksInPlaylist(@PathParam("playlistId") Integer playlistId) {
        List<Track> tracks = tracksManager.getTracksInPlaylist(playlistId);
        List<RestTrack> restTracks = tracks.stream().map(restTrackMapper::toRest).collect(Collectors.toList());
        return new RestTracksResult(restTracks);
    }


    @GET
    @Produces("application/json")
    public RestPlaylistResult getPlaylists() {
        List<Playlist> playlists = getPlaylistsWithTracks();

        return restPlaylistResultMapper.toRest(playlists);
    }

    @DELETE
    @Produces("application/json")
    @Path("/{playlistId}")
    public RestPlaylistResult deletePlaylist(@PathParam("playlistId") Integer playlistId) {
        playlistsManager.deletePlaylist(playlistId);
        List<Playlist> playlists = getPlaylistsWithTracks();
        return restPlaylistResultMapper.toRest(playlists);
    }

    @POST
    @Produces("application/json")
    @Authenticated
    public RestPlaylistResult addPlaylist(PlaylistRequest playlistRequest, @Context HttpServletRequest servletRequest) {
        Playlist playlist = new Playlist();
        playlist.setName(playlistRequest.getName());
        User user = (User) servletRequest.getAttribute("currentUser");
        playlist.setOwner(user.getUser());
        playlistsManager.addPlaylist(playlist);
        List<Playlist> playlists = getPlaylistsWithTracks();
        return restPlaylistResultMapper.toRest(playlists);
    }

    private List<Playlist> getPlaylistsWithTracks() {
        List<Playlist> playlists = playlistsManager.getPlaylists();
        playlists.forEach(playlist -> {
            playlist.setTracks(tracksManager.getTracksInPlaylist(playlist.getId()));
        });

        return playlists;
    }
}

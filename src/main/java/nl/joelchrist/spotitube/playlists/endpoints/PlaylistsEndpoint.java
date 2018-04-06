package nl.joelchrist.spotitube.playlists.endpoints;

import nl.joelchrist.spotitube.auth.config.Authenticated;
import nl.joelchrist.spotitube.playlists.domain.Playlist;
import nl.joelchrist.spotitube.playlists.managers.PlaylistsManager;
import nl.joelchrist.spotitube.playlists.rest.PlaylistRequest;
import nl.joelchrist.spotitube.playlists.rest.RestPlaylistResult;
import nl.joelchrist.spotitube.playlists.rest.RestPlaylistResultMapper;
import nl.joelchrist.spotitube.playlisttracks.domain.PlaylistTrack;
import nl.joelchrist.spotitube.playlisttracks.managers.PlaylistTrackManager;
import nl.joelchrist.spotitube.tracks.domain.Track;
import nl.joelchrist.spotitube.tracks.managers.TracksManager;
import nl.joelchrist.spotitube.tracks.rest.RestTrack;
import nl.joelchrist.spotitube.tracks.rest.RestTrackMapper;
import nl.joelchrist.spotitube.tracks.rest.RestTracksResult;
import nl.joelchrist.spotitube.tracks.rest.TrackRequest;
import nl.joelchrist.spotitube.users.domain.User;
import org.bson.types.ObjectId;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
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

    @Inject
    private PlaylistTrackManager playlistTrackManager;

    @GET
    @Path("/{playlistId}/tracks")
    @Produces("application/json")
    @Authenticated
    public RestTracksResult getTracksInPlaylist(@PathParam("playlistId") ObjectId playlistId) {
        List<RestTrack> restTracks = getRestTracks(playlistId);
        return new RestTracksResult(restTracks);
    }


    @GET
    @Produces("application/json")
    @Authenticated
    public RestPlaylistResult getPlaylists() {
        List<Playlist> playlists = getPlaylistsWithTracks();

        return restPlaylistResultMapper.toRest(playlists);
    }

    @DELETE
    @Produces("application/json")
    @Path("/{playlistId}")
    @Authenticated
    public RestPlaylistResult deletePlaylist(@PathParam("playlistId") ObjectId playlistId) {
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

    @PUT
    @Produces("application/json")
    @Path("/{playlistId}")
    @Authenticated
    public RestPlaylistResult editPlaylist(@PathParam("playlistId") ObjectId playlistId, PlaylistRequest playlistRequest) {
        playlistsManager.updateName(playlistId, playlistRequest.getName());
        List<Playlist> playlists = getPlaylistsWithTracks();
        return restPlaylistResultMapper.toRest(playlists);
    }

    @DELETE
    @Path("/{playlistId}/tracks/{trackId}")
    @Produces("application/json")
    @Authenticated
    public RestTracksResult removeTrackFromPlaylist(@PathParam("playlistId") ObjectId playlistId, @PathParam("trackId") ObjectId trackId) {
        playlistTrackManager.removeTrackFromPlaylist(playlistId, trackId);
        List<RestTrack> restTracks = getRestTracks(playlistId);
        return new RestTracksResult(restTracks);
    }

    @POST
    @Path("/{playlistId}/tracks")
    @Produces("application/json")
    @Authenticated
    public RestTracksResult addTrackToPlaylist(@PathParam("playlistId") ObjectId playlistId, TrackRequest trackRequest) {
        PlaylistTrack playlistTrack = new PlaylistTrack(new ObjectId(trackRequest.getId()), playlistId);
        playlistTrackManager.addTrackToPlaylist(playlistTrack);
        List<RestTrack> restTracks = getRestTracks(playlistId);
        return new RestTracksResult(restTracks);
    }

    private List<RestTrack> getRestTracks(ObjectId playlistId) {
        List<Track> tracks = tracksManager.getTracksInPlaylist(playlistId);
        return tracks.stream().map(restTrackMapper::toRest).collect(Collectors.toList());
    }

    private List<Playlist> getPlaylistsWithTracks() {
        List<Playlist> playlists = playlistsManager.getPlaylists();
        playlists.forEach(playlist -> {
            playlist.setTracks(tracksManager.getTracksInPlaylist(playlist.getId()));
        });
        return playlists;
    }
}

package nl.joelchrist.spotitube.playlists.rest;

import nl.joelchrist.spotitube.playlists.domain.Playlist;
import nl.joelchrist.spotitube.tracks.rest.RestTrackMapper;
import nl.joelchrist.spotitube.util.RestMapper;
import org.bson.types.ObjectId;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

public class RestPlaylistMapper implements RestMapper<nl.joelchrist.spotitube.playlists.rest.RestPlaylist, Playlist>{

    @Inject
    private RestTrackMapper restTrackMapper;

    @Override
    public nl.joelchrist.spotitube.playlists.rest.RestPlaylist toRest(Playlist nonRest) {
        return new nl.joelchrist.spotitube.playlists.rest.RestPlaylist(
                nonRest.getId().toHexString(),
                nonRest.getName(),
                nonRest.getOwner(),
                nonRest.getTracks().stream().map(restTrackMapper::toRest).collect(Collectors.toList())
        );
    }

    @Override
    public Playlist fromRest(nl.joelchrist.spotitube.playlists.rest.RestPlaylist rest) {
        return new Playlist(
                new ObjectId(rest.getId()),
                rest.getName(),
                rest.getOwner(),
                rest.getTracks().stream().map(restTrackMapper::fromRest).collect(Collectors.toList())
        );
    }

}

package nl.joelchrist.spotitube.playlists.rest;

import nl.joelchrist.spotitube.playlists.domain.Playlist;
import nl.joelchrist.spotitube.tracks.domain.Track;
import nl.joelchrist.spotitube.util.RestMapper;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class RestPlaylistResultMapper implements RestMapper<RestPlaylistResult, List<Playlist>> {

    @Inject
    private RestPlaylistMapper restPlaylistMapper;

    @Override
    public RestPlaylistResult toRest(List<Playlist> nonRest) {

        Integer totalLength = calculateLength(nonRest);

        List<RestPlaylist> restPlaylists = nonRest.stream().map(restPlaylistMapper::toRest).collect(Collectors.toList());

        return new RestPlaylistResult(restPlaylists, totalLength);
    }

    @Override
    public List<Playlist> fromRest(RestPlaylistResult rest) {
        return null;
    }

    private Integer calculateLength(List<Playlist> playlists) {
        Integer length = 0;
        for (Playlist playlist : playlists) {
            for (Track track : playlist.getTracks()) {
                length += track.getDuration();
            }
        }
        return length;
    }
}

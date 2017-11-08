package nl.joelchrist.spotitube.playlisttracks.helpers;

import nl.joelchrist.spotitube.playlisttracks.domain.PlaylistTrack;
import nl.joelchrist.spotitube.playlisttracks.managers.PlaylistTrackManager;
import nl.joelchrist.spotitube.tracks.domain.Track;

import javax.inject.Inject;
import java.util.List;

public class PlaylistTrackHelper {

    @Inject
    private PlaylistTrackManager playlistTrackManager;

    public Boolean isTrackInPlaylist(Track track, Integer playlistId) {
        List<PlaylistTrack> playlistTracks =  playlistTrackManager.getPlaylistTracksByPlaylistId(playlistId);
        return playlistTracks.stream().anyMatch(playlistTrack -> playlistTrack.getTrackId().equals(track.getId()));
    }
}

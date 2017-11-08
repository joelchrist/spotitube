package nl.joelchrist.spotitube.playlisttracks.repositories;

import nl.joelchrist.spotitube.dao.Repository;
import nl.joelchrist.spotitube.playlisttracks.domain.PlaylistTrack;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public class MySQLPlaylistTrackRepository extends Repository implements PlaylistTrackRepository {
    private Logger logger = Logger.getLogger(getClass().getName());


    @Override
    public List<PlaylistTrack> findAll() {
        List<PlaylistTrack> result = new ArrayList<>();
        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM PlaylistTrack");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                PlaylistTrack playlistTrack = buildPlaylistTrackFromResultSet(resultSet);
                result.add(playlistTrack);
            }
            return result;
        } catch (SQLException e) {
            logger.warning("Failed to get all playlistTracks from database");
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public List<PlaylistTrack> findByPlaylistId(Integer playlistId) {
        List<PlaylistTrack> result = new ArrayList<>();
        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM PlaylistTrack WHERE playlist_id = ?");
            statement.setInt(1, playlistId);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                PlaylistTrack playlistTrack = buildPlaylistTrackFromResultSet(resultSet);
                result.add(playlistTrack);
            }
            return result;
        } catch (SQLException e) {
            logger.warning(String.format("Failed to get playlistTracks by playlistId %s", playlistId));
            e.printStackTrace();
            return new ArrayList<>();
        }

    }

    @Override
    public Optional<PlaylistTrack> findByTrackId(Integer trackId) {
        return null;
    }

    @Override
    public void removeTrackFromPlaylist(Integer playlistId, Integer trackId) {
        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement("DELETE FROM `PlaylistTrack` WHERE `playlist_id` = ? AND `track_id` = ?");
            statement.setInt(1, playlistId);
            statement.setInt(2, trackId);
            statement.execute();
        } catch (SQLException e) {
            logger.warning(String.format("Failed to remove PlaylistTrack with playlistId %s and trackId %s", playlistId, trackId));
            e.printStackTrace();
        }

    }

    @Override
    public void addPlaylistTrack(PlaylistTrack playlistTrack) {
        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO `PlaylistTrack` (`track_id`, `playlist_id`) VALUES (? ,?)");
            statement.setInt(1, playlistTrack.getTrackId());
            statement.setInt(2, playlistTrack.getPlaylistId());
            statement.execute();
        }
        catch (SQLException e) {
            logger.warning(String.format("Failed to insert playlistTrack with trackId %s and playlistId %s", playlistTrack.getTrackId(), playlistTrack.getPlaylistId()));
            e.printStackTrace();
        }

    }

    private PlaylistTrack buildPlaylistTrackFromResultSet(ResultSet resultSet) throws SQLException {
        Integer trackId = resultSet.getInt("track_id");
        Integer playlistId = resultSet.getInt("playlist_id");

        return new PlaylistTrack(trackId, playlistId);
    }
}

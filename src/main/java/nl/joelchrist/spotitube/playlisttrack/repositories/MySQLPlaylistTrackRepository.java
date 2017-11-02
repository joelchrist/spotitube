package nl.joelchrist.spotitube.playlisttrack.repositories;

import nl.joelchrist.spotitube.dao.Repository;
import nl.joelchrist.spotitube.playlisttrack.domain.PlaylistTrack;

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

    private PlaylistTrack buildPlaylistTrackFromResultSet(ResultSet resultSet) throws SQLException {
        Integer trackId = resultSet.getInt("track_id");
        Integer playlistId = resultSet.getInt("playlist_id");

        return new PlaylistTrack(trackId, playlistId);
    }
}

package nl.joelchrist.spotitube.tracks.repositories;

import nl.joelchrist.spotitube.dao.Repository;
import nl.joelchrist.spotitube.tracks.domain.Track;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

public class MySQLTracksRepository extends Repository implements TracksRepository {
    private Logger logger = Logger.getLogger(getClass().getName());

    public MySQLTracksRepository()
    {
        super();
    }

    public List<Track> findAll() {
        List<Track> result = new ArrayList<>();
        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Tracks");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Track track = buildTrackFromResultSet(resultSet);
                result.add(track);
            }
            connection.close();
            return result;
        } catch (SQLException e) {
            logger.warning("Failed to get all tracks from database");
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private Track buildTrackFromResultSet(ResultSet resultSet) throws SQLException {
        Integer id = resultSet.getInt("id");
        String title = resultSet.getString("title");
        String performer = resultSet.getString("performer");
        Integer duration = resultSet.getInt("duration");
        String album = resultSet.getString("album");
        Integer playCount = resultSet.getInt("play_count");
        Date publicationDate = resultSet.getDate("publication_date");
        String description = resultSet.getString("description");
        Boolean offlineAvailable = resultSet.getBoolean("offline_available");

        return new Track(id, title, performer, duration, album, playCount, publicationDate, description, offlineAvailable);
    }
}

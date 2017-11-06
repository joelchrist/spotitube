package nl.joelchrist.spotitube.playlists.repositories;

import nl.joelchrist.spotitube.dao.Repository;
import nl.joelchrist.spotitube.playlists.domain.Playlist;
import nl.joelchrist.spotitube.tracks.domain.Track;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class MySQLPlaylistsRepository extends Repository implements PlaylistsRepository {
    private Logger logger = Logger.getLogger(getClass().getName());

    public MySQLPlaylistsRepository() {
        super();
    }

    public List<Playlist> findAll() {
        List<Playlist> result = new ArrayList<>();
        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM `Playlists`");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Playlist playlist = buildPlaylistFromResultSet(resultSet);
                result.add(playlist);
            }
            return result;
        } catch (SQLException e) {
            logger.warning("Failed to get all playlists from database");
            e.printStackTrace();
            return new ArrayList<>();

        }

    }

    @Override
    public void deletePlaylist(Integer playlistId) {
        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement("DELETE FROM `Playlists` WHERE `id` = ?");
            statement.setInt(1, playlistId);
            statement.execute();
        } catch (SQLException e) {
            logger.warning(String.format("Failed to delete playlist with id %s", playlistId));
            e.printStackTrace();
        }

    }

    private Playlist buildPlaylistFromResultSet(ResultSet resultSet) throws SQLException {
        Integer id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        String owner = resultSet.getString("owner");
        List<Track> list = new ArrayList<>();
        return new Playlist(id, name, owner, list);
    }
}

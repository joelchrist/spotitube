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
            connection.close();
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
            connection.close();
        } catch (SQLException e) {
            logger.warning(String.format("Failed to delete playlist with id %s", playlistId));
            e.printStackTrace();
        }

    }

    @Override
    public void addPlaylist(Playlist playlist) {
        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO `Playlists` (`name`, `owner`) VALUE (?, ?)");
            statement.setString(1, playlist.getName());
            statement.setString(2, playlist.getOwner());
            statement.execute();
            connection.close();
        } catch (SQLException e) {
            logger.warning(String.format("Failed to insert playlist with values %s, %s into database", playlist.getName(), playlist.getOwner()));
            e.printStackTrace();
        }
    }

    @Override
    public void updateName(Integer playlistId, String name) {
        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement("UPDATE `Playlists` SET `name` = ? WHERE `id` = ?");
            statement.setString(1, name);
            statement.setInt(2, playlistId);
            statement.execute();
            connection.close();
        } catch (SQLException e) {
            logger.warning(String.format("Failed to update name of playlist with id %n and name %s", playlistId, name));
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

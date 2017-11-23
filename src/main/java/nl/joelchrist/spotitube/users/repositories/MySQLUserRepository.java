package nl.joelchrist.spotitube.users.repositories;

import nl.joelchrist.spotitube.dao.Repository;
import nl.joelchrist.spotitube.users.domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.logging.Logger;

public class MySQLUserRepository extends Repository implements UserRepository {

    private Logger logger = Logger.getLogger(getClass().getName());

    @Override
    public Optional<User> getUser(String user) {
        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Users where user = ?");
            statement.setString(1, user);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                User returnUser = buildUserFromResultSet(resultSet);
                return Optional.of(returnUser);
            }
            connection.close();
            return Optional.empty();
        } catch (SQLException e) {
            logger.warning(String.format("Failed to get user with name: %s from database", user));
            e.printStackTrace();
            return Optional.empty();
        }
    }

    private User buildUserFromResultSet(ResultSet resultSet) throws SQLException {
        String user = resultSet.getString("user");
        String password = resultSet.getString("password");

        return new User(user, password);
    }
}

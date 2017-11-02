package nl.joelchrist.spotitube.auth.repositories;

import nl.joelchrist.spotitube.auth.domain.AuthenticationToken;
import nl.joelchrist.spotitube.dao.Repository;
import nl.joelchrist.spotitube.users.domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;
import java.util.logging.Logger;

public class MySQLAuthenticationTokenRepository extends Repository implements AuthenticationTokenRepository {
    private Logger logger = Logger.getLogger(getClass().getName());

    public MySQLAuthenticationTokenRepository() {
        super();
    }

    public Optional<AuthenticationToken> getAuthenticationTokenByToken(String token) {
        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM AuthenticationTokens WHERE token = ?");
            statement.setString(1, token);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                AuthenticationToken authenticationToken = buildAuthenticationTokenFromResultSet(resultSet);
                return Optional.of(authenticationToken);
            }
            return Optional.empty();
        } catch (SQLException e) {
            logger.warning("Failed to get authenticationToken from database");
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public void saveToken(AuthenticationToken token) {
        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO AuthenticationTokens (`user`, `token`, `expiry_date`) VALUE (?, ?, ?)");
            statement.setString(1, token.getUser());
            statement.setString(2, token.getToken());
            statement.setTimestamp(3, generateTimeStampFromDate(token.getExpiryDate()));
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.warning("Failed to save authenticationToken to database");
            e.printStackTrace();
        }
    }

    @Override
    public Optional<AuthenticationToken> getAuthenticationTokenByUser(User user) {
        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM AuthenticationTokens WHERE user = ?");
            statement.setString(1, user.getUser());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                AuthenticationToken authenticationToken = buildAuthenticationTokenFromResultSet(resultSet);
                return Optional.of(authenticationToken);
            }
            return Optional.empty();
        } catch (SQLException e) {
            logger.warning("Failed to get authenticationToken from database");
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public void updateExpiryDate(AuthenticationToken authenticationToken, Date expiryDate) {
        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement("UPDATE `AuthenticationTokens` SET `expiry_date` = ? WHERE `token` = ?");
            statement.setTimestamp(1, generateTimeStampFromDate(expiryDate));
            statement.setString(2, authenticationToken.getToken());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.warning("Failed to get authenticationToken from database");
            e.printStackTrace();
        }
    }

    private AuthenticationToken buildAuthenticationTokenFromResultSet(ResultSet resultSet) throws SQLException {
        String user = resultSet.getString("user");
        String token = resultSet.getString("token");
        Date expiryDate = resultSet.getDate("expiry_date");

        return new AuthenticationToken(user, token, expiryDate);
    }

    private Timestamp generateTimeStampFromDate(Date date) {
        return new java.sql.Timestamp(date.getTime());
    }
}

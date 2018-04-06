package nl.joelchrist.spotitube.auth.domain;

import nl.joelchrist.spotitube.dao.domain.Document;

import java.util.Date;

public class AuthenticationToken extends Document {
    private String user;
    private String token;
    private Date expiryDate;

    public AuthenticationToken(String user, String token, Date expiryDate) {
        this.user = user;
        this.token = token;
        this.expiryDate = expiryDate;
    }

    public AuthenticationToken() {

    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }
}

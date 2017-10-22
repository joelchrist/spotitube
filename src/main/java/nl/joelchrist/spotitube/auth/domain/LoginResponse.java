package nl.joelchrist.spotitube.auth.domain;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class LoginResponse {
    private String user;
    private String token;

    public LoginResponse() {
        //Empty constructor needed for Jax-RS Serialization
    }

    public LoginResponse(String user, String token) {
        this.user = user;
        this.token = token;
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
}

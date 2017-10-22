package nl.joelchrist.spotitube.auth.domain;

public class LoginCredentials {
    private String user;
    private String password;

    public LoginCredentials() {
        //Empty constructor needed for Jax-RS Serialization
    }

    public LoginCredentials(String user, String password) {
        this.user = user;
        this.password = password;
    }
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

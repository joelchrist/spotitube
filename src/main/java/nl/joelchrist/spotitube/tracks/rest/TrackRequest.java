package nl.joelchrist.spotitube.tracks.rest;

public class TrackRequest {
    private String id;

    private Boolean offlineAvailable;

    public TrackRequest(String id, Boolean offlineAvailable) {
        this.id = id;
        this.offlineAvailable = offlineAvailable;
    }

    public TrackRequest() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getOfflineAvailable() {
        return offlineAvailable;
    }

    public void setOfflineAvailable(Boolean offlineAvailable) {
        this.offlineAvailable = offlineAvailable;
    }
}

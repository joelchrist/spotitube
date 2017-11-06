package nl.joelchrist.spotitube.tracks.rest;

public class TrackRequest {
    private Integer id;
    private Boolean offlineAvailable;

    public TrackRequest(Integer id, Boolean offlineAvailable) {
        this.id = id;
        this.offlineAvailable = offlineAvailable;
    }

    public TrackRequest() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getOfflineAvailable() {
        return offlineAvailable;
    }

    public void setOfflineAvailable(Boolean offlineAvailable) {
        this.offlineAvailable = offlineAvailable;
    }
}

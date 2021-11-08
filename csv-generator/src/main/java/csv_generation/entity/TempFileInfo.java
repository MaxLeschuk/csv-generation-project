package csv_generation.entity;

public class TempFileInfo {


    private String path;
    private String status;

    public TempFileInfo(String path, String status) {
        this.path = path;
        this.status = status;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

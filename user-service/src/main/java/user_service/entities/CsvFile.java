package user_service.entities;

public class CsvFile {
    private Integer id;
    private String path;
    private String userId;

    public CsvFile(String userId, String path) {
        this.path = path;
        this.userId = userId;
    }

    public CsvFile() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}

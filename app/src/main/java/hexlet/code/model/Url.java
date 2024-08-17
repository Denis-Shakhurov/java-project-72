package hexlet.code.model;

import java.sql.Timestamp;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Url {
    private Long id;
    private String name;
    private Timestamp createdAt;
    private List<UrlCheck> urlChecks;
    private UrlCheck urlCheck;

    public Url(String name) {
        this.name = name;
    }

    public Url(String name, List<UrlCheck> urlChecks) {
        this.name = name;
        this.urlChecks = urlChecks;
    }

    public Url(String name, UrlCheck urlCheck) {
        this.name = name;
        this.urlCheck = urlCheck;
    }
}

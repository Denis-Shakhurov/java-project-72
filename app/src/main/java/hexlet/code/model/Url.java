package hexlet.code.model;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Url {
    private Long id;
    private String name;
    private LocalDateTime createdAt;
    private List<UrlCheck> urlChecks;

    public Url(String name, LocalDateTime createdAt) {
        this.name = name;
        this.createdAt = createdAt;
    }

    public Url(String name, LocalDateTime createdAt, List<UrlCheck> urlChecks) {
        this.name = name;
        this.createdAt = createdAt;
        this.urlChecks = urlChecks;
    }
}

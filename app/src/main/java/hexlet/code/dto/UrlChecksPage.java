package hexlet.code.dto;

import hexlet.code.model.UrlCheck;
import lombok.Getter;
import lombok.AllArgsConstructor;
import java.util.List;
@Getter
@AllArgsConstructor
public class UrlChecksPage {
    private List<UrlCheck> urlChecks;
}

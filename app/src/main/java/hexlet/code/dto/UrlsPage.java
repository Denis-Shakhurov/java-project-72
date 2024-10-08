package hexlet.code.dto;

import hexlet.code.model.Url;

import java.util.List;
import java.util.Map;

import hexlet.code.model.UrlCheck;
import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UrlsPage extends BasePage {
    private List<Url> urls;
    private Map<Long, UrlCheck> urlChecks;
}

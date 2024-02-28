package hexlet.code.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class UrlsPage extends BasicPage {
    private List<Url> allUrls;
}


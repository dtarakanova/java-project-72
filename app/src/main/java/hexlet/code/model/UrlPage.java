package hexlet.code.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class UrlPage extends BasicPage {
    private Url url;
    private List<UrlCheck> check;
}

package hexlet.code.controller;
import hexlet.code.model.Url;
import hexlet.code.model.UrlsPage;
import hexlet.code.repository.DataRepository;
import hexlet.code.util.NamedRoutes;
import io.javalin.http.Context;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;


public class UrlsController {
    public static void createUrl(Context ctx) throws SQLException {
        var inputUrl = ctx.formParam("url");
        URL parsedUrl;
        try {
            parsedUrl = new URI(inputUrl).toURL();
        } catch (URISyntaxException | IllegalArgumentException | NullPointerException | MalformedURLException e) {
            ctx.sessionAttribute("flash", "Некорректный URL");
            ctx.sessionAttribute("flashType", "error");
            ctx.redirect(NamedRoutes.mainPath());
            return;
        }
        String normalizedUrl = String
                .format(
                        "%s://%s%s",
                        parsedUrl.getProtocol(),
                        parsedUrl.getHost(),
                        parsedUrl.getPort() == -1 ? "" : ":" + parsedUrl.getPort()
                )
                .toLowerCase();

        Url url = DataRepository.isPresent(normalizedUrl).orElse(null);

        if (url != null) {
            ctx.sessionAttribute("flash", "Страница уже существует");
            ctx.sessionAttribute("flash-type", "warning");
        } else {
            Url newUrl = new Url(normalizedUrl);
            DataRepository.save(newUrl);
            ctx.sessionAttribute("flash", "Страница успешно добавлена");
            ctx.sessionAttribute("flash-type", "success");
        }
        ctx.redirect(NamedRoutes.urlsPath());
    }

    public static void showUrls(Context ctx) throws SQLException {
        List<Url> urls = DataRepository.getEntities();
        UrlsPage page = new UrlsPage(urls);
        page.setFlash(ctx.consumeSessionAttribute("flash"));
        page.setFlashType(ctx.consumeSessionAttribute("flash-type"));
        ctx.render("urls/urlsindex.jte", Collections.singletonMap("page", page));
    }

}

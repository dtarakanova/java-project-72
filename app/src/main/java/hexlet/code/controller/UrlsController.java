package hexlet.code.controller;
import hexlet.code.model.Url;
import hexlet.code.model.UrlsPage;
import hexlet.code.repository.DataRepository;
import io.javalin.http.Context;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class UrlsController {
    public static void createUrl(Context ctx) throws SQLException {
        var url = ctx.formParam("url");
        String normalizedUrl = url.toLowerCase();
        Url finUrl = new Url(normalizedUrl);
        DataRepository.save(finUrl);
        ctx.redirect("/urls");
    }

    public static void showUrls(Context ctx) throws SQLException {
        List<Url> urls = DataRepository.getEntities();
        UrlsPage page = new UrlsPage(urls);
        ctx.render("/urls/urlsindex.jte", Collections.singletonMap("page", page));
    }
}

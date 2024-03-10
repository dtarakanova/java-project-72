package hexlet.code.controller;

import hexlet.code.model.Url;
import hexlet.code.model.UrlCheck;
import hexlet.code.model.UrlPage;
import hexlet.code.repository.DataRepository;
import hexlet.code.repository.UrlCheckRepository;
import hexlet.code.util.NamedRoutes;
import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;

import kong.unirest.Unirest;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;

public class UrlPageController {

    public static void showUrlPage(Context ctx) throws SQLException {
        long id = ctx.pathParamAsClass("id", Long.class).getOrDefault(null);
        Url url = DataRepository.find(id)
                .orElseThrow(() -> new NotFoundResponse("Url with id: " + id + " not found"));
        List<UrlCheck> urlChecks = DataRepository.findByUrlId(id);
        UrlPage page = new UrlPage(url, urlChecks);
        page.setFlash(ctx.consumeSessionAttribute("flash"));
        page.setFlashType(ctx.consumeSessionAttribute("flash-type"));
        ctx.render("urls/urlpage.jte", Collections.singletonMap("page", page));
    }

    public static void urlCheck(Context ctx) throws SQLException {
        long id = ctx.pathParamAsClass("id", Long.class).getOrDefault(null);
        Url url = DataRepository.findById(id)
                .orElseThrow(() -> new NotFoundResponse("Url with id: " + id + " not found"));

        try {
            var response = Unirest.get(url.getName()).asString();
            int statusCode = response.getStatus();
            var createdAt = new Timestamp(System.currentTimeMillis());
            UrlCheck newCheck = new UrlCheck(statusCode, createdAt);
            newCheck.setUrlId(id);
            UrlCheckRepository.saveUrlCheck(newCheck);
            ctx.sessionAttribute("flash", "Страница успешно проверена");
            ctx.sessionAttribute("flash-type", "success");
            ctx.redirect(NamedRoutes.urlPath(id));
        } catch (Exception e) {
            ctx.sessionAttribute("flash", "Ошибка при проверке");
            ctx.sessionAttribute("flash-type", "danger");
        }
        ctx.redirect(NamedRoutes.urlPath(id));
    }
}

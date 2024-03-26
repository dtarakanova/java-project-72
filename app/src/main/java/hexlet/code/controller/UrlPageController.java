package hexlet.code.controller;

import hexlet.code.model.Url;
import hexlet.code.model.UrlCheck;
import hexlet.code.dto.UrlPage;
import hexlet.code.repository.UrlRepository;
import hexlet.code.repository.UrlCheckRepository;
import hexlet.code.util.NamedRoutes;
import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import kong.unirest.UnirestException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;

public class UrlPageController {

    public static void showUrlPage(Context ctx) throws SQLException {
        long id = ctx.pathParamAsClass("id", Long.class).getOrDefault(null);
        Url url = UrlRepository.find(id)
                .orElseThrow(() -> new NotFoundResponse("Url with id: " + id + " not found"));
        List<UrlCheck> urlChecks = UrlCheckRepository.findByUrlId(id);
        UrlPage page = new UrlPage(url, urlChecks);
        page.setFlash(ctx.consumeSessionAttribute("flash"));
        page.setFlashType(ctx.consumeSessionAttribute("flash-type"));
        ctx.render("urls/urlpage.jte", Collections.singletonMap("page", page));
    }

    public static void urlCheck(Context ctx) throws SQLException {
        long id = ctx.pathParamAsClass("id", Long.class).getOrDefault(null);
        Url url = UrlRepository.findById(id)
                .orElseThrow(() -> new NotFoundResponse("Url with id: " + id + " not found"));

        try {
            HttpResponse<String> response = Unirest.get(url.getName()).asString();
            Document doc = Jsoup.parse(response.getBody());
            int statusCode = response.getStatus();
            Timestamp dateandtime = new Timestamp(System.currentTimeMillis());
            String title = doc.title();
            Element h1El = doc.selectFirst("h1");
            String h1 = h1El == null ? "" : h1El.text();
            Element descr = doc.selectFirst("meta[name=description]");
            String description = descr == null ? "" : descr.attr("content");
            UrlCheck newCheck = new UrlCheck(statusCode, title, h1, description);

            newCheck.setUrlId(id);
            newCheck.setCreatedAt(dateandtime);
            UrlCheckRepository.saveUrlCheck(newCheck);
            ctx.sessionAttribute("flash", "Страница успешно проверена");
            ctx.sessionAttribute("flash-type", "success");
        } catch (UnirestException e) {
            ctx.sessionAttribute("flash", "Некорректный URL");
            ctx.sessionAttribute("flash-type", "danger");
        } catch (Exception e) {
            ctx.sessionAttribute("flash", e.getMessage());
            ctx.sessionAttribute("flash-type", "danger");
        }
        ctx.redirect(NamedRoutes.urlPath(id));
    }
}

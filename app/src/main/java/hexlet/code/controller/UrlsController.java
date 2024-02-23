package hexlet.code.controller;


import hexlet.code.model.Url;
import hexlet.code.repository.DataRepository;
import io.javalin.http.Context;
import java.sql.SQLException;
import java.sql.Timestamp;

public class UrlsController {
    public static void createUrl(Context ctx) throws SQLException {
        var url = ctx.formParam("url");
        String normalizedUrl = url.toLowerCase();
        Url finUrl = new Url(normalizedUrl);
        DataRepository.save(finUrl);
        ctx.redirect("/urls");
        //ctx.redirect(NamedRoutes.carsPath());
    }
}

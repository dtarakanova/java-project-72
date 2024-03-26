package hexlet.code.controller;

import hexlet.code.dto.MainPage;
import io.javalin.http.Context;

import java.util.Collections;

public class MainPageController {
    public static void welcomeMain(Context ctx) {
        MainPage page = new MainPage();
        page.setFlash(ctx.consumeSessionAttribute("flash"));
        page.setFlashType(ctx.consumeSessionAttribute("flash-type"));
        ctx.render("index.jte", Collections.singletonMap("page", page));
    };
}

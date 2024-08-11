package hexlet.code.controller;

import hexlet.code.dto.BuildUrlPage;
import hexlet.code.dto.UrlPage;
import hexlet.code.dto.UrlsPage;
import hexlet.code.model.Url;
import hexlet.code.repository.UrlRepository;
import hexlet.code.util.NamedRoutes;
import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;
import io.javalin.validation.ValidationException;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;

import static io.javalin.rendering.template.TemplateUtil.model;

public class UrlsController {
    public static void index(Context ctx) throws SQLException {
        var urls = UrlRepository.getEntities();
        var page = new UrlsPage(urls);
        //page.setFlash(ctx.consumeSessionAttribute("flash"));
        ctx.render("urls/index.jte", model("page", page));
    }

    public static void show(Context ctx) throws SQLException {
        var id = ctx.pathParamAsClass("id", Long.class).get();
        var url = UrlRepository.find(id)
                .orElseThrow(() -> new NotFoundResponse("Entity with id = " + id + " not found"));
        var page = new UrlPage(url);
        ctx.render("urls/show.jte", model("page", page));
    }

    public static void create(Context ctx) throws SQLException {
        String path = ctx.formParam("url");
        try {
            var name1 = ctx.formParamAsClass("url", String.class).get();
                    //.check(value -> value.equals(passwordConfirmation), "Пароли не совпадают")
                    //.get();
            var uri = URI.create(name1);
            var name = uri.getAuthority();
            //var name = urlPath.ge
            //var nme = urlName.getAuthority();
            var localDateTime = LocalDateTime.now();
            var url = new Url(name, localDateTime);
            UrlRepository.save(url);
            //ctx.sessionAttribute("flash", "Пользователь успешно создан");
            ctx.redirect("/");
        } catch (ValidationException e) {
            var page = new BuildUrlPage(path, e.getErrors());
            ctx.render("index.jte", model("page", page)).status(422);
        }
    }

    public static void build(Context ctx) {
        var page = new BuildUrlPage();
        ctx.render("index.jte", model("page", page));
    }
}

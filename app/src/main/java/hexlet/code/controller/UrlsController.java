package hexlet.code.controller;

import hexlet.code.dto.BuildUrlPage;
import hexlet.code.dto.UrlPage;
import hexlet.code.dto.UrlsPage;
import hexlet.code.model.Url;
import hexlet.code.repository.UrlCheckRepository;
import hexlet.code.repository.UrlRepository;
import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;
import io.javalin.validation.ValidationException;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import static io.javalin.rendering.template.TemplateUtil.model;

public class UrlsController {
    public static void index(Context ctx) throws SQLException {
        var urls = UrlRepository.getEntities();
        var page = new UrlsPage(urls);
        page.setFlash(ctx.consumeSessionAttribute("flash"));
        ctx.render("urls/index.jte", model("page", page));
    }

    public static void show(Context ctx) throws SQLException {
        var id = ctx.pathParamAsClass("id", Long.class).get();
        var url = UrlRepository.find(id)
                .orElseThrow(() -> new NotFoundResponse("Entity with id = " + id + " not found"));
        var checks = UrlCheckRepository.getEntitiesByUrlId(id);
        var name = url.getName();
        var createdAt = url.getCreatedAt();
        var url1 = new Url(name, createdAt, checks);
        url1.setId(id);
        var page = new UrlPage(url1);
        ctx.render("urls/show.jte", model("page", page));
    }

    public static void create(Context ctx) throws SQLException {
        String path = ctx.formParam("url");
        try {
            var urlPath = new URL(path);
            var uri = urlPath.toURI();
            if (uri.isOpaque()) {
                var name = uri.getScheme() + "://" + uri.getAuthority();
                var name1 = ctx.formParamAsClass("url", String.class)
                        .check(value -> {
                            try {
                                return UrlRepository.existsByName(name);
                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            }
                        }, "Страница уже существует")
                        .get();
                var createAt = Timestamp.valueOf(LocalDateTime.now());
                var url = new Url(name, createAt);
                UrlRepository.save(url);
                ctx.redirect("/urls");
                ctx.sessionAttribute("flash", "Страница успешно добавлена");
            } else {
                ctx.redirect("/");
                ctx.sessionAttribute("flash", "По указанному адресу страница не существует");
            }
        } catch (ValidationException e) {
            ctx.sessionAttribute("flash", "Страница уже существует");
            ctx.status(422);
            ctx.redirect("/");
        } catch (MalformedURLException | URISyntaxException e) {
            ctx.sessionAttribute("flash", "Некорректный URL");
            ctx.status(422);
            ctx.redirect("/");
        }
    }

    public static void build(Context ctx) {
        var page = new BuildUrlPage();
        page.setFlash(ctx.consumeSessionAttribute("flash"));
        ctx.render("index.jte", model("page", page));
    }
}

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
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;

import static io.javalin.rendering.template.TemplateUtil.model;

public class UrlsController {
    public static void index(Context ctx) throws SQLException {
        var urls = UrlRepository.getEntities();
        var urlChecks = UrlCheckRepository.getLastChecks();
        var page = new UrlsPage(urls, urlChecks);
        page.setFlash(ctx.consumeSessionAttribute("flash"));
        ctx.render("urls/index.jte", model("page", page));
    }

    public static void show(Context ctx) throws SQLException {
        var id = ctx.pathParamAsClass("id", Long.class).get();
        var url = UrlRepository.find(id)
                .orElseThrow(() -> new NotFoundResponse("Entity with id = " + id + " not found"));
        var name = url.getName();
        var urlShow = new Url(name);
        urlShow.setId(id);
        urlShow.setCreatedAt(url.getCreatedAt());
        var urlChecks = UrlCheckRepository.getEntitiesByUrlId(id);
        var page = new UrlPage(urlShow, urlChecks);
        page.setFlash(ctx.consumeSessionAttribute("flash"));
        ctx.render("urls/show.jte", model("page", page));
    }

    public static void create(Context ctx) throws SQLException{
        String path = ctx.formParam("url");
        try {
            var urlPath = new URI(path).toURL();
            var name = urlPath.getProtocol() + "://" + urlPath.getAuthority();
            var name1 = ctx.formParamAsClass("url", String.class)
                    .check(value -> {
                        try {
                            return UrlRepository.existsByName(name);
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                        }, "Страница уже существует")
                        .get();
            var url = new Url(name);
            UrlRepository.save(url);
            ctx.redirect("/urls");
            ctx.sessionAttribute("flash", "Страница успешно добавлена");

        } catch (ValidationException e) {
            ctx.sessionAttribute("flash", "Страница уже существует");
            ctx.status(422);
            ctx.redirect("/");
        } catch (MalformedURLException | URISyntaxException | IllegalArgumentException e) {
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

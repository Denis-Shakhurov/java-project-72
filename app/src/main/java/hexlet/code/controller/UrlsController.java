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
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
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
        ctx.render("urls/show.jte", model("page", page));
    }

    public static void create(Context ctx) {
        String path = ctx.formParam("url");
        try {
                var uri = new URL(path).toURI();
                var name = uri.getScheme() + "://" + uri.getAuthority();
            if (isAvailable(name)) {
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
            } else {
                throw new RuntimeException();
            }
        } catch (ValidationException e) {
            ctx.sessionAttribute("flash", "Страница уже существует");
            ctx.status(422);
            ctx.redirect("/");
        } catch (MalformedURLException | URISyntaxException e) {
            ctx.sessionAttribute("flash", "Некорректный URL");
            ctx.status(422);
            ctx.redirect("/");
        } catch (Exception e) {
            ctx.sessionAttribute("flash", "Страница не доступна");
            ctx.redirect("/");
        }
    }

    public static void build(Context ctx) {
        var page = new BuildUrlPage();
        page.setFlash(ctx.consumeSessionAttribute("flash"));
        ctx.render("index.jte", model("page", page));
    }

    private static boolean isAvailable(String url) throws Exception {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet request = new HttpGet(url);
        CloseableHttpResponse response = httpClient.execute(request);
        return response.getStatusLine().getStatusCode() == 200;
    }
}

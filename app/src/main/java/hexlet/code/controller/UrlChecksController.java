package hexlet.code.controller;

import hexlet.code.model.UrlCheck;
import hexlet.code.repository.UrlCheckRepository;
import hexlet.code.repository.UrlRepository;
import hexlet.code.util.NamedRoutes;
import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;
import kong.unirest.core.Unirest;
import kong.unirest.core.UnirestException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.sql.SQLException;
import java.util.Objects;

public class UrlChecksController {

    public static void create(Context ctx) throws SQLException {

        var urlId = ctx.pathParamAsClass("id", Long.class).get();
        var url = UrlRepository.find(urlId)
                .orElseThrow(() -> new NotFoundResponse("url не найден"));
        var urlName = url.getName();
        try {
            var jsonResponse = Unirest.get(urlName).asString();
            var body = jsonResponse.getBody();
            var statusCode = jsonResponse.getStatus();
            var title = findText(body, "title");
            var h1 = findText(body, "h1");
            var description = findText(body, "description");
            var urlCheck = new UrlCheck(statusCode, title, h1, description, urlId);
            UrlCheckRepository.save(urlCheck);
            ctx.redirect(NamedRoutes.urlPath(urlId));
        } catch  (UnirestException e) {
            ctx.sessionAttribute("flash", "Страница не доступна");
            ctx.redirect(NamedRoutes.urlPath(urlId));
        }
    }

    private static String findText(String body, String tag) {
        Document document = Jsoup.parse(body);
        switch (tag) {
            case "title" : return document.title() == null ? "" : Objects.requireNonNull(document.title());
            case "h1" : return document.selectFirst(tag) == null ? "" : Objects.requireNonNull(document.selectFirst(tag)).text();
            case "description" :
                return document.selectFirst("meta[name=" + tag + "]").attr("content") == null ? "" :
                        Objects.requireNonNull(document.selectFirst("meta[name=" + tag + "]"))
                        .attr("content");
            default : return "";
        }
    }
}

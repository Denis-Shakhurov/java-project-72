package hexlet.code.controller;

import hexlet.code.model.UrlCheck;
import hexlet.code.repository.UrlCheckRepository;
import hexlet.code.repository.UrlRepository;
import hexlet.code.util.NamedRoutes;
import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;
import kong.unirest.core.Unirest;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class UrlChecksController {

    public static void create(Context ctx) throws SQLException, IOException {
        var urlId = ctx.pathParamAsClass("id", Long.class).get();
        var url = UrlRepository.find(urlId)
                .orElseThrow(() -> new NotFoundResponse("url не найден"));
        var urlName = url.getName();
        var jsonResponse = Unirest.get(urlName).asString();
        var body = jsonResponse.getBody();
        var statusCode = jsonResponse.getStatus();
        var title = findText(body, "title");
        var h1 = findText(body, "h1");
        var description = findText(body, "description");
        var createdAt = LocalDateTime.now();
        var urlCheck = new UrlCheck(statusCode, h1, title, description, urlId, createdAt);
        UrlCheckRepository.save(urlCheck);
        ctx.redirect(NamedRoutes.urlPath(urlId));
    }

    public static String findText(String body, String tag) {
        Document document = Jsoup.parse(body);
        switch (tag) {
            case "title" : return document.title();
            case "h1" : return document.selectFirst("h1") == null ? ""
                : document.selectFirst("h1").text();
            case "description" :
                return document.selectFirst("meta[name=description]") == null ? ""
                        : document.selectFirst("meta[name=description]").attr("content");
            default : return "";
        }
    }
}

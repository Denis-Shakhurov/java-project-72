import hexlet.code.App;
import hexlet.code.model.Url;
import hexlet.code.repository.UrlCheckRepository;
import hexlet.code.repository.UrlRepository;
import io.javalin.Javalin;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import io.javalin.testtools.JavalinTest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class AppTest {
    Javalin app;
    public static MockWebServer mockBackEnd;

    @BeforeAll
    static void setUpMock() throws IOException {
        mockBackEnd = new MockWebServer();
        var html = Files.readString(Paths.get("src/test/resources/htmlForTest.html"));
        var serverResponse = new MockResponse()
                .addHeader("Content-Type", "text/html; charset=utf-8")
                .setResponseCode(200)
                .setBody(html);
        mockBackEnd.enqueue(serverResponse);
        mockBackEnd.start();
    }

    @AfterAll
    static void tearDown() throws IOException {
        mockBackEnd.shutdown();
    }


    @BeforeEach
    public final void setUp() throws IOException, SQLException {
        app = App.getApp();
    }

    @Test
    public void testMainPage() {
        JavalinTest.test(app, (server, client) -> {
            var response = client.get("/");
            assertThat(response.code()).isEqualTo(200);
            assertThat(response.body().string()).contains("Анализатор страниц");
        });
    }

    @Test
    public void testUrlsPage() {
        JavalinTest.test(app, (server, client) -> {
            var response = client.get("/urls");
            assertThat(response.code()).isEqualTo(200);
        });
    }

    @Test
    public void testUrlNotFound() throws Exception {
        JavalinTest.test(app, (server, client) -> {
            var response = client.get("/urls/999999");
            assertThat(response.code()).isEqualTo(404);
        });
    }

    @Test
    public void testUrlPage() throws SQLException {
        var url = new Url("https://regex101.com", LocalDateTime.now());
        UrlRepository.save(url);
        JavalinTest.test(app, (server, client) -> {
            var response = client.get("/urls/" + url.getId());
            assertThat(response.code()).isEqualTo(200);
        });
    }

    @Test
    public void testUrlCheckNotFound() throws Exception {
        var url = new Url("https://regex101.com", LocalDateTime.now());
        UrlRepository.save(url);
        JavalinTest.test(app, (server, client) -> {
            var response = client.get("/urls/1/check");
            assertThat(response.code()).isEqualTo(404);
        });
    }

    @Test
    public void testCreateUrl() throws SQLException {
        var url = new Url("https://sky.pro/wiki/", LocalDateTime.now());
        UrlRepository.save(url);
        JavalinTest.test(app, (server, client) -> {
            client.post("/urls/");

            var url1 = UrlRepository.find(1L).get();
            assertThat(url1.getName().contains("https://sky.pro/wiki/"));
        });
    }

    @Test
    public void testUrlCheckContent() {
        var baseUrl = mockBackEnd.url("/").toString();
        JavalinTest.test(app, (server, client) -> {
            var requestBody = "url=" + baseUrl;
            client.post("/urls", requestBody);
            client.post("/urls/1/checks");

            var urlCheck = UrlCheckRepository.getEntitiesByUrlId(1L).getFirst();

            assertThat(urlCheck.getStatusCode()).isEqualTo(200);
            assertThat(urlCheck.getH1()).contains("h1");
            assertThat(urlCheck.getTitle()).contains("Title");
            assertThat(urlCheck.getDescription()).contains("Test MockWebServer");
        });
    }

    @Test
    public void testAddWrongUrl() {
        JavalinTest.test(app, (server, client) -> {
            var requestBody = "url=123";
            var response = client.post("/urls", requestBody);
            assertThat(response.code()).isEqualTo(200);

            var urls = UrlRepository.find(123L);
            assertThat(urls).isEmpty();
        });
    }
}

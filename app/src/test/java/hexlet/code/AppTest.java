package hexlet.code;

import static hexlet.code.util.ResourceFile.readResourceFile;
import static org.assertj.core.api.Assertions.assertThat;

import hexlet.code.model.Url;
import hexlet.code.model.UrlCheck;
import hexlet.code.repository.DataRepository;
import hexlet.code.util.NamedRoutes;
import io.javalin.Javalin;
import io.javalin.testtools.JavalinTest;
import kong.unirest.HttpStatus;
import kong.unirest.Unirest;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import java.io.IOException;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class AppTest {

    static Javalin app;
    private static MockWebServer mockServer;
    private static final int PORT = 0;
    private static String basicUrl;

    @BeforeAll
    public static void startServer() throws IOException, SQLException {
        app = App.getApp();
        app.start(PORT);

        basicUrl = "http://localhost:" + app.port();
        mockServer = new MockWebServer();
        String testBody = readResourceFile("fixtures/samplepage.html");
        MockResponse mockResponse = new MockResponse().setBody(testBody);
        mockServer.enqueue(mockResponse);
        mockServer.start();
    }


    @AfterAll
    public static void stopServer() throws IOException {
        app.stop();
        mockServer.shutdown();
    }


    @Test
    public void testMainPage() {
        JavalinTest.test(app, ((server, client) -> {
            var response = client.get("/");
            assertThat(response.code()).isEqualTo(200);
        }));
    }

    @Test
    public void testUrlsPage() {
        JavalinTest.test(app, ((server, client) -> {
            var response = client.get("/urls");
            assertThat(response.code()).isEqualTo(200);
        }));
    }

    @Test
    public void testCreateUrl() {
        JavalinTest.test(app, ((server, client) -> {
            var requestBody = "name=urlname";
            var response = client.post("/urls", requestBody);
            assertThat(response.code()).isEqualTo(200);
            assertThat(response.body().string().contains("urlname"));
        }));
    }


    @Test
    public void testUrlPage() throws SQLException {
        var url = new Url("urlname");
        DataRepository.save(url);
        JavalinTest.test(app, ((server, client) -> {
            var response = client.get("/urls/" + url.getId());
            assertThat(response.code()).isEqualTo(200);
        }));
    }

    @Test
    public void testUrlNotFound() {
        JavalinTest.test(app, ((server, client) -> {
            var response = client.get("/urls/999999");
            assertThat(response.code()).isEqualTo(404);
        }));
    }

    @Test
    public void testCheck() throws SQLException {
        String url = mockServer.url("/").toString().replaceAll("/$", "");
        Unirest.post(basicUrl + NamedRoutes.urlsPath()).field("url", url).asEmpty();

        Optional<Url> currentUrl = DataRepository.findByName(url);

        assertThat(currentUrl.isEmpty()).isFalse();
        assertThat(currentUrl.get().getName()).isEqualTo(url);

        Url actualUrl = currentUrl.get();
        Unirest.post(basicUrl + NamedRoutes.urlChecks(actualUrl.getId())).asEmpty();

        List<UrlCheck> actualChecks = DataRepository.findByUrlId(actualUrl.getId());
        assertThat(actualChecks.isEmpty()).isFalse();

        UrlCheck actualCheck = actualChecks.get(0);

        assertThat(actualCheck.getStatusCode()).isEqualTo(HttpStatus.OK);
        //assertThat(actualCheck.getTitle()).isEqualTo("Test title");
        //assertThat(actualCheck.getH1()).isEqualTo("test h1");
        //assertThat(actualCheck.getDescription()).isEqualTo("test description");
    }
}

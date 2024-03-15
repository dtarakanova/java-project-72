package hexlet.code;

import static hexlet.code.util.ResourceFile.readResourceFile;
import static org.assertj.core.api.Assertions.assertThat;

import hexlet.code.model.Url;
import hexlet.code.repository.DataRepository;
import hexlet.code.repository.UrlCheckRepository;
import hexlet.code.util.NamedRoutes;
import io.javalin.Javalin;
import io.javalin.testtools.JavalinTest;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.io.IOException;

import java.sql.SQLException;


public final class AppTest {

    static Javalin app;
    private static MockWebServer mockServer;
    private static String basicUrl;



    @BeforeAll
    public static void startMockServer() throws IOException {
        mockServer = new MockWebServer();
        mockServer.start();
        basicUrl = mockServer.url("/").toString();
        var mockResponse = new MockResponse().setBody(readResourceFile());
        mockServer.enqueue(mockResponse);
    }

    @BeforeEach
    public void startServer() throws SQLException, IOException {
        app = App.getApp();
    }


    @AfterAll
    public static void stopMockServer() throws IOException {
        mockServer.shutdown();
    }



    @AfterEach
    public void stopServer() {
        app.stop();
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
        var url = new Url(basicUrl);
        DataRepository.save(url);
        JavalinTest.test(app, ((server, client) -> {
            var response = client.post(NamedRoutes.urlChecks(url.getId()));
            assertThat(response.code()).isEqualTo(200);
        }));

        var urlCheck = UrlCheckRepository.latestChecksById(url.getId()).get();
        var statusCode = urlCheck.getStatusCode();
        assertThat(statusCode).isEqualTo(200);

    }
}

package hexlet.code;

import static org.assertj.core.api.Assertions.assertThat;

import hexlet.code.model.Url;
import hexlet.code.repository.DataRepository;
import hexlet.code.repository.UrlCheckRepository;
import io.javalin.Javalin;
import io.javalin.testtools.JavalinTest;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;


public final class AppTest {

    static Javalin app;
    private static MockWebServer mockServer;


    @BeforeEach
    public void startServer() throws SQLException, IOException {
        app = App.getApp();
    }

    private static Path getFixturePath() {
        return Paths.get("src", "test", "resources", "fixtures", "samplepage.html")
                .toAbsolutePath().normalize();
    }

    private static String readFixture() throws IOException {
        Path filePath = getFixturePath();
        return Files.readString(filePath).trim();
    }

    @BeforeAll
    public static void startMockServer() throws IOException {
        mockServer = new MockWebServer();
        MockResponse mockResponse = new MockResponse()
                .setBody(readFixture());
        mockServer.enqueue(mockResponse);
        mockServer.start();
    }

    @AfterAll
    public static void stopMockServer() throws IOException {
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
    public void testCheck() throws SQLException, NullPointerException {
        var url = mockServer.url("/").toString().replaceAll("/$", "");
        JavalinTest.test(app, ((server, client) -> {
            var requestBody = "url=" + url;
            assertThat(client.post("/urls", requestBody).code()).isEqualTo(200);
        }));

        var actualUrl = DataRepository.findByName(url).orElse(null);
        var urlCheck = UrlCheckRepository.findLatestChecks().get(actualUrl.getId());
        var statusCode = urlCheck.getStatusCode();
        var title = urlCheck.getTitle();
        var h1 = urlCheck.getH1();
        var description = urlCheck.getDescription();
        assertThat(statusCode).isEqualTo(200);
        assertThat(title).isEqualTo("Hello, world");
        assertThat(h1).isEqualTo("Hello, new world");
        assertThat(description).isEqualTo("Test description");
    }
}

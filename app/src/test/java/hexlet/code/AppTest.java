package hexlet.code;

import static org.assertj.core.api.Assertions.assertThat;

import io.javalin.Javalin;
import io.javalin.testtools.JavalinTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;

public class AppTest {

    Javalin app;

    @BeforeEach
    public final void setUp() throws IOException, SQLException {
        app = App.getApp();
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

    /*
    @Test
    public void testUrlPageFound() {
        JavalinTest.test(app, ((server, client) -> {
            var response = client.get("/urls/1");
            assertThat(response.code()).isEqualTo(200);
        }));
    }
    */

    @Test
    public void testUrlNotFound() {
        JavalinTest.test(app, ((server, client) -> {
            var response = client.get("/urls/999999");
            assertThat(response.code()).isEqualTo(404);
        }));
    }
}

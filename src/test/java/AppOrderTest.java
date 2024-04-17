import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;

public class AppOrderTest {
    @Test
    void shouldTest() {
        open("http:http://0.0.0.0:9999/");
    }

}

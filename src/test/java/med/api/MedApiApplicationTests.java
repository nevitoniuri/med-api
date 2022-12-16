package med.api;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MedApiApplicationTests {

    private void doNotThrowException() {
    }

    @Test
    void contextLoads() {
        Assertions.assertDoesNotThrow(this::doNotThrowException);
    }

    @Test
    void applicationContextTest() {
        MedApiApplication.main(new String[]{});
        Assertions.assertDoesNotThrow(this::doNotThrowException);
    }
}

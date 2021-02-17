package by.itninjas;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TimeWatcherApplicationTests {

    @Autowired
    private TimeWatcherApplication timeWatcherApplication;

    @Test
    void contextLoads() {
        assertNotNull(timeWatcherApplication);
    }

}

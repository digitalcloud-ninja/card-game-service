package org.magnuson.games;

import org.junit.jupiter.api.*;

import java.io.IOException;
import java.nio.file.Path;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
/**
 * <h1>Test Harness: CardGamesApp Application</h1>
 * TODO: update documentation for your application.
 *
 */
@DisplayName("Test Harness: CardGamesApp")
public class CardGamesAppTest {
    private final static Logger logger = LogManager.getLogger(CardGamesAppTest.class.getName());

    // Test Scenario: Is executable?
    @Test
    @DisplayName("Validating CardGamesApp(properties_path)")
    void executable() throws CardGamesException, IOException {
        CardGamesApp cardGamesApp = new CardGamesApp(Path.of("src/main/resources/application.properties"));
        logger.info("PASSED: Validating Object: " + cardGamesApp.toString());
    }
}

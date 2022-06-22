package org.magnuson.games;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;


/**
 * CardGamesApp
 * Is the entry point to the Application
 * <p>
 *
 * @version 1.0
 */
public class CardGamesApp {
    private static final Logger logger = LogManager.getLogger(CardGamesApp.class.getName());
    private final Properties appProperties = new Properties();
    private final GamesList gamesLoaded;

    /**
     * Console Version of the CardGamesApp.
     * CardGamesApp.main()
     *
     * @param args {@code String[]} list of application arguments.
     * @throws CardGamesException when errors occurs in the application
     * @throws IOException when reading properties files
     * Usage: java CardGamesApp "{@code path to application.properties}"
     *
     * NOTE: When executing/debugging in your IDE, you'll need to supply application.properties path.
     *       typically: src/main/resources/application.properties
     */
    public static void main(String[] args) throws CardGamesException, IOException {
        String exceptionMessage = "init: application.properties path argument is missing.";
        if (args.length != 1) throw new IllegalArgumentException(exceptionMessage);
        CardGamesApp cardGamesApp = new CardGamesApp(Path.of(args[0]));
        logger.log(Level.INFO, "APP [CardGamesApp] init: SUCCESSFUL! " + cardGamesApp.toString());
    }

    /**
     * CardGamesApp Constructor
     * @param path the {@code Path} to application.properties
     * @throws CardGamesException when errors occurs in the application
     * @throws IOException when reading properties files
     *
     * @see Path
     */
    public CardGamesApp(Path path) throws CardGamesException, IOException {
        String exceptionMessage = "Application Exception: Error loading application.properties.";
        if (path == null) throw new CardGamesException(exceptionMessage);
        try (FileInputStream in = new FileInputStream(path.toString())) {
            this.appProperties.load(in);
            this.gamesLoaded = new GamesList(getAppProperties());
        }
        logger.debug("properties: " + appProperties );
    }

    /**
     * Returns the Application Properties
     * @return {@code Properties}
     */
    public final Properties getAppProperties() {
        return this.appProperties;
    }

    /**
     * Returns a List of Games loaded from application.properties.
     * @return {@code GameList}
     *
     * @see GamesList
     */
    public final GamesList getGamesLoaded() {
        return this.gamesLoaded;
    }

    @Override
    public String toString() {
        return String.format("CardGamesApp { properties=%1$s, gamesLoaded=%2$s }",
                getAppProperties(), getGamesLoaded());
    }
}

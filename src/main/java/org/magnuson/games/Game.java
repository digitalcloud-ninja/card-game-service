package org.magnuson.games;

/**
 * Game
 * Represents a game definition loaded from the application.properties
 *
 */
public class Game {
    private final int id;
    private final String name;
    private final String description;
    private final String className;

    /**
     * Game Constructor
     * @param id id assigned to the game.
     * @param name name of the game.
     * @param description additional description of the game.
     * @param className classname of the Class to load as the RulesManager.
     */
    public Game(int id, String name, String description, String className) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.className = className;
    }

    /**
     * Return the ID assigned to the game.
     * @return int
     */
    public int getId() {
        return id;
    }

    /**
     * Return the Name assigned to the game.
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the Description assigned to the game.
     * @return String
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the ClassName assigned to the game.
     * @return String
     */
    public String getClassName() {
        return className;
    }

    @Override
    public String toString() {
        String sb = "Game: {" + "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", className='" + className + '\'' +
                '}';
        return sb;
    }
}

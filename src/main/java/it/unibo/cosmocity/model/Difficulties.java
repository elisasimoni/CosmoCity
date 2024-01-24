package it.unibo.cosmocity.model;

public enum Difficulties {

    EASY("EASY"),
    MEDIUM("MEDIUM"),
    HARD("HARD");

    private final String difficulty;

    /**
     * @param difficulty
     */
    private Difficulties(String difficulty) {
        this.difficulty = difficulty;
    }

    
    
}

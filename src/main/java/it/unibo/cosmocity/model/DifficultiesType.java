package it.unibo.cosmocity.model;

public enum DifficultiesType {

    EASY("EASY"),
    MEDIUM("MEDIUM"),
    HARD("HARD");

    private final String difficulty;

    /**
     * @param difficulty
     */
    private DifficultiesType(String difficulty) {
        this.difficulty = difficulty;
    }

    
    
}

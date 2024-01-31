package it.unibo.cosmocity.model;

public enum DifficultiesType {

    EASY(300),
    MEDIUM(200),
    HARD(100);

    private final int difficulty;

    /**
     * @param difficulty
     */
    private DifficultiesType(int difficulty) {
        this.difficulty = difficulty;
    }

    /**
     * @return the difficulty
     */
    public int getDifficulty() {
        return difficulty;
    }

    /**
     * @param difficulty
     * @return the difficulty
     */
    public DifficultiesType setDifficulty(String difficulty) {
        switch (difficulty) {
            case "EASY":
                return DifficultiesType.EASY;
            case "MEDIUM":
                return DifficultiesType.MEDIUM;
            case "HARD":
                return DifficultiesType.HARD;
            default:
                return null;
        }
    }

    
    
}

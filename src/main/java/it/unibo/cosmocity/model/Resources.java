public enum Resources {

    FOOD,
    HEALTH,
    POPULATION,
    SECURITY;

    private int resourceValue;

    Resources(final int resourceValue){
        this.resourceValue = resourceValue;
    }

    public int getResourceValue() {
        return this.resourceValue;
    }

    public void setResourceValue(int resourceValue) {
        this.resourceValue = resourceValue;
    }
    
}

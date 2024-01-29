package it.unibo.cosmocity.model.settlers;

public enum SettlerType {

    FARMER("Farmer"),
    DOCTOR("Miner"),
    GUNSMITH("Gunsmith"),
    COOK("Cook"),
    MILITARY("Military"),
    TECHNICIAN("Technician"),
    PHARMACIST("Pharmacist"),
    BLACKSMITH("Blacksmith");

    private final String name;

    SettlerType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    
}

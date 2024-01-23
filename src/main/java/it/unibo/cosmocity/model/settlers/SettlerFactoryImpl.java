package it.unibo.cosmocity.model.settlers;

public class SettlerFactoryImpl implements SettlerFactory {

    /**
     * The function creates a new Settler object based on the given SettlerRole.
     * 
     * @param settlerRole The `settlerRole` parameter is an enum type representing
     *                    the role of the
     *                    settler. It can take one of the following values:
     *                    `FARMER`, `MECHANIC`, `MEDIC`, `MILITARY`,
     *                    `POLITICIAN`, `COOK`, `ENGINE
     * @return The method is returning a Settler object.
     */
    @Override
    public Settler createSettler(final SettlerRole settlerRole) {
        return switch (settlerRole) {
            case FARMER -> new Settler("Farmer", 10, 5);
            case MECHANIC -> new Settler("Mechanic", 10, 5);
            case MEDIC -> new Settler("Medic", 10, 5);
            case MILITARY -> new Settler("Military", 10, 5);
            case POLITICIAN -> new Settler("Politician", 10, 5);
            case COOK -> new Settler("Cook", 10, 5);
            case ENGINEER -> new Settler("Engineer", 10, 5);
            case BIOLOGIST -> new Settler("Biologist", 10, 5);
            default -> throw new IllegalArgumentException("Invalid settler role: " + settlerRole);
        };
    }

}

package cards.environment;

import gwentstone.GwentStone;

public interface EnvironmentCard {
    String CARDTYPE = "environment";

    /**
     * Executa abilitatea specifica fiecarei carti de tip "environment".
     *
     * @param gwentStone obiectul gwentStone
     * @param affectedRow indexul randului pe care se actioneaza abilitatea
     */
    void executeEnvironmentAbility(GwentStone gwentStone, int affectedRow);
}

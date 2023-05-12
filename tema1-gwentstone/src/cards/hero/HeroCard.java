package cards.hero;

import gwentstone.GwentStone;

public interface HeroCard {
    String CARDTYPE = "hero";

    /**
     * Executa abilitatea specifica fiecarui erou.
     *
     * @param gwentStone obiectul gwentStone
     * @param affectedRow indexul randului pe care se actioneaza abilitatea
     */
    void executeHeroAbility(GwentStone gwentStone, int affectedRow);
}

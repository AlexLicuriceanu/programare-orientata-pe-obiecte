package cards.minion;

import fileio.CardInput;
import gwentstone.GwentStone;

public interface MinionCard {
    String CARDTYPE = "minion";

    /**
     * Determina pe ce rand al mesei de joc trebuie plasata o carte.
     *
     * @return un string cu randul pe care trebuie plasata cartea.
     */
    String getRow();

    /**
     * Executa abilitatea specifica fiecarui minion.
     *
     * @param gwentStone obiectul gwentStone
     * @param attackedCard cartea asupra caruia se executa abilitatea
     * @param attackerCard cartea atacatorului
     */
    void executeMinionAbility(GwentStone gwentStone,
                              CardInput attackedCard,
                              CardInput attackerCard);
}

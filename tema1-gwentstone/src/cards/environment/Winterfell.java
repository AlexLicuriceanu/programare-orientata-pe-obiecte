package cards.environment;

import fileio.CardInput;
import gwentstone.GwentStone;

import java.util.ArrayList;

public final class Winterfell extends CardInput implements EnvironmentCard {
    public Winterfell(final CardInput environmentCard) {
        super(environmentCard);
        super.setCardType(EnvironmentCard.CARDTYPE);
    }

    /**
     * Toate cartile de pe rand stau o tura.
     * Flag-ul isFrozen este setat la true.
     *
     * @param gwentStone obiectul gwentStone
     * @param affectedRow indexul randului pe care se actioneaza abilitatea
     */
    public void executeEnvironmentAbility(final GwentStone gwentStone,
                                          final int affectedRow) {
        ArrayList<CardInput> row =
                gwentStone.getBoard().getBoard().get(affectedRow);

        // Se itereaza randul, se seteaza flag-ul cartilor isFrozen pe true.
        for (CardInput card : row) {
            card.setFreeze(true);
        }
    }
}

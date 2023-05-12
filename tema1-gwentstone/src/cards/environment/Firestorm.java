package cards.environment;

import fileio.CardInput;
import gwentstone.GwentStone;

import java.util.ArrayList;

public final class Firestorm extends CardInput implements EnvironmentCard {
    public Firestorm(final CardInput environmentCard) {
        super(environmentCard);
        super.setCardType(EnvironmentCard.CARDTYPE);
    }

    /**
     * Scade cu 1 viata tuturor cartilor de pe rand.
     *
     * @param gwentStone obiectul gwentStone
     * @param affectedRow indexul randului pe care se actioneaza abilitatea
     */
    public void executeEnvironmentAbility(final GwentStone gwentStone,
                                          final int affectedRow) {
        ArrayList<CardInput> row =
                gwentStone.getBoard().getBoard().get(affectedRow);

        // Se itereaza tot randul si se scade 1 din viata cartilor.
        for (CardInput card : row) {
            card.setHealth(card.getHealth() - 1);
        }

        // Se verifica daca exista carti care au fost omorate si se elimina de pe masa de joc.
        for (int i = 0; i < row.size(); i++) {
            if (row.get(i).getHealth() <= 0) {
                row.remove(i);
                i--;
            }
        }
    }
}

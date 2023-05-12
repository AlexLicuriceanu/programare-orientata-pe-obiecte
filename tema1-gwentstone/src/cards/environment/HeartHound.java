package cards.environment;

import commands.CommandController;
import fileio.CardInput;
import gwentstone.GwentStone;

import java.util.ArrayList;

public final class HeartHound extends CardInput implements EnvironmentCard {
    public HeartHound(final CardInput environmentCard) {
        super(environmentCard);
        super.setCardType(EnvironmentCard.CARDTYPE);
    }

    /**
     * Se fura minionul adversarului cu cea mai mare viata
     * si se pune pe randul simetric fata de centru.
     *
     * @param gwentStone obiectul gwentStone
     * @param affectedRow indexul randului pe care se actioneaza abilitatea
     */
    public void executeEnvironmentAbility(final GwentStone gwentStone,
                                          final int affectedRow) {
        ArrayList<CardInput> enemyRow =
                gwentStone.getBoard().getBoard().get(affectedRow);

        int stealCardIdx =
                CommandController.getMaxHealthCardIdxInRow(enemyRow);

        int rowNumber = getRowNumber(affectedRow);
        ArrayList<CardInput> myRow =
                gwentStone.getBoard().getBoard().get(rowNumber);

        myRow.add(enemyRow.remove(stealCardIdx));
    }

    private int getRowNumber(final int affectedRow) {
        return GwentStone.getMAXROWS() - affectedRow - 1;
    }
}

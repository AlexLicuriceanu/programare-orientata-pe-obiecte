package cards.hero;

import commands.CommandController;
import fileio.CardInput;
import gwentstone.GwentStone;

import java.util.ArrayList;

public final class EmpressThorina extends CardInput implements HeroCard {
    public EmpressThorina(final CardInput heroCard) {
        super(heroCard);
        super.setHealth(getHeroStartingHealth());
        super.setCardType(HeroCard.CARDTYPE);
    }

    /**
     * Cartea adversarului cu cea mai multa viata este stearsa.
     *
     * @param gwentStone obiectul gwentStone
     * @param affectedRow indexul randului pe care se actioneaza abilitatea
     */
    public void executeHeroAbility(final GwentStone gwentStone,
                                   final int affectedRow) {
        ArrayList<CardInput> enemyRow =
                gwentStone.getBoard().getBoard().get(affectedRow);

        int affectedCardIdx =
                CommandController.getMaxHealthCardIdxInRow(enemyRow);

        enemyRow.remove(affectedCardIdx);
    }
}

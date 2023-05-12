package cards.hero;

import commands.CommandController;
import fileio.CardInput;
import gwentstone.GwentStone;

import java.util.ArrayList;

public final class LordRoyce extends CardInput implements HeroCard {
    public LordRoyce(final CardInput heroCard) {
        super(heroCard);
        super.setHealth(getHeroStartingHealth());
        super.setCardType(HeroCard.CARDTYPE);
    }

    /**
     * Cartea cu cel mai mare atac de pe rand este inghetata.
     * Flag-ul isFrozen este setat la true.
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

        enemyRow.get(affectedCardIdx).setFreeze(true);
    }
}

package cards.hero;

import fileio.CardInput;
import gwentstone.GwentStone;

import java.util.ArrayList;

public final class GeneralKocioraw extends CardInput implements HeroCard {
    public GeneralKocioraw(final CardInput heroCard) {
        super(heroCard);
        super.setHealth(getHeroStartingHealth());
        super.setCardType(HeroCard.CARDTYPE);
    }

    /**
     * Toate cartile de pe rand primesc +1 atac.
     *
     * @param gwentStone obiectul gwentStone
     * @param affectedRow indexul randului pe care se actioneaza abilitatea
     */
    public void executeHeroAbility(final GwentStone gwentStone,
                                   final int affectedRow) {
        ArrayList<CardInput> row =
                gwentStone.getBoard().getBoard().get(affectedRow);

        int attackModifier = 1;

        for (CardInput card : row) {
            card.setAttackDamage(card.getAttackDamage() + attackModifier);
        }
    }
}

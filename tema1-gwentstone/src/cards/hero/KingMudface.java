package cards.hero;

import fileio.CardInput;
import gwentstone.GwentStone;

import java.util.ArrayList;

public final class KingMudface extends CardInput implements HeroCard {
    public KingMudface(final CardInput heroCard) {
        super(heroCard);
        super.setHealth(getHeroStartingHealth());
        super.setCardType(HeroCard.CARDTYPE);
    }

    /**
     * Toate cartile de pe rand primesc +1 viata.
     *
     * @param gwentStone obiectul gwentStone
     * @param affectedRow indexul randului pe care se actioneaza abilitatea
     */
    public void executeHeroAbility(final GwentStone gwentStone,
                                   final int affectedRow) {
        ArrayList<CardInput> row =
                gwentStone.getBoard().getBoard().get(affectedRow);

        int healthModifier = 1;

        for (CardInput card : row) {
            card.setHealth(card.getHealth() + healthModifier);
        }
    }
}

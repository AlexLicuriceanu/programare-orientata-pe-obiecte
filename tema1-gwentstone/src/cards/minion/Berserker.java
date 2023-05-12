package cards.minion;

import fileio.CardInput;
import gwentstone.GwentStone;

public final class Berserker extends CardInput implements MinionCard {
    private final String row = "back";

    public Berserker(final CardInput minionCard) {
        super(minionCard);
        super.setCardType(MinionCard.CARDTYPE);
    }

    @Override
    public String getRow() {
        return row;
    }

    /**
     * Executa abilitatea cartii Berserker.
     *
     * @param gwentStone obiectul gwentStone
     * @param attackedCard cartea asupra caruia se executa abilitatea
     * @param attackerCard cartea atacatorului
     */
    public void executeMinionAbility(
            final GwentStone gwentStone,
            final CardInput attackedCard,
            final CardInput attackerCard) {

    }
}

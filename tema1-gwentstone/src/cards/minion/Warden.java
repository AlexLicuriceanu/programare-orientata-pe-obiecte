package cards.minion;

import fileio.CardInput;
import gwentstone.GwentStone;

public final class Warden extends CardInput implements MinionCard {
    private final String row = "front";

    public Warden(final CardInput minionCard) {
        super(minionCard);
        super.setCardType(MinionCard.CARDTYPE);
        super.setSpecial("tank");
    }

    @Override
    public String getRow() {
        return row;
    }

    /**
     * Executa abilitatea lui Warden.
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

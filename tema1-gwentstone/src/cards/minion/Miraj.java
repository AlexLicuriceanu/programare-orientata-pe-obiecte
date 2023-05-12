package cards.minion;

import fileio.CardInput;
import gwentstone.GwentStone;

public final class Miraj extends CardInput implements MinionCard {
    private final String row = "front";

    public Miraj(final CardInput minionCard) {
        super(minionCard);
        super.setCardType(MinionCard.CARDTYPE);
    }

    @Override
    public String getRow() {
        return row;
    }

    /**
     * Executa abilitatea lui Miraj.
     * Viata cartii atacate este inversata cu viata cartii atacatorului.
     *
     * @param gwentStone obiectul gwentStone
     * @param attackedCard cartea asupra caruia se executa abilitatea
     * @param attackerCard cartea atacatorului
     */
    public void executeMinionAbility(
            final GwentStone gwentStone,
            final CardInput attackedCard,
            final CardInput attackerCard) {

        // Swap intre viata cartii atacate si viata cartii atacatorului.
        int auxHealth = attackedCard.getHealth();
        attackedCard.setHealth(attackerCard.getHealth());
        attackerCard.setHealth(auxHealth);
    }
}

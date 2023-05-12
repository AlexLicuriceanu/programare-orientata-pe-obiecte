package cards.minion;

import fileio.CardInput;
import gwentstone.GwentStone;

public final class Disciple extends CardInput implements MinionCard {
    private final String row = "back";

    public Disciple(final CardInput minionCard) {
        super(minionCard);
        super.setCardType(MinionCard.CARDTYPE);
    }

    @Override
    public String getRow() {
        return row;
    }

    /**
     * Executa abilitatea lui Disciple. Cartea influentata
     * primeste +2 viata.
     *
     * @param gwentStone obiectul gwentStone
     * @param attackedCard cartea asupra caruia se executa abilitatea
     * @param attackerCard cartea atacatorului
     */
    public void executeMinionAbility(
            final GwentStone gwentStone,
            final CardInput attackedCard,
            final CardInput attackerCard) {

        // Se adauga 2 viata cartii atacate.
        int abilityHealthPoints = 2;
        attackedCard.setHealth(attackedCard.getHealth()
                + abilityHealthPoints);
    }
}

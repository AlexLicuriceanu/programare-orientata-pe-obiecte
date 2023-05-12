package cards.minion;

import fileio.CardInput;
import gwentstone.GwentStone;

public final class TheRipper extends CardInput implements MinionCard {
    private final String row = "front";

    public TheRipper(final CardInput minionCard) {
        super(minionCard);
        super.setCardType(MinionCard.CARDTYPE);
    }

    @Override
    public String getRow() {
        return row;
    }

    /**
     * Executa abilitatea lui The Ripper.
     * Atacul cartii atacate este decrementat cu 2. Daca atacul scade sub 0,
     * acesta este resetat la 0.
     *
     * @param gwentStone obiectul gwentStone
     * @param attackedCard cartea asupra caruia se executa abilitatea
     * @param attackerCard cartea atacatorului
     */
    public void executeMinionAbility(
            final GwentStone gwentStone,
            final CardInput attackedCard,
            final CardInput attackerCard) {

        int abilityAttackPoints = 2;
        attackedCard.setAttackDamage(attackedCard.getAttackDamage() - abilityAttackPoints);

        if (attackedCard.getAttackDamage() < 0) {
            attackedCard.setAttackDamage(0);
        }
    }
}

package cards.minion;

import fileio.CardInput;
import gwentstone.GwentStone;

public final class TheCursedOne extends CardInput implements MinionCard {
    private final String row = "back";

    public TheCursedOne(final CardInput minionCard) {
        super(minionCard);
        super.setCardType(MinionCard.CARDTYPE);
    }

    @Override
    public String getRow() {
        return row;
    }

    /**
     * Executa abilitatea lui The Cursed One.
     * Viata si atacul cartii atacate sunt inversate.
     *
     * @param gwentStone obiectul gwentStone
     * @param attackedCard cartea asupra caruia se executa abilitatea
     * @param attackerCard cartea atacatorului
     */
    public void executeMinionAbility(
            final GwentStone gwentStone,
            final CardInput attackedCard,
            final CardInput attackerCard) {

        // Swap intre viata si atacul cartii.
        int auxHealth = attackedCard.getHealth();
        attackedCard.setHealth(attackedCard.getAttackDamage());
        attackedCard.setAttackDamage(auxHealth);
    }
}

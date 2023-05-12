package fileio;

import gwentstone.GwentStone;

import java.util.ArrayList;

public class CardInput {
    private int mana;
    private int attackDamage;
    private int health;
    private String description;
    private ArrayList<String> colors;
    private String name;
    private static final int HEROSTARTINGHEALTH = 30;
    private boolean isFrozen;
    private String cardType;
    private boolean hasAttacked;
    private String special;

    public CardInput() {
    }

    public CardInput(final int mana, final int attackDamage, final int health,
                     final String description,
                     final ArrayList<String> colors,
                     final String name) {
        this.mana = mana;
        this.attackDamage = attackDamage;
        this.health = health;
        this.description = description;
        this.colors = new ArrayList<>(colors);
        this.name = name;
        this.isFrozen = false;
        this.cardType = null;
        this.hasAttacked = false;
        this.special = null;
    }

    public CardInput(final CardInput card) {
        this.name = card.getName();
        this.colors = card.getColors();
        this.description = card.getDescription();
        this.health = card.getHealth();
        this.attackDamage = card.getAttackDamage();
        this.mana = card.getMana();
        this.isFrozen = false;
        this.cardType = null;
        this.hasAttacked = false;
        this.special = null;
    }

    public final int getMana() {
        return mana;
    }

    public final void setMana(final int mana) {
        this.mana = mana;
    }

    public final int getAttackDamage() {
        return attackDamage;
    }

    public final void setAttackDamage(final int attackDamage) {
        this.attackDamage = attackDamage;
    }

    public final int getHealth() {
        return health;
    }

    public final void setHealth(final int health) {
        this.health = health;
    }

    public final String getDescription() {
        return description;
    }

    public final void setDescription(final String description) {
        this.description = description;
    }

    public final ArrayList<String> getColors() {
        return colors;
    }

    public final void setColors(final ArrayList<String> colors) {
        this.colors = colors;
    }

    public final String getName() {
        return name;
    }

    public final void setName(final String name) {
        this.name = name;
    }

    public final boolean getFreeze() {
        return this.isFrozen;
    }

    public final void setFreeze(final boolean isFrozen) {
        this.isFrozen = isFrozen;
    }

    public static int getHeroStartingHealth() {
        return HEROSTARTINGHEALTH;
    }

    public final String getCardType() {
        return cardType;
    }

    public final void setCardType(final String cardType) {
        this.cardType = cardType;
    }

    @Override
    public final String toString() {
        return "CardInput{"
                +  "mana="
                + mana
                +  ", attackDamage="
                + attackDamage
                + ", health="
                + health
                +  ", description='"
                + description
                + '\''
                + ", colors="
                + colors
                + ", name='"
                +  ""
                + name
                + '\''
                + '}';
    }

    /**
     * Determina pe ce rand al mesei de joc trebuie plasata o carte.
     *
     * @return un string cu randul pe care trebuie plasata cartea.
     */

    public String getRow() {
        return null;
    }

    /**
     * Executa abilitatea cartilor minion.
     * @param gwentStone obiectul gwentStone
     * @param attackedCard cartea atacata
     * @param attackerCard cartea atacatorului
     */
    public void executeMinionAbility(
            final GwentStone gwentStone,
            final CardInput attackedCard,
            final CardInput attackerCard) {
    }

    /**
     * Executa abilitatea cartilor environment.
     * @param gwentStone obiectul gwentStone
     * @param affectedRow randul afectat
     */
    public void executeEnvironmentAbility(final GwentStone gwentStone,
                                                final int affectedRow) {
    }

    /**
     * Executa abilitatea cartilor erou.
     * @param gwentStone obiectul gwentStone
     * @param affectedRow randul afectat
     */
    public void executeHeroAbility(final GwentStone gwentStone,
                                         final int affectedRow) {
    }

    public final boolean getHasAttacked() {
        return this.hasAttacked;
    }

    public final void setHasAttacked(final boolean hasAttacked) {
        this.hasAttacked = hasAttacked;
    }

    public final String getSpecial() {
        return special;
    }
    public final void setSpecial(final String special) {
        this.special = special;
    }
}

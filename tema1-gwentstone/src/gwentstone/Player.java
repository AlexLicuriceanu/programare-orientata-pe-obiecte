package gwentstone;

import cards.environment.Firestorm;
import cards.environment.HeartHound;
import cards.environment.Winterfell;
import cards.minion.*;
import fileio.CardInput;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public final class Player {
    private int mana;           // Mana jucatorului.
    private int nrDecks;        // Numarul de pachete.
    private int nrCardsInDeck;  // Numarul de carti in feicare pachet.

    private final ArrayList<CardInput> hand;    // Cartile din mana.
    private final ArrayList<CardInput> deck;    // Pachetul de carti dupa amestecare.


    public Player(final int deckIdx, final int nrDecks,
                  final int nrCardsInDeck,
                  final ArrayList<ArrayList<CardInput>> startingDecks,
                  final int seed) {
        this.nrDecks = nrDecks;
        this.nrCardsInDeck = nrCardsInDeck;
        this.hand = new ArrayList<>();
        this.deck = new ArrayList<>();
        this.mana = 1;

        for (CardInput card : startingDecks.get(deckIdx)) {
            deck.add(createCard(card));
        }

        Collections.shuffle(deck, new Random(seed));    // Randomizarea pachetului.
        this.hand.add(this.deck.remove(0));       // Prima carte trece in mana.
    }

    private CardInput createCard(final CardInput card) {
        return switch (card.getName()) {
            case "Firestorm" -> new Firestorm(card);
            case "Heart Hound" -> new HeartHound(card);
            case "Winterfell" -> new Winterfell(card);
            case "Berserker" -> new Berserker(card);
            case "Disciple" -> new Disciple(card);
            case "Goliath" -> new Goliath(card);
            case "Miraj" -> new Miraj(card);
            case "Sentinel" -> new Sentinel(card);
            case "The Cursed One" -> new TheCursedOne(card);
            case "The Ripper" -> new TheRipper(card);
            case "Warden" -> new Warden(card);
            default -> null;
        };
    }

    public int getMana() {
        return mana;
    }

    public void setMana(final int mana) {
        this.mana = mana;
    }

    public int getNrCardsInDeck() {
        return nrCardsInDeck;
    }

    public void setNrCardsInDeck(final int nrCardsInDeck) {
        this.nrCardsInDeck = nrCardsInDeck;
    }

    public int getNrDecks() {
        return nrDecks;
    }

    public void setNrDecks(final int nrDecks) {
        this.nrDecks = nrDecks;
    }

    public ArrayList<CardInput> getHand() {
        return hand;
    }

    public ArrayList<CardInput> getDeck() {
        return deck;
    }

    /**
     * Adauga in mana urmatoarea carte din pachet.
     */
    public void addCardInHand() {
        if (this.deck.size() > 0) {
            this.hand.add(this.deck.remove(0));
        }
    }
}

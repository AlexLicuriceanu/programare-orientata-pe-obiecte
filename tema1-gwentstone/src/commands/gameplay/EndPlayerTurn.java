package commands.gameplay;

import com.fasterxml.jackson.databind.node.ArrayNode;
import commands.Command;
import fileio.CardInput;
import gwentstone.GwentStone;

public final class EndPlayerTurn implements Command {
    /**
     * Executa comanda pentru a sfarsi tura jucatorului curent.
     * <p>Daca indicele jucatorului curent este 1, jucatorul curent devine
     * 2, cartile lui 2 sunt dezghetate si cartile lui 1 au cooldown-ul de atac/abilitate
     * resetat. In mod asemanator se procedeaza si in cazul in care jucatorul curent este
     * 1.</p>
     * <p></p>
     * <p>Se verifica si de asemenea daca amandoi jucatorii au completat o tura. Daca da,
     * se incrementeaza numarul rundei si se apeleaza functia nextRound() care face ca
     * fiecare jucator sa primeasca mana si sa traga o carte noua din pachet.</p>
     * @param output fisierul json
     * @param gwentStone obiectul gwentStone
     */
    public void executeCommand(final ArrayNode output,
                               final GwentStone gwentStone) {
        if (gwentStone.getPlayerTurn() == 1) {
            gwentStone.setPlayerTurn(2);
            unfreezeCards(gwentStone, 2);
            resetHasAttacked(gwentStone, 1);

            if (gwentStone.getStartingPlayer() == 2) {
                gwentStone.setRoundNumber(gwentStone.getRoundNumber() + 1);
                nextRound(gwentStone);
            }
        } else if (gwentStone.getPlayerTurn() == 2) {
            gwentStone.setPlayerTurn(1);
            unfreezeCards(gwentStone, 1);
            resetHasAttacked(gwentStone, 2);

            if (gwentStone.getStartingPlayer() == 1) {
                gwentStone.setRoundNumber(gwentStone.getRoundNumber() + 1);
                nextRound(gwentStone);
            }
        }
    }

    private void nextRound(final GwentStone gwentStone) {
        gwentStone.setRoundNumber(gwentStone.getRoundNumber());
        gwentStone.getPlayerOne().addCardInHand();
        gwentStone.getPlayerTwo().addCardInHand();

        if (gwentStone.getRoundNumber() < GwentStone.getMAXMANA()) {
                gwentStone.getPlayerOne().setMana(
                        gwentStone.getPlayerOne().getMana()
                                + gwentStone.getRoundNumber());

                gwentStone.getPlayerTwo().setMana(
                        gwentStone.getPlayerTwo().getMana()
                                + gwentStone.getRoundNumber());
        } else {
            gwentStone.getPlayerOne().setMana(
                    gwentStone.getPlayerOne().getMana()
                            + GwentStone.getMAXMANA());
            gwentStone.getPlayerTwo().setMana(
                    gwentStone.getPlayerTwo().getMana()
                            + GwentStone.getMAXMANA());
        }
    }

    /**
     * Parcurge toate cartile de pe masa ale jucatorului cu index-ul dat ca
     * parametru si le seteaza flag-ul isFrozen la false.
     * @param gwentStone obiectul gwentStone
     * @param playerIdx index-ul jucatorului
     */
    private void unfreezeCards(final GwentStone gwentStone,
                               final int playerIdx) {
        if (playerIdx == 1 && gwentStone.getBoard().getBoard().size() != 0) {
            for (CardInput card : gwentStone.getBoard().getBoard().get(0)) {
                card.setFreeze(false);
            }
            for (CardInput card : gwentStone.getBoard().getBoard().get(1)) {
                card.setFreeze(false);
            }
        }
        if (playerIdx == 2 && gwentStone.getBoard().getBoard().size() != 0) {
            for (CardInput card : gwentStone.getBoard().getBoard().get(2)) {
                card.setFreeze(false);
            }
            for (CardInput card : gwentStone.getBoard().getBoard().get(3)) {
                card.setFreeze(false);
            }
        }
    }

    /**
     * Parcurge toate cartile de pe masa ale jucatorului cu index-ul dat ca
     * parametru si le seteaza flag-ul hasAttacked la false. De asemenea,
     * flag-ul hasAttacked al eroului este setat la false.
     * @param gwentStone obiectul gwentStone
     * @param playerIdx index-ul jucatorului
     */
    private void resetHasAttacked(final GwentStone gwentStone,
                                  final int playerIdx) {
        if (playerIdx == 1) {
            for (int i = 2; i < GwentStone.getMAXROWS(); i++) {
                for (CardInput card : gwentStone.getBoard().getBoard().get(i)) {
                    card.setHasAttacked(false);
                }
            }
        } else if (playerIdx == 2) {
            for (int i = 0; i < GwentStone.getMAXROWS() / 2; i++) {
                for (CardInput card : gwentStone.getBoard().getBoard().get(i)) {
                    card.setHasAttacked(false);
                }
            }
        }
        gwentStone.getBoard().getPlayerHero(playerIdx).setHasAttacked(false);
    }
}

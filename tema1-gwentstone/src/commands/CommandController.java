package commands;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import commands.debug.*;
import commands.gameplay.*;
import fileio.ActionsInput;
import fileio.CardInput;
import gwentstone.GwentStone;

import java.util.ArrayList;

public final class CommandController {
    private ArrayList<ActionsInput> actions;
    private final ArrayList<Command> commands;
    private final GwentStone gwentStone;

    /* O solutie nu tocmai eleganta. As fi vrut sa evit un asemenea design in favoarea unui
     * HashMap (sau ceva similar) pentru a face ceea ce face acel switch, insa nu am mai avut
     * timp pentru o implementare care sa si mearga... sorry :)
     */
    public CommandController(final GwentStone gwentStone) {
        this.actions = gwentStone.getActions();
        this.gwentStone = gwentStone;
        this.commands = new ArrayList<>();

        for (ActionsInput action : this.actions) {
            switch (action.getCommand()) {
                case "getPlayerTurn" -> this.commands.add(
                        new GetPlayerTurn());
                case "getPlayerDeck" -> this.commands.add(
                        new GetPlayerDeck(gwentStone, action.getPlayerIdx()));
                case "getPlayerHero" -> this.commands.add(
                        new GetPlayerHero(gwentStone, action.getPlayerIdx()));
                case "endPlayerTurn" -> this.commands.add(
                        new EndPlayerTurn());
                case "getCardsInHand" -> this.commands.add(
                        new GetCardsInHand(gwentStone, action.getPlayerIdx()));
                case "placeCard" -> this.commands.add(
                       new PlaceCard(action.getHandIdx()));
                case "getPlayerMana" -> this.commands.add(
                        new GetPlayerMana(action.getPlayerIdx()));
                case "getCardsOnTable" -> this.commands.add(
                        new GetCardsOnTable());
                case "getEnvironmentCardsInHand" -> this.commands.add(
                        new GetEnvironmentCardsInHand(
                                gwentStone, action.getPlayerIdx()));
                case "useEnvironmentCard" -> this.commands.add(
                        new UseEnvironmentCard(action.getHandIdx(),
                                action.getAffectedRow()));
                case "getCardAtPosition" -> this.commands.add(
                        new GetCardAtPosition(action.getX(), action.getY()));
                case "getFrozenCardsOnTable" -> this.commands.add(
                        new GetFrozenCardsOnTable());
                case "cardUsesAttack" -> this.commands.add(
                        new CardUsesAttack(action.getCardAttacker(),
                                action.getCardAttacked()));
                case "cardUsesAbility" -> this.commands.add(
                        new CardUsesAbility(action.getCardAttacker(),
                                action.getCardAttacked()));
                case "useAttackHero" -> this.commands.add(
                        new UseAttackHero(action.getCardAttacker()));
                case "useHeroAbility" -> this.commands.add(
                        new UseHeroAbility(action.getAffectedRow()));
                case "getPlayerOneWins" -> this.commands.add(
                        new GetPlayerOneWins());
                case "getPlayerTwoWins" -> this.commands.add(
                        new GetPlayerTwoWins());
                case "getTotalGamesPlayed" -> this.commands.add(
                        new GetTotalGamesPlayed());
                default -> {
                }
            }
        }
    }

    /**
     * Itereaza lista de comenzi creata in constructor si o executa pe fiecare.
     */
    public void executeCommands() {
        for (Command command : commands) {
            command.executeCommand(gwentStone.getOutputFile(), gwentStone);
        }
    }

    /**
     * Intoarce valoarea flag-ului isFrozen pentru o carte deta ca parametru.
     * @param card carte
     * @return true daca este ingehtata, false in caz contrar
     */
    public static boolean isFrozen(final CardInput card) {
        return card.getFreeze();
    }

    /**
     * Intoarce valoarea flag-ului hasAttacked pentru o carte deta ca parametru.
     * @param card carte
     * @return true daca a atacat deja in tura curenta, false in caz contrar
     */
    public static boolean hasAttacked(final CardInput card) {
        return card.getHasAttacked();
    }

    /**
     * Verifica daca perechea de coordonate apartine unei carti a adversarului.
     * @param playerTurn indexul jucatorului care ataca
     * @param attackedX coordonata X a cartii atacate
     * @param attackedY coordonata Y a cartii atacate
     * @return true daca apartine inamicului, false daca nu apratine
     */
    public static boolean isEnemyCard(final int playerTurn,
                                      final int attackedX,
                                      final int attackedY) {
        if (playerTurn == 1 && (attackedX == 0 || attackedX == 1)) {
            return true;
        } else {
            return playerTurn == 2 && (attackedX == 2 || attackedX == 3);
        }
    }

    /**
     * Verifica daca este de tipul "tank" cartea primita ca parametru.
     * @param card cartea de verificat
     * @return true daca este "tank", false daca nu este "tank"
     */
    public static boolean isTank(final CardInput card) {
        try {
            return card.getSpecial().equals("tank");
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Returneaza prima carte a inamicului de tipul "tank", parcurgand
     * masa de joc sus-jos, stanga-dreapta.
     * @param board masa de joc
     * @param playerTurn indexul jucatorului curent
     * @return prima carte "tank" gasita
     */
    public static CardInput getFirstTankCard(
            final ArrayList<ArrayList<CardInput>> board,
            final int playerTurn) {
        if (playerTurn == 1) {
            for (int i = 0; i < GwentStone.getMAXROWS() / 2; i++) {
                for (CardInput card : board.get(i)) {
                    if (CommandController.isTank(card)) {
                        return card;
                    }
                }
            }
        } else if (playerTurn == 2) {
            for (int i = GwentStone.getMAXROWS() / 2;
                 i < GwentStone.getMAXROWS(); i++) {
                for (CardInput card : board.get(i)) {
                    if (CommandController.isTank(card)) {
                        return card;
                    }
                }
            }
        }
        return null;
    }

    /**
     * Verifica daca indexul randului pasat ca parametru este sau nu
     * al adversarului.
     * @param playerTurn indexul jucatorului curent
     * @param affectedRow indexul randului de verificat
     * @return true daca randul este al inamicului, false in caz contrar
     */
    public static boolean checkRow(final int playerTurn,
                                   final int affectedRow) {
        if (playerTurn == 2 && (affectedRow == 2 || affectedRow == 3)) {
            return true;
        }
        return playerTurn == 1 && (affectedRow == 0 || affectedRow == 1);
    }

    /**
     * Gaseste index-ul cartii cu viata maxima de pe randul transmis ca parametru.
     * @param row randul in care se cauta
     * @return index-ul cartii care are viata maxima
     */
    public static int getMaxHealthCardIdxInRow(
            final ArrayList<CardInput> row) {
        int cardIdx = -1;
        int maxHealth = -1;
        for (CardInput card : row) {
            if (card.getHealth() > maxHealth) {
                maxHealth = card.getHealth();
                cardIdx = row.indexOf(card);
            }
        }
        return cardIdx;
    }

    /**
     * Creeaza nodul json pentru afisarea proprietatilor unei carti.
     * @param mapper mapper-ul json
     * @param card cartea de afisat
     * @return nod json
     */
    public static ObjectNode createCardNode(final ObjectMapper mapper,
                                            final CardInput card) {
        ObjectNode node1 = mapper.createObjectNode();

        node1.put("mana", card.getMana());
        node1.put("attackDamage", card.getAttackDamage());
        node1.put("health", card.getHealth());
        node1.put("description", card.getDescription());

        ArrayNode colors = mapper.createArrayNode();
        for (String color : card.getColors()) {
            colors.add(color);
        }
        node1.set("colors", colors);
        node1.put("name", card.getName());
        return node1;
    }

    /**
     * Creeaza nodul json pentru afisarea proprietatilor unei carti, fara
     * campurile de attackDamage si health, in cazul in care health este 0
     * sau mai mic.
     * @param mapper mapper-ul json
     * @param card cartea de afisat
     * @return nod json
     */
    public static ObjectNode createCardNodeNoHealth(final ObjectMapper mapper,
                                                    final CardInput card) {
        ObjectNode node1 = mapper.createObjectNode();
        node1.put("mana", card.getMana());
        if (card.getHealth() != 0) {
            node1.put("attackDamage", card.getAttackDamage());
            node1.put("health", card.getHealth());
        }
        node1.put("description", card.getDescription());

        ArrayNode colorsNode = mapper.createArrayNode();
        for (String color : card.getColors()) {
            colorsNode.add(color);
        }
        node1.put("colors", colorsNode);
        node1.put("name", card.getName());
        return node1;
    }

    /**
     * Creeaza nodul json pentru afisarea proprietatilor cartii erou.
     * @param mapper mapper-ul json
     * @param card cartea de afisat
     * @return nod json
     */
    public static ObjectNode createHeroCardNode(final ObjectMapper mapper,
                                                final CardInput card) {
        ObjectNode node1 = mapper.createObjectNode();
        node1.put("mana", card.getMana());
        node1.put("description", card.getDescription());

        ArrayNode colors = mapper.createArrayNode();
        for (String color : card.getColors()) {
            colors.add(color);
        }
        node1.set("colors", colors);
        node1.put("name", card.getName());
        node1.put("health", card.getHealth());
        return node1;
    }

    public ArrayList<ActionsInput> getActions() {
        return actions;
    }

    public void setActions(final ArrayList<ActionsInput> actions) {
        this.actions = actions;
    }

    public GwentStone getGwentStone() {
        return gwentStone;
    }
}

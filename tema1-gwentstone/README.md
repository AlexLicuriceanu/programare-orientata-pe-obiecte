```
test01_game_start.json --------------------------------------------------------------- PASSED (+3)
test02_place_card.json --------------------------------------------------------------- PASSED (+4)
test03_place_card_invalid.json ------------------------------------------------------- PASSED (+4)
test04_use_environment_card.json ----------------------------------------------------- PASSED (+4)
test05_use_environment_card_invalid.json --------------------------------------------- PASSED (+4)
test06_attack_card.json -------------------------------------------------------------- PASSED (+4)
test07_attack_card_invalid.json ------------------------------------------------------ PASSED (+4)
test08_use_card_ability.json --------------------------------------------------------- PASSED (+4)
test09_use_card_ability_invalid.json ------------------------------------------------- PASSED (+4)
test10_attack_hero.json -------------------------------------------------------------- PASSED (+4)
test11_attack_hero_invalid.json ------------------------------------------------------ PASSED (+4)
test12_use_hero_ability_1.json ------------------------------------------------------- PASSED (+4)
test13_use_hero_ability_2.json ------------------------------------------------------- PASSED (+4)
test14_use_hero_ability_1_invalid.json ----------------------------------------------- PASSED (+4)
test15_use_hero_ability_2_invalid.json ----------------------------------------------- PASSED (+4)
test16_multiple_games_valid.json ----------------------------------------------------- PASSED (+5)
test17_multiple_games_invalid.json --------------------------------------------------- PASSED (+6)
test18_big_game.json ----------------------------------------------------------------- PASSED (+10)
-----------------------------------------------------
TESTS = 80/80
-----------------------------------------------------
Checkstyle: Ok
Checkstyle errors: 10
-----------------------------------------------------
CHECKSTYLE = 10/10
-----------------------------------------------------
.GIT score = 5/5
-----------------------------------------------------
README score = 5/5
-----------------------------------------------------
FINAL SCORE = 100/100
```

# Tema POO  - GwentStone

Metodele sunt descrise mai detaliat in javadoc-urile lor in
fisierele sursa.

Pentru fiecare meci sunt instantiate obiecte de tipul GwentStone,
CommandController si StatController. Comenzile se executa cu
metoda executeCommands() din obiectul lui CommandController.

## Obiectul de tip "GwentStone"
Contine toate datele necesare pentru a rula un meci: carti, jucatori,
comenzi etc. In cadrul acestui obiect se gasesc si obiectele
jucatorilor si al mesei de joc.

## Obiectul de tip "CommandController"
La initializarea obiectului de tip CommandController, fiecare comanda
va fi instantiata cu un obiect propriu, similar unui command pattern.

Metoda executeCommands() a lui CommandController executa toate comenzile
care au fost primite in fisierul de input.

## Obiectul de tip "StatController"
Este un singleton care salveaza numarul de victorii ale jucatorilor si 
este resetat la fiecare set nou de test.

## Cartile environment, hero si minion
Fiecare tip de carte implementeaza o interfata cu metode care executa
abilitatile speciale (daca acestea exista) ale cartilor.

## Comenzile de gameplay si de debug
Aproape toate comenzile de gameplay si debug au edge case-uri care au 
fost tratate asa cum este precizat in cerinta temei, afisand in 
fisierele json erorile aferente.



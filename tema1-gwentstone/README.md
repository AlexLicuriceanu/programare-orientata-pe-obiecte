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



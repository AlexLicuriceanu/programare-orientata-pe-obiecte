Nume: Alexandru Licuriceanu
Grupă: 325CD

# Proiect POO Etapa 1 - POO TV 

Organizare & Implementare
-
Toate datele de intrare precum filme, utilizatori si comenzi sunt incarcate in Singleton-uri care se comporta ca niste baze de date. Actiunile sunt citite din fisiere si sunt transformate in comenzi care se executa apoi una cate una. La finalul fiecarui set de test este resetat Singleton-ul care contine celelalte doua Singleton-uri pentru bazele de date ale filmelor si utilizatorilor, implicit stergand toate filmele si toti utilizatorii din testul anterior. In clasa Constants am pus diferite constante pe care le-am folosit de-a lungul implementarii.

Pentru generearea comenzilor si a paginilor am folosit design pattern-ul factory iar fiecare comanda si pagina este implementata intr-o clasa separata. 
Pentru navigare pe pagini, la instantierea fiecarei clase de pagina, se vor insera intr-o lista paginile catre care un utilizator poate naviga si comenzile care se pot executa pe pagina respectiva.

Mult mai multe detalii de implementare se afla in codul sursa. readme scurt, criza de timp

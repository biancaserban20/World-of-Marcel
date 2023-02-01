# World-of-Marcel

CUPRINS
• Introducere
• Realizarea proiectului

INTRODUCERE
Din punctul meu de vedere, tema a avut un nivel de dificultate mediu. Durata de rezolvare a temei a fost de aproximativ o saptamana (recunosc, nu am lucrat consecvent).
Mi-a placut sa rezolv aceasta tema doarece am putut sa imi folosesc cunostiintele de programare in Java acumulate pana acum la cursul si laboratoarele de POO, dar si pe cele dobandite prin studiu individual. Consider ca aceasta tema mi-a fost de ajutor in intelegerea Paterns-urilor si ma bucur ca mi-am pus la punct anumite informatii pe care la curs nu le intelesesem chiar atat de bine.

REALIZAREA TEMEI
In ceea ce priveste rezolvarea temei, am implementat atat clasele si metodele din cerinte, cat si altele auxiliare care mi-au facut realizarea anumitor cerinte mai usoara.
Detalii legate de implementare:
• Clasa Game:
- Am implementat mai multe metode care sa intoarca diferite obiecte (ex: Credentials, Account etc) pentru ca functia run() sa fie mai usor de vizualizat
- Am tratat 2 scenarii: cazul in care utilizatorul doreste sa foloseasca o harta generata de calculator si cazul in care el doreste sa joace conform scenariului hardcodat, dat ca exemplu
in enuntul temei
• Clasa Grid:
- Am implementat o functie generateHardCodedScenario() care genereaza o harta exact ca cea din imaginea de mai jos:
![image](https://user-images.githubusercontent.com/95043563/216064593-321963f3-ca23-4800-af77-7b4a53aac7b4.png)
- Si o functie printGrid() pentru afisarea hartii.
• Pentru restul claselor am implementat numai metodele specificate in cerinta, insa la cele
care se cerea generarea aleatoare, am implementat metoda getRandomNumberInRange()
care returneaza un nr. intreg intr-un interval de doua nr. intregi;
• Am create doua clase suplimentare (CFactory si InformationBuilder) care m-au ajutat in
realizarea patterns-urilor Factory si Builder.

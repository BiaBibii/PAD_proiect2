# PAD_proiect2
Cantina UPT


- permite utilizatorilor (clientilor)
    • sa vada
    • sa aleaga
    • sa comande
    • sa plateasca mancare.
- Administratorul poate sa:
    • adauge
    • modifice
    • stearga produsele.
- utilizatorii se vor conecta la paltforma cu ajutorul unei aplicatii web (Angular).
- utilizatorii vor putea sa se inregistreze (daca nu au cont inca creat), sa se conecteze, sa isi schimbe parola. (Parolele vor fi criptate).

Serverul este o aplicatie Java construita cu ajutor framework-ului Spring. Va furniza servicii de inregistrare, afisarea produselor, etc. cu ajutorul REST endpoints-urilor. 
Acesta este primul server pe care il folosim, este serverul principal.

Pentru al doilea server am folosit Kafka, care monitorizează comenzile primite și le afișează prin intermediul unui consumer.

Datele vor fi stocate intr-o baza de date (Sql).



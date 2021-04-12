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
- Comenzile vor fi acceptate de un manager de la cantina.
- 
- utilizatorii se vor conecta la paltforma cu ajutorul unei aplicatii web (Angular).
- utilizatorii vor putea sa se inregistreze (daca nu au cont inca creat), sa se conecteze, sa isi schimbe parola. (Parolele vor fi criptate).

Serverul este o aplicatie Java construita cu ajutor framework-ului Spring. Va furniza servicii de inregistrare, afisarea produselor, etc. cu ajutorul REST endpoints-urilor.

Pentru a evita supraincarcarea serverului, atunci cand numarul comenzilor depaseste maximul posibil (impus de noi), serverul  gateway se va conecta la brokerul Kafka, care va retine informatia comenzilor pana se elibereaza serverul.

Datele vor fi stocate intr-o baza de date (Sql).



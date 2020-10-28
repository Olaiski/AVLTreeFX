# AVLTreeFX
AVL tree animation (JavaFX) - Obligatorisk Oppgave


Lite program som lar deg sette inn, slette og finne elementer i et AVL-tre. Generic, men test applikasjonen bruker Integers.<br/> 

Det skal utviklast ein applikasjon der brukaren kan legge inn tal i eit AVL-tre. For kvar endring skal treet teiknast i applikasjonen.<br/>
<b>Oppgave tekst:</b><br/>
Det er altså to hovuddelar. Den eine er å implementere ei generisk AVL-klasse som held treet balansert under innsetting. Dette er avskrift av AVLTree i læreboka. Den andre delen er ein grafisk applikasjon som opprettar ein instans av AVL-klassa, som gjer det mogeleg å legge inn verdiar i treet, og som heile tida teiknar ut treet. Du finn mykje kode i kapittel 25 som kan nyttast.

Du skal fylgje MVC-prinsippet (Model-View-Controller) på det viset at AVLTree og AVLTreeNode skal ikkje drive operasjonar mot det grafiske grensesnittet. Dersom du treng å "opne" klassene noko for å få teikna ut så kan du det, men pass på at du ikkje opnar meire enn naudsynt.

Applikasjonen skal ha stor teikneflate der treet teiknast ut, verdien i noden teiknast inni ein sirkel, sjå figur 25.15 i læreboka. Applikasjonen skal opprette eit tre av Integer, men tre-klassa skal altså vera generisk. Applikasjonen skal ha desse funksjonane:

- Søk: Brukar skriv eit tal i eit tekstfelt, programmet rapporterer om det finn talet i treet.
- Innsetting: Brukar skriv eit tal i eit tekstfelt, talet leggast inn i treet, og teikninga oppdaterast. Rapporter om vellykka eller feil - duplikat.
- Sletting: Brukar skriv eit tal i eit tekstfelt, talet slettast frå treet, og teikninga oppdaterast. Rapporter om vellykka eller feil - finn ikkje verdien.
- For rask testing lag ein knapp som legg inn 10 tilfeldige verdiar i treet. Teikninga oppdaterast.
- Treet skal også kunne aksesserast etter nummerering. Brukar skriv inn eit tal, t.d. 5, og programmet finn det 5. minste talet og rapporterer dette. Det skal skje med O(logN)-algoritme    Kvar node må altså ha ein "size" og denne må vedlikehaldast ved innsetting og sletting




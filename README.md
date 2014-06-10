JavaGro
=======

Java-reader til Gyldendals ordb�ger

Dette projekt er en videref�rsel af Athas-crew'ets arbejde med at lave en reader af Gyldendals ordb�ger, der fungerer p� andet end Windows og Mac:
https://github.com/Athas/spt-gro

I et andet projekt har jeg udvidet det til at kunne h�ndtere flere ordb�ger:
https://github.com/ejvindh/spt-gro

I dette projekt har jeg oversat Python-koden til Java-kode.

Dette projekt foruds�tter Java8 for at kunne fungere.

Dette projekt kan h�ndtere de ordb�ger, der findes p�:
http://ordbog.gyldendal.dk/
Det er testet p� "Download" ordb�gerne, s� hvis du i forvejen har en CDrom el.lign med ordb�gerne, er det ikke sikkert, at du kan bruge databaserne derfra -- i hvert fald ikke hvis det er en �ldre udgave.

I skrivende stund findes der ordb�ger engelsk, tysk, fransk, spansk, italiensk, svensk, norsk, engelsk (fag/teknik), stor dansk-engelsk, stor engelsk-dansk, retskrivning, fremmedord, synonymer, dansk. Alle disse kan h�ndteres af dette projekt.

For at k�re programmet, skal du
(1) Hente JavaGro.jar ned p� din computer.

(2) Kopiere ordbogsfilerne over i en undermappe til der hvor JavaGro ligger. Mappen skal hedde "data". For at finde disse filer, er du n�dt til at have installeret den p�g�ldende ordbog p� en windows-computer (eller Wine i Linux). Herefter kan du finde dem i mappen %Program Files%Gyldendal/R�de Ordb�ger/data -- det drejer sig om to filer for hver ordbog, og de har fil-endelserne *.dat og *.gdd.

(3) S� er du klar til at k�re programmet. Det startes med kommandoen:
java -jar JavaGro.jar

God arbejdslyst. Ligesom Athas vil jeg ogs� gerne understrege, at det er strengt ulovligt at piratkopiere ordbogen. Hvis du kan lide sproget, s� k�b det! Dette program er udelukkende t�nkt til at hj�lpe folk til at kunne migrere til andre styresystemer end Windows og Mac -- uden at skulle miste adgangen til deres legalt erhvervede software.

//ejvindh

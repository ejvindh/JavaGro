JavaGro
=======

Java-reader til Gyldendals ordbøger

Dette projekt er en videreførsel af Athas-crew'ets arbejde med at lave en reader af Gyldendals ordbøger, der fungerer på andet end Windows og Mac:
https://github.com/Athas/spt-gro

I et andet projekt har jeg udvidet det til at kunne håndtere flere ordbøger:
https://github.com/ejvindh/spt-gro

I dette projekt har jeg oversat Python-koden til Java-kode.

Dette projekt forudsætter Java8 for at kunne fungere.

Dette projekt kan håndtere de ordbøger, der findes på:
http://ordbog.gyldendal.dk/
Det er testet på "Download" ordbøgerne, så hvis du i forvejen har en CDrom el.lign med ordbøgerne, er det ikke sikkert, at du kan bruge databaserne derfra -- i hvert fald ikke hvis det er en ældre udgave.

I skrivende stund findes der ordbøger engelsk, tysk, fransk, spansk, italiensk, svensk, norsk, engelsk (fag/teknik), stor dansk-engelsk, stor engelsk-dansk, retskrivning, fremmedord, synonymer, dansk. Alle disse kan håndteres af dette projekt.

For at køre programmet, skal du

(1) Hente JavaGro.jar ned på din computer.

(2) Kopiere ordbogsfilerne over i en undermappe til der hvor JavaGro ligger. Mappen skal hedde "data". For at finde disse filer, er du nødt til at have installeret den pågældende ordbog på en windows-computer (eller Wine i Linux). Herefter kan du finde dem i mappen %Program Files%Gyldendal/Røde Ordbøger/data -- det drejer sig om to filer for hver ordbog, og de har fil-endelserne *.dat og *.gdd.

(3) Så er du klar til at køre programmet. Det startes med kommandoen:
java -jar JavaGro.jar

God arbejdslyst. Ligesom Athas vil jeg også gerne understrege, at det er strengt ulovligt at piratkopiere ordbogen. Hvis du kan lide sproget, så køb det! Dette program er udelukkende tænkt til at hjælpe folk til at kunne migrere til andre styresystemer end Windows og Mac -- uden at skulle miste adgangen til deres legalt erhvervede software.

//ejvindh

v1.2.2: Juni 2021
-- Bugfix: Mere effektiv ctrl-l tastaturgenvej.

v1.2: Oktober/November 2014
-- Bugfix: Bedre søgning på internationale bogstaver
-- Bugfix: Indføre manglende mellemrum i visse opslag i franske ordbøger
-- Indført tastatur-genveje

v1.1: Juni 2014:
-- Emigrere til JavaFX
-- Mulighed for at gemme sidestørrelse, placering af vindue
-- Mulighed for at forstørre/formindske tekst-størrelse
-- Mulighed for at copy/paste uden formatering
-- Shortcut til at komme op i søgefeltet (cmd-s)

v1.0: Sommer 2013:
-- Første version, kører GUI-mæssigt på SWT, hvor jeg bundler pakker til 6 styresystemer (mac, lin, win, 32/64bit)
-- De facto kan jeg ikke få det til at virke i mac

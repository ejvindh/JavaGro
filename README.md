<<<<<<< HEAD
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

(2) Så er du klar til at køre programmet. Det startes med kommandoen:
java -jar JavaGro.jar

God arbejdslyst. Ligesom Athas vil jeg også gerne understrege, at det er strengt ulovligt at piratkopiere ordbogen. Hvis du kan lide sproget, så køb det! Dette program er udelukkende tænkt til at hjælpe folk til at kunne migrere til andre styresystemer end Windows og Mac -- uden at skulle miste adgangen til deres legalt erhvervede software.

//ejvindh
=======
JavaGro
=======

Java-reader til Gyldendals ordbÃ¸ger

Dette projekt er en viderefÃ¸rsel af Athas-crew'ets arbejde med at lave en reader af Gyldendals ordbÃ¸ger, der fungerer pÃ¥ andet end Windows og Mac:
https://github.com/Athas/spt-gro

I et andet projekt har jeg udvidet det til at kunne hÃ¥ndtere flere ordbÃ¸ger:
https://github.com/ejvindh/spt-gro

I dette projekt har jeg oversat Python-koden til Java-kode. Perspektivet er her, at man vil kunne kÃ¸re Javakoden pÃ¥ endnu flere platforme. ForelÃ¸big er det kun testet til at virke pÃ¥ Windows og Linux. PÃ¥ Mac har jeg ikke kunne fÃ¥ det til at kÃ¸re, eftersom jeg ikke har lykkedes med at finde en maskine med Java7 installeret.

Dette projekt forudsÃ¦tter Java7 for at kunne fungere.

Dette projekt kan hÃ¥ndtere de ordbÃ¸ger, der findes pÃ¥:
http://ordbog.gyldendal.dk/
Det er testet pÃ¥ "Download" ordbÃ¸gerne, sÃ¥ hvis du i forvejen har en CDrom el.lign med ordbÃ¸gerne, er det ikke sikkert, at du kan bruge databaserne derfra -- i hvert fald ikke hvis det er en Ã¦ldre udgave.

I skrivende stund findes der ordbÃ¸ger engelsk, tysk, fransk, spansk, italiensk, svensk, norsk, engelsk (fag/teknik), stor dansk-engelsk, stor engelsk-dansk, retskrivning, fremmedord, synonymer, dansk. Alle disse kan hÃ¥ndteres af dette projekt.

For at kÃ¸re programmet, skal du
(1) Kopiere ordbogsfilerne over i /data/-mappen. For at finde disse filer, er du nÃ¸dt til at have installeret den pÃ¥gÃ¦ldende ordbog pÃ¥ en windows-computer (eller Wine i Linux). Herefter kan du finde dem i mappen %Program Files%Gyldendal/RÃ¸de OrdbÃ¸ger/data -- det drejer sig om to filer for hver ordbog, og de har fil-endelserne *.dat og *.gdd.

(2) SÃ¥ er du klar til at kÃ¸re programmet. Det startes med kommandoen:
java -jar JavaGro.jar

God arbejdslyst. Ligesom Athas vil jeg ogsÃ¥ gerne understrege, at det er strengt ulovligt at piratkopiere ordbogen. Hvis du kan lide sproget, sÃ¥ kÃ¸b det! Dette program er udelukkende tÃ¦nkt til at hjÃ¦lpe folk til at kunne migrere til andre styresystemer end Windows og Mac -- uden at skulle miste adgangen til deres legalt erhvervede software.

//ejvindh
>>>>>>> f9e0f29076d744980ad48af990f6f145efb043a8

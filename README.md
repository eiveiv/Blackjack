# Blackjack

Blackjack spill med følgende regler.
- Dealer vinner hvis begge starter med 2 ess
- Sam vinner hvis begger starter med blackjack
- Sam må trekke til han får over 17, samme med dealer etter Sam er ferdig
- Startkortene deles i rekkefølgen sam, dealer, sam, dealer

Kan legge inn input fil som kortstokk, må være kommeseparert på følgende format D5,HJ,DK osv
Hvis filen ikke finnes så lages det bare en vanlig kortstokk som stokkes

For å kjøre fra kommandolinje må man bruke jar-with-dependencies
Eks

java -jar target/Blackjack-1.0-SNAPSHOT-jar-with-dependencies.jar /home/eivind/textfiles/card32sv


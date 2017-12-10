# Blackjack

Blackjack spill med følgende regler.
- Dealer vinner hvis begge starter med 2 ess
- Sam vinner hvis begger starter med blackjack
- Sam må trekke til han får over 17, dealer må trekke til han er over Sam
- Startkortene deles i rekkefølgen sam, dealer, sam, dealer

Kan legge inn input fil som kortstokk, må være kommeseparert på følgende format D5,HJ,DK osv
Hvis filen ikke finnes så lages det bare en vanlig kortstokk som stokkes

For å kjøre fra kommandolinje må man først bygge med maven,
og så kjøre jar-with-dependencies
Eks

java -jar target/Blackjack-1.0-SNAPSHOT-jar-with-dependencies.jar /home/somefolder/textfiles/card32sv

Hvis det ikke er nok kort i inputfilen til å spille en full runde blackjack,
oppretter man et ny kortstokk som brukes istedenfor.
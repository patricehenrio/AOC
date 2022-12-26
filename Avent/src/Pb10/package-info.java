package Pb10;
/**
--- Jour 10 : Tube cathodique ---
Vous évitez les cordes, plongez dans la rivière et nagez jusqu'au rivage.

Les elfes crient quelque chose à propos de les retrouver en amont, mais la rivière est trop forte pour dire exactement ce qu'ils disent. Ils
finissent de traverser le pont et disparaissent de la vue.

Des situations comme celle-ci doivent être la raison pour laquelle les elfes ont donné la priorité au fonctionnement du système de communication
sur votre appareil portable. Vous le sortez de votre sac, mais la quantité d'eau qui s'écoule lentement d'une grande fissure dans son écran vous
indique qu'il ne sera probablement pas d'une grande utilité immédiate.

À moins que vous ne puissiez concevoir un système de remplacement pour le système vidéo de l'appareil ! Il semble s'agir d'une sorte d' écran à
tube cathodique et d'un simple processeur qui sont tous deux pilotés par un circuit d'horloge précis. Le circuit d'horloge fait tic-tac à un
rythme constant ; chaque tick est appelé un cycle.

Commencez par déterminer le signal envoyé par le CPU. Le CPU a un seul registre, X, qui commence par la valeur 1. Il ne prend en charge que deux
instructions :

addx V prend deux cycles pour se terminer. Après deux cycles, le registre X est incrémenté de la valeur V, (V peut être négatif).
noop prend un cycle mpour se terminer. Cela n'a pas d'autre effet.
Le processeur utilise ces instructions dans un programme (votre entrée de puzzle) pour, d'une manière ou d'une autre, indiquer à l'écran ce qu'il
faut dessiner.

Considérez le petit programme suivant :

noop
addx 3
addx -5
L'exécution de ce programme se déroule comme suit :

Au début du premier cycle, l'instruction noop commence son exécution. Au cours du premier cycle, X vaut 1. Après le premier cycle, l'instruction
noop termine son exécution sans rien faire.
Au début du deuxième cycle, l'instruction addx 3 commence son exécution. Au cours du deuxième cycle, X est encore 1.
Au cours du troisième cycle, X est encore 1. Après le troisième cycle, l'instruction addx 3 termine son exécution et X passe à 4.
Au début du quatrième cycle, l'instruction addx -5 commence son exécution. Au cours du quatrième cycle, X est encore 4.
Au cours du cinquième cycle, X est encore 4. Après le cinquième cycle, l'instruction addx -5 termine son exécution et X passe à -1.
Peut-être pouvez-vous apprendre quelque chose en regardant la valeur du registre X tout au long de l'exécution. Pour l'instant, considérez la
puissance du signal (le nombre de cycles multiplié par la valeur du registre X) pendant le 20e cycle et tous les 40 cycles suivants (c'est-à-dire
pendant les 20e, 60e, 100e, 140e, 180e et 220e cycles).

Par exemple, considérez ce programme plus vaste :

addx 15
addx -11
addx 6
addx -3
addx 5
addx -1
addx -8
addx 13
addx 4
noop
addx -1
addx 5
addx -1
addx 5
addx -1
addx 5
addx -1
addx 5
addx -1
addx -35
addx 1
addx 24
addx -19
addx 1
addx 16
addx -11
noop
noop
addx 21
addx -15
noop
noop
addx -3
addx 9
addx 1
addx -3
addx 8
addx 1
addx 5
noop
noop
noop
noop
noop
addx -36
noop
addx 1
addx 7
noop
noop
noop
addx 2
addx 6
noop
noop
noop
noop
noop
addx 1
noop
noop
addx 7
addx 1
noop
addx -13
addx 13
addx 7
noop
addx 1
addx -33
noop
noop
noop
addx 2
noop
noop
noop
addx 8
noop
addx -1
addx 2
addx 1
noop
addx 17
addx -9
addx 1
addx 1
addx -3
addx 11
noop
noop
addx 1
noop
addx 1
noop
noop
addx -13
addx -19
addx 1
addx 3
addx 26
addx -30
addx 12
addx -1
addx 3
addx 1
noop
noop
noop
addx -9
addx 18
addx 1
addx 2
noop
noop
addx 9
noop
noop
noop
addx -1
addx 2
addx -37
addx 1
addx 3
noop
addx 15
addx -21
addx 22
addx -6
addx 1
noop
addx 2
addx 1
noop
addx -10
noop
noop
addx 20
addx 1
addx 2
addx 2
addx -6
addx -11
noop
noop
noop
Les intensités de signal intéressantes peuvent être déterminées comme suit :

Au cours du 20e cycle, le registre X a la valeur 21, donc la puissance du signal est de 20 * 21 = 420. Le 20e cycle se produit au milieu du
deuxième cycle de l'instruction addx -1, donc la valeur de registre X est la valeur de départ, 1, plus toutes les autres addxvaleurs jusqu'à
ce point : 1 + 15 - 11 + 6 - 3 + 5 - 1 - 8 + 13 + 4 = 21.
Au cours du 60e cycle, le registre X a la valeur 19, donc la force du signal est de 60 * 19 = 1140.
Au cours du 100e cycle, le registre X a la valeur 18, donc la force du signal est de 100 * 18 = 1800.
Au cours du 140e cycle, le registre X a la valeur 21, donc la puissance du signal est de 140 * 21 = 2940.
Au cours du 180e cycle, le registre X a la valeur 16, donc la puissance du signal est de 180 * 16 = 2880.
Au cours du 220e cycle, le registre X a la valeur 18, donc la puissance du signal est de 220 * 18 = 3960.
La somme de ces intensités de signal est 13140.

Trouvez la force du signal pendant les 20e, 60e, 100e, 140e, 180e et 220e cycles. Quelle est la somme de ces six intensités de signal ?

Votre réponse au puzzle était 15880.

--- Deuxième partie ---
Il semble que le registre X contrôle la position horizontale d'un motif. Plus précisément, le motif a une largeur de 3 pixels et le registre X
définit la position horizontale du milieu de ce motif. Dans ce système, il n'y a pas de "position verticale": si la position horizontale du
motif place ses pixels là où le CRT est en train de dessiner, alors ces pixels seront dessinés.

Vous comptez les pixels sur le CRT : 40 de large et 6 de haut. Cet écran CRT dessine la rangée supérieure de pixels de gauche à droite, puis la
rangée inférieure, et ainsi de suite. Le pixel le plus à gauche de chaque ligne est en position 0, et le pixel le plus à droite de chaque ligne
est en position 39.

Comme le CPU, le CRT est étroitement lié au circuit d'horloge : le CRT dessine un seul pixel à chaque cycle. En représentant chaque pixel de
l'écran sous la forme d'un #, voici les cycles pendant lesquels le premier et le dernier pixel de chaque ligne sont dessinés :

Cycle   1 -> ######################################## <- Cycle  40
Cycle  41 -> ######################################## <- Cycle  80
Cycle  81 -> ######################################## <- Cycle 120
Cycle 121 -> ######################################## <- Cycle 160
Cycle 161 -> ######################################## <- Cycle 200
Cycle 201 -> ######################################## <- Cycle 240
Ainsi, en chronométrant soigneusement les instructions CPU et les opérations de dessin CRT, vous devriez être en mesure de déterminer si le
motif est visible à l'instant où chaque pixel est dessiné. Si le motif est positionné de manière à ce que l'un de ses trois pixels soit le pixel
en cours de dessin, l'écran produit un pixel allumé (#); sinon, l'écran laisse le pixel noir (.).

Les premiers pixels de l'exemple plus grand ci-dessus sont dessinés comme suit :

position motif : ###.....................................

Start cycle   1: begin executing addx 15
During cycle  1: CRT draws pixel in position 0
Current CRT row: #

During cycle  2: CRT draws pixel in position 1
Current CRT row: ##
End of cycle  2: finish executing addx 15 (Register X is now 16)
position motif :...............###......................

Start cycle   3: begin executing addx -11
During cycle  3: CRT draws pixel in position 2
Current CRT row: ##.

During cycle  4: CRT draws pixel in position 3
Current CRT row: ##..
End of cycle  4: finish executing addx -11 (Register X is now 5)
position motif :....###.................................

Start cycle   5: begin executing addx 6
During cycle  5: CRT draws pixel in position 4
Current CRT row: ##..#

During cycle  6: CRT draws pixel in position 5
Current CRT row: ##..##
End of cycle  6: finish executing addx 6 (Register X is now 11)
position motif :..........###...........................

Start cycle   7: begin executing addx -3
During cycle  7: CRT draws pixel in position 6
Current CRT row: ##..##.

During cycle  8: CRT draws pixel in position 7
Current CRT row: ##..##..
End of cycle  8: finish executing addx -3 (Register X is now 8)
position motif :.......###..............................

Start cycle   9: begin executing addx 5
During cycle  9: CRT draws pixel in position 8
Current CRT row: ##..##..#

During cycle 10: CRT draws pixel in position 9
Current CRT row: ##..##..##
End of cycle 10: finish executing addx 5 (Register X is now 13)
position motif :............###.........................

Start cycle  11: begin executing addx -1
During cycle 11: CRT draws pixel in position 10
Current CRT row: ##..##..##.

During cycle 12: CRT draws pixel in position 11
Current CRT row: ##..##..##..
End of cycle 12: finish executing addx -1 (Register X is now 12)
position motif :...........###..........................

Start cycle  13: begin executing addx -8
During cycle 13: CRT draws pixel in position 12
Current CRT row: ##..##..##..#

During cycle 14: CRT draws pixel in position 13
Current CRT row: ##..##..##..##
End of cycle 14: finish executing addx -8 (Register X is now 4)
position motif :...###..................................

Start cycle  15: begin executing addx 13
During cycle 15: CRT draws pixel in position 14
Current CRT row: ##..##..##..##.

During cycle 16: CRT draws pixel in position 15
Current CRT row: ##..##..##..##..
End of cycle 16: finish executing addx 13 (Register X is now 17)
position motif :................###.....................

Start cycle  17: begin executing addx 4
During cycle 17: CRT draws pixel in position 16
Current CRT row: ##..##..##..##..#

During cycle 18: CRT draws pixel in position 17
Current CRT row: ##..##..##..##..##
End of cycle 18: finish executing addx 4 (Register X is now 21)
position motif :....................###.................

Start cycle  19: begin executing noop
During cycle 19: CRT draws pixel in position 18
Current CRT row: ##..##..##..##..##.
End of cycle 19: finish executing noop

Start cycle  20: begin executing addx -1
During cycle 20: CRT draws pixel in position 19
Current CRT row: ##..##..##..##..##..

During cycle 21: CRT draws pixel in position 20
Current CRT row: ##..##..##..##..##..#
End of cycle 21: finish executing addx -1 (Register X is now 20)
position motif :...................###..................
Laisser le programme s'exécuter jusqu'à la fin entraîne la production par le CRT de l'image suivante :

##..##..##..##..##..##..##..##..##..##..
###...###...###...###...###...###...###.
####....####....####....####....####....
#####.....#####.....#####.....#####.....
######......######......######......####
#######.......#######.......#######.....
Rendre l'image donnée par votre programme. Quelles huit lettres majuscules apparaissent sur votre CRT ?

Votre réponse au puzzle était PLGFKAZG.
*/

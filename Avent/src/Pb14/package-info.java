package Pb14;
/**
--- Jour 14 : Réservoir Regolith ---
Le signal de détresse vous mène à une cascade géante ! En fait, attendez - le signal semble provenir de la cascade elle-même, et cela n'a aucun sens.
Cependant, vous remarquez un petit chemin qui mène derrière la cascade.

Correction : le signal de détresse vous mène derrière une cascade géante ! Il semble y avoir un grand système de grottes ici, et le signal mène
certainement plus loin à l'intérieur.

Alors que vous commencez à vous frayer un chemin plus profondément sous terre, vous sentez le sol gronder pendant un instant. Le sable commence à se
déverser dans la grotte ! Si vous ne savez pas rapidement où va le sable, vous pourriez vous retrouver piégé !

Heureusement, votre familiarité avec l'analyse de la trajectoire des chutes de matériaux vous sera utile ici. Vous scannez une tranche verticale en
deux dimensions de la grotte au-dessus de vous (votre entrée de puzzle) et découvrez qu'il s'agit principalement d'air avec des structures en roche.

Votre analyse trace le chemin de chaque structure rocheuse solide et rapporte les coordonnées x,y qui donnent la forme du chemin, où x représente la
distance vers la droite et y représente la distance vers le bas. Chaque chemin apparaît sous la forme d'une seule ligne de texte dans votre
numérisation. Après le premier point de chaque chemin, chaque point indique la fin d'une ligne droite horizontale ou verticale à tracer à partir du
point précédent. Par exemple:

498,4 -> 498,6 -> 496,6
503,4 -> 502,4 -> 502,9 -> 494,9
Ce balayage signifie qu'il y a deux chemins de roche ; le premier chemin se compose de deux lignes droites et le second chemin se compose de trois
lignes droites. Plus précisément, le premier chemin se compose d'une ligne de roche de 498,4 vers 498,6 et d'une autre ligne de roche de 498,6 vers
496,6.

Le sable se déverse dans la grotte à partir du point 500,0.

Dessiner la roche comme #, l'air comme., et la source du sable comme +, cela devient :


  4     5  5
  9     0  0
  4     0  3
0......+...
1..........
2..........
3..........
4....#...##
5....#...#.
6..###...#.
7........#.
8........#.
9 #########.
Le sable est produit une unité à la fois et l'unité de sable suivante n'est pas produite jusqu'à ce que l'unité de sable précédente s'immobilise. Une
unité de sable est assez grande pour remplir une case d'air dans votre analyse.

Une unité de sable tombe toujours d'une marche si possible. Si la tuile immédiatement en dessous est bloquée (par de la roche ou du sable), l'unité de
sable tente de se déplacer en diagonale d'un pas vers le bas et vers la gauche. Si cette tuile est bloquée, l'unité de sable tente à la place de se
déplacer en diagonale d'un pas vers le bas et vers la droite. Le sable continue de bouger tant qu'il en est capable, essayant à chaque pas de descendre,
puis de descendre à gauche, puis de descendre à droite. Si les trois destinations possibles sont bloquées, l'unité de sable s'immobilise et ne bouge plus,
à ce point l'unité de sable suivante est recréée à la source.

Ainsi, en dessinant du sable qui s'est immobilisé comme o, la première unité de sable tombe simplement tout droit puis s'arrête :

......+...
..........
..........
..........
....#...##
....#...#.
..###...#.
........#.
......o.#.
#########.
La deuxième unité de sable tombe alors droit vers le bas, atterrit sur la première, puis s'immobilise à sa gauche :

......+...
..........
..........
..........
....#...##
....#...#.
..###...#.
........#.
.....oo.#.
#########.
Après qu'un total de cinq unités de sable se soient immobilisées, elles forment ce motif :

......+...
..........
..........
..........
....#...##
....#...#.
..###...#.
......o.#.
....oooo#.
#########.
Après un total de 22 unités de sable :

......+...
..........
......o...
.....ooo..
....#ooo##
....#ooo#.
..###ooo#.
....oooo#.
...ooooo#.
#########.
Enfin, seules deux autres unités de sable peuvent éventuellement s'immobiliser :

......+...
..........
......o...
.....ooo..
....#ooo##
...o#ooo#.
..###ooo#.
....oooo#.
.o.ooooo#.
#########.
Une fois que toutes les 24unités de sable illustrées ci-dessus se sont immobilisées, tout le sable supplémentaire s'écoule du fond, tombant dans le
vide sans fin. Juste pour le plaisir, le chemin que prend tout nouveau sable avant de tomber pour toujours est montré ici avec ~:

.......+...
.......~...
......~o...
.....~ooo..
....~#ooo##
...~o#ooo#.
..~###ooo#.
..~..oooo#.
.~o.ooooo#.
~#########.
~..........
~..........
~..........
À l'aide de votre scan, simulez la chute de sable. Combien d'unités de sable s'immobilisent avant que le sable ne commence à couler dans l'abîme en
dessous ?

Votre réponse au puzzle était 715.

--- Deuxième partie ---
Vous réalisez que vous avez mal lu le scan. Il n'y a pas un vide sans fin au bas du scan - il y a le sol et vous vous tenez dessus !

Vous n'avez pas le temps de balayer le sol, alors supposez que le sol est une ligne horizontale infinie avec une coordonnée y égale à deux plus la
coordonnée y la plus élevée de n'importe quel point de votre balayage.

Dans l'exemple ci-dessus, la ycoordonnée la plus élevée de n'importe quel point est 9, et donc le sol est à y=11. C'est comme si votre scan contenait
un chemin de roche supplémentaire comme -infini,11 -> infini,11. Avec le sol ajouté, l'exemple ci-dessus ressemble maintenant à ceci :

       ...........+........
       ....................
       ....................
       ....................
       .........#...##.....
       .........#...#......
       .......###...#......
       .............#......
       .............#......
       .....#########......
       ....................
<-- etc #################### etc -->
Pour trouver un endroit sûr où se tenir debout, vous devrez simuler une chute de sable jusqu'à ce qu'une unité de sable s'immobilise à 500,0, bloquant
entièrement la source et arrêtant le flux de sable dans la grotte. Dans l'exemple ci-dessus, la situation ressemble finalement à ceci après que 93l 
unités de sable se soient immobilisées :

............o............
...........ooo...........
..........ooooo..........
.........ooooooo.........
........oo#ooo##o........
.......ooo#ooo#ooo.......
......oo###ooo#oooo......
.....oooo.oooo#ooooo.....
....oooooooooo#oooooo....
...ooo#########ooooooo...
..ooooo.......ooooooooo..
#########################
À l'aide de votre analyse, simulez la chute de sable jusqu'à ce que la source du sable soit bloquée. Combien d'unités de sable s'immobilisent ?

Votre réponse au puzzle était 25248.
*/

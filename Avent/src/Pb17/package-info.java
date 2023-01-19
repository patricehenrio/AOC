package Pb17;
/**
--- Jour 17 : Coulée pyroclastique ---
Votre appareil portable a localisé une sortie alternative de la grotte pour vous et les éléphants. Le sol gronde presque
continuellement maintenant, mais les vannes étranges vous ont fait gagner du temps. Il fait certainement plus chaud ici,
cependant.

Les tunnels finissent par s'ouvrir dans une chambre très haute et étroite. De gros rochers aux formes étranges tombent
dans la chambre par le haut, probablement à cause de tous les grondements. Si vous ne savez pas où les rochers tomberont
ensuite, vous risquez d'être écrasé !

Les cinq types de roches ont les formes particulières suivantes, où # est la roche et . est l'espace vide :

####

.#.
###
.#.

..#
..#
###

#
#
#
#

##
##
Les roches tombent dans l'ordre indiqué ci-dessus : d'abord la forme -, puis la forme +, et ainsi de suite. Une fois la fin de la
liste atteinte, le même ordre se répète : la forme - tombe en premier, sixième, 11e, 16e, etc.

Les rochers ne tournent pas, mais ils sont poussés par des jets de gaz chaud sortant des murs eux-mêmes. Une analyse rapide révèle
l'effet que les jets de gaz chaud auront sur les rochers lors de leur chute (votre entrée de puzzle).

Par exemple, supposons que ce soit le modèle de jet dans votre grotte :

>>><<><>><<<>><>>><<<>>><<<><<<>><>><<>>
Dans les jets, < signifie une poussée vers la gauche, tandis que > signifie une poussée vers la droite. Le schéma ci-dessus signifie
que les jets pousseront un rocher qui tombe à droite, puis à droite, puis à droite, puis à gauche, puis à gauche, puis à droite, et
ainsi de suite. Si la fin de la liste est atteinte, elle se répète.

La grande chambre verticale mesure exactement sept unités de large . Chaque rocher apparaît de sorte que son bord gauche soit à deux
unités du mur de gauche et son bord inférieur à trois unités au-dessus du rocher le plus haut de la pièce (ou du sol, s'il n'y en a pas).

Après l'apparition d'un rocher, il alterne entre être poussé par un jet de gaz chaud d'une unité (dans la direction indiquée par le
symbole suivant dans le schéma du jet) puis tomber d'une unité vers le bas. Si un mouvement provoque le déplacement d'une partie de la
roche dans les murs, le sol ou une roche arrêtée, le mouvement ne se produit pas à la place. Si un mouvement vers le bas aurait provoqué
le déplacement d'une pierre tombante dans le sol ou une pierre déjà tombée, la pierre tombante s'arrête là où elle se trouve (ayant
atterri sur quelque chose) et une nouvelle pierre commence immédiatement à tomber.

En dessinant des chutes de pierres avec @ et des pierres arrêtées avec #, le motif de jet dans l'exemple ci-dessus se manifeste comme suit :

The first rock begins falling:
|..@@@@.|
|.......|
|.......|
|.......|
+-------+

Jet of gas pushes rock right:
|...@@@@|
|.......|
|.......|
|.......|
+-------+

Rock falls 1 unit:
|...@@@@|
|.......|
|.......|
+-------+

Jet of gas pushes rock right, but nothing happens:
|...@@@@|
|.......|
|.......|
+-------+

Rock falls 1 unit:
|...@@@@|
|.......|
+-------+

Jet of gas pushes rock right, but nothing happens:
|...@@@@|
|.......|
+-------+

Rock falls 1 unit:
|...@@@@|
+-------+

Jet of gas pushes rock left:
|..@@@@.|
+-------+

Rock falls 1 unit, causing it to come to rest:
|..####.|
+-------+

A new rock begins falling:
|...@...|
|..@@@..|
|...@...|
|.......|
|.......|
|.......|
|..####.|
+-------+

Jet of gas pushes rock left:
|..@....|
|.@@@...|
|..@....|
|.......|
|.......|
|.......|
|..####.|
+-------+

Rock falls 1 unit:
|..@....|
|.@@@...|
|..@....|
|.......|
|.......|
|..####.|
+-------+

Jet of gas pushes rock right:
|...@...|
|..@@@..|
|...@...|
|.......|
|.......|
|..####.|
+-------+

Rock falls 1 unit:
|...@...|
|..@@@..|
|...@...|
|.......|
|..####.|
+-------+

Jet of gas pushes rock left:
|..@....|
|.@@@...|
|..@....|
|.......|
|..####.|
+-------+

Rock falls 1 unit:
|..@....|
|.@@@...|
|..@....|
|..####.|
+-------+

Jet of gas pushes rock right:
|...@...|
|..@@@..|
|...@...|
|..####.|
+-------+

Rock falls 1 unit, causing it to come to rest:
|...#...|
|..###..|
|...#...|
|..####.|
+-------+

A new rock begins falling:
|....@..|
|....@..|
|..@@@..|
|.......|
|.......|
|.......|
|...#...|
|..###..|
|...#...|
|..####.|
+-------+
Au moment où chacun des prochains rochers commence à tomber, vous verriez ceci :

|..@....|
|..@....|
|..@....|
|..@....|
|.......|
|.......|
|.......|
|..#....|
|..#....|
|####...|
|..###..|
|...#...|
|..####.|
+-------+

|..@@...|
|..@@...|
|.......|
|.......|
|.......|
|....#..|
|..#.#..|
|..#.#..|
|#####..|
|..###..|
|...#...|
|..####.|
+-------+

|..@@@@.|
|.......|
|.......|
|.......|
|....##.|
|....##.|
|....#..|
|..#.#..|
|..#.#..|
|#####..|
|..###..|
|...#...|
|..####.|
+-------+

|...@...|
|..@@@..|
|...@...|
|.......|
|.......|
|.......|
|.####..|
|....##.|
|....##.|
|....#..|
|..#.#..|
|..#.#..|
|#####..|
|..###..|
|...#...|
|..####.|
+-------+

|....@..|
|....@..|
|..@@@..|
|.......|
|.......|
|.......|
|..#....|
|.###...|
|..#....|
|.####..|
|....##.|
|....##.|
|....#..|
|..#.#..|
|..#.#..|
|#####..|
|..###..|
|...#...|
|..####.|
+-------+

|..@....|
|..@....|
|..@....|
|..@....|
|.......|
|.......|
|.......|
|.....#.|
|.....#.|
|..####.|
|.###...|
|..#....|
|.####..|
|....##.|
|....##.|
|....#..|
|..#.#..|
|..#.#..|
|#####..|
|..###..|
|...#...|
|..####.|
+-------+

|..@@...|
|..@@...|
|.......|
|.......|
|.......|
|....#..|
|....#..|
|....##.|
|....##.|
|..####.|
|.###...|
|..#....|
|.####..|
|....##.|
|....##.|
|....#..|
|..#.#..|
|..#.#..|
|#####..|
|..###..|
|...#...|
|..####.|
+-------+

|..@@@@.|
|.......|
|.......|
|.......|
|....#..|
|....#..|
|....##.|
|##..##.|
|######.|
|.###...|
|..#....|
|.####..|
|....##.|
|....##.|
|....#..|
|..#.#..|
|..#.#..|
|#####..|
|..###..|
|...#...|
|..####.|
+-------+
Pour prouver aux éléphants que votre simulation est exacte, ils veulent savoir quelle sera la hauteur de la tour après l'arrêt de 2022
rochers (mais avant que le 2023e rocher ne commence à tomber). Dans cet exemple, la tour de rochers aura 3068 unités de hauteur.

Combien d'unités de hauteur la tour de rochers aura-t-elle après que les rochers de 2022 auront cessé de tomber ?

Votre réponse au puzzle était 3157.

La première moitié de ce puzzle est terminée ! Il fournit une étoile d'or : *

--- Deuxième partie ---
Les éléphants ne sont pas impressionnés par votre simulation. Ils exigent de savoir quelle sera la hauteur de la tour une fois les 1000000000000
rochers arrêtés ! Ce n'est qu'alors qu'ils se sentiront suffisamment en confiance pour traverser la grotte.

Dans l'exemple ci-dessus, la tour aurait 1514285714288 unités de hauteur !

Quelle sera la hauteur de la tour une fois les 1000000000000 rochers arrêtés ?

Votre réponse au puzzle était 1581449275319.
*/
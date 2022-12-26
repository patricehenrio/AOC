package Pb12;
/**
--- Jour 12 : Algorithme d'escalade ---
Vous essayez de contacter les elfes à l'aide de votre appareil portable, mais la rivière que vous suivez doit être trop basse pour obtenir un
signal décent.

Vous demandez à l'appareil une carte des hauteurs de la zone environnante (votre saisie de puzzle). La carte de hauteur montre la zone locale
d'en haut divisée en une grille ; l'élévation de chaque carré de la grille est donnée par une seule lettre minuscule, où a est l'élévation la
plus basse, b est la suivante la plus basse, et ainsi de suite jusqu'à l'élévation la plus élevée, z.

La carte des hauteurs comprend également des repères pour votre position actuelle (S) et l'emplacement qui devrait recevoir le meilleur signal
(E). Votre position actuelle (S) a une élévation a, et l'emplacement qui devrait recevoir le meilleur signal (E) a une élévation z.

Vous aimeriez atteindre E, mais pour économiser de l'énergie, vous devez le faire en aussi peu d'étapes que possible . À chaque étape, vous
pouvez vous déplacer exactement d'une case vers le haut, le bas, la gauche ou la droite. Pour éviter d'avoir à sortir votre matériel d'escalade,
l'élévation de la case de destination peut être supérieure d'au plus une unité à l'élévation de votre case actuelle ; c'est-à-dire que si votre
élévation actuelle est m, vous pouvez passer à l'élévation n, mais pas à l'élévation o. Cela signifie également que l'élévation de la case de
destination peut être bien inférieure à l'élévation de votre case actuelle.

Par exemple:

Sabqponm
abcryxxl
accszExk
acctuvwj
abdefghi
Ici, vous commencez dans le coin supérieur gauche ; votre objectif est proche du milieu. Vous pouvez commencer par vous déplacer vers le bas ou
vers la droite, mais vous devrez éventuellement vous diriger en dessous de E. De là, vous pouvez tourner en rond vers l'objectif :

v..v<<<<
>v.vv<<^
.>vv>E^^
..v>>>^^
..>>>>>^
Dans le diagramme ci-dessus, les symboles indiquent si le chemin sort de chaque case en se déplaçant vers le haut (^), vers le bas (v), vers la
gauche (<) ou vers la droite (>). L'emplacement qui devrait recevoir le meilleur signal est toujours E, et '.' marque les places non visitées.

Ce chemin atteint le but en 31 étapes, le moins possible.

Quel est le moins d'étapes nécessaires pour passer de votre position actuelle à l'emplacement qui devrait recevoir le meilleur signal ?

Votre réponse au puzzle était 339.

--- Deuxième partie ---
En montant la colline, vous soupçonnez que les elfes voudront en faire un sentier de randonnée. Le début n'est pas très pittoresque, cependant;
peut-être pouvez-vous trouver un meilleur point de départ.

Pour maximiser l'exercice pendant la randonnée, le sentier doit commencer le plus bas possible : altitude a. Le but est toujours la case marquée E.
Cependant, la piste doit toujours être directe, en faisant le moins de pas possible pour atteindre son objectif. Ainsi, vous devrez trouver le chemin
le plus court depuis n'importe quelle case d'altitude 'a' jusqu'à la case marquée E.

Considérons à nouveau l'exemple ci-dessus :

Sabqponm
abcryxxl
accszExk
acctuvwj
abdefghi
Maintenant, il y a six choix pour la position de départ (cinq marqués 'a', plus le carré marqué S qui compte comme étant à l'élévation 'a'). Si vous
commencez par la case en bas à gauche, vous pouvez atteindre l'objectif le plus rapidement :

...v<<<<
...vv<<^
...v>E^^
.>v>>>^^
>^>>>>>^
Ce chemin atteint le but en seulement 29 étapes, c'est le moins possible.

Quel est le moins de pas requis pour se déplacer à partir de n'importe quelle case d'élévation 'a' jusqu'à l'emplacement qui devrait recevoir le meilleur
signal ?

Votre réponse au puzzle était 332.
*/

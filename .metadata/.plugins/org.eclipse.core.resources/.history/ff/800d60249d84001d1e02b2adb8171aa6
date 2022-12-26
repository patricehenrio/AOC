package Pb08;
/**
--- Jour 8 : Treetop Tree House ---
L'expédition tombe sur une parcelle particulière de grands arbres tous soigneusement plantés dans une grille. Les elfes expliquent qu'une
expédition précédente a planté ces arbres dans le cadre d'un effort de reboisement. Maintenant, ils sont curieux de savoir si ce serait un
bon emplacement pour une cabane dans les arbres.

Tout d'abord, déterminez s'il y a suffisamment de couverture arborée ici pour cacher une cabane dans les arbres. Pour ce faire, vous devez
compter le nombre d'arbres visibles de l'extérieur de la grille lorsque vous regardez directement le long d'une ligne ou d'une colonne.

Les elfes ont déjà lancé un quadricoptère pour générer une carte avec la hauteur de chaque arbre ( votre puzzle d'entrée ). Par exemple:

30373
25512
65332
33549
35390
Chaque arbre est représenté par un seul chiffre dont la valeur est sa hauteur, où 0 est le plus court et 9 est le plus haut.

Un arbre est visible si tous les autres arbres entre lui et un bord de la grille sont plus courts que lui. Ne considérez que les arbres de
la même ligne ou colonne ; c'est-à-dire, ne regardez que vers le haut, le bas, la gauche ou la droite d'un arbre donné.

Tous les arbres autour du bord de la grille sont visibles - puisqu'ils sont déjà sur le bord, il n'y a pas d'arbres pour bloquer la vue. Dans
cet exemple, cela ne laisse que les neuf arbres intérieurs à considérer :

Le haut-gauche 5 est visible de la gauche et du haut. (Il n'est pas visible de la droite ou du bas car d'autres arbres de hauteur 5 gênent).
Le milieu supérieur 5 est visible du haut et de la droite.
Le coin supérieur droit 1 n'est visible d'aucune direction ; pour qu'il soit visible, il suffirait qu'il y ait des arbres de hauteur 0 entre
lui et une arête.
Le milieu gauche 5 est visible , mais uniquement de la droite.
Le centre 3 n'est visible d'aucune direction ; pour qu'il soit visible, il faudrait qu'il n'y ait que des arbres d'au plus 2 entre lui et un
bord.
Le milieu droit 3 est visible de la droite.
Dans la rangée du bas, le milieu 5 est visible, mais les 3 et 4 ne le sont sont pas.
Avec 16 arbres visibles sur le bord et 5 autres visibles à l'intérieur, un total de 21 arbres sont visibles dans cet arrangement.

Considérez votre carte ; combien d'arbres sont visibles de l'extérieur de la grille ?

Votre réponse au puzzle était 1832.

--- Deuxième partie ---
Satisfaits de la quantité de couvert arboré disponible, les elfes ont juste besoin de connaître le meilleur endroit pour construire leur cabane
dans les arbres : ils aimeraient pouvoir voir beaucoup d' arbres.

Pour mesurer la distance d'observation à partir d'un arbre donné, regardez en haut, en bas, à gauche et à droite de cet arbre ; arrêtez-vous si
vous atteignez une lisière ou au premier arbre qui est de la même hauteur ou plus grand que l'arbre considéré. Si un arbre est juste sur le bord,
au moins une de ses distances de visualisation sera nulle.

Les elfes ne se soucient pas des arbres éloignés plus grands que ceux trouvés par les règles ci-dessus ; la cabane dans les arbres proposée a de
grands avant-toits pour la garder au sec, de sorte qu'ils ne pourraient pas voir plus haut que la cabane dans les arbres de toute façon.

Dans l'exemple ci-dessus, considérez le milieu 5 de la deuxième ligne :

30373
25512
65332
33549
35390
En levant les yeux, sa vue n'est pas obstruée ; il peut voir 1 arbre (de hauteur 3).
En regardant à gauche, sa vue est immédiatement bloquée ; il ne peut voir que 1 arbre (de hauteur 5, juste à côté).
En regardant à droite, sa vue n'est pas obstruée ; il peut voir 2 arbres.
En regardant vers le bas, sa vue est finalement bloquée ; il peut voir 2 arbres (un de hauteur 3, puis l'arbre de hauteur 5 qui lui bloque la vue).
Le score scénique d'un arbre est obtenu en multipliant sa distance d'observation dans chacune des quatre directions. Pour cet arbre, c'est 4 (trouvé
en multipliant 1 * 1 * 2 * 2).

Cependant, vous pouvez faire encore mieux : considérez l'arbre de hauteur 5 au milieu de la quatrième rangée :

30373
25512
65332
33549
35390
En regardant vers le haut, sa vue est bloquée par un autre arbre d'une hauteur de 5.
En regardant à gauche, sa vue n'est pas obstruée ; il peut voir les 2 arbres.
En regardant vers le bas, sa vue n'est pas non plus bloquée il peut voir 1 arbre.
En regardant à droite, sa vue est obstruée par par un arbre de hauteur 9.
Le score scénique de cet arbre est 8( 2 * 2 * 1 * 2); c'est l'endroit idéal pour la cabane dans les arbres.

Considérez chaque arbre sur votre carte. Quel est le score panoramique le plus élevé possible pour n'importe quel arbre ?

Votre réponse au puzzle était 157320.
*/

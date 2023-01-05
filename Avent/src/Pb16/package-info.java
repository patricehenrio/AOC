package Pb16;
/**
--- Jour 16: Volcan Proboscidea ---
Les capteurs vous ont conduit à l'origine du signal de détresse : encore un autre appareil portatif, tout comme celui que les Elfes vous ont donné.
Cependant, vous ne voyez aucun elfe dans les parages ; au lieu de cela, l'appareil est entouré d'éléphants ! Ils ont dû se perdre dans ces tunnels,
et l'un des éléphants a apparemment compris comment activer le signal de détresse.

Le sol gronde à nouveau, beaucoup plus fort cette fois. Quel genre de grotte est-ce, exactement ? Vous scannez la grotte avec votre appareil
portable; il signale surtout de la roche ignée, quelques cendres, des poches de gaz sous pression, du magma... ce n'est pas qu'une grotte, c'est un
volcan !

Vous devez faire sortir les éléphants d'ici, rapidement. Votre appareil estime qu'il vous reste 30 minutes avant l'éruption du volcan, vous n'avez
donc pas le temps de repartir par où vous êtes entré.

Vous parcourez la grotte pour d'autres options et découvrez un réseau de tuyaux et de soupapes de surpression . Vous ne savez pas comment un tel
système est entré dans un volcan, mais vous n'avez pas le temps de vous plaindre ; votre appareil produit un rapport (votre entrée de puzzle) du
débit de chaque vanne si elle était ouverte (en pression par minute) et des tunnels que vous pourriez utiliser pour vous déplacer entre les vannes.

Il y a même une vanne dans la pièce où vous et les éléphants vous trouvez actuellement et qui est étiquetée AA. Vous estimez qu'il vous faudra une
minute pour ouvrir une seule vanne et une minute pour suivre n'importe quel tunnel d'une vanne à l'autre. Quelle est la pression maximale que vous
pourriez relâcher ?

Par exemple, supposons que vous disposiez du résultat d'analyse suivant :

Valve AA has flow rate=0; tunnels lead to valves DD, II, BB
Valve BB has flow rate=13; tunnels lead to valves CC, AA
Valve CC has flow rate=2; tunnels lead to valves DD, BB
Valve DD has flow rate=20; tunnels lead to valves CC, AA, EE
Valve EE has flow rate=3; tunnels lead to valves FF, DD
Valve FF has flow rate=0; tunnels lead to valves EE, GG
Valve GG has flow rate=0; tunnels lead to valves FF, HH
Valve HH has flow rate=22; tunnel leads to valve GG
Valve II has flow rate=0; tunnels lead to valves AA, JJ
Valve JJ has flow rate=21; tunnel leads to valve II
Toutes les vannes commencent fermées. Vous commencez à la vanne AA, mais elle doit être endommagée ou coincée ou quelque chose comme ça : son débit
est de 0, donc ça ne sert à rien de l'ouvrir. Cependant, vous pourriez passer une minute à vous déplacer vers la vanne BB et une autre minute à
l'ouvrir ; cela relâcherait la pression pendant les 28 minutes restantes à un débit de 13, une libération de pression finale totale de 28*13 = 364.
Ensuite, vous pourriez passer votre troisième minute à vous déplacer vers la vanne CC et votre quatrième minute à l'ouvrir, fournissant 26 minutes
supplémentaires de libération de pression éventuelle à un débit de 2, ou la pression totale libérée par la vanne CC sera de 52.

En vous frayant un chemin à travers les tunnels comme celui-ci, vous pourriez probablement ouvrir plusieurs ou toutes les vannes au bout de 30
minutes. Cependant, vous devez relâcher le plus de pression possible, vous devrez donc être méthodique. Considérez plutôt cette approche :

== Minute 1 ==
No valves are open.
You move to valve DD.

== Minute 2 ==
No valves are open.
You open valve DD.

== Minute 3 ==
Valve DD is open, releasing 20 pressure.
You move to valve CC.

== Minute 4 ==
Valve DD is open, releasing 20 pressure.
You move to valve BB.

== Minute 5 ==
Valve DD is open, releasing 20 pressure.
You open valve BB.

== Minute 6 ==
Valves BB and DD are open, releasing 33 pressure.
You move to valve AA.

== Minute 7 ==
Valves BB and DD are open, releasing 33 pressure.
You move to valve II.

== Minute 8 ==
Valves BB and DD are open, releasing 33 pressure.
You move to valve JJ.

== Minute 9 ==
Valves BB and DD are open, releasing 33 pressure.
You open valve JJ.

== Minute 10 ==
Valves BB, DD, and JJ are open, releasing 54 pressure.
You move to valve II.

== Minute 11 ==
Valves BB, DD, and JJ are open, releasing 54 pressure.
You move to valve AA.

== Minute 12 ==
Valves BB, DD, and JJ are open, releasing 54 pressure.
You move to valve DD.

== Minute 13 ==
Valves BB, DD, and JJ are open, releasing 54 pressure.
You move to valve EE.

== Minute 14 ==
Valves BB, DD, and JJ are open, releasing 54 pressure.
You move to valve FF.

== Minute 15 ==
Valves BB, DD, and JJ are open, releasing 54 pressure.
You move to valve GG.

== Minute 16 ==
Valves BB, DD, and JJ are open, releasing 54 pressure.
You move to valve HH.

== Minute 17 ==
Valves BB, DD, and JJ are open, releasing 54 pressure.
You open valve HH.

== Minute 18 ==
Valves BB, DD, HH, and JJ are open, releasing 76 pressure.
You move to valve GG.

== Minute 19 ==
Valves BB, DD, HH, and JJ are open, releasing 76 pressure.
You move to valve FF.

== Minute 20 ==
Valves BB, DD, HH, and JJ are open, releasing 76 pressure.
You move to valve EE.

== Minute 21 ==
Valves BB, DD, HH, and JJ are open, releasing 76 pressure.
You open valve EE.

== Minute 22 ==
Valves BB, DD, EE, HH, and JJ are open, releasing 79 pressure.
You move to valve DD.

== Minute 23 ==
Valves BB, DD, EE, HH, and JJ are open, releasing 79 pressure.
You move to valve CC.

== Minute 24 ==
Valves BB, DD, EE, HH, and JJ are open, releasing 79 pressure.
You open valve CC.

== Minute 25 ==
Valves BB, CC, DD, EE, HH, and JJ are open, releasing 81 pressure.

== Minute 26 ==
Valves BB, CC, DD, EE, HH, and JJ are open, releasing 81 pressure.

== Minute 27 ==
Valves BB, CC, DD, EE, HH, and JJ are open, releasing 81 pressure.

== Minute 28 ==
Valves BB, CC, DD, EE, HH, and JJ are open, releasing 81 pressure.

== Minute 29 ==
Valves BB, CC, DD, EE, HH, and JJ are open, releasing 81 pressure.

== Minute 30 ==
Valves BB, CC, DD, EE, HH, and JJ are open, releasing 81 pressure.
Cette approche vous permet de relâcher le plus de pression possible en 30 minutes avec cette disposition de soupape, 1651.

Travaillez sur les étapes pour relâcher le plus de pression en 30 minutes. Quelle est la pression maximale que vous pouvez relâcher ?

Votre réponse au puzzle était 1728.

La première moitié de ce puzzle est terminée ! Il fournit une étoile d'or : *

--- Deuxième partie ---
Vous craignez que même avec une approche optimale, la pression libérée ne soit pas suffisante. Et si vous demandiez à l'un des éléphants de vous aider ?

Il vous faudrait 4 minutes pour apprendre à un éléphant à ouvrir les bonnes vannes dans le bon ordre, vous laissant seulement 26 minutes pour exécuter votre plan.
Serait-il préférable de travailler à deux, quitte à avoir moins de temps ? (Supposez que vous apprenez à l'éléphant avant d'ouvrir vous-même les vannes, ce qui vous
donne à tous les deux les mêmes 26 minutes complètes.)

Dans l'exemple ci-dessus, vous pourriez apprendre à l'éléphant à vous aider comme suit :

== Minute 1 ==
No valves are open.
You move to valve II.
The elephant moves to valve DD.

== Minute 2 ==
No valves are open.
You move to valve JJ.
The elephant opens valve DD.

== Minute 3 ==
Valve DD is open, releasing 20 pressure.
You open valve JJ.
The elephant moves to valve EE.

== Minute 4 ==
Valves DD and JJ are open, releasing 41 pressure.
You move to valve II.
The elephant moves to valve FF.

== Minute 5 ==
Valves DD and JJ are open, releasing 41 pressure.
You move to valve AA.
The elephant moves to valve GG.

== Minute 6 ==
Valves DD and JJ are open, releasing 41 pressure.
You move to valve BB.
The elephant moves to valve HH.

== Minute 7 ==
Valves DD and JJ are open, releasing 41 pressure.
You open valve BB.
The elephant opens valve HH.

== Minute 8 ==
Valves BB, DD, HH, and JJ are open, releasing 76 pressure.
You move to valve CC.
The elephant moves to valve GG.

== Minute 9 ==
Valves BB, DD, HH, and JJ are open, releasing 76 pressure.
You open valve CC.
The elephant moves to valve FF.

== Minute 10 ==
Valves BB, CC, DD, HH, and JJ are open, releasing 78 pressure.
The elephant moves to valve EE.

== Minute 11 ==
Valves BB, CC, DD, HH, and JJ are open, releasing 78 pressure.
The elephant opens valve EE.

(At this point, all valves are open.)

== Minute 12 ==
Valves BB, CC, DD, EE, HH, and JJ are open, releasing 81 pressure.

...

== Minute 20 ==
Valves BB, CC, DD, EE, HH, and JJ are open, releasing 81 pressure.

...

== Minute 26 ==
Valves BB, CC, DD, EE, HH, and JJ are open, releasing 81 pressure.
Avec l'aide de l'éléphant, après 26 minutes, le mieux que vous puissiez faire relâcherait une pression totale de 1707.

Avec vous et un éléphant travaillant ensemble pendant 26 minutes, quelle est la pression maximale que vous pourriez relâcher ?
*/
package Pb18;
/**
--- Jour 18 : Rochers bouillants ---
Vous et les éléphants atteignez enfin l'air frais. Vous avez émergé près de la base d'un grand volcan qui semble être en éruption active ! 
Heureusement, la lave semble s'éloigner de vous et se diriger vers l'océan.

Des morceaux de lave sont toujours éjectés vers vous, vous vous abritez donc un peu plus longtemps à la sortie de la caverne. À l'extérieur
de la grotte, vous pouvez voir la lave atterrir dans un étang et l'entendre siffler bruyamment lorsqu'elle se solidifie.

Selon les composés spécifiques de la lave et la vitesse à laquelle elle se refroidit, elle pourrait former de l' obsidienne ! Le taux de
refroidissement doit être basé sur la surface des gouttelettes de lave, de sorte que vous effectuez un balayage rapide d'une gouttelette
lorsqu'elle passe devant vous (votre entrée de puzzle).

En raison de la rapidité avec laquelle la lave se déplace, le scan n'est pas très bon ; sa résolution est assez faible et, par conséquent,
la forme de la gouttelette de lave se rapproche de cubes de 1 unité de côté, chacun étant donné par sa position x,y,z.

Pour approximer la surface, comptez le nombre de côtés de chaque cube qui ne sont pas immédiatement connectés à un autre cube. Ainsi, si
votre analyse ne portait que sur deux cubes adjacents comme 1,1,1et 2,1,1, chaque cube aurait un seul côté couvert et cinq côtés exposés
une surface totale de 10 côtés.

Voici un exemple plus grand :

2,2,2
1,2,2
3,2,2
2,1,2
2,3,2
2,2,1
2,2,3
2,2,4
2,2,6
1,2,5
3,2,5
2,1,5
2,3,5
Dans l'exemple ci-dessus, après avoir compté tous les côtés qui ne sont pas connectés à un autre cube, la surface totale est de 64.

Quelle est la surface de vos gouttelettes de lave scannées ?

Votre réponse au puzzle était 3390.

La première moitié de ce puzzle est terminée ! Il fournit une étoile d'or : *

--- Deuxième partie ---
Quelque chose ne va pas dans votre calcul. Le taux de refroidissement dépend de la surface extérieure, mais votre calcul a également inclus 
la surface des poches d'air emprisonnées dans la gouttelette de lave.

Au lieu de cela, ne considérez que les côtés du cube qui pourraient être atteints par l'eau et la vapeur lorsque la gouttelette de lave tombe
dans l'étang. La vapeur se dilatera pour atteindre autant que possible, déplaçant complètement tout air à l'extérieur de la gouttelette de lave
mais ne se dilatant jamais en diagonale.

Dans l'exemple plus grand ci-dessus, exactement un cube d'air est emprisonné dans la gouttelette de lave (à 2,2,5), donc la surface extérieure
de la gouttelette de lave est 58.

Quelle est la surface extérieure de votre gouttelette de lave scannée ?
*/
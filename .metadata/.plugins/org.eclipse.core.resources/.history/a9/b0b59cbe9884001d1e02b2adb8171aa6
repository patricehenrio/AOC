package Pb07;
/**
--- Jour 7 : Il ne reste plus d'espace sur l'appareil ---
Vous pouvez entendre les oiseaux gazouiller et les gouttes de pluie frapper les feuilles au fur et à mesure de l'expédition.
Parfois, vous pouvez même entendre des sons beaucoup plus forts au loin ; quelle est la taille des animaux ici, de toute façon ?

L'appareil que les elfes vous ont donné a des problèmes avec plus que son système de communication. Vous essayez d'exécuter une
mise à jour du système :

$ system-update --please --pretty-please-with-sugar-on-top
Error: No space left on device
Peut-être pouvez-vous supprimer certains fichiers pour faire de la place pour la mise à jour ?

Vous parcourez le système de fichiers pour évaluer la situation et enregistrez la sortie du terminal résultante (votre entrée de
puzzle). 
Par exemple:

$ cd /
$ ls
dir a
14848514 b.txt
8504156 c.dat
dir d
$ cd a
$ ls
dir e
29116 f
2557 g
62596 h.lst
$ cd e
$ ls
584 i
$ cd ..
$ cd ..
$ cd d
$ ls
4060174 j
8033020 d.log
5626152 d.ext
7214296 k
Le système de fichiers consiste en une arborescence de fichiers (données brutes) et de répertoires (qui peuvent contenir d'autres
répertoires ou fichiers). Le répertoire le plus externe s'appelle /. Vous pouvez naviguer dans le système de fichiers, vous déplacer
dans ou hors des répertoires et répertorier le contenu du répertoire dans lequel vous vous trouvez actuellement.

Dans la sortie du terminal, les lignes qui commencent par $ sont des commandes que vous avez exécutées, un peu comme certains
ordinateurs modernes :

"cd" signifie changer de répertoire . Cela change le répertoire courant, mais le résultat spécifique dépend de l'argument :
"cd x" se déplace d' un niveau : il recherche dans le répertoire courant le répertoire nommé x et en fait le répertoire courant.
"cd .." descend d' un niveau : il trouve le répertoire qui contient le répertoire courant, puis fait de ce répertoire le répertoire courant.
"cd /" passe du répertoire courant au répertoire le plus externe, /.
"ls" signifie liste. Il imprime tous les fichiers et répertoires immédiatement contenus par le répertoire courant :
"123 abc" signifie que le répertoire courant contient un fichier "abc" de taille 123.
"dir xyz" signifie que le répertoire courant contient un répertoire nommé "xyz".
Compte tenu des commandes et de la sortie de l'exemple ci-dessus, vous pouvez déterminer que le système de fichiers ressemble visuellement
à ceci :

- / (dir)
  - a (dir)
    - e (dir)
      - i (file, size=584)
    - f (file, size=29116)
    - g (file, size=2557)
    - h.lst (file, size=62596)
  - b.txt (file, size=14848514)
  - c.dat (file, size=8504156)
  - d (dir)
    - j (file, size=4060174)
    - d.log (file, size=8033020)
    - d.ext (file, size=5626152)
    - k (file, size=7214296)
Ici, il y a quatre répertoires : /(le répertoire le plus externe), a et d (qui sont dans /), et e (qui est dans a). Ces répertoires
contiennent également des fichiers de différentes tailles.

Puisque le disque est plein, votre première étape devrait probablement être de trouver des répertoires qui sont de bons candidats pour la
suppression. Pour ce faire, vous devez déterminer la taille totale de chaque répertoire. La taille totale d'un répertoire est la somme des
tailles des fichiers qu'il contient, directement ou indirectement. (Les répertoires eux-mêmes ne comptent pas comme ayant une taille intrinsèque.)

Les tailles totales des répertoires ci-dessus peuvent être trouvées comme suit :

La taille totale du répertoire "e" est de 584 car il contient un seul fichier "i" de taille 584 et aucun autre répertoire.
Le répertoire "a" a une taille totale de 94853 car il contient les fichiers "f" (taille 29116), "g" (taille 2557) et "h.lst"(taille 62596), plus un
fichier "i" indirectement ("a" contient "e" qui contient i).
Le répertoire "d" a une taille totale de 24933642 .
En tant que répertoire le plus externe, "/" contient tous les fichiers. Sa taille totale est de 48381165 , la somme de la taille de chaque fichier.
Pour commencer, trouvez tous les répertoires avec une taille totale d'au plus 100000, puis calculez la somme de leurs tailles totales. Dans
l'exemple ci-dessus, ces répertoires sont "a" et "e" ; la somme de leurs tailles totales est 95437(94853 + 584). (Comme dans cet exemple, ce
processus peut compter les fichiers plus d'une fois !)

Trouvez tous les répertoires avec une taille totale d'au plus 100000. Quelle est la somme des tailles totales de ces répertoires ?

Votre réponse au puzzle était 1792222.

--- Deuxième partie ---
Vous êtes maintenant prêt à choisir un répertoire à supprimer.

L'espace disque total disponible pour le système de fichiers est de 70000000. Pour exécuter la mise à jour, vous avez besoin d'un espace inutilisé
d'au moins 30000000. Vous devez trouver un répertoire que vous pouvez supprimer et qui libérera suffisamment d'espace pour exécuter la mise à jour.

Dans l'exemple ci-dessus, la taille totale du répertoire le plus externe (et donc la quantité totale d'espace utilisé) est 48381165; cela signifie
que la taille de l' espace inutilisé doit actuellement être 21618835, ce qui n'est pas tout à fait les 30000000 requis par la mise à jour. Par
conséquent, la mise à jour nécessite toujours un répertoire d'une taille totale d'au moins 8381165à supprimer avant de pouvoir s'exécuter.

Pour y parvenir, vous disposez des options suivantes :

Supprimer le répertoire "e", ce qui augmenterait l'espace inutilisé de 584.
Supprimer le répertoire "a", ce qui augmenterait l'espace inutilisé de 94853.
Supprimer le répertoire "d", ce qui augmenterait l'espace inutilisé de 24933642.
Supprimer le répertoire "/", ce qui augmenterait l'espace inutilisé de 48381165.
Les répertoires "e" et "a" sont trop petits ; les supprimer ne libérerait pas assez d'espace. Cependant, les répertoires "d" et "/"sont tous les
deux suffisamment volumineux ! Entre ceux-ci, choisissez le plus petit : "d", en augmentant l'espace inutilisé de 24933642.

Trouvez le plus petit répertoire qui, s'il était supprimé, libérerait suffisamment d'espace sur le système de fichiers pour exécuter la mise à jour.
Quelle est la taille totale de ce répertoire ?

Votre réponse au puzzle était 1112963.
*/

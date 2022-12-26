package Pb13;
/**
--- Jour 13 : Signal de détresse ---
Vous montez la colline et essayez à nouveau de contacter les elfes. Cependant, vous recevez à la place un signal auquel vous ne vous attendiez
pas : un signal de détresse.

Votre appareil portable ne doit toujours pas fonctionner correctement ; les paquets du signal de détresse ont été décodés dans le désordre. Vous
devrez réorganiser la liste des paquets reçus (votre entrée de puzzle) pour décoder le message.

Votre liste se compose de paires de paquets ; les paires sont séparées par une ligne vide. Vous devez identifier combien de paires de paquets sont
dans le bon ordre.

Par exemple:

[1,1,3,1,1]
[1,1,5,1,1]

[[1],[2,3,4]]
[[1],4]

[9]
[[8,7,6]]

[[4,4],4,4]
[[4,4],4,4,4]

[7,7,7,7]
[7,7,7]

[]
[3]

[[[]]]
[[]]

[1,[2,[3,[4,[5,6,7]]]],8,9]
[1,[2,[3,[4,[5,6,0]]]],8,9]
Les données de paquet se composent de listes et de nombres entiers. Chaque liste commence par [, se termine par ], et contient zéro ou plusieurs
valeurs séparées par des virgules (soit des entiers, soit d'autres listes). Chaque paquet est toujours une liste et apparaît sur sa propre ligne.

Lors de la comparaison de deux valeurs, la première valeur est appelée left et la deuxième valeur est appelée right. Alors:

Si les deux valeurs sont des entiers, l' entier inférieur doit venir en premier. Si l'entier de gauche est inférieur à l'entier de droite, les
entrées sont dans le bon ordre. Si l'entier de gauche est supérieur à l'entier de droite, les entrées ne sont pas dans le bon ordre. Sinon, les
entrées sont le même entier ; continuer à vérifier la partie suivante de l'entrée.
Si les deux valeurs sont des listes, comparez la première valeur de chaque liste, puis la deuxième valeur, et ainsi de suite. Si la liste de gauche
n'a plus d'éléments en premier, les entrées sont dans le bon ordre. Si la liste de droite manque d'éléments en premier, les entrées ne sont pas dans
le bon ordre. Si les listes ont la même longueur et qu'aucune comparaison ne prend de décision sur l'ordre, continuez à vérifier la partie suivante
de l'entrée.
Si seulement l'une des valeurs est un entier, convertissez l'entier en une liste qui contient cet entier comme seule valeur, puis réessayez la
comparaison. Par exemple, si vous comparez [0,0,0] et 2, convertissez la valeur en [2](une liste contenant 2) ; le résultat est alors trouvé en
comparant à la place [0,0,0]et [2].
À l'aide de ces règles, vous pouvez déterminer lesquelles des paires de l'exemple sont dans le bon ordre :

== Pair 1 ==
- Compare [1,1,3,1,1] vs [1,1,5,1,1]
  - Compare 1 vs 1
  - Compare 1 vs 1
  - Compare 3 vs 5
    - Left side is smaller, so inputs are in the right order

== Pair 2 ==
- Compare [[1],[2,3,4]] vs [[1],4]
  - Compare [1] vs [1]
    - Compare 1 vs 1
  - Compare [2,3,4] vs 4
    - Mixed types; convert right to [4] and retry comparison
    - Compare [2,3,4] vs [4]
      - Compare 2 vs 4
        - Left side is smaller, so inputs are in the right order

== Pair 3 ==
- Compare [9] vs [[8,7,6]]
  - Compare 9 vs [8,7,6]
    - Mixed types; convert left to [9] and retry comparison
    - Compare [9] vs [8,7,6]
      - Compare 9 vs 8
        - Right side is smaller, so inputs are not in the right order

== Pair 4 ==
- Compare [[4,4],4,4] vs [[4,4],4,4,4]
  - Compare [4,4] vs [4,4]
    - Compare 4 vs 4
    - Compare 4 vs 4
  - Compare 4 vs 4
  - Compare 4 vs 4
  - Left side ran out of items, so inputs are in the right order

== Pair 5 ==
- Compare [7,7,7,7] vs [7,7,7]
  - Compare 7 vs 7
  - Compare 7 vs 7
  - Compare 7 vs 7
  - Right side ran out of items, so inputs are not in the right order

== Pair 6 ==
- Compare [] vs [3]
  - Left side ran out of items, so inputs are in the right order

== Pair 7 ==
- Compare [[[]]] vs [[]]
  - Compare [[]] vs []
    - Right side ran out of items, so inputs are not in the right order

== Pair 8 ==
- Compare [1,[2,[3,[4,[5,6,7]]]],8,9] vs [1,[2,[3,[4,[5,6,0]]]],8,9]
  - Compare 1 vs 1
  - Compare [2,[3,[4,[5,6,7]]]] vs [2,[3,[4,[5,6,0]]]]
    - Compare 2 vs 2
    - Compare [3,[4,[5,6,7]]] vs [3,[4,[5,6,0]]]
      - Compare 3 vs 3
      - Compare [4,[5,6,7]] vs [4,[5,6,0]]
        - Compare 4 vs 4
        - Compare [5,6,7] vs [5,6,0]
          - Compare 5 vs 5
          - Compare 6 vs 6
          - Compare 7 vs 0
            - Right side is smaller, so inputs are not in the right order
Quels sont les indices des paires qui sont déjà dans le bon ordre ? La première paire a l'index 1, la deuxième paire a l'index 2, et ainsi de
suite. Dans l'exemple ci-dessus, les paires dans le bon ordre sont 1, 2, 4 et 6 ; la somme de ces indices est 13.

Déterminez quelles paires de paquets sont déjà dans le bon ordre. Quelle est la somme des indices de ces paires ?

Votre réponse au puzzle était 5720.

--- Deuxième partie ---
Maintenant, il vous suffit de mettre tous les paquets dans le bon ordre. Ne tenez pas compte des lignes vides dans votre liste de paquets reçus.

Le protocole de signal de détresse nécessite également que vous incluiez deux paquets diviseurs supplémentaires :

[[2]]
[[6]]
En utilisant les mêmes règles qu'auparavant, organisez tous les paquets - ceux de votre liste de paquets reçus ainsi que les deux paquets
séparateurs - dans le bon ordre.

Pour l'exemple ci-dessus, le résultat de la mise des paquets dans le bon ordre est :

[]
[[]]
[[[]]]
[1,1,3,1,1]
[1,1,5,1,1]
[[1],[2,3,4]]
[1,[2,[3,[4,[5,6,0]]]],8,9]
[1,[2,[3,[4,[5,6,7]]]],8,9]
[[1],4]
[[2]]
[3]
[[4,4],4,4]
[[4,4],4,4,4]
[[6]]
[7,7,7]
[7,7,7,7]
[[8,7,6]]
[9]
Ensuite, localisez les paquets diviseurs. Pour trouver la clé du décodeur pour ce signal de détresse, vous devez déterminer les indices des
deux paquets diviseurs et les multiplier ensemble. Le premier paquet est à l'index 1, le deuxième paquet est à l'index 2, et ainsi de suite.
Dans cet exemple, les paquets diviseurs sont 10e et 14e, et donc la clé du décodeur est 140.

Organisez tous les paquets dans le bon ordre. Quelle est la clé du décodeur pour le signal de détresse ?

Votre réponse au puzzle était 23504.
*/

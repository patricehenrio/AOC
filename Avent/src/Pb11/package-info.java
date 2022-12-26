package Pb11;
/**
--- Jour 11 : Singe au Milieu ---
Alors que vous commencez enfin à remonter la rivière, vous vous rendez compte que votre sac est beaucoup plus léger que dans votre souvenir.
À ce moment-là, l'un des objets de votre sac vole au-dessus de votre tête. Les singes jouent Keep Away avec vos objets manquants !

Pour récupérer vos affaires, vous devez être capable de prédire où les singes jetteront vos objets. Après une observation attentive, vous
réalisez que les singes fonctionnent en fonction de votre inquiétude à propos de chaque élément .

Vous prenez des notes (votre entrée de puzzle) sur les objets que chaque singe possède actuellement, à quel point vous êtes inquiet à propos
de ces objets et comment le singe prend des décisions en fonction de votre niveau d'inquiétude. Par exemple:

Monkey 0:
  Starting items: 79, 98
  Operation: new = old * 19
  Test: divisible by 23
    If true: throw to monkey 2
    If false: throw to monkey 3

Monkey 1:
  Starting items: 54, 65, 75, 74
  Operation: new = old + 6
  Test: divisible by 19
    If true: throw to monkey 2
    If false: throw to monkey 0

Monkey 2:
  Starting items: 79, 60, 97
  Operation: new = old * old
  Test: divisible by 13
    If true: throw to monkey 1
    If false: throw to monkey 3

Monkey 3:
  Starting items: 74
  Operation: new = old + 3
  Test: divisible by 17
    If true: throw to monkey 0
    If false: throw to monkey 1
Chaque singe possède plusieurs attributs :

Starting items répertorie votre niveau d'inquiétude pour chaque élément que le singe tient actuellement dans l'ordre dans lequel ils seront
inspectés.
Operation montre comment votre niveau d'inquiétude change lorsque ce singe inspecte un objet. Une opération comme new = old * 5 signifie que
votre niveau d'inquiétude après que le singe a inspecté l'article est cinq fois supérieur à votre niveau d'inquiétude avant l'inspection.
Test montre comment le singe utilise votre niveau d'inquiétude pour décider où lancer un objet ensuite.
If true montre ce qui se passe avec un élément si Test était vrai.
If false montre ce qui se passe avec un élément si Test était faux.
Après que chaque singe ait inspecté un objet mais avant qu'il ne teste votre niveau d'inquiétude, votre soulagement que l'inspection du singe
n'ait pas endommagé l'objet divise votre niveau d'inquiétude par trois et l'arrondit à l'entier inférieur le plus proche.

Les singes inspectent et lancent à tour de rôle des objets. Au tour d'un seul singe , il inspecte et jette tous les objets qu'il tient un à la
fois et dans l'ordre indiqué. Le singe 0 joue en premier, puis le singe 1, et ainsi de suite jusqu'à ce que chaque singe ait eu un tour. Le
processus de chaque singe prenant un seul tour s'appelle un tour .

Lorsqu'un singe lance un objet à un autre singe, l'objet va à la fin de la liste du singe destinataire. Un singe qui commence une manche sans
objets pourrait finir par inspecter et lancer de nombreux objets au moment où son tour arrive. Si un singe ne tient aucun objet au début de son
tour, son tour se termine.

Dans l'exemple ci-dessus, le premier tour se déroule comme suit :

Monkey 0:
  Monkey inspects an item with a worry level of 79.
    Worry level is multiplied by 19 to 1501.
    Monkey gets bored with item. Worry level is divided by 3 to 500.
    Current worry level is not divisible by 23.
    Item with worry level 500 is thrown to monkey 3.
  Monkey inspects an item with a worry level of 98.
    Worry level is multiplied by 19 to 1862.
    Monkey gets bored with item. Worry level is divided by 3 to 620.
    Current worry level is not divisible by 23.
    Item with worry level 620 is thrown to monkey 3.
Monkey 1:
  Monkey inspects an item with a worry level of 54.
    Worry level increases by 6 to 60.
    Monkey gets bored with item. Worry level is divided by 3 to 20.
    Current worry level is not divisible by 19.
    Item with worry level 20 is thrown to monkey 0.
  Monkey inspects an item with a worry level of 65.
    Worry level increases by 6 to 71.
    Monkey gets bored with item. Worry level is divided by 3 to 23.
    Current worry level is not divisible by 19.
    Item with worry level 23 is thrown to monkey 0.
  Monkey inspects an item with a worry level of 75.
    Worry level increases by 6 to 81.
    Monkey gets bored with item. Worry level is divided by 3 to 27.
    Current worry level is not divisible by 19.
    Item with worry level 27 is thrown to monkey 0.
  Monkey inspects an item with a worry level of 74.
    Worry level increases by 6 to 80.
    Monkey gets bored with item. Worry level is divided by 3 to 26.
    Current worry level is not divisible by 19.
    Item with worry level 26 is thrown to monkey 0.
Monkey 2:
  Monkey inspects an item with a worry level of 79.
    Worry level is multiplied by itself to 6241.
    Monkey gets bored with item. Worry level is divided by 3 to 2080.
    Current worry level is divisible by 13.
    Item with worry level 2080 is thrown to monkey 1.
  Monkey inspects an item with a worry level of 60.
    Worry level is multiplied by itself to 3600.
    Monkey gets bored with item. Worry level is divided by 3 to 1200.
    Current worry level is not divisible by 13.
    Item with worry level 1200 is thrown to monkey 3.
  Monkey inspects an item with a worry level of 97.
    Worry level is multiplied by itself to 9409.
    Monkey gets bored with item. Worry level is divided by 3 to 3136.
    Current worry level is not divisible by 13.
    Item with worry level 3136 is thrown to monkey 3.
Monkey 3:
  Monkey inspects an item with a worry level of 74.
    Worry level increases by 3 to 77.
    Monkey gets bored with item. Worry level is divided by 3 to 25.
    Current worry level is not divisible by 17.
    Item with worry level 25 is thrown to monkey 1.
  Monkey inspects an item with a worry level of 500.
    Worry level increases by 3 to 503.
    Monkey gets bored with item. Worry level is divided by 3 to 167.
    Current worry level is not divisible by 17.
    Item with worry level 167 is thrown to monkey 1.
  Monkey inspects an item with a worry level of 620.
    Worry level increases by 3 to 623.
    Monkey gets bored with item. Worry level is divided by 3 to 207.
    Current worry level is not divisible by 17.
    Item with worry level 207 is thrown to monkey 1.
  Monkey inspects an item with a worry level of 1200.
    Worry level increases by 3 to 1203.
    Monkey gets bored with item. Worry level is divided by 3 to 401.
    Current worry level is not divisible by 17.
    Item with worry level 401 is thrown to monkey 1.
  Monkey inspects an item with a worry level of 3136.
    Worry level increases by 3 to 3139.
    Monkey gets bored with item. Worry level is divided by 3 to 1046.
    Current worry level is not divisible by 17.
    Item with worry level 1046 is thrown to monkey 1.
Après le premier tour, les singes tiennent des objets avec ces niveaux d'inquiétude :

Monkey 0: 20, 23, 27, 26
Monkey 1: 2080, 25, 167, 207, 401, 1046
Monkey 2: 
Monkey 3: 
Les singes 2 et 3 ne tiennent aucun objet à la fin du tour ; ils ont tous deux inspecté les objets pendant le tour et les ont tous jetés avant la
fin du tour.

Ce processus se poursuit pendant quelques tours supplémentaires :

After round 2, the monkeys are holding items with these worry levels:
Monkey 0: 695, 10, 71, 135, 350
Monkey 1: 43, 49, 58, 55, 362
Monkey 2: 
Monkey 3: 

After round 3, the monkeys are holding items with these worry levels:
Monkey 0: 16, 18, 21, 20, 122
Monkey 1: 1468, 22, 150, 286, 739
Monkey 2: 
Monkey 3: 

After round 4, the monkeys are holding items with these worry levels:
Monkey 0: 491, 9, 52, 97, 248, 34
Monkey 1: 39, 45, 43, 258
Monkey 2: 
Monkey 3: 

After round 5, the monkeys are holding items with these worry levels:
Monkey 0: 15, 17, 16, 88, 1037
Monkey 1: 20, 110, 205, 524, 72
Monkey 2: 
Monkey 3: 

After round 6, the monkeys are holding items with these worry levels:
Monkey 0: 8, 70, 176, 26, 34
Monkey 1: 481, 32, 36, 186, 2190
Monkey 2: 
Monkey 3: 

After round 7, the monkeys are holding items with these worry levels:
Monkey 0: 162, 12, 14, 64, 732, 17
Monkey 1: 148, 372, 55, 72
Monkey 2: 
Monkey 3: 

After round 8, the monkeys are holding items with these worry levels:
Monkey 0: 51, 126, 20, 26, 136
Monkey 1: 343, 26, 30, 1546, 36
Monkey 2: 
Monkey 3: 

After round 9, the monkeys are holding items with these worry levels:
Monkey 0: 116, 10, 12, 517, 14
Monkey 1: 108, 267, 43, 55, 288
Monkey 2: 
Monkey 3: 

After round 10, the monkeys are holding items with these worry levels:
Monkey 0: 91, 16, 20, 98
Monkey 1: 481, 245, 22, 26, 1092, 30
Monkey 2: 
Monkey 3: 

...

After round 15, the monkeys are holding items with these worry levels:
Monkey 0: 83, 44, 8, 184, 9, 20, 26, 102
Monkey 1: 110, 36
Monkey 2: 
Monkey 3: 

...

After round 20, the monkeys are holding items with these worry levels:
Monkey 0: 10, 12, 14, 26, 34
Monkey 1: 245, 93, 53, 199, 115
Monkey 2: 
Monkey 3: 
Chasser tous les singes à la fois est impossible; vous allez devoir vous concentrer sur les deux singes les plus actifs si vous voulez espérer
récupérer vos affaires. Comptez le nombre total de fois que chaque singe inspecte des objets sur 20 tours :

Monkey 0 inspected items 101 times.
Monkey 1 inspected items 95 times.
Monkey 2 inspected items 7 times.
Monkey 3 inspected items 105 times.
Dans cet exemple, les deux singes les plus actifs ont inspecté les objets 101 et 105 fois. Le niveau d'activité des singes dans cette situation
peut être trouvé en les multipliant ensemble : 10605.

Déterminez quels singes chasser en comptant le nombre d'objets qu'ils inspectent en 20 tours. Quel est le niveau d'activité des singes après 20
séries de manigances simiennes à lancer des objets ?

Votre réponse au puzzle était 112221.

--- Deuxième partie ---
Vous craignez de ne jamais récupérer vos articles. Tellement inquiet, en fait, que votre soulagement que l'inspection d'un singe n'ait pas endommagé
un objet ne divise plus votre niveau d'inquiétude par trois.

Malheureusement, ce soulagement était tout ce qui empêchait votre niveau d'inquiétude d'atteindre des niveaux ridicules. Vous devrez trouver un autre
moyen de garder votre niveau d'inquiétude gérable.

A ce rythme, vous risquez de supporter ces singes très longtemps - peut-être 10000 tours !

Avec ces nouvelles règles, vous pouvez toujours comprendre l'activité des singes après 10000 tours. En utilisant le même exemple ci-dessus :

== After round 1 ==
Monkey 0 inspected items 2 times.
Monkey 1 inspected items 4 times.
Monkey 2 inspected items 3 times.
Monkey 3 inspected items 6 times.

== After round 20 ==
Monkey 0 inspected items 99 times.
Monkey 1 inspected items 97 times.
Monkey 2 inspected items 8 times.
Monkey 3 inspected items 103 times.

== After round 1000 ==
Monkey 0 inspected items 5204 times.
Monkey 1 inspected items 4792 times.
Monkey 2 inspected items 199 times.
Monkey 3 inspected items 5192 times.

== After round 2000 ==
Monkey 0 inspected items 10419 times.
Monkey 1 inspected items 9577 times.
Monkey 2 inspected items 392 times.
Monkey 3 inspected items 10391 times.

== After round 3000 ==
Monkey 0 inspected items 15638 times.
Monkey 1 inspected items 14358 times.
Monkey 2 inspected items 587 times.
Monkey 3 inspected items 15593 times.

== After round 4000 ==
Monkey 0 inspected items 20858 times.
Monkey 1 inspected items 19138 times.
Monkey 2 inspected items 780 times.
Monkey 3 inspected items 20797 times.

== After round 5000 ==
Monkey 0 inspected items 26075 times.
Monkey 1 inspected items 23921 times.
Monkey 2 inspected items 974 times.
Monkey 3 inspected items 26000 times.

== After round 6000 ==
Monkey 0 inspected items 31294 times.
Monkey 1 inspected items 28702 times.
Monkey 2 inspected items 1165 times.
Monkey 3 inspected items 31204 times.

== After round 7000 ==
Monkey 0 inspected items 36508 times.
Monkey 1 inspected items 33488 times.
Monkey 2 inspected items 1360 times.
Monkey 3 inspected items 36400 times.

== After round 8000 ==
Monkey 0 inspected items 41728 times.
Monkey 1 inspected items 38268 times.
Monkey 2 inspected items 1553 times.
Monkey 3 inspected items 41606 times.

== After round 9000 ==
Monkey 0 inspected items 46945 times.
Monkey 1 inspected items 43051 times.
Monkey 2 inspected items 1746 times.
Monkey 3 inspected items 46807 times.

== After round 10000 ==
Monkey 0 inspected items 52166 times.
Monkey 1 inspected items 47830 times.
Monkey 2 inspected items 1938 times.
Monkey 3 inspected items 52013 times.
Après 10000 tours, les deux singes les plus actifs ont inspecté les objets 52166 et 52013 fois. En les multipliant ensemble, le niveau d' activité
des singes dans cette situation est maintenant de 2713310158.

Les niveaux d'inquiétude ne sont plus divisés par trois après l'inspection de chaque article ; vous devrez trouver un autre moyen de garder votre
niveau d'inquiétude gérable. En partant de l'état initial dans votre entrée de puzzle, quel est le niveau d'activité des singes après 10000 tours ?

Votre réponse au puzzle était 25272176808.
*/

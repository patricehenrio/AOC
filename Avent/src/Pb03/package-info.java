package Pb03;
/**
--- Jour 3 : Réorganisation du sac à dos ---
Un elfe a la tâche importante de charger tous les sacs à dos avec des fournitures pour le voyage dans la jungle . Malheureusement, cet elfe
n'a pas tout à fait suivi les instructions d'emballage, et donc quelques articles doivent maintenant être réarrangés.

Chaque sac à dos a deux grands compartiments . Tous les articles d'un type donné sont destinés à entrer exactement dans l'un des deux
compartiments. L'elfe qui a fait l'emballage n'a pas suivi cette règle pour exactement un type d'objet par sac à dos.

Les Elfes ont fait une liste de tous les objets actuellement dans chaque sac à dos (votre entrée de puzzle), mais ils ont besoin de votre aide
pour trouver les erreurs. Chaque type d'élément est identifié par une seule lettre minuscule ou majuscule (c'est-à-dire a et A font référence 
à différents types d'éléments).

La liste des éléments de chaque sac à dos est donnée sous forme de caractères sur une seule ligne. Un sac à dos donné a toujours le même nombre
d'articles dans chacun de ses deux compartiments, de sorte que la première moitié des caractères représente des articles dans le premier
compartiment, tandis que la seconde moitié des caractères représente des articles dans le deuxième compartiment.

Par exemple, supposons que vous ayez la liste suivante du contenu de six sacs à dos :

vJrwpWtwJgWrhcsFMMfFFhFp
jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL
PmmdzqPrVvPwwTWBwg
wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn
ttgJtRGJQctTZtZT
CrZsJsPPZsGzwwsLwLmpwMDw

Le premier sac à dos contient les objets vJrwpWtwJgWrhcsFMMfFFhFp, ce qui signifie que son premier compartiment contient les objets vJrwpWtwJgWr,
tandis que le deuxième compartiment contient les objets hcsFMMfFFhFp. Le seul type d'élément qui apparaît dans les deux compartiments est l'objet
p.
Les compartiments du deuxième sac à dos contiennent jqHRNqRjqzjGDLGL et rsFMfFZSrLrFZsSL. Le seul type d'élément qui apparaît dans les deux
compartiments est l'objet L.
Les compartiments du troisième sac à dos contiennent PmmdzqPrVet vPwwTWBwg; le seul type d'élément courant est P.
Les compartiments du quatrième sac à dos partagent uniquement le type d'article v.
Les compartiments du cinquième sac à dos ne partagent que t.
Les compartiments du sixième sac à dos ne partagent que s.
Pour vous aider à hiérarchiser la réorganisation des éléments, chaque type d'élément peut être converti en priorité :

Les types d'éléments en minuscules a à z ont des priorités de 1 à 26.
Les types d'éléments en majuscules A à Z ont des priorités de 27 à 52.
Dans l'exemple ci-dessus, la priorité du type d'article qui apparaît dans les deux compartiments de chaque sac à dos est 16 (p), 38 (L), 42 (P),
22 (v), 20 (t) et 19 (s) ; la somme de ceux-ci est 157.

Trouvez le type d'article qui apparaît dans les deux compartiments de chaque sac à dos. Quelle est la somme des priorités de ces types d'éléments ?

Votre réponse au puzzle était 7917.

--- Deuxième partie ---
Alors que vous finissez d'identifier les objets égarés, les elfes viennent vous voir avec un autre problème.

Par sécurité, les elfes sont divisés en groupes de trois. Chaque elfe porte un badge qui identifie son groupe. Pour plus d'efficacité, au sein de
chaque groupe de trois elfes, le badge est le seul type d'objet porté par les trois elfes . Autrement dit, si le badge d'un groupe est de type
objet B, alors les trois elfes auront un type d'objet B quelque part dans leur sac à dos, et au plus deux des elfes porteront tout autre type d'objet.

Le problème est que quelqu'un a oublié de mettre l'autocollant d'authenticité mis à jour cette année sur les badges. Tous les badges doivent être
retirés des sacs à dos afin que les nouveaux autocollants d'authenticité puissent être attachés.

De plus, personne n'a noté quel type d'élément correspond aux badges de chaque groupe. La seule façon de savoir quel type d'objet est le bon est de
trouver le type d'objet commun aux trois elfes de chaque groupe.

Chaque ensemble de trois lignes de votre liste correspond à un seul groupe, mais chaque groupe peut avoir un type d'élément de badge différent. Ainsi,
dans l'exemple ci-dessus, les sacs à dos du premier groupe sont les trois premières lignes :

vJrwpWtwJgWrhcsFMMfFFhFp
jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL
PmmdzqPrVvPwwTWBwg

Et les sacs à dos du deuxième groupe sont les trois lignes suivantes :

wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn
ttgJtRGJQctTZtZT
CrZsJsPPZsGzwwsLwLmpwMDw

Dans le premier groupe, le seul type d'élément qui apparaît dans les trois sacs à dos est r; ce doit être leurs badges. Dans le deuxième groupe, leur
type d'élément de badge doit être Z.

Encore faut-il trouver des priorités pour ces items afin d'organiser les efforts de pose d'autocollants : ici, ils sont 18 (r) pour le premier groupe
et 52 (Z) pour le second groupe. La somme de ceux-ci est 70.

Trouvez le type d'objet qui correspond aux badges de chaque groupe de trois elfes. Quelle est la somme des priorités de ces types d'éléments ?

Votre réponse au puzzle était 2585.
 */

package Pb17;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.UnaryOperator;

import AOC.AOC;


/**
 * Pour la partie 2, la méthode utilisée dans la partie 1 est beaucoup trop longue pour donner un 
 * résulat (plus de 4 heures). D'autres programmeurs d'AOC m'ont donné la solution en recherchant
 * un "cycle", c'est à dire des instants où l'on est rendu au même index dans les mouvements, 
 * la même forme et les mêmes rochers au sommet du tas.
 * Comme on cherche un état périodique, on peut se limiter à la recherche lorsque l'on retrouve
 * la forme 0
 * 
 * Toutes les méthodes spécifiques à la partie 2 seront suffixées avec un 2, celles de la partie 1
 * seront suffixées avec un 1
 * 
 * De plus le nombre de coups à tester est un long (entier > 2147483647) et ne peut plus être
 * un index d'une liste. Le tas de rochers est potentiellement un long aussi. Il faut
 * changer le tas de rochers en HashMap<Long, Byte> au lieu de List<Byte>.
 * 
 */

public class Pb17 extends AOC 
{
	//les 5 formes de rocher -, +, L renversé, I, carré
	static private Rocher[] rochers = new Rocher[5];
	//le nombre de mouvements (quand on a épuisé tous les mouvements possibles
	//on reprend au début des mouvements)
	static private int nombreMouvements;
	//les mouvements des rochers (gauche/droite)
	static private char[] mouvements;
	//les 7 bytes < 128 avec un seul bit à 1
	//ils servent pour isoler un bit particulier d'un byte.
	static byte[]puissanceDe2 = {1,2,4,8,16,32,64};
	
	public static void main(String[] args) throws IOException 
	{
		//les données
		String donnees = chargerDonnees("pb 17.txt");
		
		//test
		//donnees = ">>><<><>><<<>><>>><<<>>><<<><<<>><>><<>>";
		
		//les mouvements
		mouvements = donnees.toCharArray();
		nombreMouvements = mouvements.length;

		//les formes : la réprésentation sous forme de String, la largeur du motif
		//La forme -
		rochers[0] = new Rocher("####", 4);
		//la forme +
		rochers[1] = new Rocher(" # \n###\n # ", 3);
		//la forme L renversé
		rochers[2] = new Rocher("  #\n  #\n###", 3);
		//la forme I
		rochers[3] = new Rocher("#\n#\n#\n#", 1);
		//la forme carré
		rochers[4] = new Rocher("##\n##", 2);
		
		//pour ne pas encombrer la mémoire avec cette variable qui ne sert plus
		donnees = null;
		
		//les durées des traitements : parties 1 et 2
        long debut = System.currentTimeMillis();
		//première partie
		System.out.println(partie_1());
        long duree = System.currentTimeMillis() - debut;
        System.out.println(duree + "ms");
        debut = System.currentTimeMillis();
        System.out.println("*****************************");
		//Deuxième partie
		System.out.println(partie_2());
		duree = (System.currentTimeMillis() - debut);
		System.out.println(duree + "ms");
	}

	/**
	 * N rocher tombent, sont poussés à droite ou à gauche et recommencent à tomber jusqu'à
	 * ne plus pouvoir tomber.
	 * 
	 * @return la hauteur des rochers tombés
	 */
	private static long partie_2() 
	{
		//le tas de rocher est un dictionnaire : la clé est l'ordonnée dans le tas (la hauteur)
		//la valeur est le byte représentant la ligne (entre 0 et 127)
		HashMap<Long,Byte> tas = new HashMap<Long, Byte>();
		//le nombre de rochers qui doivent tomber au total
		long nbRochersTotal = 1000000000000L;
		//l'index des rochers (0 à 4), on commence par la forme première forme, celle d'indice 0
	    int indexRocher = 0;
	    //l'index dans les mouvements (0 à 10090), on commence par le premier mouvement, celui d'indice 0
        int indexMouvement = 0;
        
        //dictionnaire des états du tas de rocher
		//La clé est l'index du mouvement
        //La valeur est un record de deux valeurs : la hauteur du tas au moment où l'on 
        //enregistre l'état, la hauteur de la base du rocher en cours
        //on enregistre une liste des couples RocherHauteur pour chaque index du mouvement pour lequel
        //la forme concernée est la forme "####" (0)
	    HashMap<Integer, List<RocherHauteur>> etatDuTas = new HashMap<Integer, List<RocherHauteur>>();  
        
		for(long round = 0; round < nbRochersTotal; round++)
		{
			//le rocher qui tombe 
            Rocher cur = rochers[indexRocher];

            //l'abscisse du rocher, il démarre avec x = 2
			//le rocher est une forme représentée par un tableau de bytes du type 0xxxxxxx
			//c'est à dire des bytes entre 0 et 127.
			//x est le nombre de 0 à gauche de xxxxxxx pour la base de la forme.
			int x = 2;
			//ordonnée du bas du rocher 
			//le rocher commence à tomber 3 lignes au dessus du tas et bouge à gauche ou à 
			//droite. Les mouvements sont dans l'ordre : chute puis droite ou gauche.
			//Donc pour démarrer 3 lignes au dessus du tas, il faut placer le rocher 4 lignes
			//au dessus du tas pour que la première chute le passe sur la 3ème ligne.
			//Cette astuce permet de traiter tous les déplacements de la même façon.
            long y = sommetDuTas2(tas) + 4;
            //la forme du rocher qui tombe
 			List<Byte> forme = new ArrayList<Byte>(cur.rocher);
 			
			//tant que la chute n'est pas terminée, on chute puis on déplace à gauche ou à droite
			while(chutePossible2(forme, tas, y))
			{
				//chute (l'ordonnée est décrémentée)
				y--;

				//mouvement gauche ou droite (modification de l'abscisse)
				//c'est à dire modification du byte par exemple 0011100 devient 0111000 pour un
				//déplacement vers la gauche et 0001110 pour un déplacement vers la droite.
				x = mouvement2(tas, forme, x, y, cur.largeur, indexMouvement);
				
				//le mouvement gauche/droite suivant
				indexMouvement++;
				if (indexMouvement == nombreMouvements) indexMouvement = 0;
			}

			//la forme est arrêtée : y = ordonnée de la base de la forme, x n'est plus utilisé
			ajoute2(tas, forme, y);
			
			//Si l'état correspond à la forme 0, on stocke l'état du tas à ce moment dans un 
			//dictionnaire, la clé est l'index du mouvement, la valeur est la liste des états du
			//tas correspondants à cette clé, c'est à dire une liste de RocherHauteur
			if (indexRocher == 0)
			{
				//quelle est la liste des états pour ce mouvement
				List<RocherHauteur> liste = etatDuTas.get(indexMouvement);
				//si cette liste est null, on l'initialise à vide
				if (liste == null) liste = new ArrayList<RocherHauteur>();
				//on ajoute l'état RocherHauteur au début de cette liste
				liste.add(0,new RocherHauteur(round, y));
				//on modifie la valeur de l'état du tas pour cette clé
				etatDuTas.put(indexMouvement, liste);
				//Si on dispose d'au moins 3 correspondance, on compare la valeur du tas pour
				//les deux dernières valeurs ajoutées.
				//je n'ai pas compris pourquoi il fallait au moins 3 valeurs pour comparer
				if (liste.size() > 2)
				{
					//On compare les deux dernières valeurs entrées dans la liste (c'est à dire celles
					//d'index 0 et 1 car les valeurs sont ajoutées au début. Ces valeurs correspondent 
					//à des lignes du tas. On vérifie si les deux lignes du tas sont identiques. 
					if (equals2(liste.get(0).hauteur, liste.get(1).hauteur, tas))
					{
						System.out.println("liste = " + liste);
						RocherHauteur rh0 = liste.get(0);
						RocherHauteur rh1 = liste.get(1);
						//début de la période
						long debutPeriode = rh1.rocher;
						System.out.println("debutPeriode = " + debutPeriode);
						//fin de la période
						long finPeriode = rh0.rocher;
						System.out.println("finPeriod = " + finPeriode);
						//nombre de rochers dans la période
						long periodeRochers = finPeriode - debutPeriode;
						System.out.println("nombre de rochers dans la période = " + periodeRochers);
						//hauteur de rochers de la période
						long hauteurPeriode = rh0.hauteur - rh1.hauteur;
						System.out.println("hauteur de la période = " + hauteurPeriode);
						//nombre de périodes dans 1000000000000L - début de la période
						long nombrePeriodes = (nbRochersTotal - debutPeriode)/ periodeRochers;
						System.out.println("nombre de périodes = " + nombrePeriodes);
						//Combien manque-t-il de rochers pour atteindre 1000000000000L
						long resteIterations = nbRochersTotal - nombrePeriodes * periodeRochers - debutPeriode;
						System.out.println("resteIterations = " + resteIterations);
						//il faut la hauteur à ajouter pour ce nombre de rochers qui manque
						//mais à partir du début de la période
						long valeurATester = debutPeriode + resteIterations;
						System.out.println("valeurATester = " + valeurATester);
						//hauteur du tas à l'issue des périodes nécessaires pour se rapprocher
						//de 1000000000000L
						long hauteurAtteinte = nombrePeriodes * hauteurPeriode;
						System.out.println("hauteurAtteinte = " + hauteurAtteinte);
						//hauteur à rajouter avec le reliquat de rochers
						Long hauteurDeRochersAvantEtApres = 0L;
						for(List<RocherHauteur> l : etatDuTas.values())
						{
							for(int i = 0; i < l.size(); i++)
							{
								RocherHauteur rh = l.get(i);
								long rocher = rh.rocher;
								if (rocher == valeurATester)
								{
									hauteurDeRochersAvantEtApres = rh.hauteur;
									break;
								}
							}
							if (hauteurDeRochersAvantEtApres > 0) break;
						}
						System.out.println("hauteurDeRochersAvantEtApres = " + hauteurDeRochersAvantEtApres);
						//le résultat cherché
						long resultat = hauteurDeRochersAvantEtApres + hauteurAtteinte;
						System.out.println("resultat = " + resultat);
							
						return resultat;
					}
				}
			}
			//le rocher suivant avant de boucler
			indexRocher++;
			if (indexRocher == 5) indexRocher = 0;
		}
		//juste pour renvoyer uen valeur dans tous les cas
		return 0L;
	}

	/**
	 * Renvoie vrai si la ligne y0 du tas est identique à la ligne y1
	 * @param y0	la première ligne à tester
	 * @param y1	la deuxième ligne à tester
	 * @param tas	le tas de rochers
	 * @return		vrai ou faux
	 */
	private static boolean equals2(long y0, long y1, HashMap<Long, Byte> tas) 
	{
		String s0 = lignes2(tas, y0);
		String s1 = lignes2(tas, y1);
		
		return s0.equals(s1);
	}

	/**
	 * renvoie la ligne du tas en dessous de la ligne y.
	 * @param tas	le tas de rochers
	 * @param y		la ligne en dessous de laquelle on regarde
	 * @return		une String qui représente les lignes	
	 */
	private static String lignes2(HashMap<Long, Byte> tas, long y) 
	{
		String s = "(";
		y--;
		s += tas.get(y) + ", ";
		s += ")";
		
		return s.replace(", )", ")");
	}

	/**
	 * On ajoute le rocher sur le tas. On a vérifié auparavant que ce rocher pouvait 
	 * s'insérer à la ligne y
	 * @param tas	le tas de rochers
	 * @param forme	le rocher à insérer
	 * @param y		l'ordonnée où s'insère 
	 */
	private static void ajoute2(HashMap<Long, Byte> tas, List<Byte> forme, long y) 
	{
		//le rocher s'insère dans le tas (pas nécessairement au sommet du tas)
		//l'ordonnée de la base du rocher dans le tas de rochers 
		long yt = y;
		//pour chaque ligne de la forme
		for(int yf = 0; yf < forme.size(); yf++)
		{
			//le byte correspondant à la ligne yf du rocher 
			byte bf = forme.get(yf);
			//le byte correspondant à la ligne yt du tas
			Byte bt = tas.get( yt);
			if (bt == null) bt = 0;
			//l'ajout n'est possible que si bf et bt n'ont aucun bit à 1 commun, il faut que bf ait la place de 
			//s'insérer. Sinon c'est une erreur (cela a normalement été vérifié avant l'ajout)
			if ((bf & ~bt) != bf) throw new RuntimeException("Le rocher " + forme + " ne peut pas s'insérer à cet"
					+ " endroit dans le tas " + tas + "\n\tyt = " + yt + " bt = " + Integer.toBinaryString(bt)
														   + "\n\tyf = " + yf + " bf = " + Integer.toBinaryString(bf));
			//sinon on remplace (ou on place) le byte bf|bt à la ligne yt
			tas.put(yt, (byte) (bf | bt));
			//la ligne au dessus
			yt++;
		}
	}

	/**
	 * la chute n'est possible que si le rocher a la place de s'insérer
	 * 
	 * @param forme la forme à insérer
	 * @param tas	le tas de rochers
	 * @param y		l'ordonnée où l'on cherche à insérer le rocher
	 * @return		vrai ou faux selon que l'insertion est possible ou non
	 */
	private static boolean chutePossible2(List<Byte> forme, HashMap<Long, Byte> tas, long y) 
	{
		long yt = y - 1;
		if (yt < 0) return false;
		for(int yf = 0; yf < forme.size(); yf++)
		{
			byte bf = forme.get(yf);
			Byte bt = tas.get(yt);
			if (bt == null) bt = 0;
			//le déplacement n'est possible que si bf et bt n'ont aucun bit à 1 commun
			if ((bf & ~bt) != bf) 
			{
				return false;
			}
			yt++;
		}
		//on ne renvoie vrai que si tout le rocher peut s'insérer 
		return true;
	}

	/**
	 * déplacement à gauche ou à droite
	 * 
	 * @param tas		le tas de rochers
	 * @param forme		la forme à déplacer
	 * @param x			l'abscisse de la base du rocher
	 * @param y			l'ordonnée de la base du rocher
	 * @param largeur	largeur du rocher
	 * @param mouvement	l'index du mouvement
	 * @return			la nouvelle abscisse du rocher
	 */
	private static int mouvement2(HashMap<Long, Byte> tas, List<Byte> forme, int x, long y, 
									int largeur, int mouvement) 
	{
		//x = mouvement(tas, forme, x, y, rocher.largeur, mouvement);
		char c = mouvements[mouvement];
		switch(c)
		{
			case '>' :
				if (x + largeur >= 7) return x;
				if (!deplacementDroitePossible2(y, forme, tas)) return x;
				forme.replaceAll(shiftDroite());
				return ++x;
			case '<' :
				if (x <= 0) return x;
				if (!deplacementGauchePossible2(y, forme, tas)) return x;
				forme.replaceAll(shiftGauche());
				return --x;
			default : throw new RuntimeException("Erreur de caractère " + mouvement);	
		}
	}

	/**
	 * L'ordonnée du sommet du tas
	 * @param	tas le tas de rochers
	 * @return	le nombre de lignes du tas 
	 */
	private static long sommetDuTas2(HashMap<Long, Byte> tas) 
	{
		return tas.keySet().size();
	}

	/**
	 * Renvoie vrai si le rocher peut se déplacer vers la gauche
	 * @param y		la ligne où se trouve le rocher
	 * @param forme	le rocher à déplacer
	 * @param tas	le tas de rocher
	 * @return		vrai ou faux
	 */
	private static boolean deplacementGauchePossible2(long y, List<Byte> forme, HashMap<Long, Byte> tas) 
	{
		for(int yf = 0; yf < forme.size(); yf++)
		{
			byte bf = forme.get(yf);
			long yt = y + yf;
			Byte bt = tas.get(yt);
			if (bt == null) bt = 0;
			//le déplacement n'est possible que si la case à gauche de la forme est libre
			//on décale vers la gauche les bits, mais comme on ne s'intéresse qu'aux 7 premiers
			//on supprime les bits excédentaires via l'opération & 127.
			bf = (byte) ((bf << 1) & 127);
			//le premier bit à 1 en commençant par le bit le plus élevé
			//le premier bit à 1 de 00000100 (4) est le bit 29 car un entier est codé sur 4 octets
			//on ne s'intéresse qu'aux 7 premiers octets d'où l'opération utilisée (31 - 29 = 2)
			int bitLibre = 31 - Integer.numberOfLeadingZeros(bf);
			if ((bt & puissanceDe2[bitLibre]) != 0) return false;
		}
		//on ne renvoie vrai que si tout le rocher peut se déplacer
		return true;
	}

	/**
	 * Renvoie vrai si le rocher peut se déplacer vers la droite
	 * @param y		la ligne où se trouve le rocher
	 * @param forme	le rocher à déplacer
	 * @param tas	le tas de rocher
	 * @return		vrai ou faux
	 */
	private static boolean deplacementDroitePossible2(long y, List<Byte> forme, HashMap<Long, Byte> tas) 
	{
		for(int yf = 0; yf < forme.size(); yf++)
		{
			byte bf = forme.get(yf);
			long yt = y + yf;
			Byte bt = tas.get(yt);
			if (bt == null) bt = 0;
			//le déplacement n'est possible que si la case à droite de la forme est libre
			//déplacement à droite de bf
			//00001000 (8) devient 00000100 (4)
			bf = (byte) (bf >> 1);
			
			//premier bit à 1 l'écriture binaire de bf en commençant par les unités
			//le premier bit à 1 de 00000100 (4) est le bit 2 (la numérotation des bits commencent à 0)
			int bitLibre = Integer.numberOfTrailingZeros(bf);
			if ((bt & puissanceDe2[bitLibre]) != 0) return false;
		}
		//on ne renvoie vrai que si tout le rocher peut se déplacer
		return true;
	}

	/**
	 * N rocher tombent, sont poussés à droite ou à gauche et recommencent à tomber jusqu'à
	 * ne plus pouvoir tomber.
	 * 
	 * @return la hauteur des rochers tombés
	 */
	private static Long partie_1() 
	{
		List<Byte> tas = new ArrayList<Byte>();
		//le nombre de rochers qui doivent tomber
		long goal = 2022;
		//l'index du rocher tombant (0 à 4)
		int n= 0;
		//l'index du mouvement en cours ( 0 à mouvements.length()-1)
		int mouvement = -1;
		//i est le rocher i-ème rocher tombant 
		for(int i = 0; i < goal; i++)
		{
			//le rocher concerné
			Rocher rocher = rochers[n];
			//l'index de rocher suivant
			n++;
			if (n == 5) n = 0;
			//le rocher démarre avec x = 2
			int x = 2;
			//et y = 4 lignes au dessus du tas de façon que le premier mouvement vers le bas
			//l'amène 3 lignes au dessus du tas
			//pour ne pas traiter à part le premier mouvement je le fais démarrer une ligne au
			//dessus de ce qui est nécessaire, la première chute l'ammène au bon endroit.
			int y = tas.size() + 4;
			//la forme du rocher, c'est un  byte < 128. Cette valeur va être modifiée par les
			//mouvements à gauche et à droite (décalage d'un rang dans le même sens des bits 
			//de la forme)
			List<Byte> forme = new ArrayList<Byte>(rocher.rocher);

			//tant que la chute n'est pas terminée, on chute puis on déplace à gauche ou à droite
			while(chutePossible1(forme, tas, y))
			{
				y--;
				//mouvement gauche ou droite
				mouvement++;
				if (mouvement == nombreMouvements) mouvement = 0;
				x = mouvement1(tas, forme, x, y, rocher.largeur, mouvement);
			}
			ajoute1(tas, forme, y);
//			affiche(tas);
//			if (hauteurDuTas(tas) > 5) break;
		}
		return (long) tas.size();
	}

	/**
	 * la chute n'est possible que si le rocher a la place de s'insérer
	 * 		
	 * @param forme la forme du rocher
	 * @param tas 	le tas de rochers déjà tombés
	 * @param y		la hauteur où se trouve la base du rocher 
	 * @return		vrai ou faux
	 */
	private static boolean chutePossible1(List<Byte>forme, List<Byte> tas, int y)
	{
		long yt = y - 1;
		if (yt < 0) return false;
		for(int yf = 0; yf < forme.size(); yf++)
		{
			byte bf = forme.get(yf);
			byte bt = yt < tas.size() ? tas.get((int) yt) : 0;
			//le déplacement n'est possible que si bf et bt n'ont aucun bit à 1 commun
			if ((bf & ~bt) != bf)
			{
				return false;
			}
			yt++;
		}
		//on ne renvoie vrai que si tout le rocher peut s'insérer 
		return true;
	}
	
	/**
	 * déplacement à gauche ou à droite
	 * 
	 * @param tas		le tas de rochers déjà tombés
	 * @param forme		le rocher en train de tomber
	 * @param x			l'abscisse de la base du rocher
	 * @param y			l'ordonnée de la base du rocher
	 * @param largeur	largeur de la base du rocher
	 * @param mouvement	la caractère du mouvement
	 * @return 			la nouvelle valeur de x
	 */
	private static int mouvement1(List<Byte> tas, List<Byte> forme, int x, int y, int largeur, int mouvement) 
	{
		char c = mouvements[mouvement];
		switch(c)
		{
			case '>' :
				if (x + largeur >= 7) return x;
				if (!deplacementDroitePossible1(y, forme, tas)) 
				{
					return x;
				}
				forme.replaceAll(shiftDroite());
				return ++x;
			case '<' :
				if (x <= 0) return x;
				if (!deplacementGauchePossible1(y, forme, tas)) return x;
				forme.replaceAll(shiftGauche());
				return --x;
			default : throw new RuntimeException("Erreur de caractère " + mouvement);	
		}
	}

	/**
	 * Le mouvement vers la droite est-il possible
	 * 
	 * @param y		la hauteur où se trouve le rocher
	 * @param forme	la forme du rocher
	 * @return		vrai ou faux
	 */
	private static boolean deplacementDroitePossible1(int y, List<Byte> forme, List<Byte> tas) 
	{
		for(int yf = 0; yf < forme.size(); yf++)
		{
			byte bf = forme.get(yf);
			long yt = y + yf;
			byte bt = yt < tas.size() ? tas.get((int) yt) : 0;
			//le déplacement n'est possible que si la case à droite de la forme est libre
			//déplacement à droite de bf
			//00001000 (8) devient 00000100 (4)
			bf = (byte) (bf >> 1);
			
			//premier bit à 1 l'écriture binaire de bf en commençant par les unités
			//le premier bit à 1 de 00000100 (4) est le bit 2 (la numérotation des bits commencent à 0)
			int bitLibre = Integer.numberOfTrailingZeros(bf);
			if ((bt & puissanceDe2[bitLibre]) != 0) return false;
		}
		//on ne renvoie vrai que si tout le rocher peut se déplacer
		return true;
	}

	private static boolean deplacementGauchePossible1(int y, List<Byte> forme, List<Byte> tas) 
	{
		for(int yf = 0; yf < forme.size(); yf++)
		{
			byte bf = forme.get(yf);
			long yt = y + yf;
			byte bt = yt < tas.size() ? tas.get((int) yt) : 0;
			//le déplacement n'est possible que si la case à gauche de la forme est libre
			//on décale vers la gauche les bits, mais comme on ne s'intéresse qu'aux 7 premiers
			//on supprime les bits excédentaires via l'opération & 127.
			bf = (byte) ((bf << 1) & 127);
			//le premier bit à 1 en commençant par le bit le plus élevé
			//le premier bit à 1 de 00000100 (4) est le bit 29 car un entier est codé sur 4 octets
			//on ne s'intéresse qu'aux 7 premiers octets d'où l'opération utilisée (31 - 29 = 2)
			int bitLibre = 31 - Integer.numberOfLeadingZeros(bf);
			if ((bt & puissanceDe2[bitLibre]) != 0) return false;
		}
		return true;
	}

	/**
	 * Ajout de la forme 
	 * 
	 * @param tas	le tas de rochers déjà tombés
	 * @param forme	la forme du rocher qui tombe
	 * @param y		la hauteur où doit se placer le rocher
	 */
	private static void ajoute1(List<Byte> tas, List<Byte> forme, long y) 
	{
		long yt = y;
		for(int yf = 0; yf < forme.size(); yf++)
		{
			byte bf = forme.get(yf);
			byte bt = yt < tas.size() ? tas.get((int) yt) : 0;
			//le déplacement n'est possible que si bf et bt n'ont aucun bit à 1 commun
			//sinon c'est une erreur
			if ((bf & ~bt) != bf) throw new RuntimeException("yt = " + yt + " bt = " + Integer.toBinaryString(bt)
														   + " yf = " + yf + " bf = " + Integer.toBinaryString(bf)
														   + " forme = " + forme + " tas = " + tas);
			if (yt < tas.size()) tas.set((int) yt, (byte)(bf | bt));
			else tas.add(bf);
			yt++;
		}
	}

	/**
	 * Pour utiliser avec la méthode replace de List
	 * @return une opération unaire
	 */
	private static UnaryOperator<Byte> shiftGauche() 
	{
		UnaryOperator<Byte> operation = new UnaryOperator<Byte>() 
		{
			@Override
			public Byte apply(Byte t) 
			{
				return (byte) ((t << 1) & 127);
			}
		};
		return operation;
	}
	private static UnaryOperator<Byte> shiftDroite()
	{
		UnaryOperator<Byte> operation = new UnaryOperator<Byte>() 
		{
			@Override
			public Byte apply(Byte t) 
			{
				return (byte) (t >> 1);
			}
		};
		return operation;
	}
	
	/**
	 * La classe rocher 
	 */
	static class Rocher
	{
		int largeur;
		List<Byte> rocher = new ArrayList<Byte>();
		
		public Rocher(String s, int largeur) 
		{
			this.largeur = largeur;
			switch(s)
			{
				case "####" : 
					rocher.add((byte) 30);
					break;
				case " # \n"
				   + "###\n"
				   + " # ":
					rocher.add((byte) 8);
					rocher.add((byte) 28);
					rocher.add((byte) 8);
					break;
				case "  #\n"
				   + "  #\n"
				   + "###" :
					rocher.add((byte) 28);
					rocher.add((byte) 4);
					rocher.add((byte) 4);
					break;
				case "#\n"
				   + "#\n"
				   + "#\n"
				   + "#" :
					rocher.add((byte) 16);
					rocher.add((byte) 16);
					rocher.add((byte) 16);
					rocher.add((byte) 16);
					break;
				case "##\n"
				   + "##" :
					rocher.add((byte) 24);
					rocher.add((byte) 24);
					break;
				default : throw new RuntimeException("forme incorrecte :\n" + s);
			}
		}
	}
    
	/**
	 * Le couple (numéro du rocher qui tombe, ordonnée du rocher dans le tas)
	 */
    record RocherHauteur(long rocher, long hauteur) {}
}

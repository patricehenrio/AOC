package Pb16;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

import AOC.AOC;

/**
 * J'utilise un nombre à N bits, où N est le nombre de valves à flux non nul. Les valves à flux nul n'apportent 
 * rien en terme de pression libérée.
 * 2^N - 1 correspond à toutes les valves ouvertes (N bits à 1), 0 correspond à toutes les valves fermées ( N bits à 0).
 * 
 * 
 * @author Patrice
 *
 */
public class Pb16 extends AOC 
{
	//un dictionnaire des vannes avec pour clé le nom de la vanne 
	static HashMap<String,Valve> valves = new HashMap<String,Valve>();
	//les noms des valves
	static TreeSet<String> cles = new TreeSet<String>();
	//la liste des valves à flux non nul
	static List<String> valvesAFluxNonNul = new ArrayList<String>();
	//le  coût en temps pour aller d'une valve à une autre
	static HashMap<String, HashMap<String, Integer>> chemins = new HashMap<>();
	//le nombre de valves à flux non nul
	private static int N;
	//les puissances de 2 de 1 à 2^N
	private static int[] puissancesDe2;
	//les flux des valves à flux non nul
	private static int[] flux;
	//2^N, toutes les valves ouvertes est un entier de N bits à 1, soit 2^N-1
	private static int etatMaximum;
	//les pressions libérées en fonction du temps, de la valve et de l'état des valves,
	//cela permet d'étudier toutes les configurations possibles en fonction de l'instant
	//et donc de l'ordre dans lequel on ouvre les valves
	private static int[][][] pressions;
	
	public static void main(String[] args) throws IOException 
	{
		//les données
		String donnees = chargerDonnees("pb 16.txt");
		
		//test
//		donnees = "Valve AA has flow rate=0; tunnels lead to valves DD, II, BB\n"
//				+ "Valve BB has flow rate=13; tunnels lead to valves CC, AA\n"
//				+ "Valve CC has flow rate=2; tunnels lead to valves DD, BB\n"
//				+ "Valve DD has flow rate=20; tunnels lead to valves CC, AA, EE\n"
//				+ "Valve EE has flow rate=3; tunnels lead to valves FF, DD\n"
//				+ "Valve FF has flow rate=0; tunnels lead to valves EE, GG\n"
//				+ "Valve GG has flow rate=0; tunnels lead to valves FF, HH\n"
//				+ "Valve HH has flow rate=22; tunnel leads to valve GG\n"
//				+ "Valve II has flow rate=0; tunnels lead to valves AA, JJ\n"
//				+ "Valve JJ has flow rate=21; tunnel leads to valve II";
		initialisation(donnees);

		//première partie
		System.out.println(partie_1());
		//Deuxième partie
		System.out.println(partie_2());
		
		
	}

	private static int partie_2() 
	{
		//le tableau des pressions a été rempli dans la partie 1
		
        int pression = 0;
		//on parcourt tous les états possibles des valves (pour les deux opérateurs en même temps)
        for(int e = 1; e < etatMaximum; e++)
        {
        	//les deux opérateurs s'occupent chacun d'un ensemble disjoint de valves représentés par deux états distincts
        	//on parcourt tous les états possibles des valves pour l'opérateur 2 (par exemple)
        	for(int e2 = 1; e2 < etatMaximum; e2++)
        	{
        		//les valves ouvertes par l'opérateur 2 (e2) doivent être ouvertes dans l'état des deux opérateurs (e)
        		if ((e & e2) != e2) continue;
        		int p1 = 0, p2 = 0;
        		int e1 = e & ~e2;
        		//on cherche la valve libérant le plus de pression pour chaque opérateur
        		//ce ne peut pas être la même car e1 et e2 sont disjoints
	        	for(int v = 0; v < N; v++)
	        	{
		        	if (pressions[26][v][e1] > p1) p1 = pressions[26][v][e1];
		        	if (pressions[26][v][e2] > p2) p2 = pressions[26][v][e2];
	        	}
	        	if((p1 + p2) > pression) pression = p1 + p2;
        	}
        }
        
        return pression;
	}

	private static int chemin(String A, String B) 
	{
		return chemins.get(A).get(B);
	}

	private static int partie_1() 
	{
        //remplir le tableau pressions à la valeur minimale des entiers 
        pressions = new int[31][N][etatMaximum];
        for(int[][] instants_x_valves : pressions)
            for(int[] etats : instants_x_valves)
                Arrays.fill(etats,Integer.MIN_VALUE);
        
        //Pour chaque valve à flux non nul, la pression libérée tant que la valve n'est pas ouverte est 0
        //On place donc dans le tableau de pressions la valeur 0 à l'instant correspondant au temps pour atteindre 
        //cette valve à partir de "AA" et son ouverture, et l'état est le nombre de 15 bits où seul le bit i est à 1.
        for(int v = 0; v < N; v++) 
        {
            int instant = 1 + chemin("AA", valvesAFluxNonNul.get(v));
            int etat = puissancesDe2[v];
            pressions[instant][v][etat] = 0;
        }
        
        //pression totale libérée
        int pressionTotale = 0;
        //à chaque instant
        for(int instant = 1; instant <= 30; instant++) 
        {
            //pour chaque valve à flux non nul
            for(int valve = 0; valve < N; valve++) 
            {
                //pour chaque état possible
                for(int etat = 0; etat < etatMaximum; etat++) 
                {
                	//si la valve n'est pas ouverte on continue l'itération des états
                	//c'est à dire que l'on ne s'intéresse qu'aux états où la valve est ouverte
                	if (!valveOuverte(valve, etat)) continue;
                	
                	//flux total libéré
                	int f = 0;
                	for(int v = 0; v < N; v++) 
                	{
                		if (valveOuverte(v,etat)) f += flux[v];
                	}
                	//ajout de la pression libérée
                    int p = pressions[instant-1][valve][etat] + f;
                    //si cette pression est supérieure à celle qui était déjà stockée, on remplace la valeur stockée
                    //par cette nouvelle valeur
                    if(p > pressions[instant][valve][etat])  pressions[instant][valve][etat] = p;
                    //si cette pression est supérieure à la pression précédemment calculée, on remplace cette
                    //pression par celle-ci
                    if (p > pressionTotale) pressionTotale = p;

                    //on cherche la valve suivante la plus intéressante
                    for(int v = 0; v < N; v++) 
                    {
                        //si cette valve est déjà ouverte, on l'ignore, ce qui permet de ne pas essayer d'ouvrir deux fois la même valve
                    	if (valveOuverte(v, etat)) continue;

                        //quelle est la durée du trajet de la valve en cours (valve) à celle-ci (v)
                    	int duree = chemin(valve, v);
                    	//le nouvel instant (le précédent augmenté de la durée du chemin et de 1 pour l'ouverture)
                        int i = instant + duree + 1;
                        //on ignore cette valve si le trajet avec ouverture de valve vers v est trop long 
                        if(i > 30) continue;

                        //on calcule la pression totale en intégrant le trajet vers v et son ouverture
                        //c'est à dire la pression libérée pendant le trajet et l'ouverture mais avant la prise en compte de 
                        //l'ouverture de la valve v
                        int pression =  pressions[instant][valve][etat] + f * (duree + 1);

                        //on ajoute l'ouverture de la valve v dans l'état
                        int e = ajoutEtat(v,etat);

                        //si la pression libérée est supérieure à la pression stockée, on remplace cette dernière.
                        if(pression > pressions[i][v][e]) pressions[i][v][e] = pression;
                    }
                }
            }
        }
        return pressionTotale;
	}

	/**
	 * Passer le bit i de e à 1, le premier bit est le bit 0
	 * Par exemple : i = 3 et e = 000011110000111, retour = 000011110001111
	 * 
	 * @param i le bit à modifier
	 * @param e un nombre (seuls les 15 premiers bits nous intéressent)
	 * @return le nouveau nombre 
	 */
	private static int ajoutEtat(int i, int e) 
	{
		return e | puissancesDe2[i];
	}

	private static int chemin(int a, int b) 
	{
		return chemin(valvesAFluxNonNul.get(a), valvesAFluxNonNul.get(b));
	}

	//la valve à flux non nul i est ouverte si le bit i de etat est à 1
	private static boolean valveOuverte(int i, int etat) 
	{
		return (puissancesDe2[i] & etat) != 0;
	}

	private static void initialisation(String donnees) 
	{
		for(String ligne : donnees.split("\n"))
		{
			int n1 = ligne.indexOf(' ') + 1;
			int n2 = ligne.indexOf(' ', n1);
			String name = ligne.substring(n1, n2);
			n1 = ligne.indexOf('=') + 1;
			n2 = ligne.indexOf(';');
			int flow = Integer.parseInt(ligne.substring(n1, n2));
			n1 = ligne.indexOf("valve", n2) + 1;
			n2 = ligne.indexOf(' ', n1) + 1;
			List<String> connections = Arrays.asList(ligne.substring(n2).split(", "));
			Valve valve = new Valve(name, flow, connections);
			valves.put(name,  valve);
			cles.add(name);
			if (flow > 0) valvesAFluxNonNul.add(name);
		}
		
		//construction du dictionnaire des chemins
        for(String cle : cles) 
        {
        	HashMap<String, Integer> Achemins = new HashMap<String, Integer>();
        	for(String c : cles) 
        	{
        		Achemins.put(c, distanceLaPlusCourte(cle, c));
        	}
        	chemins.put(cle, Achemins);	
        }
		
		//pour modifier la pression totale il faut passer par une valve à flux non nul
        //je vais utiliser un tableau à trois dimension pour représenter une fonction à trois variables discrètes
        //La première dimension est l'instant (0 à 30 minutes)
        //La deuxième dimension est une valve à flux non nul (les valves à flux nul n'interviennent que pour la durée
        //qui se calcule à partir du chemin le plus court).
        //La troisième dimension est un entier représentant quelles valves sont ouvertes ou fermées l'état des valves donc).
        //L'état des valves est donc un entier inférieur à 2^n, où n est le nombre de valves à flux non nuls 
        N = valvesAFluxNonNul.size();
        puissancesDe2 = new int[N];
        flux = new int[N];
	    puissancesDe2[0] = 1;
	    flux[0] = valves.get(valvesAFluxNonNul.get(0)).flow;
	    for(int i = 1; i < N; i++)
	    {
	    	puissancesDe2[i] = 2* puissancesDe2[i-1];
	    	flux[i] = valves.get(valvesAFluxNonNul.get(i)).flow;
	    }
        etatMaximum = 2*puissancesDe2[N-1];
	}

	private static int distanceLaPlusCourte(String A, String B)
	{
		HashMap<String, Integer> distances = new HashMap<String, Integer>();
		
		LinkedList<String>file = new LinkedList<String>();

		for(String s : cles)
		{
			distances.put(s, Integer.MAX_VALUE);
			valves.get(s).marque = false;
		}
		
		distances.put(A, 0);
		file.add(A);
		while(!file.isEmpty())
		{
			String v = file.poll();
			for(String s : voisins(v))
			{
				if (1 + distances.get(v) < distances.get(s)) distances.put(s, 1 + distances.get(v));
				Valve sValve = valves.get(s);
				if (!sValve.marque) 
				{
					file.add(s);
					sValve.marque = true;
				}
			}
		}
		return distances.get(B);		
	}

	private static List<String> voisins(String v)
	{
		return valves.get(v).connections;
	}

	static private class Valve 
    {
		List<String> connections;
		boolean marque;
		int flow;
		String name;

		public Valve(String name, int flow, List<String> connections)
		{
			this.name = name;
			this.flow = flow;
			this.connections = connections;
		}

		public String toString()
		{
			return name + "\t(" + flow + ")\t" + connections;
		}
    }
}

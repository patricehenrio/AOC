package Pb16;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

import AOC.AOC;

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
	private static int[][][] pressions;
	
	public static void main(String[] args) throws IOException 
	{
		//les données
		String donnees = chargerDonnees("pb 16.txt");
		
//		//test
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
        //remplir le tableau pressions à la valeur minimale des entiers 
        pressions = new int[31][N][etatMaximum];
        for(int[][] instants_x_valves : pressions)
            for(int[] etats : instants_x_valves)
                Arrays.fill(etats,Integer.MIN_VALUE);

        for(int v = 0; v < N; v++) 
        {
            int instant = 1 + chemins.get("AA").get(valvesAFluxNonNul.get(v));
            int etat = puissancesDe2[v];
            pressions[instant][v][etat] = 0;
        }
        
        //à chaque instant
        for(int instant = 1; instant <= 26; instant++) 
        {
            //pour chaque valve à flux non nul
            for(int valve = 0; valve < N; valve++) 
            {
                //pour chaque état possible
                for(int etat = 0; etat < etatMaximum; etat++) 
                {
                	int f = 0;
                	for(int i = 0; i < N; i++) 
                	{
                		if(((puissancesDe2[i]) & etat) != 0) f += flux[i];
                	}
                	//ajout de la pression libérée
                    int p = pressions[instant-1][valve][etat] + f;
                    //si cette pression est supérieure à celle qui était déjà stockée, on remplace la valeur stockée
                    //par cette nouvelle valeur
                    if(p > pressions[instant][valve][etat])  pressions[instant][valve][etat] = p;

                    //si la valve n'est pas ouverte on continue l'itération
                    if(((puissancesDe2[valve]) & etat) == 0)  continue;

                    //on cherche la valve suivante la plus intéressante
                    for(int v = 0; v < N; v++) 
                    {
                        //si cette valve est déjà ouverte, on l'ignore
                        if(((puissancesDe2[v])& etat) != 0) continue;

                        //quelle est la durée du trajet de la valve en cours (valve) à celle-ci (v)
                        int duree = chemins.get(valvesAFluxNonNul.get(valve)).get(valvesAFluxNonNul.get(v));
                        int i = instant + duree + 1;
                        //on ignore cette valve si le trajet avec ouverture de valve vers v est trop long 
                        if(i > 26) continue;

                        //on calcule la pression totale en intégrant le trajet vers v et son ouverture
                        int pressionTotale =  pressions[instant][valve][etat] + f * (duree + 1);

                        //on ajoute l'ouverture de la valve v dans l'état
                        int e = etat | puissancesDe2[v];

                        //si la pression libérée est supérieure 
                        if(pressionTotale > pressions[i][v][e]) pressions[i][v][e] = pressionTotale;
                    }
                }
            }
        }
        //dans cette deuxième partie il s'agit de trouver la puissance maximum que deux opérateurs peuvent libérer
        //les deux opérateurs s'occupent chacun d'un ensemble distinct de valves représentés par deux états distincts
        int pression = 0;
        for(int etat1 = 1; etat1 < etatMaximum; etat1++)
        {
        	for(int etat2 = 1; etat2 < etatMaximum; etat2++)
        	{
        		//les valves ouvertes dans etat2 doivent être ouvertes dans etat1
        		if ((etat1 & etat2) != etat2) continue;
        		int p1 = 0, p2 = 0;
	        	for(int i = 0; i < N; i++)
	        	{
	        		int etat1SansEtat2 = etat1 & ~etat2;
		        	if (pressions[26][i][etat1SansEtat2] > p1) p1 = pressions[26][i][etat1SansEtat2];
		        	if (pressions[26][i][etat2] > p2) p2 = pressions[26][i][etat2];
	        	}
	        	
	        	if((p1 + p2) > pression) pression = p1 + p2;
        	}
        }
        return pression;
	}

	private static int partie_1() 
	{
        //remplir le tableau pressions à la valeur minimale des entiers 
        pressions = new int[31][N][etatMaximum];
        for(int[][] instants_x_valves : pressions)
            for(int[] etats : instants_x_valves)
                Arrays.fill(etats,Integer.MIN_VALUE);

        for(int v = 0; v < N; v++) 
        {
            int instant = 1 + chemins.get("AA").get(valvesAFluxNonNul.get(v));
            int etat = puissancesDe2[v];
            pressions[instant][v][etat] = 0;
        }
        
        int pression = 0;
        //à chaque instant
        for(int instant = 1; instant <= 30; instant++) 
        {
            //pour chaque valve à flux non nul
            for(int valve = 0; valve < N; valve++) 
            {
                //pour chaque état possible
                for(int etat = 0; etat < etatMaximum; etat++) 
                {
                	int f = 0;
                	for(int i = 0; i < N; i++) 
                	{
                		if(((puissancesDe2[i]) & etat) != 0) f += flux[i];
                	}
                	//ajout de la pression libérée
                    int p = pressions[instant-1][valve][etat] + f;
                    //si cette pression est supérieure à celle qui était déjà stockée, on remplace la valeur stockée
                    //par cette nouvelle valeur
                    if(p > pressions[instant][valve][etat])  pressions[instant][valve][etat] = p;
                    //si cette pression est supérieure à la pression précédemment calculée, on remplace cette
                    //pression par celle-ci
                    if (p > pression) pression = p;

                    //si la valve n'est pas ouverte on continue l'itération
                    if(((puissancesDe2[valve]) & etat) == 0)  continue;

                    //on cherche la valve suivante la plus intéressante
                    for(int v = 0; v < N; v++) 
                    {
                        //si cette valve est déjà ouverte, on l'ignore
                        if(((puissancesDe2[v])& etat) != 0) continue;

                        //quelle est la durée du trajet de la valve en cours (valve) à celle-ci (v)
                        int duree = chemins.get(valvesAFluxNonNul.get(valve)).get(valvesAFluxNonNul.get(v));
                        int i = instant + duree + 1;
                        //on ignore cette valve si le trajet avec ouverture de valve vers v est trop long 
                        if(i > 30) continue;

                        //on calcule la pression totale en intégrant le trajet vers v et son ouverture
                        int pressionTotale =  pressions[instant][valve][etat] + f * (duree + 1);

                        //on ajoute l'ouverture de la valve v dans l'état
                        int e = etat | puissancesDe2[v];

                        //si la pression libérée est supérieure 
                        if(pressionTotale > pressions[i][v][e]) pressions[instant + duree + 1][v][e] = pressionTotale;
                    }
                }
            }
        }
        return pression;
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

	static public class Valve 
    {
		public List<String> connections;
		public boolean marque;
		private int flow;
		private String name;

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

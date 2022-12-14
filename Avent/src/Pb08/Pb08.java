package Pb08;

import java.io.IOException;

import AOC.AOC;

public class Pb08 extends AOC

{
	static String repertoire = "F:\\AOC\\calendrier avent\\";
	static String[] foret;
	static int cote;

	public static void main(String[] args) throws IOException 
	{
		String donnees = chargerDonnees("pb 08.txt");

		//test
//		donnees = "30373\n"
//				+ "25512\n"
//				+ "65332\n"
//				+ "33549\n"
//				+ "35390";
		
		foret = donnees.split("\n");
		
		//la forêt est un carré
		cote = foret.length;

		//première partie
		System.out.println(partie_1());
		
		//Deuxième partie
		System.out.println(partie_2());
	}

	private static int partie_2() 
	{
		int scorePanoramique = 0;
		for(int i = 1; i <= cote; i++)
		{
			for(int j = 1; j <= cote; j++)
			{
				int n = distance(i,j);
				if (n > scorePanoramique) scorePanoramique = n;
			}
		}
		return scorePanoramique;
	}

	private static int partie_1() 
	{
		int nbVisibles = 0;
		for(int i = 1; i <= cote; i++)
		{
			for(int j = 1; j <= cote; j++)
			{
				if (isVisible(i,j)) 
				{
					nbVisibles++;
				} else {
				}
			}
		}
		return nbVisibles;
	}
	
	private static int distance(int i, int j) 
	{
		if (i == 1) return 0;
		if (i == cote) return 0;
		if (j == 1) return 0;
		if (j == cote) return 0;
		
		char c = arbre(i,j);
		
		char c1;
		int k;
		int haut = 0, bas = 0, gauche = 0, droite = 0;

		//vers le haut
		k = i-1;
		haut = 1;
		while (k > 1)
		{
			c1 = arbre(k,j);
			if (c <= c1) break;
			haut++;
			k--;
		}
		
		//vers la gauche
		k = j-1;
		gauche = 1;
		while (k > 1)
		{
			c1 = arbre(i,k);
			if (c <= c1) break;
			gauche++;
			k--;
		}
		
		//vers la droite
		k = j+1;
		droite = 1;
		while (k < cote)
		{
			c1 = arbre(i,k);
			if (c <= c1) break;
			droite++;
			k++;
		}
		
		//vers le bas
		k = i+1;
		bas = 1;
		while (k < cote)
		{
			c1 = arbre(k,j);
			if (c <= c1) break;
			bas++;
			k++;
		}
		
		return haut*bas*gauche*droite;
	}

	private static boolean isVisible(int i, int j) 
	{
		if (i == 1) return true;
		if (i == cote) return true;
		if (j == 1) return true;
		if (j == cote) return true;
		
		char c = arbre(i,j);
		
		char c1;
		int k;
		boolean visibleHaut = true, visibleBas = true, visibleGauche = true, visibleDroite = true;
		//vers la gauche
		k = j-1;
		while (k > 0)
		{
			c1 = arbre(i, k);
			if (c <= c1)
			{
				visibleGauche = false;
				break;
			}
			k--;
		}
		if (visibleGauche) return true;

		//vers la droite
		k = j+1;
		while (k <= cote)
		{
			c1 = arbre(i, k);
			if (c <= c1)
			{
				visibleDroite = false;
				break;
			}
			k++;
		}
		if (visibleDroite) return true;
		
		//vers le haut
		k = i-1;
		while (k > 0)
		{
			c1 = arbre(k, j);
			if (c <= c1)
			{
				visibleHaut = false;
				break;
			}
			k--;
		}
		if (visibleHaut) return true;
		
		//vers le bas
		k = i+1;
		while (k <= cote)
		{
			c1 = arbre(k, j);
			if (c <= c1)
			{
				visibleBas = false;
				break;
			}
			k++;
		}
		return visibleBas;
	}

	private static char arbre(int i, int j) 
	{
		String ligne = foret[i-1];
		return ligne.charAt(j-1);
	}

}

package Pb08;

import java.io.IOException;

import fichiers.Fichiers;

public class pb08_2 
{
	static String repertoire = "F:\\AOC\\calendrier avent\\";
	static String[] foret;
	static int cote;

	public static void main(String[] args) throws IOException 
	{
		String fichier = repertoire + "pb 08.txt";
		String donnees = Fichiers.chargerContenuTexte(fichier);
		
//		//test
//		donnees = "30373\n"
//				+ "25512\n"
//				+ "65332\n"
//				+ "33549\n"
//				+ "35390";
		
		foret = donnees.split("\n");
		//la forêt est un carré
		cote = foret.length;
		
//		int[][] distances = new int[cote][cote];
		int scorePanoramique = 0;
//		
//		System.out.println(distance(2,3));
//		System.out.println(distance(4,3));
		
		for(int i = 1; i <= cote; i++)
		{
			for(int j = 1; j <= cote; j++)
			{
				int n = distance(i,j);
				if (n > scorePanoramique) scorePanoramique = n;
			}
		}
		System.out.println(scorePanoramique);
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
//		gauche =1;
		
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
//		droite = 1;
		
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
//		bas = 1;
		
		return haut*bas*gauche*droite;
	}

	private static char arbre(int i, int j) 
	{
		String ligne = foret[i-1];
		return ligne.charAt(j-1);
	}
}

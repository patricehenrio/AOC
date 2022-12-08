package Pb08;

import java.io.IOException;

import fichiers.Fichiers;

public class Pb08_1

{
	static String repertoire = "F:\\AOC\\calendrier avent\\";
	static String[] foret;
	static int cote;

	public static void main(String[] args) throws IOException 
	{
		String fichier = repertoire + "pb 08.txt";
		String donnees = Fichiers.chargerContenuTexte(fichier);

		//test
		donnees = "30373\n"
				+ "25512\n"
				+ "65332\n"
				+ "33549\n"
				+ "35390";
		
		foret = donnees.split("\n");
		
//		//crée un nouveau jeu de données de 100 arbres
//		donnees = "";
//		for(int i = 0; i < 10; i++)
//		{
//			donnees += "\n" + foret[i].substring(0, 10);
//		}
//		donnees = donnees.substring(1);
//		foret = donnees.split("\n");
		
		//la forêt est un carré
		cote = foret.length;
//		System.out.println(arbre(2,2));
//		System.out.println(isVisible(2,2));
		//tous les arbres sur le côté sont visibles
		//seuls les arbres à l'intérieur peuvent être invisibles
		String s = "";
		int nbVisibles = 0;
		for(int i = 1; i <= cote; i++)
		{
			s += "\n";
			for(int j = 1; j <= cote; j++)
			{
				if (isVisible(i,j)) 
				{
					s += arbre(i,j);
					nbVisibles++;
				}
				else s += " ";
			}
		}
		System.out.println(s.substring(1));
		
//		int nbVisibles = 0;
//		for(int i = 1; i <= cote; i++)
//		{
//			for(int j = 1; j <= cote; j++)
//			{
//				if (arbre(i,j) > ' ') nbVisibles++;
//			}
//		}
		System.out.println(nbVisibles);
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
//		System.out.println("gauche : " + visibleGauche);

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
//		System.out.println("droite : " + visibleDroite);
		
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

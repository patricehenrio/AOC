package Pb12;

import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import AOC.AOC;


public class Pb12 extends AOC 
{
	private static int nombreLignes;
	private static int nombreColonnes;
	private static char[][] carte;
	private static Point depart;
	private static Point arrivee;
	private static Chemin[][] chemins;
	private static List<Point> collinesNonVisitees;

	public static void main(String[] args) throws IOException 
	{
		String donnees = chargerDonnees("pb 12.txt");
		String[] lignes = donnees.split("\n");
		//le nombre de lignes et de colonnes
		nombreLignes = lignes.length;
		nombreColonnes = lignes[0].length();
		//remplir la carte
		carte = new char[nombreLignes][nombreColonnes];	
		
		//première partie
		for (int lig = 0; lig < nombreLignes; lig++) 
		{
			for (int col = 0; col < nombreColonnes; col++) 
			{
				char c = lignes[lig].charAt(col);
				if (c == 'S') 
				{
					depart = new Point(lig, col);
					carte[lig][col] = 'a';
				} 
				else if (c == 'E') 
				{
					arrivee = new Point(lig, col);
					carte[lig][col] = 'z';
				}
				else carte[lig][col] = c;
			}
		}
		System.out.println(partie_1());
		
		//Deuxième partie
		for (int lig = 0; lig < nombreLignes; lig++) 
		{
			for (int col = 0; col < nombreColonnes; col++) 
			{
				char c = lignes[lig].charAt(col);
				if (c == 'S') 
				{
					depart = new Point(lig, col);
					carte[lig][col] = 'a';
				} 
				else if (c == 'E') 
				{
					arrivee = new Point(lig, col);
					carte[lig][col] = 'z';
				}
				else carte[lig][col] = c;
			}
		}
		System.out.println(partie_2());
	}

	 private static String partie_2() 
	 {
			collinesNonVisitees = new ArrayList<Point>();
			//un chemin est une liste de cases
			//pour chaque colline on note le plus court chemin pour y parvenir
			chemins = new Chemin[nombreLignes][nombreColonnes];
			
			//initialiser les chemins
			int cout = Integer.MAX_VALUE;
			for(int ligne = 0; ligne < nombreLignes; ligne++)
			{
				for(int colonne = 0; colonne < nombreColonnes; colonne++)
				{
					if (carte[ligne][colonne] == 'a')
					{
						depart = new Point(ligne, colonne);
						int c = trouverPlusCourtChemin();
						if (c < cout) cout = c;
					}
				}
			}
			return "Le chemin le plus court permet d'atteindre l'arrivée en " + cout + " étapes.";

	}

	private static int trouverPlusCourtChemin() 
	{
		//un chemin est une liste de case
		//pour chaque colline on note le plus court chemin pour y parvenir
		chemins = new Chemin[nombreLignes][nombreColonnes];
		collinesNonVisitees = new ArrayList<Point>();
		
		//initialiser les chemins
        for (int lig = 0; lig < nombreLignes; lig++) 
        {
            for (int col = 0; col < nombreColonnes; col++) 
            {
            	chemins[lig][col] = new Chemin();
            	collinesNonVisitees.add(new Point(lig, col));
            }
        }
        //le point de départ
        chemins[depart.x][depart.y].setChemin(new ArrayList<Point>());
        int lig = -1, col = -1;
        //tant que chaque colline n'a pas un cout calculé
        //le cout calculé est le nombre minimum de cases à partir du départ pour y accéder 
        while (collinesNonVisitees.size() > 0) 
        {
         	//la colline non visitée la plus proche du départ
			 Point colline = collineAMoindreCout();
			 if (colline == null) 
			{
				 if (lig < 0) 
				 {
					 carte[depart.x][depart.y] = '|';
					 return Integer.MAX_VALUE;
				 }
				 List<Point> chemin = chemins[lig][col].getChemin();
				 for(Point P : chemin)
				 {
					 //'|' = 'z' + 2, donc c'est une case inacessible
					 carte[P.x][P.y]='|';
				 }
				 return Integer.MAX_VALUE;
			}
			 //le chemin le plus court pour y accéder
			 List<Point> chemin = chemins[colline.x][colline.y].getChemin();
			 //si on est arrivé à notre but
			 if (colline.equals(arrivee)) return chemins[arrivee.x][arrivee.y].getCout();
			 
			 //le cout pour accéder à colline
			 int cout = chemins[colline.x][colline.y].getCout() + 1;
			 //les collines voisines accessibles
			 List<Point> voisines = getCollinesVoisines(colline);
			 //pour chacune de ces voisines dont le cout n'est pas encore estimé 
			 for (Point voisine : voisines) 
			 {
				 if (!collinesNonVisitees.contains(voisine)) continue;
				 
				 lig = voisine.x;
				 col = voisine.y;
				 
				 if (cout < chemins[lig][col].getCout())
				 {
					 List<Point> points = new ArrayList<Point>();
					 points.addAll(chemin);
					 points.add(colline);
					 chemins[lig][col].setChemin(points);
					 collinesNonVisitees.remove(colline);
				 }
			 }
        }
        return -1;
	}

	private static String partie_1() 
	{
		collinesNonVisitees = new ArrayList<Point>();
		//un chemin est une liste de case
		//pour chaque colline on note le plus court chemin pour y parvenir
		chemins = new Chemin[nombreLignes][nombreColonnes];
		//initialiser les chemins
        for (int lig = 0; lig < nombreLignes; lig++) 
        {
            for (int col = 0; col < nombreColonnes; col++) 
            {
            	chemins[lig][col] = new Chemin();
            	collinesNonVisitees.add(new Point(lig, col));
            }
        }
        //le point de départ
        chemins[depart.x][depart.y].setChemin(new ArrayList<Point>());
        //tant que chaque colline n'a pas un cout calculé
        //le cout calculé est le nombre minimum de cases à partir du départ pour y accéder 
        while (collinesNonVisitees.size() > 0) 
        {
         	//la colline non visitée la plus proche du départ
			 Point colline = collineAMoindreCout();
			 //le chemin le plus court pour y accéder
			 List<Point> chemin = chemins[colline.x][colline.y].getChemin();
			 //si on est arriver à notre but
			 if (colline.equals(arrivee))
			 {
				return "Le chemin le plus court permet d'atteindre l'arrivée en " + 
						 						chemins[arrivee.x][arrivee.y].getCout() + " étapes.";
			 }
			 //le cout pour accéder à colline
			 int cout = chemins[colline.x][colline.y].getCout() + 1;
			 //les collines voisines accessibles
			 List<Point> voisines = getCollinesVoisines(colline);
			 //pour chacune de ces voisines dont le cout n'est pas encore estimé 
			 for (Point voisine : voisines) 
			 {
				 if (!collinesNonVisitees.contains(voisine)) continue;
				 
				 int lig = voisine.x;
				 int col = voisine.y;
				 
				 if (cout < chemins[lig][col].getCout())
				 {
					 List<Point> points = new ArrayList<Point>();
					 points.addAll(chemin);
					 points.add(colline);
					 chemins[lig][col].setChemin(points);
					 
					 collinesNonVisitees.remove(colline);
				 }
			 }
       }
		return null;
	}

	private static List<Point> getCollinesVoisines(Point P) 
	{
		int lig = P.x, col = P.y;
		int hauteurMaxi = carte[lig][col] + 1;
		List<Point>voisines = new ArrayList<Point>();
		//en haut
		if (lig > 1)
		{
			if (carte[lig-1][col] <= hauteurMaxi)
			{
				voisines.add(new Point(lig-1, col));
			}
		}
		//à droite
		if (col < nombreColonnes-1)
		{
			if (carte[lig][col+1] <= hauteurMaxi)
			{
				voisines.add(new Point(lig, col+1));
			}
		}
		//en bas
		if (lig < nombreLignes-1)
		{
			if (carte[lig+1][col] <= hauteurMaxi)
			{
				voisines.add(new Point(lig+1, col));
			}
		}
		//à gauche
		if (col > 1)
		{
			if (carte[lig][col-1] <= hauteurMaxi)
			{
				voisines.add(new Point(lig, col-1));
			}
		}
		return voisines;
	}

	private static Point collineAMoindreCout() 
	{
		int min = Integer.MAX_VALUE;
		Point collineAMoindreCout = null;

		for(Point P : collinesNonVisitees)
		{
			Chemin chemin = chemins[P.x][P.y];
			if (chemin.getCout() < min)
			{
				min = chemin.getCout();
				collineAMoindreCout = P;
			}
      	}
	
		collinesNonVisitees.remove(collineAMoindreCout);
		return collineAMoindreCout;
	}

	static class Chemin
	{
		List<Point> chemin = null;

	 	public List<Point> getChemin() 
	 	{
	 		return chemin;
	 	}

	 	public void setChemin(List<Point> chemin) 
	 	{
	 		this.chemin = chemin;
	 	}

	 	public int getCout() 
	 	{
	 		if (chemin == null) return Integer.MAX_VALUE;
	 		
	 		return chemin.size();
	 	}
	}	
}

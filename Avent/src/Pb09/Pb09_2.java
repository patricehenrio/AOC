package Pb09;

import java.awt.Point;
import java.io.IOException;

import fichiers.Fichiers;

public class Pb09_2 
{
	static String repertoire = "F:\\AOC\\calendrier avent\\";
	static Point[] corde = new Point[10];
	static char[][] deplacements;
	static int xDepart = 200, yDepart = 200;
	static int nbLignes = 500, nbColonnes = 500;

	public static void main(String[] args) throws IOException 
	{
		String fichier = repertoire + "pb 09.txt";
		String donnees = Fichiers.chargerContenuTexte(fichier);

//		donnees = "R 5\nU 8\nL 8\nD 3\nR 17\nD 10\nL 25\nU 20";//R 5\nU 8\nL 8\nD 3\nR 17\nD 10\nL 25\nR 20;
		String[] mouvements = donnees.split("\n");
		
		//dimensions et point de départ déterminés empiriquement par programme 
		deplacements = new char[nbLignes][nbColonnes];
		
		for(int i = 0; i < 10; i++)
		{
			corde[i] = new Point(xDepart,yDepart);
		}
		
		for(String s : mouvements)
		{
			traiter(s);
		}
		
		if (deplacements[yDepart][xDepart] == 0) deplacements[yDepart][xDepart] = 's';
		
		int resultat = 0;
		for(int i = 0; i < nbLignes; i++)
			for(int j = 0; j < nbColonnes; j++)
				if (deplacements[i][j] != 0) resultat ++;

		System.out.println(resultat);
		
	}

	private static void traiter(String s) 
	{
		char c = s.charAt(0);
		int n = Integer.parseInt(s.substring(2));
//		System.out.println("\n== " + s + " ==\n");
		Point H = corde[0];
//		deplacements[H.y][H.x] = 0;
		
		while(n > 0)
		{
			switch(c)
			{
				case 'U' : //up
					H = new Point(H.x, H.y+1);
					break;
				case 'D' : //down
					H = new Point(H.x, H.y-1);
					break;
				case 'R' : //right
					H = new Point(H.x+1, H.y);
					break;
				case 'L' : //left
					H = new Point(H.x-1, H.y);
					break;
				default : 
					System.out.println("erreur sur cette ligne : " + s);
			}
			corde[0] = H;

			deplacerCorde();
			n--;
		}
		
	}

	private static void deplacerCorde() 
	{
		for(int i = 1; i < 10; i++) 
			deplacerNoeud(i);
	}

	private static void deplacerNoeud(int i) 
	{
		Point H = corde[i-1];
		Point T = corde[i];
		if (T.x == H.x)//même colonne
		{
			if(Math.abs(T.y - H.y) < 2) return;
			if (T.y == H.y-2)
			{
				T = new Point(T.x, T.y+1);
				if (i == 9) deplacements[T.y][T.x]='#';
				corde[i] = T;
				return;
			}
			if (T.y == H.y +2)
			{
				T = new Point(T.x, T.y-1);
				if (i == 9) deplacements[T.y][T.x]='#';
				corde[i] = T;
				return;
			}
			throw new RuntimeException("Erreur de déplacement");	
		}
		
		if (T.x == H.x + 1)//T à gauche de H
		{
			if(Math.abs(T.y - H.y) < 2) return;
			if (T.y == H.y-2)
			{
				T = new Point(T.x-1, T.y+1);
				if (i == 9) deplacements[T.y][T.x]='#';
				corde[i] = T;
				return;
			}
			if (T.y == H.y +2)
			{
				T = new Point(T.x-1, T.y-1);
				if (i == 9) deplacements[T.y][T.x]='#';
				corde[i] = T;
				return;
			}
			throw new RuntimeException("Erreur de déplacement");	
		}
		
		if (T.x == H.x - 1)//une lmigne au dessous
		{
			if(Math.abs(T.y - H.y) < 2) return;
			if (T.y == H.y-2)
			{
				T = new Point(T.x+1, T.y+1);
				if (i == 9) deplacements[T.y][T.x]='#';
				corde[i] = T;
				return;
			}
			if (T.y == H.y +2)
			{
				T = new Point(T.x+1, T.y-1);
				if (i == 9) deplacements[T.y][T.x]='#';
				corde[i] = T;
				return;
			}
			throw new RuntimeException("Erreur de déplacement");	
		}
		
		if (T.x == H.x + 2)//une ligne au dessus
		{
			if (T.y == H.y)
			{
				T = new Point(T.x-1, T.y);
				if (i == 9) deplacements[T.y][T.x]='#';
				corde[i] = T;
				return;
			}
			if (T.y == H.y-1)
			{
				T = new Point(T.x-1, T.y+1);
				if (i == 9) deplacements[T.y][T.x]='#';
				corde[i] = T;
				return;
			}
			if (T.y == H.y+1)
			{
				T = new Point(T.x-1, T.y-1);
				if (i == 9) deplacements[T.y][T.x]='#';
				corde[i] = T;
				return;
			}
			if (T.y == H.y + 2)
			{
				T = new Point(T.x-1, T.y-1);
				if (i == 9) deplacements[T.y][T.x]='#';
				corde[i] = T;
				return;
			}
			if (T.y == H.y - 2)
			{
				T = new Point(T.x-1, T.y+1);
				if (i == 9) deplacements[T.y][T.x]='#';
				corde[i] = T;
				return;
			}
			
			throw new RuntimeException("Erreur de déplacement");	
		}
		
		if (T.x == H.x - 2)//la queue (Tail) est deux colonnes à gauche de la tête (H)
		{
			if (T.y == H.y)
			{
				T = new Point(T.x+1, T.y);
				if (i == 9) deplacements[T.y][T.x]='#';
				corde[i] = T;
				return;
			}
			if (T.y == H.y-1)
			{
				T = new Point(T.x+1, T.y+1);
				if (i == 9) deplacements[T.y][T.x]='#';
				corde[i] = T;
				return;
			}
			if (T.y == H.y+1)
			{
				T = new Point(T.x+1, T.y-1);
				if (i == 9) deplacements[T.y][T.x]='#';
				corde[i] = T;
				return;
			}
			if (T.y == H.y + 2)
			{
				T = new Point(T.x+1, T.y-1);
				if (i == 9) deplacements[T.y][T.x]='#';
				corde[i] = T;
				return;
			}
			if (T.y == H.y - 2)
			{
				T = new Point(T.x+1, T.y+1);
				if (i == 9) deplacements[T.y][T.x]='#';
				corde[i] = T;
				return;
			}
			throw new RuntimeException("Erreur de déplacement " + H + T);	
		}
		throw new RuntimeException("Erreur de déplacement");
	}
}

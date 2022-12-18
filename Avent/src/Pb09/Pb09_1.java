package Pb09;

import java.awt.Point;
import java.io.IOException;

import fichiers.Fichiers;

public class Pb09_1 
{
	static String repertoire = "F:\\AOC\\calendrier avent\\";
	static char[][] deplacements = new char[400][250];
	static Point T = new Point(120,200),H = new Point(120,200);
	static int u = 200, d = 120, r = 120, l = 200;
	static int maxR = 0, minR = 120;
	static int maxU = 0, minU = 200;
	
	public static void main(String[] args) throws IOException 
	{
		String fichier = repertoire + "pb 09.txt";
		String donnees = Fichiers.chargerContenuTexte(fichier);
		
		//test
		//donnees = "R 4\nU 4\nL 3\nD 1\nR 4\nD 1\nL 5\nR 2";
		String[] mouvements = donnees.split("\n");
		//dimensions et point de départ déterminés empiriquement par programme 
		deplacements = new char[359][224];
		int xDepart = 100, yDepart = 175;
		T = new Point(xDepart,yDepart);
		H = new Point(xDepart,yDepart);
		deplacements[H.x][H.y] = 's';
		
		for(String s :mouvements)
		{
//			System.out.println(s);
			traiter(s);
//			System.out.println("H " + H);
//			System.out.println("T " + T);
//			deplacements[0][0] = 's';
//			deplacements[T.x][T.y] = '#';
//			afficher();
		}
//		afficher();
		int resultat = 0;
		for(int i = 0; i < 359; i++)
			for(int j = 0; j < 224; j++)
				if (deplacements[i][j] != 0) resultat++;
		System.out.println(resultat);
	}

//	private static void afficher()
//	{
//		String str = "";
//		for(int j = 4; j >= 0; j--)
//		{
//			for(int i = 0; i < 6; i++)
//			{
//				if (deplacements[i][j] == 0) str += '.';
//				else str += deplacements[i][j];
//			}
//			str += "\n";
//		}
//		
//		System.out.println(str);
//	}

	private static void traiter(String s) 
	{
		char c = s.charAt(0);
		int n = Integer.parseInt(s.substring(2));
		
		while(n > 0)
		{
//			System.out.println(c + " " + n);
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
			n--;
			deplacerQueue();
//			System.out.println(n + "\t" + T + "\t" + deplacements[T.y][T.x]);
//			afficher();
		}
	}

	private static void deplacerQueue() 
	{
		if (T.x == H.x)//même colonne
		{
			if(Math.abs(T.y - H.y) < 2) return;
			if (T.y == H.y-2)
			{
				T = new Point(T.x, T.y+1);
				deplacements[T.x][T.y]='#';
				return;
			}
			if (T.y == H.y +2)
			{
				T = new Point(T.x, T.y-1);
				deplacements[T.x][T.y]='#';
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
				deplacements[T.x][T.y]='#';
				return;
			}
			if (T.y == H.y +2)
			{
				T = new Point(T.x-1, T.y-1);
				deplacements[T.x][T.y]='#';
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
				deplacements[T.x][T.y]='#';
				return;
			}
			if (T.y == H.y +2)
			{
				T = new Point(T.x+1, T.y-1);
				deplacements[T.x][T.y]='#';
				return;
			}
			throw new RuntimeException("Erreur de déplacement");	
		}
		
		if (T.x == H.x + 2)//une ligne au dessus
		{
			if (T.y == H.y)
			{
				T = new Point(T.x-1, T.y);
				deplacements[T.x][T.y]='#';
				return;
			}
			if (T.y == H.y-1)
			{
				T = new Point(T.x-1, T.y+1);
				deplacements[T.x][T.y]='#';
				return;
			}
			if (T.y == H.y+1)
			{
				T = new Point(T.x-1, T.y-1);
				deplacements[T.x][T.y]='#';
				return;
			}
			throw new RuntimeException("Erreur de déplacement");	
		}
		
		if (T.x == H.x - 2)//la queue (Tail) est deux colonnes à gauche de la tête (H)
		{
			if (T.y == H.y)
			{
				T = new Point(T.x+1, T.y);
				deplacements[T.x][T.y]='#';
				return;
			}
			if (T.y == H.y-1)
			{
				T = new Point(T.x+1, T.y+1);
				deplacements[T.x][T.y]='#';
				return;
			}
			if (T.y == H.y+1)
			{
				T = new Point(T.x+1, T.y-1);
				deplacements[T.x][T.y]='#';
				return;
			}
			throw new RuntimeException("Erreur de déplacement");	
		}
		
		throw new RuntimeException("Erreur de déplacement");
	}
}
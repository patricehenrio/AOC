package Pb09;

import java.awt.Point;
import java.io.IOException;

import AOC.AOC;

public class Pb09 extends AOC 
{
	static char[][] deplacements;
	//les données ci-dessous ont été déterminées empiriquement
	static int xDepart = 200, yDepart = 200;
	static int nbLignes = 500, nbColonnes = 500;
	
	static Point[] corde = new Point[10];

	private static String[] mouvements;

	public static void main(String[] args) throws IOException 
	{
		String donnees = chargerDonnees("pb 09.txt");
		
		//test
		//donnees = "R 4\nU 4\nL 3\nD 1\nR 4\nD 1\nL 5\nR 2";
		
		mouvements = donnees.split("\n");
		
		//première partie
		System.out.println(partie_1());
		//Deuxième partie
		System.out.println(partie_2());
		
	}

	private static int partie_2() 
	{
		//dimensions et point de départ déterminés empiriquement par programme 
		deplacements = new char[nbLignes][nbColonnes];
		
		for(int i = 0; i < 10; i++)
		{
			corde[i] = new Point(xDepart,yDepart);
		}

		for(String s : mouvements)
		{
			traiter2(s);
		}
		
		if (deplacements[yDepart][xDepart] == 0) deplacements[yDepart][xDepart] = 's';
		
		int resultat = 0;
		for(int i = 0; i < nbLignes; i++)
		{
			for(int j = 0; j < nbColonnes; j++)
			{
				char c = deplacements[i][j];
				if (c != 0) resultat++;
			}
		}
		return resultat;
	}

	private static void traiter2(String s) 
	{
		
		char c = s.charAt(0);
		int n = Integer.parseInt(s.substring(2));
		Point H = corde[0];
		
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
		
		if (T.x == H.x - 1)//une ligne au dessous
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

	private static int partie_1() 
	{
		//dimensions et point de départ déterminés empiriquement par programme 
		deplacements = new char[nbLignes][nbColonnes];
		corde[0] = new Point(xDepart,yDepart);
		corde[1] = new Point(xDepart,yDepart);

		for(String s :mouvements)
		{
			traiter1(s);
		}
		int resultat = 0;
		for(int i = 0; i < nbLignes; i++)
			for(int j = 0; j < nbColonnes; j++)
				if (deplacements[i][j] != 0) resultat++;
		return resultat;
	}

	private static void traiter1(String s)
	{
		char c = s.charAt(0);
		int n = Integer.parseInt(s.substring(2));
		
		while(n > 0)
		{
			Point H = corde[0];
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
			deplacerQueue();
			n--;
		}
	}

	private static void deplacerQueue() 
	{
		Point H = corde[0];
		Point T = corde[1];
		
		if (T.x == H.x)//même colonne
		{
			if(Math.abs(T.y - H.y) < 2) return;
			if (T.y == H.y-2)
			{
				T = new Point(T.x, T.y+1);
				deplacements[T.x][T.y]='#';
				corde[1] = T;
				return;
			}
			if (T.y == H.y +2)
			{
				T = new Point(T.x, T.y-1);
				deplacements[T.x][T.y]='#';
				corde[1] = T;
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
				corde[1] = T;
				return;
			}
			if (T.y == H.y +2)
			{
				T = new Point(T.x-1, T.y-1);
				deplacements[T.x][T.y]='#';
				corde[1] = T;
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
				corde[1] = T;
				return;
			}
			if (T.y == H.y +2)
			{
				T = new Point(T.x+1, T.y-1);
				deplacements[T.x][T.y]='#';
				corde[1] = T;
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
				corde[1] = T;
				return;
			}
			if (T.y == H.y-1)
			{
				T = new Point(T.x-1, T.y+1);
				deplacements[T.x][T.y]='#';
				corde[1] = T;
				return;
			}
			if (T.y == H.y+1)
			{
				T = new Point(T.x-1, T.y-1);
				deplacements[T.x][T.y]='#';
				corde[1] = T;
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
				corde[1] = T;
				return;
			}
			if (T.y == H.y-1)
			{
				T = new Point(T.x+1, T.y+1);
				deplacements[T.x][T.y]='#';
				corde[1] = T;
				return;
			}
			if (T.y == H.y+1)
			{
				T = new Point(T.x+1, T.y-1);
				deplacements[T.x][T.y]='#';
				corde[1] = T;
				return;
			}
			throw new RuntimeException("Erreur de déplacement");	
		}
		throw new RuntimeException("Erreur de déplacement");
	}
}

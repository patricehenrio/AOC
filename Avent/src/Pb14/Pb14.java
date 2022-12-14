package Pb14;

import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import AOC.AOC;

public class Pb14 extends AOC 
{
	static List<Chemin> chemins;
	private static int longueur;
	private static int hauteur;
	private static Point mini;
	private static Point maxi;
	private static char[][] caracteres;
	private static int depassement = 0;
	static Point depart = new Point(500,0);

	public static void main(String[] args) throws IOException 
	{
		String donnees = chargerDonnees("pb 14.txt");
		String[] lignes = donnees.split("\n");
		chemins = new ArrayList<Chemin>();
		for(String s : lignes) chemins.add(new Chemin(s));

		Chemin chemin = chemins.get(0);
		int mini_x = chemin.mini.x, 
			mini_y = chemin.mini.y, 
			maxi_x = chemin.maxi.x, 
			maxi_y = chemin.maxi.y;
		
		for(int i = 1; i < chemins.size(); i++)
		{
			chemin = chemins.get(i);
			if (chemin.mini.x < mini_x) mini_x =chemin.mini.x;
			if (chemin.mini.y < mini_y) mini_y =chemin.mini.y;
			if (chemin.maxi.x > maxi_x) maxi_x =chemin.maxi.x;
			if (chemin.maxi.y > maxi_y) maxi_y =chemin.maxi.y;
		}
		mini = new Point(mini_x, mini_y);
		maxi = new Point(maxi_x, maxi_y);
		
		//première partie
		longueur = maxi_x - mini_x;
		hauteur = maxi_y;
		System.out.println(partie_1());
		
		//Deuxième partie
		longueur = maxi_x - mini_x;
		hauteur = maxi_y;
		System.out.println(partie_2());
		
	}

	/**
	 * partie 2
	 */
	private static int partie_2() 
	{
		depassement = 500; //valeur déterminée empiriquement
		longueur += 1 + 2*depassement;
		hauteur += 3;
		caracteres = new char[hauteur][longueur];
		
		for(int i = 0; i < hauteur; i++)
		{
			for(int j = 0; j < longueur; j++)
			{
				caracteres[i][j] = '.';
			}
		}
		setCaractere(depart.x, depart.y, '+');
		for(int i = 0; i < chemins.size(); i++)
		{
			Chemin chemin = chemins.get(i);
			for(Point point : chemin.points)
			{
				setCaractere(point.x, point.y, '#');
			}
		}
		for(int i = 0; i < longueur; i++)
		{
			caracteres[hauteur-1][i] = '#';
		}
		traiter2();
		
		return compter();
	}

	private static void traiter2() 
	{
		while(true)
		{
			Point P = depart;
			Point suivant;
			while(true)
			{
				suivant = getProchainPoint2(P);
				if (suivant == null)
				{
					setCaractere(P.x, P.y, 'o');
					break;
				}
				if (suivant.equals(depart))
				{
					setCaractere(depart.x, depart.y, 'o');
					break;
				}
				P = suivant;
			}
			if (depart.equals(P)) 
			{
				break;
			}
		}
	}

	private static Point getProchainPoint2(Point P) 
	{
		char c = getCaractere(P.x,P.y+1);
		if (c == '.') return new Point(P.x, P.y+1);

		c = getCaractere(P.x-1, P.y+1);
		if (c == '.') return new Point(P.x-1, P.y+1);
		
		c = getCaractere(P.x+1, P.y+1);
		if (c == '.') return new Point(P.x+1, P.y+1);
		
		return null;
	}

	/**
	 * partie 1
	 */
	private static int partie_1() 
	{
		longueur += 2;
		hauteur += 1;
		
		caracteres = new char[hauteur][longueur];
		
		for(int i = 0; i < hauteur; i++)
		{
			for(int j = 0; j < longueur; j++)
			{
				caracteres[i][j] = '.';
			}
		}
		setCaractere(depart.x, depart.y, '+');
		for(int i = 0; i < chemins.size(); i++)
		{
			Chemin chemin = chemins.get(i);
			for(Point point : chemin.points)
			{
				setCaractere(point.x, point.y, '#');
			}
		}
		for(int i = 0; i < longueur; i++)
		{
			caracteres[hauteur-1][i] = '#';
		}
		
		traiter1();
		
		return compter();
	}

	private static void traiter1() 
	{
		while(true)
		{
			String s = afficher();
			Point P = depart;
			Point suivant;
			while(true)
			{
				suivant = getProchainPoint1(P);
				
				if (suivant == null)
				{
					setCaractere(P.x, P.y, 'o');
					break;
				}
				if (suivant.equals(new Point(-1,-1)))
				{
					setCaractere(P.x, P.y, '~');
					break;
				}
				P = suivant;
			}
			if (s.equals(afficher())) break;
		}
	}

	private static Point getProchainPoint1(Point P) 
	{
		if (P.x <= mini.x) return new Point(-1,-1);
		if (P.x >= maxi.x) return new Point(-1,-1);
		if (P.y >= maxi.y) return new Point(-1,-1);
		
		char c = getCaractere(P.x,P.y+1);
		if (c == '.') return new Point(P.x, P.y+1);
		if (c == '~') return new Point(-1,-1);
		c = getCaractere(P.x-1, P.y+1);
		if (c == '.') return new Point(P.x-1, P.y+1);
		if (c == '~') return new Point(-1,-1);
		
		c = getCaractere(P.x+1, P.y+1);
		if (c == '.') return new Point(P.x+1, P.y+1);
		if (c == '~') return new Point(-1,-1);
		
		return null;
	}

	private static char getCaractere(int x, int y) 
	{
		return caracteres[y][x-mini.x+1+depassement];	
	}


	private static String afficher() 
	{
		String s = "";
		for(int i = 0; i < hauteur; i++)
			s += "\n" + i + "\t" + new String(caracteres[i]);
		return s;
	}


	private static int compter() 
	{
		int resultat = 0;
		for(int i = 0; i < hauteur; i++)
		{
			for(int j = 0; j < longueur; j++)
			{
				if (caracteres[i][j] == 'o') resultat++;
			}
		}
		return resultat;
	}
	

	private static void setCaractere(int x, int y, char c)
	{
		caracteres[y][x-mini.x+1+depassement] = c;
	}
	
	static class Chemin
	{
		List<Point> points = new ArrayList<Point>();
		Point mini, maxi;
		
		public Chemin(String s) 
		{
			String[] coordonnees = s.split(" -> ");
			for(String p : coordonnees) 
			{
				String[] xy = p.split(",");
				int x = Integer.parseInt(xy[0]);
				int y = Integer.parseInt(xy[1]);
				points.add(new Point(x,y));
			}
			définirExtreme();
			int nombrePoints = points.size();
			Point A = points.get(0);
			Point B;
			int k;
			for(int i = 1; i < nombrePoints; i++)
			{
				B = points.get(i);
				
				if (A.x == B.x)
				{
					k = A.y < B.y ? 1 : -1; 
					int y = A.y;
					while(true)
					{
						y += k;
						if (y == B.y) break;
						points.add(new Point(A.x, y));
					}
				}
				else
				{
					k = A.x < B.x ? 1 : -1;
					int x = A.x;
					while(true)
					{
						x += k;
						if (x == B.x) break;
						points.add(new Point(x, A.y));
					}
				}
				A = B;
			}
		}
		
		private void définirExtreme() 
		{
			Point point = points.get(0);
			int maxi_x = point.x;
			int mini_x = point.x;
			int maxi_y = point.y;
			int mini_y = point.y;
			for(int i = 1; i < points.size(); i++)
			{
				point = points.get(i);
				if (point.x < mini_x) mini_x = point.x;
				if (point.x > maxi_x) maxi_x = point.x;
				if (point.y < mini_y) mini_y = point.y;
				if (point.y > maxi_y) maxi_y = point.y;
			}
			
			mini = new Point(mini_x, mini_y);
			maxi = new Point(maxi_x, maxi_y);
		}
	}
}

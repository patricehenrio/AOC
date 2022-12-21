package Pb14;
import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import fichiers.Fichiers;

public class Pb14_1 
{
	static Point mini;
	static Point maxi;
	static int longueur, hauteur;
	static char[][]caracteres; 
	static List<Chemin> chemins;
	static Point depart = new Point(500,0);
	
	public static void main(String[] args) throws IOException 
	{
		//les données
		String donnees = Fichiers.chargerContenuTexte("F:\\AOC\\calendrier avent\\pb 14.txt");

		//test
//		donnees = "498,4 -> 498,6 -> 496,6\n"
//				+ "503,4 -> 502,4 -> 502,9 -> 494,9";
		
		
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
		longueur = maxi_x - mini_x + 2;
		hauteur = maxi_y + 1;
		mini = new Point(mini_x, mini_y);
		maxi = new Point(maxi_x, maxi_y);
		
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
			chemin = chemins.get(i);
			for(Point point : chemin.points)
			{
				setCaractere(point.x, point.y, '#');
			}
		}
		for(int i = 0; i < hauteur; i++)
		{
			caracteres[i][0] = '~';
		}

//		System.out.println(afficher());
		traiter();
//		System.out.println(afficher());
		System.out.println(compter());
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

	private static void traiter() 
	{
		while(true)
		{
			String s = afficher();
			Point P = depart;
			Point suivant;
			while(true)
			{
				suivant = getProchainPoint(P);
				
				
//				System.out.println(P + "\t" + suivant + "\t" + n);
				
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

	private static Point getProchainPoint(Point P) 
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
		return caracteres[y][x-mini.x+1];
	}
	
	private static void setCaractere(int x, int y, char c)
	{
		caracteres[y][x-mini.x+1] = c;
	}

	private static String afficher() 
	{
		String s = "";
		for(int i = 0; i < hauteur; i++)
			s += "\n" + i + "\t" + new String(caracteres[i]);
		return s;
		
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
//					System.out.println(A + " et " + B +  " " + k);
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
//					System.out.println(A + " et " + B +  " " + k);
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

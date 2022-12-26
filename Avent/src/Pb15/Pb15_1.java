package Pb15;

import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import fichiers.Fichiers;

public class Pb15_1 
{
	static List<CapteurBalise> capteursBalises = new ArrayList<CapteurBalise>();
	static int ligneTestee;
	static List<Intervalle> intervalles = new ArrayList<Intervalle>();
	static int nbBaliseSurLigneTestee = 0;
	
	public static void main(String[] args)  throws IOException 
	{
		//les données
		String donnees = Fichiers.chargerContenuTexte("F:\\AOC\\calendrier avent\\pb 15.txt");
		ligneTestee = 2000000;

		//test
//		donnees = "Sensor at x=2, y=18: closest beacon is at x=-2, y=15\n"
//				+ "Sensor at x=9, y=16: closest beacon is at x=10, y=16\n"
//				+ "Sensor at x=13, y=2: closest beacon is at x=15, y=3\n"
//				+ "Sensor at x=12, y=14: closest beacon is at x=10, y=16\n"
//				+ "Sensor at x=10, y=20: closest beacon is at x=10, y=16\n"
//				+ "Sensor at x=14, y=17: closest beacon is at x=10, y=16\n"
//				+ "Sensor at x=8, y=7: closest beacon is at x=2, y=10\n"
//				+ "Sensor at x=2, y=0: closest beacon is at x=2, y=10\n"
//				+ "Sensor at x=0, y=11: closest beacon is at x=2, y=10\n"
//				+ "Sensor at x=20, y=14: closest beacon is at x=25, y=17\n"
//				+ "Sensor at x=17, y=20: closest beacon is at x=21, y=22\n"
//				+ "Sensor at x=16, y=7: closest beacon is at x=15, y=3\n"
//				+ "Sensor at x=14, y=3: closest beacon is at x=15, y=3\n"
//				+ "Sensor at x=20, y=1: closest beacon is at x=15, y=3";
//		ligneTestee = 10;
		
		
		remplirListes(donnees);
//		System.out.println(capteursBalises.size());
		
		for(int i = 0; i < capteursBalises.size(); i++)
		{
			CapteurBalise cb = capteursBalises.get(i);
			Point c = cb.capteur;
			int d = cb.distance;
//			boolean test = c.equals(new Point(12,14));
//			test = true;
//			if (test) System.out.println(cb);
//			if (test) System.out.println(Math.abs(ligneTestee - c.y) <= d);
			//pour les capteurs dont la zone de détection rencontre la ligne test
			int j = Math.abs(ligneTestee - c.y);
//			if (test) System.out.println(j);
			if (j <= d)
			{
//				if (test) System.out.println(d-j);
				Intervalle intervalle;
				if (c.y < ligneTestee)
				{
//					if (test) System.out.println(c.x);
//					if (test) System.out.println(c.y + j);
					intervalle = new Intervalle(new Point(c.x, c.y + j), d - j);
//					cb.setIntervalle(intervalle); 
//					if (test) System.out.println(intervalle.longueur());
//					if (test) System.out.println(cb);
				}
				else
				{
//					if (test) System.out.println(c.x);
//					if (test) System.out.println(c.y - j);
					intervalle = new Intervalle(new Point(c.x, c.y - j), d - j);
//					cb.setIntervalle(intervalle); 
					
//					if (test) System.out.println(intervalle.longueur());
//					if (test) System.out.println(cb);
				}
				cb.setIntervalle(intervalle);
				System.out.println(intervalle);
//				ajoute(intervalle);
			}
		}
		System.out.println(nbBaliseSurLigneTestee);

//		for(Intervalle intervalle : intervalles)
//		{
//			System.out.println(intervalle);
//		}
		
//		int nb =0;
//		for(char c : caracteres[ligneTest])
//			if (c == '#') nb++;
//		System.out.println(nb);
	}

	private static void ajoute(Intervalle I) 
	{
		boolean aAjouter = false;

		if (intervalles.isEmpty())
		{
			intervalles.add(I);
			return;
		}
		
		int n = 0;
		while(n < intervalles.size())
		{
			Intervalle I2 = intervalles.get(n);
			if (I.contient(I2)) 
			{
				I2.A = I.A;
				I2.B = I.B;
				n++;
				continue;
			}
			if (I2.contient(I))
			{
				return;
			}

			if (I.disjoint(I2))
			{
				aAjouter = true;
				n++;
				continue;
			}
			
			if (!I.disjoint(I2) && I2.aDroite(I))
			{
				I2.A = I.A;
				n++;
				continue;
			}
			//cas non prévu
			String s = "" + intervalles.get(0);
			for(int i = 1; i < intervalles.size(); i++) s += " ; " + intervalles.get(i);
			throw new RuntimeException(I + " à ajouter à " + s);
		}
		
//		
//		for(int j = 0; j < i; j++)
//		{
//			
//		}
//		boolean trouve = false;
//		
//		for(Intervalle i1 : intervalles)
//		{
//			if (I.disjoint(i1)) continue;
//			trouve = true;
//			if (I.contient(i1))
//			{
//				intervalles.remove(i1);
//				continue;
//			}
//			if (i1.contient(I)) continue;
//		}
	}

	private static void remplirListes(String donnees) 
	{
		int x, y, n1, n2;
		Point capteur, balise;
		
		
		for(String s : donnees.split("\n"))
		{
			//"Sensor at x=2, y=18: closest beacon is at x=-2, y=15"
			n1 = s.indexOf('=') + 1;
			n2 = s.indexOf(',');
			x = Integer.parseInt(s.substring(n1,n2));
			n1 = s.indexOf('=', n2) + 1;
			n2 = s.indexOf(':');
			y = Integer.parseInt(s.substring(n1,n2));
			capteur = new Point(x,y);
			
			n1 = s.indexOf('=', n2) + 1;
			n2 = s.indexOf(',', n1);
			x = Integer.parseInt(s.substring(n1,n2));
			n1 = s.indexOf('=', n1) + 1;
			y = Integer.parseInt(s.substring(n1));
			balise = new Point(x,y);
			
			if (balise.y == ligneTestee) nbBaliseSurLigneTestee++;
			CapteurBalise cb = new CapteurBalise(capteur, balise);
			capteursBalises.add(cb);
		}
	}

	static class CapteurBalise
	{
		public int distance;
		Point capteur, balise; 
		Intervalle intervalle = null;
		String valeur;
		
		public CapteurBalise(Point capteur, Point balise) 
		{
			this.capteur = capteur;
			this.balise = balise;
			distance = Math.abs(capteur.x - balise.x) + Math.abs(capteur.y - balise.y);
		}
		
		public String toString()
		{
			String s = "capteur = " + capteur + " et balise = " + balise + " distance = " + distance + "\n\t";
			if (intervalle != null)
			{
				for (int i = 0; i < intervalle.longueur(); i++)
				{
					s += "#";
				}
			}
			else s += "rien";
			
			return s;
		}

		public void setIntervalle(Intervalle intervalle) {
			this.intervalle = intervalle;
		}
	}
	static class Intervalle
	{
		Point A, B;
		int longueur = -1;
		
		public Intervalle(Point A, int d)
		{
			this.A = new Point(A.x-d,A.y);
			this.B = new Point(A.x+d, A.y);
			longueur = 2*d + 1;
		}

		public boolean aDroite(Intervalle i) 
		{
			return i.A.x < A.x && i.B.x >= A.x;
		}

		public boolean contient(Intervalle i) 
		{
			return A.x <= i.A.x && B.x >= i.B.x;
		}

		public boolean disjoint(Intervalle i) 
		{
			return i.B.x < A.x || i.A.x > B.x;
		}

		public int longueur() 
		{
			return longueur;
		}
		
		public String toString()
		{
			return "[" + A.x + " ; " + B.x + "]";
		}
	}
	
	
}
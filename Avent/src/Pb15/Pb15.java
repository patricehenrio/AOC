package Pb15;

import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import javax.swing.JOptionPane;

import AOC.AOC;

public class Pb15 extends AOC 
{
	private static int ligneTestee;
	private static int nbBaliseSurLigneTestee;
	static List<CapteurBalise> capteursBalises = new ArrayList<CapteurBalise>();
	static TreeSet<Intervalle> intervalles = new TreeSet<Intervalle>();
	static TreeSet<Integer> balises = new TreeSet<Integer>();
	private static int ligneMaxi;

	public static void main(String[] args) throws IOException 
	{
		//les données
		String donnees = chargerDonnees("pb 15.txt");
		ligneTestee = 2000000;
		ligneMaxi = 4000000;

//		//test
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
		
		//première partie
		System.out.println(partie_1());
		//Deuxième partie
		System.out.println(partie_2());
	}

	private static long partie_2() 
	{
		int ligneCible = -1;
		TreeSet<Intervalle> resultat = new TreeSet<Intervalle>();
		Intervalle intervalle;
		
		for(int ligne = 0; ligne <=ligneMaxi; ligne++)
		{
			ligneTestee = ligne;
			intervalles.clear();
			for(int i = 0; i < capteursBalises.size(); i++)
			{
				CapteurBalise cb = capteursBalises.get(i);
				Point c = cb.capteur;
				int d = cb.distance;
				
				long k = Math.abs(c.y - ligneTestee);
				//si k est supérieur à d, cela signifie que la zone de détection du capteur 
				//ne comprend aucun point de la ligne testée
				if (k > d) continue;
				//pour les capteurs dont la zone de détection rencontre la ligne test
				long gauche = k + c.x - d;
				long droite = c.x + d - k;
				if (gauche > ligneMaxi) continue;
				if (droite < 0) continue;
				if (gauche < 0) gauche = 0;
				if (droite > ligneMaxi) droite = ligneMaxi;
				intervalle = new Intervalle(gauche, droite);
				intervalles.add(intervalle);
			}
			intervalle = null;
			for(Intervalle I : intervalles) 
			{
				if (intervalle == null)
				{
					intervalle = I;
					continue;
				}
				if (intervalle.contient(I))
				{
					continue;
				}
				if (intervalle.contient(I.debut))
				{
					intervalle = new Intervalle(intervalle.debut, I.fin);
					continue;
				}
				if (intervalle.fin == (I.debut -1))
				{
					intervalle = new Intervalle(intervalle.debut, I.fin);
					continue;
				}
				
				ligneCible = ligne;
				resultat.add(intervalle);
				resultat.add(I);
				System.out.println("ligne pouvant comporter une balise, ligne = " +  ligne + " intervalle = " + intervalle + " et I = " + I);
			}
			if (ligneCible >=0) break;
		}
	    String message = JOptionPane.showInputDialog(null, "Quelle est la ligne concernée !", "Advent of code, jour 15 !", JOptionPane.QUESTION_MESSAGE);
		long y = Long.parseLong(message);
		
		message = JOptionPane.showInputDialog(null, "Quelle est la colonne concernée !", "Advent of code, jour 15 !", JOptionPane.QUESTION_MESSAGE);
		long x = Long.parseLong(message);
		
		return x * ligneMaxi + y;
	}

	private static long partie_1() 
	{
		for(CapteurBalise cb : capteursBalises)
		{
			if(cb.balise.y == ligneTestee) balises.add(cb.balise.x);
		}
		
		nbBaliseSurLigneTestee =balises.size();
		for(int i = 0; i < capteursBalises.size(); i++)
		{
			CapteurBalise cb = capteursBalises.get(i);
			Point c = cb.capteur;
			int d = cb.distance;
			
			int k = Math.abs(c.y - ligneTestee);
			//si k est supérieur à d, cela signifie que la zone de détection du capteur 
			//ne comprend aucun point de la ligne testée
			if (k > d) continue;
			//pour les capteurs dont la zone de détection rencontre la ligne test
			int gauche = k + c.x - d, droite = c.x + d - k;
			Intervalle intervalle = new Intervalle(gauche, droite);
			intervalles.add(intervalle);
		}
		Intervalle intervalle = null;
		for(Intervalle I : intervalles) 
		{
			if (intervalle == null)
			{
				intervalle = I;
				continue;
			}
			if (intervalle.contient(I))
			{
				continue;
			}
			if (intervalle.contient(I.debut))
			{
				intervalle = new Intervalle(intervalle.debut, I.fin);
				continue;
			}
			throw new RuntimeException("cas non prévu, intervalle = " + intervalle + " et I = " + I);
		}
		
		long N = intervalle.longueur();
		return N - nbBaliseSurLigneTestee;
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
			
			CapteurBalise cb = new CapteurBalise(capteur, balise);
			capteursBalises.add(cb);
		}
	}
	
	static class CapteurBalise
	{
		public int distance;
		Point capteur, balise; 
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
			return s;
		}
	}
	
	/**
	 * Une classe pour représenter un intervalle d'entiers.<br>
	 * On aura souvent besoin de savoir si une liste d'intervalle répond à certains
	 * critères.<br>
	 * Soit I(n) la liste des intervalles et D(n) et F(n) leur début et fin.<br>
	 * <ul>
	 * <li>La liste doit avoir des intervalles contigus : pour chaque n, D(n + 1)=
	 * F(n) + 1</li>
	 * <li>La liste ne doit pas avoir de chevauchement : pour chaque n, D(n+1) >
	 * F(n)</li>
	 * </ul>
	 *
	 * @author Patrice
	 */
	public static class Intervalle implements Comparable<Intervalle>
	{
		/**
		 * L'extrémité gauche, si I=[n,m], debut = n
		 */
		long debut;

		/**
		 * L'extrémité droite, si I=[n,m], fin = m
		 */
		long fin;

		/**
		 * constructeur
		 *
		 * @param gauche
		 * @param droite
		 */
		public Intervalle(long gauche, long droite)
		{
			if (gauche > droite)
			{
				this.debut = droite;
				this.fin = gauche;
			} else
			{
				this.debut = gauche;
				this.fin = droite;
			}

		}

		public boolean contient(Intervalle I) 
		{
			return this.contient(I.debut) && this.contient(I.fin);
		}

		public long longueur() 
		{
			return fin - debut + 1;
		}

		/**
		 * Comparer un intervalle à celui-ci Soient I1 cet intervalle et I2 un autre
		 * intervalle<br>
		 * D1 et F1, début et fin de I1, D2 et F2 pour I2<br>
		 * Si D1 < D2 alors I1 est avant I2<br>
		 * Si D1 > D2 alors I1 est après I2<br>
		 * Si D1 = D2 et F1 < F2 alors I1 est avant I2<br>
		 * Si D1 = D2 et F1 > F2 alors I1 est après I2<br>
		 * Si D1 = D2 et F1 = F2 alors I1 = I2<br>
		 *
		 * @return -1, 1 ou 0
		 */
		@Override
		public int compareTo(Intervalle autre)
		{
			if (debut < autre.debut)
				return -1;
			if (debut > autre.debut)
				return 1;
			if (fin < autre.fin)
				return -1;
			if (fin > autre.fin)
				return 1;
			return 0;
		}

		/**
		 * Prédicat pour indiquer si debut <= n <= fin
		 *
		 * @param n l'entier à tester
		 * @return true si l'entier vérifie la condition
		 */
		public boolean contient(long n)
		{
			return debut <= n && fin >= n;
		}

		/**
		 * On surcharge le prédicat equals()<br>
		 * un prédicat qui indique si cet intervalle est égal à l'intervalle I
		 * testé.<br>
		 * C'est à dire s'il a mêmes début et fin que I.<br>
		 * Si l'objet comparé n'est pas un intervalle, le cast lèvera une exception.
		 *
		 * @param o l'objet à comparer
		 * @return true si c'est le cas
		 */
		@Override
		public boolean equals(Object o)
		{
			Intervalle I = (Intervalle) o;
			return compareTo(I) == 0;
		}

		/**
		 * @return renvoie le début
		 */
		public long getDebut()
		{
			return debut;
		}

		/**
		 * @return la fin
		 */
		public long getFin()
		{
			return fin;
		}

		/**
		 * pour savoir si deux intervalles ont une intersection vide
		 */
		public boolean intersectionVide(Intervalle I)
		{
			return debut > I.fin || fin < I.debut;
		}

		/**
		 * un prédicat qui indique si cet intervalle est après l'intervalle I testé.<br>
		 * C'est à dire si son début est après celui de I ou alors est égal et c'est sa
		 * fin qui est après celle de I.
		 *
		 * @param I l'intervalle à tester
		 * @return true si c'est le cas
		 */
		public boolean isApres(Intervalle I)
		{
			return compareTo(I) > 0;
		}

		/**
		 * un prédicat qui indique si cet intervalle est avant l'intervalle I testé.<br>
		 * C'est à dire si son début est avant celui de I ou alors est égal et c'est sa
		 * fin qui est avant celle de I.
		 *
		 * @param I l'intervalle à tester
		 * @return true si c'est le cas
		 */
		public boolean isAvant(Intervalle I)
		{
			return compareTo(I) < 0;
		}

		public boolean isEgal(Intervalle I)
		{
			return compareTo(I) == 0;
		}

		/**
		 * modifie le début
		 *
		 * @param debut la nouvelle extrémité
		 */
		public void setDebut(int debut)
		{
			if (debut > fin)
				throw new IllegalArgumentException(
						"Erreur : début (" + debut + ") doit être " + "inférieur à fin (" + fin + ")");
			this.debut = debut;
		}

		/**
		 * modifie la fin
		 *
		 * @param fin la nouvelle extrémité
		 */
		public void setFin(int fin)
		{
			if (fin < debut)
				throw new IllegalArgumentException(
						"Erreur : début (" + debut + ") doit être " + "inférieur à fin (" + fin + ")");
			this.fin = fin;
		}

		/**
		 * pour obtenir une description en chaîne de caractères d'un intervalle
		 */
		@Override
		public String toString()
		{
			return "[" + debut + "," + fin + "]";
		}
	}
}

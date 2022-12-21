package Pb14;

import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Pb14.Pb14_1.Chemin;
import fichiers.Fichiers;

public class Pb14_2 
{
	static Point mini;
	static Point maxi;
	static int longueur, hauteur;
	static char[][]caracteres; 
	static List<Chemin> chemins;
	static Point depart = new Point(500,0);
	static int depassement = 500; //valeur déterminée empiriquement

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
		longueur = maxi_x - mini_x + 1 + 2*depassement;
		hauteur = maxi_y + 3;
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
		for(int i = 0; i < longueur; i++)
		{
			caracteres[hauteur-1][i] = '#';
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
	
	private static String afficher() 
	{
		String s = "";
		for(int i = 0; i < hauteur; i++)
			s += "\n" + i + "\t" + new String(caracteres[i]);
		return s;
	}

	private static char getCaractere(int x, int y) 
	{
		return caracteres[y][x-mini.x+1+depassement];
	}
	
	private static void setCaractere(int x, int y, char c)
	{
		caracteres[y][x-mini.x+1+depassement] = c;
	}
	
	private static void traiter() 
	{
		while(true)
		{
			Point P = depart;
			Point suivant;
			while(true)
			{
				suivant = getProchainPoint(P);
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

	private static Point getProchainPoint(Point P) 
	{
		char c = getCaractere(P.x,P.y+1);
		if (c == '.') return new Point(P.x, P.y+1);

		c = getCaractere(P.x-1, P.y+1);
		if (c == '.') return new Point(P.x-1, P.y+1);
		
		c = getCaractere(P.x+1, P.y+1);
		if (c == '.') return new Point(P.x+1, P.y+1);
		
		return null;
	}
	
}
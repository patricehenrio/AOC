package Pb05;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import AOC.AOC;
import chaines.Chaines;

public class Pb05 extends AOC
{
	static List<String> piles = new ArrayList<String>();
	static String repertoire = "F:\\AOC\\calendrier avent\\";
	static int part = 2;
	private static String[] lignes;

	public static void main(String[] args) throws IOException 
	{
		String donnees = chargerDonnees("pb 05.txt");
		
		String[] aux = donnees.split("\n\n");
		
		
		lignes = aux[1].split("\n");
		
		traiterPiles(aux[0]);
		//première partie
		System.out.println(partie_1());

		traiterPiles(aux[0]);
		//Deuxième partie
		System.out.println(partie_2());
	}
	
	private static void traiterPiles(String s) 
	{
		String[] aux = s.split("\n");
		char[] car = aux[0].toCharArray();
		for(int i = 1; i < car.length; i +=4)
		{
			if(car[i] == ' ') piles.add("");
			else piles.add(""+car[i]);
		}
		
		for(int i = 1; i < aux.length; i++)
		{
			car = aux[i].toCharArray();
			if (car[1] == '1') break;
			for(int j = 1; j < car.length; j +=4)
			{
				if(car[j] != ' ')
				{
					int k = j/4;
					piles.set(k, piles.get(k)+ car[j]);
				}
			}
		}
	}

	private static String partie_2() 
	{
		for(int i = 0; i < lignes.length; i++)
		{
			traiter2(lignes[i]);
		}
		String resultat = "";
		for(int j = 0; j < 9; j++) resultat += piles.get(j).charAt(0);
		return resultat;
	}

	private static String partie_1() 
	{
		for(int i = 0; i < lignes.length; i++)
		{
			traiter1(lignes[i]);
		}
		String resultat = "";
		for(int j = 0; j < 9; j++) resultat += piles.get(j).charAt(0);
		return resultat;
	}

	private static void traiter2(String string) 
	{
		int nb = Integer.parseInt(Chaines.entreMarqueurs(string, "move ", " from"));
		int depart = Integer.parseInt(Chaines.entreMarqueurs(string, "from ", " to"))-1;
		int arrivee = Integer.parseInt(Chaines.apresMarqueur(string, " to "))-1;
		
		String s = piles.get(depart);
		String deplacement = s.substring(0,nb);
		piles.set(depart, s.substring(nb));
		s = piles.get(arrivee);
		piles.set(arrivee, deplacement + s);
	}
	
	private static void traiter1(String string) 
	{
		int nb = Integer.parseInt(Chaines.entreMarqueurs(string, "move ", " from"));
		int depart = Integer.parseInt(Chaines.entreMarqueurs(string, "from ", " to"))-1;
		int arrivee = Integer.parseInt(Chaines.apresMarqueur(string, " to "))-1;
		
		String s = "";
		while(nb > 0)
		{
			s = piles.get(depart);
			char c = s.charAt(0);
			piles.set(depart, s.substring(1));
			s = piles.get(arrivee);
			piles.set(arrivee, c + s);
			nb--;
		}
	}
}

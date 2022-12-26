package Pb04;

import java.io.IOException;

import AOC.AOC;
import chaines.Chaines;

public class Pb04 extends AOC
{
	private static String[] lignes;

	public static void main(String[] args) throws IOException 
	{
		String donnees = chargerDonnees("pb 04.txt");
		lignes = donnees.split("\n");
		
		//première partie
		System.out.println(partie_1());

		//Deuxième partie
		System.out.println(partie_2());
	}
	
	private static int partie_2() 
	{
		int NbLigne = 0;
		for(String ligne : lignes)
		{
			String s1 = Chaines.avantMarqueur(ligne, ",");
			String s2 = Chaines.apresMarqueur(ligne, ",");
			Intervalle I1 = new Intervalle(s1);
			Intervalle I2 = new Intervalle(s2);
			if (I1.chevauche(I2) || I2.chevauche(I1)) 
			{
				NbLigne++;
			}
		}
		return NbLigne;
	}

	private static int partie_1() 
	{
		int NbLigne = 0;
		for(String ligne : lignes)
		{
			String s1 = Chaines.avantMarqueur(ligne, ",");
			String s2 = Chaines.apresMarqueur(ligne, ",");
			Intervalle I1 = new Intervalle(s1);
			Intervalle I2 = new Intervalle(s2);
			if (I1.contient(I2) || I2.contient(I1)) 
			{
				NbLigne++;
			}
		}
		return NbLigne;
	}

	public static class Intervalle
	{
		int debut, fin;
		public Intervalle(String s)
		{
			debut = Integer.parseInt(Chaines.avantMarqueur(s, "-"));
			fin = Integer.parseInt(Chaines.apresMarqueur(s,"-"));
		}
		public boolean chevauche(Intervalle I) 
		{
			if (I.debut < debut) return I.chevauche(this);
			return !(fin < I.debut);
		}
		public boolean contient(Intervalle I) 
		{
			return (I.debut >= debut) && (I.fin <= fin);
		}
	}

}

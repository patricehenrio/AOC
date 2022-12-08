package Pb04;

import java.io.IOException;

import chaines.Chaines;
import fichiers.Fichiers;

public class Pb04 
{
	static String repertoire = "F:\\AOC\\calendrier avent\\";

	public static void main(String[] args) throws IOException 
	{
		String fichier = repertoire + "pb 04.txt";
		String donnees = Fichiers.chargerContenuTexte(fichier);
		String[] lignes = donnees.split("\n");
		//première partie
//		int NbLigne = 0;
//		for(String ligne : lignes)
//		{
//			String s1 = Chaines.avantMarqueur(ligne, ",");
//			String s2 = Chaines.apresMarqueur(ligne, ",");
//			Intervalle I1 = new Intervalle(s1);
//			Intervalle I2 = new Intervalle(s2);
//			if (I1.contient(I2) || I2.contient(I1)) 
//			{
//				System.out.println(ligne);
//				NbLigne++;
//			}
//		}
//		System.out.println(NbLigne);
		
		//deuxième partie
		int NbLigne = 0;
		for(String ligne : lignes)
		{
			String s1 = Chaines.avantMarqueur(ligne, ",");
			String s2 = Chaines.apresMarqueur(ligne, ",");
			Intervalle I1 = new Intervalle(s1);
			Intervalle I2 = new Intervalle(s2);
			if (I1.chevauche(I2) || I2.chevauche(I1)) 
			{
				System.out.println(ligne);
				NbLigne++;
			}
		}
		System.out.println(NbLigne);

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

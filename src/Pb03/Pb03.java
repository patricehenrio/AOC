package Pb03;

import java.io.IOException;

import fichiers.Fichiers;

public class Pb03 
{
	public static String alphabet = " abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

	public static void main(String[] args) throws IOException
	{
		String fichier = "C:\\Users\\patri\\Desktop\\calendrier avent\\pb 03.txt";
		String donnees = Fichiers.chargerContenuTexte(fichier);
		String[] lignes = donnees.split("\n");
		//première partie
		int prio = 0;
//		//for(char c : "pLPvtsZr".toCharArray())    System.out.println(c+ "\t" + alphabet.indexOf(c));
//		for(int i = 0; i <lignes.length; i++)
//		{
//			String s = lignes[i];
//			int n = s.length()/2;
//			String s1 = s.substring(0,n), s2 = s.substring(n);
//			char[] caracteres = s1.toCharArray();
//			for(char c : caracteres) if (s2.contains("" + c))
//			{
//				prio += alphabet.indexOf(c);
//				break;
//			};
//		}
		
		//deuxième partie
		int i = 0;
		String s1, s2, s3;
		while(i < lignes.length)
		{
			s1 = lignes[i];
			i++;
			s2 = lignes[i];
			i++;
			s3 = lignes[i];
			i++;
			for(char c : s1.toCharArray())
			{
				if (s2.indexOf(c) < 0) continue;
				if (s3.indexOf(c) < 0) continue;
				prio += alphabet.indexOf(c);
				break;
			}
		}
		System.out.println(prio);
	}
}

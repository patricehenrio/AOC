package Pb03;

import java.io.IOException;

import AOC.AOC;

public class Pb03 extends AOC 
{
	public static String alphabet = " abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static String[] lignes;

	public static void main(String[] args) throws IOException
	{
		String donnees = chargerDonnees("pb 03.txt");
		lignes = donnees.split("\n");
		
		//première partie
		System.out.println(partie_1());

		//Deuxième partie
		System.out.println(partie_2());
	}

	private static int partie_2() 
	{
		int prio = 0;
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
		return prio;
	}

	private static int partie_1() 
	{
		int prio = 0;

		for(int i = 0; i <lignes.length; i++)
		{
			String s = lignes[i];
			int n = s.length()/2;
			String s1 = s.substring(0,n), s2 = s.substring(n);
			char[] caracteres = s1.toCharArray();
			for(char c : caracteres) if (s2.contains("" + c))
			{
				prio += alphabet.indexOf(c);
				break;
			};
		}
		return prio;
	}
}

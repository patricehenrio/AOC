package Pb01;

import java.io.IOException;

import AOC.AOC;

public class Pb01 extends AOC
{
	static String[] elfes;
	public static void main(String[] args) throws IOException 
	{
		String donnees = chargerDonnees("pb 01.txt");
		
		elfes = donnees.split("\n\n");
		
		//première partie
		System.out.println(partie_1());

		//Deuxième partie
		System.out.println(partie_2());
	}

	private static String partie_2() 
	{
		int max1 = 0, max2 = 0, max3 = 0;
		
		for(int i = 0; i < elfes.length; i++)
		{
			int calorie = 0;
			String[] lignes = elfes[i].split("\n");
			for (String ligne : lignes) calorie += Integer.parseInt(ligne);
			if (calorie < max3) continue;
			if (calorie >= max1) 
			{
				max3 = max2;
				max2 = max1;
				max1 = calorie;
			}
			else 
			{
				if (calorie >= max2)
				{
					max3 = max2;
					max2 = calorie;
				}
				else max3 = calorie;
			}
		}
		
		return "partie 2 : " + (max1 + max2 + max3);
	}

	private static String partie_1() 
	{
		int max = 0;
 		for(int i = 0; i < elfes.length; i++)
		{
			int calorie = 0;
			String[] lignes = elfes[i].split("\n");
			for (String ligne : lignes)
			{
				calorie += Integer.parseInt(ligne);
				if (calorie > max) max = calorie;
			}
		}
		return "partie 1 : " + max;
	}

}

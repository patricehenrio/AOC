package Pb01;

import java.io.IOException;

import fichiers.Fichiers;

public class Pb01 
{
	static String repertoire = "F:\\AOC\\calendrier avent\\";

	public static void main(String[] args) throws IOException 
	{
		String fichier = repertoire + "pb 01.txt";
		String donnees = Fichiers.chargerContenuTexte(fichier);
		String[] elfes = donnees.split("\n\n");
		int calorie, max1 = 0, max2 = 0, max3 = 0;
//      //première partie
//		for(int i = 0; i < elfes.length; i++)
//		{
//			calorie = 0;
//			String[] lignes = elfes[i].split("\n");
//			for (String ligne : lignes)
//			{
//				calorie += Integer.parseInt(ligne);
//				if (calorie > max) max = calorie;
//			}
//		}
		//Deuxième partie
		
		for(int i = 0; i < elfes.length; i++)
		{
			calorie = 0;
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
		
		System.out.println(max1 + max2 + max3);
	}

}

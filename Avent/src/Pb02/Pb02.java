package Pb02;

import java.io.IOException;

import AOC.AOC;

public class Pb02 extends AOC 
{
	private static String[] lignes;

	public static void main(String[] args) throws IOException 
	{
		String donnees = chargerDonnees("pb 02.txt");
		lignes = donnees.split("\n");
		
		//première partie
		System.out.println(partie_1());

		//Deuxième partie
		System.out.println(partie_2());
	}

	/**
	 * Deuxième partie
	 */
	private static int partie_2() 
	{
		int total = 0;
		for(String ligne : lignes)
		{
			switch(ligne)
			{
				case "A X" : total += 3;
					break;
				case "A Y" : total += 4;
					break;
				case "A Z" : total += 8;
					break;
				case "B X" : total += 1;
					break;
				case "B Y" : total += 5;
					break;
				case "B Z" : total += 9;
					break;
				case "C X" : total += 2;
					break;
				case "C Y" : total += 6;
					break;
				case "C Z" : total += 7;
					break;
				default : throw new RuntimeException("erreur " + ligne);
			}
		}
		
		return total;
	}

	/**
	 * Première partie
	 */
	private static int partie_1() 
	{
		int total = 0;

		for(String ligne : lignes)
		{
			char a = ligne.charAt(0);
			char x = ligne.charAt(2);
			switch(a)
			{
				case 'A' :
					switch(x)
					{
						case 'X' :
							total += 4;
							break;
						case 'Y' :
							total += 8;
							break;
						case 'Z' :
							total += 3;
							break;
						default : throw new RuntimeException("erreur sur le deuxième lettre : " + a + " " + x);	
					}
					break;
				case 'B' :
					switch(x)
					{
						case 'X' :
							total += 1;
							break;
						case 'Y' :
							total += 5;
							break;
						case 'Z' :
							total += 9;
							break;
						default : throw new RuntimeException("erreur sur le deuxième lettre : " + a + " " + x);	
					}
					break;
				case 'C' :
					switch(x)
					{
						case 'X' :
							total += 7;
							break;
						case 'Y' :
							total += 2;
							break;
						case 'Z' :
							total += 6;
							break;
						default : throw new RuntimeException("erreur sur le deuxième lettre : " + a + " " + x);	
					}
					break;
				default : throw new RuntimeException("erreur sur le deuxième lettre : " + a + " " + x);	
			}
		}
		return total;
	}
}

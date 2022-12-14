package Pb06;

import java.io.IOException;

import AOC.AOC;

public class Pb06 extends AOC
{
	static String repertoire = "F:\\AOC\\calendrier avent\\";
	private static String donnees;

	public static void main(String[] args) throws IOException 
	{
		donnees = chargerDonnees("pb 06.txt");
		
		//test
//		donnees = "mjqjpqmgbljsphdztnvjfqwrcgsmlb";
//		donnees = "bvwbjplbgvbhsrlpgdmjqwftvncz";
//		donnees = "nppdvjthqldpwncqszvftbrmjlhg";
//		donnees = "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg";
//		donnees = "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw";

		//première partie
		System.out.println(partie_1());

		//Deuxième partie
		System.out.println(partie_2());
	}

	private static String partie_2() 
	{
		return traiter(14);
	}

	private static String partie_1() 
	{
		return traiter(4);
	}

	private static String traiter(int nbCar) 
	{
		String s = donnees;
		String resultat = "";
		int i = 0;
		while (resultat.length() < nbCar)
		{
			i++;
			char c = s.charAt(0);
			int n = s.indexOf(c, 1);
			s = s.substring(1);
			if (n > 0 && n < (nbCar - resultat.length())) 
			{
				resultat = "";
				continue;
			}
			resultat += c;
		}
		return resultat + "\t" + i;
	}
}

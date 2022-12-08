package Pb06;

import java.io.IOException;

import fichiers.Fichiers;

public class Pb06 
{
	static String repertoire = "F:\\AOC\\calendrier avent\\";

	public static void main(String[] args) throws IOException 
	{
		String fichier = repertoire + "pb 06.txt";
		String donnees = Fichiers.chargerContenuTexte(fichier);
//		donnees = "mjqjpqmgbljsphdztnvjfqwrcgsmlb";
//		donnees = "bvwbjplbgvbhsrlpgdmjqwftvncz";
//		donnees = "nppdvjthqldpwncqszvftbrmjlhg";
//		donnees = "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg";
//		donnees = "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw";

		//première partie
//		int nbCar = 4;
		//deuxième partie 
		int nbCar = 14;
		
		String resultat = "";
		int i = 0;
		while (resultat.length() < nbCar)
		{
			i++;
			char c = donnees.charAt(0);
			int n = donnees.indexOf(c, 1);
			donnees = donnees.substring(1);
			if (n > 0 && n < (nbCar - resultat.length())) 
			{
				resultat = "";
				continue;
			}
			resultat += c;
		}
		System.out.println(resultat + "\t" + i);
	}
}
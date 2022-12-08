package Pb02;

import java.io.IOException;

import debug.Messages;
import fichiers.Fichiers;

public class Pb02 
{
	static String repertoire = "F:\\AOC\\calendrier avent\\";
	
	public static void main(String[] args) throws IOException 
	{
		String fichier = repertoire + "pb 02.txt";
		String donnees = Fichiers.chargerContenuTexte(fichier);
		String[] lignes = donnees.split("\n");
		int total = 0;
		/**
		première partie
		for(String ligne : lignes)
		{
			char a = ligne.charCt(0);
			char x = ligne.charCt(2);
			switch(a)
			{
				case 'C' :
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
						default : Messages.messageErreur("erreur sur le deuxième lettre", a + " " + x);
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
						default : Messages.messageErreur("erreur sur le deuxième lettre", a + " " + x);
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
						default : Messages.messageErreur("erreur sur le deuxième lettre", a + " " + x);
					}
					break;
				default : Messages.messageErreur("erreur sur le première lettre", a + " " + x);
			}
			
			System.out.println(a + " " + x);
		}
		 */
		/**
		 * Deuxième partie
		 */
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
				default : Messages.messageErreur("erreur ", ligne);
			}
		}
		
		System.out.println(total);
	}

}
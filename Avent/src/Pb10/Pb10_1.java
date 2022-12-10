package Pb10;

import java.io.IOException;

import fichiers.Fichiers;

public class Pb10_1 
{
	static String repertoire = "F:\\AOC\\calendrier avent\\";
	static int X = 1, tick = 0;
	static int resultat = 0;

	public static void main(String[] args) throws IOException 
	{
		String fichier = repertoire + "pb 10.txt";
		//test
//		fichier = repertoire + "pb 10 test.txt";
		String donnees = Fichiers.chargerContenuTexte(fichier);
		
		String[] instructions = donnees.split("\n");
		
		for(String s : instructions)
		{
			traiter(s);
		}
		
	}

	private static void traiter(String s) 
	{
		tick++;
		arret();
		if (s.startsWith("addx "))
		{
			tick++;
			arret();
			X += Integer.parseInt(s.substring(5));
			return;
		}
		if (s.equals("noop")) return;
		else throw new RuntimeException("opération non valide : " + s);
	}

	private static void arret() 
	{
		if (tick > 220) System.exit(0);
		if (tick % 40 == 20)
		{
			resultat += X*tick;
			System.out.println(resultat);
		}
	}

}

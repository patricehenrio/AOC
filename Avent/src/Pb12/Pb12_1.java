package Pb12;

import java.io.IOException;

import fichiers.Fichiers;

public class Pb12_1 
{
	static String repertoire = "F:\\AOC\\calendrier avent\\";
	static String[] lignes;
	
	public static void main(String[] args) throws IOException 
	{
		String fichier = repertoire + "pb 12.txt";
		//test
//		fichier = repertoire + "pb 11 test.txt";
		
		String donnees = Fichiers.chargerContenuTexte(fichier);
		
		lignes = donnees.split("\n");
		
		int nombreColonnes = lignes[0].length(), nombreLignes = lignes.length;
		
		int[][] tableau = new int[nombreLignes][nombreColonnes];
		
		char c = donnerChar(3,7);
		
		System.out.println(c);

	}

	private static char donnerChar(int ligne, int colonne) 
	{
		return lignes[ligne-1].charAt(colonne-1);
	}

}

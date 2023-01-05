package AOC;

import java.io.IOException;

import fichiers.Fichiers;

public class AOC 
{
	static String repertoire = "F:\\AOC\\calendrier avent\\";
	
	protected static String chargerDonnees(String fichier) throws IOException
	{
		fichier = repertoire + fichier;
		String donnees = Fichiers.chargerContenuTexte(fichier);
		return donnees;
	}
}

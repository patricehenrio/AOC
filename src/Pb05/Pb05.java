package Pb05;

import java.io.IOException;

import chaines.Chaines;
import debug.Messages;
import fichiers.Fichiers;

public class Pb05 
{
	static String[] piles = {"GJZ","CVFWPRLQ","RGLCMPF","MHPWBFL","QVSFCG","LTQMZJHW","VBSFH","SZJF","TBHFPDCM"};
	public static void main(String[] args) throws IOException 
	{
		String fichier = "C:\\Users\\patri\\Desktop\\calendrier avent\\pb 05.txt";
		String donnees = Fichiers.chargerContenuTexte(fichier);
		String[] lignes = donnees.split("\n");
		
//		String[] tableau = new String[9];
//		for(int i = 0; i < 9; i++) piles[i] = "";
//		for(int i = 0; i < 8; i++)
//		{
//			String ligne = lignes[i];
//			char[] caracteres = ligne.toCharArray();
//			for(int j = 0; j < 9; j++)
//			{
//				char c = ligne.charAt(4*j+1);
//				if (c == ' ') continue;
//				piles[j] +=  c;
//			}
//		}
//		System.out.println("***************************************");
//		for(int j = 0; j < 9; j++) 
//		{
//			if (!tableau[j].equals(piles[j]))
//			{
//				System.out.println(tableau[j] + " <> " + piles[j]);
//			}
//		}
		
		//première partie
		for(int i = 10; i < 511; i++)
		{
//			traiter1(lignes[i]);
			traiter2(lignes[i]);
		}
		
		for(int j = 0; j < 9; j++) System.out.println((j+1) + "\t" + piles[j]);
		System.out.println("**************************************");
		String resultat = "";
		for(int j = 0; j < 9; j++) resultat += piles[j].charAt(0);
		System.out.println(resultat);
	}
	
	private static void traiter2(String string) 
	{
		int nb = Integer.parseInt(Chaines.entreMarqueurs(string, "move ", " from"));
		int depart = Integer.parseInt(Chaines.entreMarqueurs(string, "from ", " to"))-1;
		int arrivee = Integer.parseInt(Chaines.apresMarqueur(string, " to "))-1;
		
		String deplacement = piles[depart].substring(0,nb);
		piles[depart] = piles[depart].substring(nb);
		piles[arrivee] = deplacement + piles[arrivee];
		
//		while(nb > 0)
//		{
//			char c = piles[depart].charAt(0);
//			piles[depart] = piles[depart].substring(1);
//			piles[arrivee] = c + piles[arrivee];
//			nb--;
//		}
	}

	private static void traiter1(String string) 
	{
		int nb = Integer.parseInt(Chaines.entreMarqueurs(string, "move ", " from"));
		int depart = Integer.parseInt(Chaines.entreMarqueurs(string, "from ", " to"))-1;
		int arrivee = Integer.parseInt(Chaines.apresMarqueur(string, " to "))-1;
		
		while(nb > 0)
		{
			char c = piles[depart].charAt(0);
			piles[depart] = piles[depart].substring(1);
			piles[arrivee] = c + piles[arrivee];
			nb--;
		}
	}

}

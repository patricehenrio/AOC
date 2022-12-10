package Pb07;

import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.TreeSet;

import debug.Messages;
import fichiers.Fichiers;

public class Pb07 
{
	static String repertoire = "F:\\AOC\\calendrier avent\\";
	static HashMap <String, Integer> arborescence = new HashMap <String, Integer>();
	static HashMap <String, Integer> arborescenceTotale = new HashMap<String, Integer>();
	static String repertoireCourant = "";
	static int part = 2;
	static TreeSet<String> rep = new TreeSet<String>(new Comparateur());

	
	
	public static void main(String[] args) throws IOException 
	{
		String fichier = repertoire + "pb 07.txt";
		String donnees = Fichiers.chargerContenuTexte(fichier);
		//données test
//		donnees = "$ cd /\n"
//				+ "$ ls\n"
//				+ "dir a\n"
//				+ "14848514 b.txt\n"
//				+ "8504156 c.dat\n"
//				+ "dir d\n"
//				+ "$ cd a\n"
//				+ "$ ls\n"
//				+ "dir e\n"
//				+ "29116 f\n"
//				+ "2557 g\n"
//				+ "62596 h.lst\n"
//				+ "$ cd e\n"
//				+ "$ ls\n"
//				+ "584 i\n"
//				+ "$ cd ..\n"
//				+ "$ cd ..\n"
//				+ "$ cd d\n"
//				+ "$ ls\n"
//				+ "4060174 j\n"
//				+ "8033020 d.log\n"
//				+ "5626152 d.ext\n"
//				+ "7214296 k";

		String[] lignes = donnees.split("\n");
		
		for(int i = 0; i < lignes.length; i++)
		{
			String ligne = lignes[i];
//			System.out.println(ligne);
			char c = ligne.charAt(0);
			if (c=='$') traiterLigneCommande(ligne);
			else 
			{
				if (c=='d') traiterLigneDir(ligne);
				else 
				{
					if (Character.isDigit(c)) traiterLigneFile(ligne);
					else Messages.messageErreur(ligne, "pb sur le début de la ligne");
				}
			}
		}

		for(String s : arborescence.keySet()) rep.add(s);
		for(String s : rep) arborescenceTotale.put(s, arborescence.get(s));
		for(String s : rep) 
		{
			int n = s.lastIndexOf("/");
			if (n > 0)
			{	
				String t = s.substring(0,n);
				int m = arborescenceTotale.get(t);
				arborescenceTotale.put(t, m + arborescenceTotale.get(s));
//				System.out.println(s + " ---> " + arborescenceTotale.get(s));
//				System.out.println(t + " ---> " + arborescenceTotale.get(t));
//				System.out.println("*************************************");
			}
		}

		
		if (part == 1) traiter1();
		else traiter2();
	}


	private static void traiter2() 
	{
		//capacité totale
		int tot = 70000000;
		//capacité nécessaire 
		int nec = 30000000;
		//capacité utilisée
		int uti = arborescenceTotale.get("root");
		System.out.println("utilisés : " + uti);
		//capacité restante
		int res = tot - uti;
		System.out.println("restants : " + res);
		//capacité manquante
		int man = nec -res;
		System.out.println("manquants : " + man);
		
		for(String s : rep)
		{
//			System.out.println(s + " ---> " + arborescenceTotale.get(s));
			int n = arborescenceTotale.get(s);
			if (n < man) continue;
			if (n < tot)  tot = n;
		}
		System.out.println(tot);
	}


	private static void traiter1() 
	{
		int somme = 0;
		for(String s : arborescence.keySet())
		{
			int n = arborescenceTotale.get(s);
			if (n < 100000) somme += n;
		}
		System.out.println(somme);
	}

	private static void traiterLigneFile(String ligne) 
	{
		int n = ligne.indexOf(' ');
		n = Integer.parseInt(ligne.substring(0, n)) + arborescence.get(repertoireCourant);
		arborescence.put(repertoireCourant, n);
	}

	private static void traiterLigneDir(String ligne) 
	{
		if (ligne.startsWith("dir ")) 
		{
			String s = repertoireCourant + "/" + ligne.substring(4);
			if (! arborescence.containsKey(s)) arborescence.put(s, 0);
		}
		else Messages.messageErreur(ligne, "pb sur la ligne");
	}

	/**
	 * Toutes les lignes commençant par $ se poursuivent par cd ou ls
	 * @param ligne
	 */
	private static void traiterLigneCommande(String ligne) 
	{
		int n;
		if (ligne.startsWith("$ cd "))
		{
			String s = ligne.substring(5);
			switch(s)
			{
				case "/" : repertoireCourant = "root";
					break;
				case ".." : 
					n = repertoireCourant.lastIndexOf('/');
					repertoireCourant = repertoireCourant.substring(0,n);
					break;
				default :
					repertoireCourant = repertoireCourant + "/" + ligne.substring(5);
			}
		}
		else if (ligne.equals("$ ls"))
		{
			if (! arborescence.containsKey(repertoireCourant)) arborescence.put(repertoireCourant, 0);
		}
		else Messages.messageErreur(ligne, "pb sur la ligne");
	}
	
	static class Comparateur implements Comparator<String>
	{

		@Override
		public int compare(String s1, String s2) 
		{
			if(s1.equals(s2)) return 0;
			int n1 = s1.split("/").length;
			int n2 = s2.split("/").length;
			if (n1 < n2) return 1;
			if (n1 > n2) return -1;
			return s1.compareTo(s2);
		}
		
	}
}

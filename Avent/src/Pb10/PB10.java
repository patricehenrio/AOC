package Pb10;

import java.io.IOException;

import javax.swing.JOptionPane;

import AOC.AOC;

public class PB10 extends AOC 
{

	private static String[] instructions;
	private static int cycle;
	private static int X = 1;

	private static int ligneCourante;
	private static String row = "";
	private static String[] lignes = new String[7];
	static String ecran = "###" + ".".repeat(237);
			
	public static void main(String[] args) throws IOException 
	{
		String donnees = chargerDonnees("pb 10.txt");
		instructions = donnees.split("\n");
		//première partie
		System.out.println(partie_1());
		//Deuxième partie
		System.out.println(partie_2());
	}

	private static String partie_2() 
	{
		cycle = 0;
		X = 1;
		for(String s : instructions)
		{
			traiter2(s);
		}
		
		for(int i = 0; i < 7; i++)
		{
			System.out.println(lignes [i]);
		}

	    String message = JOptionPane.showInputDialog(null, "Que lisez-vous dans la console !", "Advent of code, jour 10 !", JOptionPane.QUESTION_MESSAGE);
		return message;
	}

	private static void traiter2(String s) 
	{
		if (s.startsWith("addx")) traiterAddx(s);
		else traiterNoop(s);
	}

	private static void traiterNoop(String s) 
	{
		cycle++;
		if ((cycle-1)%40 == 0)
		{
			lignes[ligneCourante] = row;
			ligneCourante++;
			row = "";
		}
		
		row += ecran.charAt((cycle-1)%40);
		if (cycle == 240) lignes[ligneCourante] = row;
	}

	private static void traiterAddx(String s) 
	{
		cycle++;
		if ((cycle-1)%40 == 0)
		{
			lignes[ligneCourante] = row;
			ligneCourante++;
			row = "";
		}

		row += ecran.charAt((cycle-1) % 40);
		cycle++;
		if ((cycle-1)%40 == 0)
		{
			lignes[ligneCourante] = row;
			ligneCourante++;
			row = "";
		}

		row += ecran.charAt((cycle-1) % 40);
		X += Integer.parseInt(s.substring(5));
		if (X == -1) ecran = "##" + ".".repeat(238);
		else if (X == 0) ecran = "###" + ".".repeat(237);
		else ecran = ".".repeat(X-1) + "###" + ".".repeat(238-X);
	}

	private static int partie_1() 
	{
		int resultat = 0;
		cycle = 0;
		for(String s : instructions)
		{
			resultat = traiter1(s, resultat);
			if (cycle > 220) break;
		}
		return resultat;
	}

	private static int traiter1(String s, int resultat) 
	{
		cycle ++;
		if (cycle % 40 == 20)
		{
			resultat += X*cycle;	
		}
		if (s.startsWith("addx "))
		{
			cycle++;
			if (cycle % 40 == 20) resultat += X*cycle;	
			X += Integer.parseInt(s.substring(5));
			return resultat;
		}
		if (s.equals("noop")) return resultat;
		else throw new RuntimeException("opération non valide : " + s);
	}
}

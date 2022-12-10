package Pb10;

import java.io.IOException;

import fichiers.Fichiers;

public class Pb10_2 
{
	static String repertoire = "F:\\AOC\\calendrier avent\\";
	static int X = 1, cycle = 0, position = 0;
	static String sprite = "###.....................................";
	static String row = "";
	static String spritePosition = "Sprite position: " + sprite + "\n";
	static int ligneCourante = 0;
	static String[] lignes = new String[7];

	
	static String ecran = "###" + ".".repeat(237);
	
	static int ligne = -1;
	static int x = 0;
	
	public static void main(String[] args) throws IOException 
	{
		String fichier = repertoire + "pb 10.txt";
		//test
//		fichier = repertoire + "pb 10 test.txt";
		String donnees = Fichiers.chargerContenuTexte(fichier);
		String[] instructions = donnees.split("\n");

//		for(String s : instructions)
//		{
//			if (s.startsWith("add"))
//			{
//				X += Integer.parseInt(s.substring(5));
//				System.out.println(X);
//			}
//		}
//		System.exit(0);
		
		afficherSpritePosition();

		for(String s : instructions)
		{
			traiter(s);
		}
		
		for(int i = 0; i < 7; i++)
		{
			System.out.println(lignes[i]);
		}
	}

	private static void afficherSpritePosition() 
	{
		System.out.println("Sprite position: " + ecran.substring(0,40) + "\n");
	}

	private static void traiter(String s) 
	{
		if (s.startsWith("addx")) traiterAddx(s);
		else traiterNoop(s);
//		afficherTexte(true, s);
//		if (s.equals("noop")) return;
//		X += Integer.parseInt(s.substring(5));		
//		tick++;
//		afficherTexte(false, s);
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
		
		String start = "Start cycle ";
		String espace = "";
		if (cycle < 10) espace = "  ";
		else if (cycle < 100) espace = " ";
		start += espace + cycle + ": begin executing " + s;
		System.out.println(start);
		
		String during = "During cycle" + espace + cycle + ": CRT draws pixel in position " + (cycle - 1);
		System.out.println(during);
		
		row += ecran.charAt((cycle-1)%40);
		String current = "Current CRT row: " + row;
		System.out.println(current);
		
		String endCycle = "End od cycle" + espace + cycle + ":" + " finish Executing " + s + "\n";
		System.out.println(endCycle);
		
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

		String start = "Start cycle ";
		String espace = "";
		if (cycle < 10) espace = "  ";
		else if (cycle < 100) espace = " ";
		start += espace + cycle + ": begin executing " + s;
		System.out.println(start);

		String during = "During cycle" + espace + cycle + ": CRT draws pixel in position " + (cycle - 1);
		System.out.println(during);
		
		row += ecran.charAt((cycle-1) % 40);
		String current = "Current CRT row: " + row;
		System.out.println(current);
		
		cycle++;
		if ((cycle-1)%40 == 0)
		{
			lignes[ligneCourante] = row;
			ligneCourante++;
			row = "";
		}

		System.out.println("");
		during = "During cycle" + espace + cycle + ": CRT draws pixel in position " + (cycle - 1);
		System.out.println(during);
		
		row += ecran.charAt((cycle-1) % 40);
		current = "Current CRT row: " + row;
		System.out.println(current);
		
		X += Integer.parseInt(s.substring(5));
		String endCycle = "End od cycle" + espace + cycle + ":" + " finish Executing " + s + " (Register X is now " + X + ")";
		System.out.println(endCycle);
		
		if (X == -1) ecran = "##" + ".".repeat(238);
		else if (X == 0) ecran = "###" + ".".repeat(237);
		else ecran = ".".repeat(X-1) + "###" + ".".repeat(238-X);
		afficherSpritePosition();
	}

	private static void afficherTexte(boolean b, String s) 
	{
//		System.out.println(tick);
		String during = "During cycle";
		while(during.length() < 15-(""+cycle).length()) during += " ";
		during += cycle + ":" + " CRT draws pixel in position " + position;
		position++;

		String sp = spritePosition.substring(17);
		if (position == 11)
		{
			System.out.println("TEST");
			System.out.println(sp);
			System.out.println(cycle % 40);
			System.out.println(sp.charAt(cycle % 40));
			System.exit(0);
		}
		
		//b vrai début du cycle
		if (b)
		{
			String start = "Start cycle";
			while(start.length() < 15 - (""+cycle).length()) start= start+ " ";
			start += cycle + ":" + " begin executing " + s;
			row += sp.charAt(cycle % 40);
			String current = "Current CRT row: " + row;
			
			String endCycle = "End od cycle";
			while(endCycle.length() < 15-(""+cycle).length()) endCycle += " ";
			endCycle += cycle + ":" + " finish Executing " + s;
			if (! s.equals("noop"))
			{
				endCycle += " (Register X is now " + X + ")";
			}
			
			
			System.out.println(start);
			System.out.println(during);
			System.out.println(current);
			System.out.println("");
			return;
		}
		
		//b faux, fin du cycle
		row += sp.charAt(cycle % 40);
		String current = "Current CRT row: " + row;
		
		String endCycle = "End od cycle";
		while(endCycle.length() < 15-(""+cycle).length()) endCycle = endCycle+ " ";
		endCycle= endCycle + cycle + ":" + " finish Executing " + s + " (Register X is now " + X + ")";
		
		spritePosition = "";
		while(spritePosition.length() < X-1) spritePosition += ".";
		spritePosition += "###";
		while(spritePosition.length() < 40) spritePosition += ".";
		spritePosition = "Sprite position: " + spritePosition + "\n";
				
		System.out.println(during);
		System.out.println(current);
		System.out.println(endCycle);
		System.out.println(spritePosition);
	}

	private static void arret() 
	{
		if (cycle > 21) System.exit(0);
		// TODO Auto-generated method stub
		
	}
	

}

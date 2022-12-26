package Pb11;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import fichiers.Fichiers;

public class Pb11_1 
{
	static String repertoire = "F:\\AOC\\calendrier avent\\";
	static Singe[] singes = new Singe[8];
	static int inquietude;
	static int round = 0;
	static int nbSinges = 8;
	static boolean test = false;

	public static void main(String[] args) throws IOException 
	{
		
//		for(int i = 1; i < 100; i++)
//			System.out.println(i + "\t" + (int) Math.sqrt(i));
//		System.exit(0);
		String fichier = repertoire + "pb 11.txt";
		//test
//		fichier = repertoire + "pb 11 test.txt";
//		nbSinges = 4; 
		
		String donnees = Fichiers.chargerContenuTexte(fichier);
		
//		for(int i = 0; i < 10; i++) System.out.println(i + "\t" + i / 3);
//		System.exit(0);

		String[] notes = donnees.split("\n\n");
		
		for(String s : notes)
		{
			int n = Integer.parseInt(s.substring(7,8));
			singes[n] = new Singe(s);
//			System.out.println(singes[n]);
		}
		
		while (round < 20)
		{ 
			round++;
//			if (round == 11) System.exit(0);
//			test = round > 8;
//			System.out.println("round " + round);
			if (test) 
			{
				System.out.println("round " + round + " position avant la ronde");
				afficherWL();
				System.out.println(singes[2]);
//				for(int i : singes[3].worryLevel) System.out.println(i);
			}
			for(int n = 0; n < nbSinges; n++)
			{
				traiter(singes[n]);
			}
			
//			if (test) afficherWL();
		}
		
		int max1 = 1, max2 = 0;
		for(int i = 0; i < nbSinges; i++)
		{
			int n = singes[i].getTotalInspection();
			System.out.println("Singe " + i + "\t" + n);
			if (n > max1)
			{
				max2 = max1;
				max1 = n;
			}
			else if (n > max2) max2 = n;
		}
		
		System.out.println(max1*max2);
	}

	private static void afficherWL() 
	{
		for(int i = 0; i < nbSinges; i++)
		{
			String s = "Monkey " + i + "\t" + "";
			for(Long t : singes[i].getWorryLevel())
			{
				s += t + ", ";
			}
			System.out.println(s);
		}
		
	}

	private static void traiter(Singe singe) 
	{
		if (test) System.out.println("Traitement monkey " + singe.numero);
		for(int i = 0; i < singe.getWorryLevel().size(); i++)
		{
			singe.incrementeTotalInspection();
			Long worryLevel = singe.getWorryLevel().get(i);
//			if (singe.getNumero() == 2) System.out.println("Monkey 2: " + worryLevel);
			int[] dest = singe.getDestinataires();
			String operation = singe.getOperation();
			char operateur = operation.charAt(0);
			int operande = -1;
			String s = operation.substring(2);
			if (operation.equals("* old")) operateur = '²';
			else operande = Integer.parseInt(s);
//			if (singe.getNumero() == 2) System.out.println("Monkey 2: " + operateur);
			switch (operateur)
			{
				case '²' :
					worryLevel = worryLevel * worryLevel;
//					if (singe.getNumero() == 2) System.out.println("Monkey 2: " + worryLevel);
					worryLevel = worryLevel / 3;
//					if (singe.getNumero() == 2) System.out.println("Monkey 2: " + worryLevel);
//					if (singe.getNumero() == 2) System.out.println("Monkey 2: " + singe.getDivisible());
//					if (singe.getNumero() == 2) System.out.println("Monkey 2: " + (worryLevel % singe.getDivisible()));
//					if (singe.getNumero() == 2) System.out.println("Monkey 2: " + (dest[0]));
					if (modulo(worryLevel, singe.getDivisible()) == 0) 
						singes[dest[0]].ajouteWorryLevel(worryLevel);
					else
						singes[dest[1]].ajouteWorryLevel(worryLevel);
					break;
					
				case '+' :
					worryLevel = worryLevel + operande;
					worryLevel = worryLevel / 3;
					if (worryLevel % singe.getDivisible() == 0) 
						singes[dest[0]].ajouteWorryLevel(worryLevel);
					else
						singes[dest[1]].ajouteWorryLevel(worryLevel);
					break;
					
				case '*' :
					worryLevel = worryLevel * operande;
					worryLevel = worryLevel / 3;
					if (worryLevel % singe.getDivisible() == 0) 
						singes[dest[0]].ajouteWorryLevel(worryLevel);
					else
						singes[dest[1]].ajouteWorryLevel(worryLevel);
					break;
				default :
					 throw new RuntimeException("Cas non, traité " + operation);
			}
//			if (singe.getNumero() == 2) System.out.println("Monkey 2: " + worryLevel);
			
//			if (test) System.out.println(singe.getNumero() + "\t" + worryLevel);
		}
		singe.videWorryLevel();
		if (test) afficherWL();
	}

	private static int modulo(Long worryLevel, int D) 
	{
		if (worryLevel < 1000000) return (int) (worryLevel % D);
		
		long x = (long) Math.sqrt(worryLevel);
		int r = modulo(x,D);
		long n = worryLevel - x*x;
		return modulo(r*r + n, D); 
	}

	static class Singe
	{
		int numero;
		List<Long> worryLevel = new ArrayList<Long>();
		int divisible;
		int[] destinataires = new int[2];
		int totalInspection = 0;

		String operation;
		
		public Singe(String s) 
		{
			String[] donnees = s.split("\n");
			//numero
			numero = Integer.parseInt(s.substring(7,8));
			
			//liste des items
			String liste = donnees[1].substring(18);
			String[] listeItems = liste.split(", ");
			for(String item : listeItems)
			{
				worryLevel.add((long) Integer.parseInt(item));
			}
			
			//operation
			operation = donnees[2].substring(23);
			
			//test
			divisible = Integer.parseInt(donnees[3].substring(21));
			
			//destinataires
			destinataires[0] = Integer.parseInt(donnees[4].substring(29));
			destinataires[1] = Integer.parseInt(donnees[5].substring(30));
		}

		public void incrementeTotalInspection() 
		{
			totalInspection++;
			
		}

		public void videWorryLevel() 
		{
			worryLevel.clear();
		}

		public void ajouteWorryLevel(long wl) 
		{
			worryLevel.add(wl);
		}

		public int[] getDestinataires() {
			return destinataires;
		}

		public void setDestinataires(int[] destinataires) {
			this.destinataires = destinataires;
		}

		public int getDivisible() {
			return divisible;
		}

		public void setDivisible(int divisible) {
			this.divisible = divisible;
		}
		public int getNumero() {
			return numero;
		}

		public void setNumero(int numero) {
			this.numero = numero;
		}

		public List<Long> getWorryLevel() {
			return worryLevel;
		}

		public void setWorryLevel(List<Long> worryLevel) {
			this.worryLevel = worryLevel;
		}

		public String getOperation() {
			return operation;
		}

		public void setOperation(String operation) {
			this.operation = operation;
		}
		
		public int getTotalInspection() {
			return totalInspection;
		}

		public void setTotalInspection(int totalInspection) {
			this.totalInspection = totalInspection;
		}

		public String toString()
		{
			String s = "Monkey " + numero + ":";
			
			s += "\n  Starting items:";
			String t = "";
			for(Long i : worryLevel) t += ", " + i;
			if (! t.isEmpty()) t= t.substring(1); 
			s += t; 
			s += "\n  Operation: new = old " + operation;
			
			s += "\n  Test: divisible by " + divisible;
			
			s += "\n    If true: throw to Monkey " + destinataires[0];
			s += "\n    If false: throw to Monkey " + destinataires[1];
			
			return s + "\n";
		}
	}
}

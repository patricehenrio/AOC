package Pb11;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import AOC.AOC;

/**
 * Les nombres utilisés pour la partie 2 dépassent les capacité de calcul des integer et même des long.
 * Mais on peut remplacer les valeurs calculées par leur modulo 13*17*19*23 (= 96577) pour 4 singes
 * Pour 8 singes : N = 2*3*5*7*11*13*17*19
 */

public class Pb11 extends AOC 
{
	static Singe[] singes = new Singe[8];
	static int nbRounds;
	static int nbSinges;
	static int round = 0;
	static boolean test = false;
	static long N = 2L*3L*5L*7L*11L*13L*17L*19L;
	
	public static void main(String[] args) throws IOException 
	{
		String donnees = chargerDonnees("pb 11.txt");
		
		//test
//		donnees = "Monkey 0:\n"
//				+ "  Starting items: 79, 98\n"
//				+ "  Operation: new = old * 19\n"
//				+ "  Test: divisible by 23\n"
//				+ "    If true: throw to monkey 2\n"
//				+ "    If false: throw to monkey 3\n"
//				+ "\n"
//				+ "Monkey 1:\n"
//				+ "  Starting items: 54, 65, 75, 74\n"
//				+ "  Operation: new = old + 6\n"
//				+ "  Test: divisible by 19\n"
//				+ "    If true: throw to monkey 2\n"
//				+ "    If false: throw to monkey 0\n"
//				+ "\n"
//				+ "Monkey 2:\n"
//				+ "  Starting items: 79, 60, 97\n"
//				+ "  Operation: new = old * old\n"
//				+ "  Test: divisible by 13\n"
//				+ "    If true: throw to monkey 1\n"
//				+ "    If false: throw to monkey 3\n"
//				+ "\n"
//				+ "Monkey 3:\n"
//				+ "  Starting items: 74\n"
//				+ "  Operation: new = old + 3\n"
//				+ "  Test: divisible by 17\n"
//				+ "    If true: throw to monkey 0\n"
//				+ "    If false: throw to monkey 1";
//		test = true;
//		nbSinges = 4; 
		
		nbSinges = 8;
		String[] notes = donnees.split("\n\n");
		
		//première partie
		for(String s : notes)
		{
			int n = Integer.parseInt(s.substring(7,8));
			singes[n] = new Singe(s);
		}
		System.out.println(partie_1());
		
		//Deuxième partie
		for(String s : notes)
		{
			int n = Integer.parseInt(s.substring(7,8));
			singes[n] = new Singe(s);
		}
		System.out.println(partie_2());
	}

	private static long partie_2() 
	{
		round = 0;
		nbRounds = 10000;
		while (round < nbRounds)
		{ 
			round++;
			traiter0();
			traiter1();
			traiter2();
			traiter3();
			traiter4();
			traiter5();
			traiter6();
			traiter7();
		}
		long max1 = 1, max2 = 0;
		for(int i = 0; i < nbSinges; i++)
		{
			long n = singes[i].getTotalInspection();
			if (n > max1)
			{
				max2 = max1;
				max1 = n;
			}
			else if (n > max2) max2 = n;
		}
		
		return max1*max2;
	}

	private static void traiter7() 
	{
		Singe singe = singes[7];
		if (test) return;
		else
		{
			/**
			 * Monkey 7:
				  Starting items: 73, 59, 82, 65
				  Operation: new = old + 6
				  Test: divisible by 2
				    If true: throw to monkey 4
				    If false: throw to monkey 3	 
			 */
			for(int i = 0; i < singe.getWorryLevel().size(); i++)
			{
				singe.incrementeTotalInspection();
				long wl = singe.getWorryLevel().get(i);
				wl = (wl+ 6) % N;
				
				if (wl % 2 == 0) singes[4].ajouteWorryLevel(wl);
				else singes[3].ajouteWorryLevel(wl);
			}
			singe.videWorryLevel();
		}
	}

	private static void traiter6() 
	{
		Singe singe = singes[6];
		if (test) return;
		else
		{
			/**
			 * Monkey 6:
				  Starting items: 87, 57, 63, 86, 87, 53
				  Operation: new = old * old
				  Test: divisible by 11
				    If true: throw to monkey 5
				    If false: throw to monkey 0
			 */
			for(int i = 0; i < singe.getWorryLevel().size(); i++)
			{
				singe.incrementeTotalInspection();
				long wl = singe.getWorryLevel().get(i);
				wl = (wl*wl) % N;
				
				if (wl % 11 == 0) singes[5].ajouteWorryLevel(wl);
				else singes[0].ajouteWorryLevel(wl);
			}
			singe.videWorryLevel();
		}
	}

	private static void traiter5() 
	{
		Singe singe = singes[5];
		if (test) return;
		else
		{
			/**
			 * Monkey 5:
				  Starting items: 88
				  Operation: new = old + 3
				  Test: divisible by 7
				    If true: throw to monkey 1
				    If false: throw to monkey 0
			 */
			for(int i = 0; i < singe.getWorryLevel().size(); i++)
			{
				singe.incrementeTotalInspection();
				long wl = singe.getWorryLevel().get(i);
				wl = (wl+ 3) % N;
				
				if (wl % 7 == 0) singes[1].ajouteWorryLevel(wl);
				else singes[0].ajouteWorryLevel(wl);
			}
			singe.videWorryLevel();
		}
	}

	private static void traiter4() 
	{
		Singe singe = singes[4];
		if (test) return;
		else
		{
			/**
			 * Monkey 4:
				  Starting items: 78, 97, 51, 85, 66, 63, 62
				  Operation: new = old * 17
				  Test: divisible by 5
				    If true: throw to monkey 6
				    If false: throw to monkey 3
			 */
			for(int i = 0; i < singe.getWorryLevel().size(); i++)
			{
				singe.incrementeTotalInspection();
				long wl = singe.getWorryLevel().get(i);
				wl = (wl*17) % N;
				
				if (wl % 5 == 0) singes[6].ajouteWorryLevel(wl);
				else singes[3].ajouteWorryLevel(wl);
			}
			singe.videWorryLevel();
		}
	}

	private static void traiter3() 
	{
		Singe singe = singes[3];
		if (test)
		{
			/**
			 * Monkey 3:
				  Starting items: 74
				  Operation: new = old + 3
				  Test: divisible by 17
				    If true: throw to monkey 0
				    If false: throw to monkey 1
			 */
			for(int i = 0; i < singe.getWorryLevel().size(); i++)
			{
				singe.incrementeTotalInspection();
				long wl = singe.getWorryLevel().get(i);
				wl = (wl+3) % N;
				
				if (wl % 17 == 0) singes[0].ajouteWorryLevel(wl);
				else singes[1].ajouteWorryLevel(wl);
			}
			singe.videWorryLevel();
			return;
		}
		else
		{
			/**
			 * Monkey 3:
				  Starting items: 97, 91
				  Operation: new = old + 1
				  Test: divisible by 17
				    If true: throw to monkey 6
				    If false: throw to monkey 5
			 */
			for(int i = 0; i < singe.getWorryLevel().size(); i++)
			{
				singe.incrementeTotalInspection();
				long wl = singe.getWorryLevel().get(i);
				wl = (wl+1) % N;
				
				if (wl % 17 == 0) singes[6].ajouteWorryLevel(wl);
				else singes[5].ajouteWorryLevel(wl);
			}
			singe.videWorryLevel();
		}
	}

	private static void traiter2() 
	{
		Singe singe = singes[2];
		if (test)
		{
			/**
			 * Monkey 2:
				  Starting items: 79, 60, 97
				  Operation: new = old * old
				  Test: divisible by 13
				    If true: throw to monkey 1
				    If false: throw to monkey 3
			 */
			for(int i = 0; i < singe.getWorryLevel().size(); i++)
			{
				singe.incrementeTotalInspection();
				long wl = singe.getWorryLevel().get(i);
				wl = (wl*wl) % N;
				
				if (wl % 13 == 0) singes[1].ajouteWorryLevel(wl);
				else singes[3].ajouteWorryLevel(wl);
			}
			singe.videWorryLevel();
			return;
		}
		else
		{
			/**
			 * Monkey 2:
				  Starting items: 77, 73, 86, 72, 87
				  Operation: new = old + 8
				  Test: divisible by 19
				    If true: throw to monkey 4
				    If false: throw to monkey 7
			 */
			for(int i = 0; i < singe.getWorryLevel().size(); i++)
			{
				singe.incrementeTotalInspection();
				long wl = singe.getWorryLevel().get(i);
				wl = (wl + 8) % N;
				
				if (wl % 19 == 0) singes[4].ajouteWorryLevel(wl);
				else singes[7].ajouteWorryLevel(wl);
			}
			singe.videWorryLevel();
		}
	}

	private static void traiter1() 
	{
		Singe singe = singes[1];
		if (test)
		{
			/**
			 * Monkey 1:
				  Starting items: 54, 65, 75, 74
				  Operation: new = old + 6
				  Test: divisible by 19
				    If true: throw to monkey 2
				    If false: throw to monkey 0			 
			 */
			for(int i = 0; i < singe.getWorryLevel().size(); i++)
			{
				singe.incrementeTotalInspection();
				long wl = singe.getWorryLevel().get(i);
				wl = (wl+6) % N;
				
				if (wl % 19 == 0) singes[2].ajouteWorryLevel(wl);
				else singes[0].ajouteWorryLevel(wl);
			}
			singe.videWorryLevel();
			return;
		}
		else
		{
			/**
			 * Monkey 1:
				  Starting items: 71, 55, 82
				  Operation: new = old + 2
				  Test: divisible by 13
				    If true: throw to monkey 7
				    If false: throw to monkey 2
			 */
			for(int i = 0; i < singe.getWorryLevel().size(); i++)
			{
				singe.incrementeTotalInspection();
				long wl = singe.getWorryLevel().get(i);
				wl = (wl+2) % N;
				
				if (wl % 13 == 0) singes[7].ajouteWorryLevel(wl);
				else singes[2].ajouteWorryLevel(wl);
			}
			singe.videWorryLevel();
		}
	}

	private static void traiter0() 
	{	
		Singe singe = singes[0];
		if (test)
		{
			/**
			 * Monkey 0:
  				Starting items: 79, 98
  				Operation: new = old * 19
  				Test: divisible by 23
    				If true: throw to monkey 2
    				If false: throw to monkey 3
			 */
			for(int i = 0; i < singe.getWorryLevel().size(); i++)
			{
				singe.incrementeTotalInspection();
				long wl = singe.getWorryLevel().get(i);
				wl = (wl*19) % N;
				
				if (wl % 23 == 0) singes[2].ajouteWorryLevel(wl);
				else singes[3].ajouteWorryLevel(wl);
			}
			singe.videWorryLevel();
		}
		else
		{
			/**
			 * Monkey 0:
				  Starting items: 54, 98, 50, 94, 69, 62, 53, 85
				  Operation: new = old * 13
				  Test: divisible by 3
				    If true: throw to monkey 2
				    If false: throw to monkey 1
			 */
			for(int i = 0; i < singe.getWorryLevel().size(); i++)
			{
				singe.incrementeTotalInspection();
				long wl = singe.getWorryLevel().get(i);
				wl = (wl*13) % N;
				
				if (wl % 3 == 0) singes[2].ajouteWorryLevel(wl);
				else singes[1].ajouteWorryLevel(wl);
			}
			singe.videWorryLevel();
			
		}
	}

	private static long partie_1() 
	{
		nbRounds = 20;

		while (round < nbRounds)
		{
			round++;
			for(int n = 0; n < nbSinges; n++)
			{
				traiter(singes[n]);
			}
		}
		long max1 = 1;
		long max2 = 0;
		for(int i = 0; i < nbSinges; i++)
		{
			long n = singes[i].getTotalInspection();
			if (n > max1)
			{
				max2 = max1;
				max1 = n;
			}
			else if (n > max2) max2 = n;
		}
		
		return max1*max2;
	}

	private static void traiter(Singe singe) 
	{
		for(int i = 0; i < singe.getWorryLevel().size(); i++)
		{
			singe.incrementeTotalInspection();
			Long worryLevel = singe.getWorryLevel().get(i);
			int[] dest = singe.getDestinataires();
			String operation = singe.getOperation();
			char operateur = operation.charAt(0);
			int operande = -1;
			String s = operation.substring(2);
			if (operation.equals("* old")) operateur = '²';
			else operande = Integer.parseInt(s);
			switch (operateur)
			{
				case '²' :
					worryLevel = worryLevel * worryLevel;
					worryLevel = worryLevel / 3;
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
		}
		singe.videWorryLevel();	
	}

	private static int modulo(Long worryLevel, long l) 
	{
		if (worryLevel < 1000000) return (int) (worryLevel % l);
		
		long x = (long) Math.sqrt(worryLevel);
		int r = modulo(x,l);
		long n = worryLevel - x*x;
		return modulo(r*r + n, l); 
	}

	static class Singe
	{
		int numero;
		List<Long> worryLevel = new ArrayList<Long>();
		long divisible;
		int[] destinataires = new int[2];
		long totalInspection = 0;
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
				worryLevel.add(Long.parseLong(item));
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

		public long getDivisible() {
			return divisible;
		}

		public void setDivisible(int divisible) {
			this.divisible = divisible;
		}
		public int getNumero() 
		{
			return numero;
		}

		public void setNumero(int numero) 
		{
			this.numero = numero;
		}

		public List<Long> getWorryLevel() 
		{
			return worryLevel;
		}

		public void setWorryLevel(List<Long> worryLevel) 
		{
			this.worryLevel = worryLevel;
		}

		public String getOperation() 
		{
			return operation;
		}

		public void setOperation(String operation) 
		{
			this.operation = operation;
		}
		
		public long getTotalInspection() 
		{
			return totalInspection;
		}

		public void setTotalInspection(long totalInspection) 
		{
			this.totalInspection = totalInspection;
		}

		public String toString()
		{
			String s = "Monkey " + numero + ":";
			
			s += "\n  Starting items:";
			String t = "";
			for(long i : worryLevel) t += ", " + i;
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

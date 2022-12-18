package Pb11;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import fichiers.Fichiers;

/**
 * Les nombres utilisés dépassent les capacité de calcul des integre et même des long.
 * Mais on peut remplacer les valeurs calculées par leur modulo 13*17*19*23 (= 96577) pour 4 singes
 * Pour 8 singes : N = 2*3*5*7*11*13*17*19
 * 
 * 
 * 
 * @author Patrice
 *
 */
public class Pb11_2 
{
	static String repertoire = "F:\\AOC\\calendrier avent\\";
	static Singe[] singes = new Singe[8];
	static int inquietude;
	static int round = 0;
	static int nbSinges = 8;
	static int nbRounds = 10000;
	static long N = 2L*3L*5L*7L*11L*13L*17L*19L;
	static boolean test = false;
	

	public static void main(String[] args) throws IOException 
	{
		String fichier = repertoire + "pb 11.txt";
//		//test
//		fichier = repertoire + "pb 11 test.txt";
//		nbSinges = 4; 
//		N = 13*17*19*23;
//		test = true;
		
		String donnees = Fichiers.chargerContenuTexte(fichier);
		String[] notes = donnees.split("\n\n");
		
		for(String s : notes)
		{
			int n = Integer.parseInt(s.substring(7,8));
			singes[n] = new Singe(s);
		}
		
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
//			afficherSinges();
			if (round  == 1) afficherSinges
			();
			if (round == 20) afficherSinges();
			if (round % 1000 == 0) afficherSinges();
			
			
//			for(int n = 0; n < nbSinges; n++)
//			{
//				traiter(singes[n]);
//			}
//			afficherSinges();
//			if (round % 1000 == 0) afficherSinges();
		}
		
		long max1 = 1, max2 = 0;
		for(int i = 0; i < nbSinges; i++)
		{
			long n = singes[i].getTotalInspection();
//			System.out.println("Singe " + i + "\t" + n);
			if (n > max1)
			{
				max2 = max1;
				max1 = n;
			}
			else if (n > max2) max2 = n;
		}
		
		System.out.println(max1 + "\n" + max2 + "\n" + (max1*max2) + "\n" + multiplie("" + max1, "" + max2));
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
//			System.out.println("traiter3)");
//			afficherWorryLevel();
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
//			System.out.println("traiter2)");
//			afficherWorryLevel();
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
//			System.out.println("traiter1)");
//			afficherWorryLevel();
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
//			System.out.println("traiter0)");
//			afficherWorryLevel();
//			return;
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

	private static void afficherWorryLevel() 
	{
		System.out.println("After round " + round);
		for(int i = 0; i < nbSinges; i++)
		{
			List<Long> wl = singes[i].getWorryLevel();
			String s = "";
			for(long n : wl) s += n + ", ";
			System.out.println("Singe " + i + ": " + s);
		}
		System.out.println("");
		
	}

	private static void afficherSinges() 
	{
		System.out.println("== After round " + round + " ==");
		for(int i = 0; i < nbSinges; i++)
		{
			long n = singes[i].getTotalInspection();
			System.out.println("Singe " + i + "\t" + n);
		}
		System.out.println("");
	}

	private static void traiter(Singe singe) 
	{
		for(int i = 0; i < singe.getWorryLevel().size(); i++)
		{
			singe.incrementeTotalInspection();
			long wl = singe.getWorryLevel().get(i);
			int[] dest = singe.getDestinataires();
			String operation = singe.getOperation();
			long modulo = -1;
			switch(operation)
			{
				case "* 13" :
					wl = 13L * wl;
					wl = wl % singe.getDivisible();
					break;
				case "+ 2" :
					wl = wl + 2L;
					wl = wl % singe.getDivisible();
					break;
				case "+ 8" :
					wl = wl + 8L;
					wl = wl % singe.getDivisible();
					break;
				case "+ 1" :
					wl = wl + 1L;
					wl = wl % singe.getDivisible();
					break;
				case "* 17" : 
					wl = wl + 17L;
					wl = wl % singe.getDivisible();
					break;
				case "+ 3" :
					wl = wl + 3L;
					wl = wl % singe.getDivisible();
					break;
				case "* old" : //le cas du carré, le résultat est modulo 11
					wl = wl * wl;
					modulo = wl % 11L;
					modulo = modulo*modulo;
					break;
				case "+ 6" :
					wl = wl + 6L;
					wl = wl % singe.getDivisible();
					break;
				case "* 19" :
					wl = 19L * wl;
					wl = wl % singe.getDivisible();
					break;
				default :
					 throw new RuntimeException("Cas non, traité " + operation);
			}
			wl = wl % N;
			if ((modulo) == 0) 
				singes[dest[0]].ajouteWorryLevel(wl);
			else
				singes[dest[1]].ajouteWorryLevel(wl);
			
		}
		singe.videWorryLevel();
	}

	/**
	 * n compris entre 0 et 9
	 * @param s
	 * @param n
	 * @return
	 */
	
	private static String ajoute(String s, int n)
	{
		if (n == 0) return s;
		int c = (s.charAt(s.length() - 1) - 48);
		c = c + n;
		if (c > 9)
		{
			s = s.substring(0, s.length()-1);
			return ajoute(s,1) + (c % 10);
		}
		
		return s.substring(0, s.length()-1) + c;
	}
	
	/**
	 * a et b sont des nombres
	 * @param a
	 * @param b
	 */
	private static String ajoute(String a, String b)
	{
		if (b.length() > a.length()) return ajoute(b,a);
		
		if (b.length() == 1)
		{
			int n = b.charAt(0)-48;
			return ajoute(a,n);
		}
		char[] chiffresA = new StringBuilder(a).reverse().toString().toCharArray();
		char[] chiffresB = new StringBuilder(b).reverse().toString().toCharArray();
		List<Character> listeChiffres = new ArrayList<Character>();
		int retenue = 0;
		for(int i = 0; i < a.length(); i++)
		{
			int ca = chiffresA[i]-48;
			int cb = (i < b.length() ? chiffresB[i]-48 : 0);
			int n = ca + cb + retenue;
			if (n < 10)
			{
				listeChiffres.add(Character.forDigit(n, 10));
				retenue = 0;
			}
			else
			{
				listeChiffres.add(Character.forDigit(n - 10, 10));
				retenue = 1;
			}
		}
		
		String resultat = "";
		for(int i = 0; i < listeChiffres.size(); i++)
			resultat = listeChiffres.get(i) + resultat;
		if (retenue == 0) return resultat;
		return "1" + resultat;
		
	}
	
	/**
	 * n entre 0 et 9
	 * @param s
	 * @param n
	 * @return
	 */
	private static String multiplie(String s, int n)
	{
		if (s.length() == 1) return "" + (Integer.parseInt(s) * n);
		switch(n)
		{
			case 0 :
				return "0";
			case 1 :
				return s;
			case 2 :
				return ajoute(s,s);
			case 3 : 
				return ajoute(s, ajoute(s,s));
			case 4 :
			    return multiplie(multiplie(s,2),2);
			case 5 : 
				return ajoute(multiplie(s,2),multiplie(s,3));
			case 6 :
				return multiplie(multiplie(s,2),3);
			case 7 : 
				return ajoute(multiplie(s,6), s);
			case 8 :
				return multiplie(multiplie(s,4),2);
			case 9 :
				return ajoute(multiplie(s,8), s);
			default :
				throw new RuntimeException("Cas non prévu : " + s + " x " + n);
		}
	}

	private static String multiplie(String a, String b)
	{
		if (b.length() > a.length()) return multiplie(b,a);
		
		if (b.length() == 1)
		{
			int n = b.charAt(0)-48;
			return multiplie(a,n);
		}
		char[] chiffresB = new StringBuilder(b).reverse().toString().toCharArray();
		String[] lignes = new String[b.length()];
		for(int i = 0; i < b.length(); i++)
		{
			int cb = chiffresB[i]-48;
			lignes[i] = multiplie(a, cb);
		}
		
		//inverser l'ordre des lignes
		String ajout = "";
		
		String resultat = lignes[0];
		
		for(int i = 1; i < b.length(); i++)
		{
			ajout += "0";
			resultat = ajoute(resultat, lignes[i] + ajout);
		}
		
		return resultat;
		
	}
	
	private static String carre(String s) 
	{
		return multiplie(s,s);
	}

//	/**
//	 * N peut être un nombre très grand
//	 * @param string
//	 * @param l
//	 * @return
//	 */
//	private static int modulo(String string, long l) 
//	{
//		if (string < 10000) return (int) (string % l);
//		else return modulo(""  + string, l);
//		String s = ;
//		int n = 10000 % D;
//		
//		//wl = (tous les chiffre sde wl sauf les 5 derniers) * 10000 + les 5 derniers;
//		; 
//		
//		
////		String s = "" + worryLevel;
//		
//		long x = (long) Math.sqrt(worryLevel);
//		int r = modulo(x,D);
//		long n = worryLevel - x*x;
//		return modulo(r*r + n, D); 
//	}

//	private static int modulo(String s, int div) 
//	{
//		String nombre = s;
//		int dixModuloDiv = 10 % div, resultat = 0;
//
//		for (char c : nombre.toCharArray())
//		{
//			resultat = (dixModuloDiv * resultat + c - 48) % div;
//		}
//		return resultat;
		
//		if (s.length() <= 5) return Integer.parseInt(s) % d;
//		
//		int n = s.length()/2;
//		
//		
//		System.out.println("grand nombre : " + s);
//		System.out.println("10000 : " + 10000 % d);
//		System.out.println(s.substring(0, s.length() - 5));
//		System.out.println("5 derniers chiffres : " + s.substring(s.length() - 5));
//		
//		return (10000 % d) * modulo(s.substring(0, s.length() - 5), d) + (Integer.parseInt(s.substring(s.length()-5)) % d);
//	}

	private static void test() 
	{
		System.out.println(N);
		System.out.println(2*3*5*7*11*13*17*19);
		System.out.println(2*3*17*19);
		System.out.println(5*7*11*13);
		System.out.println(multiplie("1938", "5005"));
		
//		long a = 52166;
//		long b = 52013;
//		System.out.println(a*b);
//		String s = "1712354689741254785124595879132004667965316544487499999";
//		String s = "1243";
//		System.out.println(s);
//		System.out.println(multiplie(s, "19"));
//		System.out.println(19*1243);
//		System.out.println(ajoute("9944", "1243"));
//		while (s.length() > 5)
//		{
//			
//			int a = Integer.parseInt(s.substring(0,5));
//			
//			int n = a % 17;
//			s = s.substring(5);
//			if (n != 0) s = n + s;
//			System.out.println(s);
//		}
//		
//		System.out.println(Integer.parseInt(s) % 17);
//		long n = modulo("1712354689741254785124595879132004667965316544487454341", 17);
//		
//		
//		System.out.println("grand nombre : " + n);
//		while(n > 17)
//		{
//			n = modulo(""+n, 17);
//			System.out.println("boucle : " + n);
//		}
		System.exit(0);
	}
	
	static class Singe
	{
		int numero;
		List<Long> worryLevel = new ArrayList<Long>();
//		List<Long> worryLevel = new ArrayList<Long>();
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

		public void retire(long wl) {
			// TODO Auto-generated method stub
			
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
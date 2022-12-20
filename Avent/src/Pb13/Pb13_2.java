package Pb13;

import java.io.IOException;
import java.util.TreeSet;

import fichiers.Fichiers;

public class Pb13_2 
{
	public static void main(String[] args) throws IOException 
	{
		//les données
		String donnees = Fichiers.chargerContenuTexte("F:\\AOC\\calendrier avent\\pb 13.txt");
		
		TreeSet<Paquet> lesPaquets = new TreeSet<Paquet>();
		Paquet p1 = new Paquet("[[2]]");
		Paquet p2 = new Paquet("[[6]]");
		lesPaquets.add(p2);
		lesPaquets.add(p1);
		for(String s : donnees.split("\n"))
		{
			if (s.isEmpty()) continue;
			lesPaquets.add(new Paquet(s));
		}
		int n = 0;
		int solution = 1;
		for(Paquet p : lesPaquets) 
		{
			n++;
			if (p.equals(p1))
			{
				solution = n;
				continue;
			}
			if (p.equals(p2))
			{
				solution = solution * n;
				break;
			}
		}
		System.out.println(solution);
	}
	static class Paquet implements Comparable<Paquet>
	{
		String debut = "", suite = "";
		String paquet;
		
		public Paquet(String s) 
		{
			paquet = s;
			if (s.equals("[]")) return;
			if (s.isEmpty()) return;
			if (s.startsWith("[")) s = s.substring(1, s.length()-1);
			
			int i = 0;
			char c = s.charAt(0);
			switch (c)
			{
				case '[' :
					int nbOuvrant = 1;
					debut += c;
					i++;
					while(i < s.length())
					{
						c = s.charAt(i);
						debut += c;
						switch (c)
						{
							case ']' :
								nbOuvrant--;
								i++;
								break;
							case '[' :
								nbOuvrant++;
							default : 
								i++;
						}
						if (nbOuvrant == 0) break;
					}
					if (i < s.length()-1) suite = s.substring(i+1);
					
				default : //les autres caractères 
					while (c >= '0' && c <= '9')
					{
						debut += c;
						i++;
						if (i < s.length())  c = s.charAt(i);
						else break;
					}
					if (i < s.length()-1) 
					{
						if (s.charAt(i+1) == ',') i++;
						suite = "[" + s.substring(i+1) + "]";
					}
					else suite = "";
			}
		}

		public String toString()
		{
			return((debut.isEmpty() ? null : debut) + " ---> " + (suite.isEmpty() ? null : suite));
		}

		@Override
		public int compareTo(Paquet paquet) 
		{
//			if (this.equals(P)) System.out.println(paquet);
			
			
			if (this.equals(paquet)) return 0;
			
			String gauche = debut;
			String droite = paquet.debut;
			
			if (gauche.equals(droite))
			{
				if (suite.isEmpty()) return -1;
				if (paquet.suite.isEmpty()) return 1;
				return new Paquet(suite).compareTo(new Paquet(paquet.suite));
			}
			
			if (gauche.isEmpty()) return -1;
			if (droite.isEmpty()) return 1;
			
			char g = gauche.charAt(0);
			char d = droite.charAt(0);
			
			if (g == '[' && d == '[')
			{
				int n = new Paquet(gauche).compareTo(new Paquet(droite));
				if (n < 0) return -1;
				if (n > 0) return 1;
				return new Paquet(suite).compareTo(new Paquet(paquet.suite));
			}
			
			if (Character.isDigit(g) && Character.isDigit(d)) 
			{
				int n = Integer.parseInt(gauche)-Integer.parseInt(droite);
				if (n < 0) return -1;
				if (n > 0) return 1;
				
				return new Paquet(suite).compareTo(new Paquet(paquet.suite));
			}
			
			if (g == '[' && d != '[')
			{
				int n = new Paquet(gauche).compareTo(new Paquet("[" + droite + "]"));
				if (n < 0) return -1;
				if (n > 0) return 1;
				return new Paquet(suite).compareTo(new Paquet(paquet.suite));
			}
			
			if (g != '[' && d == '[')
			{
				int n = new Paquet("[" + gauche + "]").compareTo(new Paquet(droite));
				if (n < 0) return -1;
				if (n > 0) return 1;
				return new Paquet(suite).compareTo(new Paquet(paquet.suite));
			}
			//un cas non prévu, cela ne devrait pas arriver
			throw new RuntimeException("\n\t" + gauche + "\n\t" + droite);
		}

		private boolean equals(Paquet autre)
		{
			return debut.equals(autre.debut) && suite.equals(autre.suite);
		}
	}
}

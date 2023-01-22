package Pb18;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import AOC.AOC;

public class Pb18 extends AOC 
{
	public enum Etat 
	{
		PLEIN, VIDE, EXT;
	}
	
	static List<Cube> cubes = new ArrayList<Cube>();	
	static List<Cube> cubesExterieurs = new ArrayList<Cube>();
	static int maxX = 0, maxY = 0, maxZ = 0;
	static int minX = Integer.MAX_VALUE, minY = Integer.MAX_VALUE, minZ = Integer.MAX_VALUE;
	static int nbFacesVisibles = 0;
	
	
	public static void main(String[] args) throws IOException 
	{
		//les données
		String donnees = chargerDonnees("pb 18.txt");
		
		/**
		 * juste pour le test
			donnees = "2,2,2\n"
					+ "1,2,2\n"
					+ "3,2,2\n"
					+ "2,1,2\n"
					+ "2,3,2\n"
					+ "2,2,1\n"
					+ "2,2,3\n"
					+ "2,2,4\n"
					+ "2,2,6\n"
					+ "1,2,5\n"
					+ "3,2,5\n"
					+ "2,1,5\n"
					+ "2,3,5";
		**/
	
		String[] s = donnees.split("\n");
		donnees = null;
		
		for(int i = 0; i < s.length; i++)
		{
			Cube c = new Cube(s[i]);
			cubes.add(c);
			if (c.x < minX) minX = c.x;
			else if (c.x > maxX) maxX = c.x;
			if (c.y < minY) minY = c.y;
			else if (c.y > maxY) maxY = c.y;
			if (c.z < minZ) minZ = c.z;
			else if (c.z > maxZ) maxZ = c.z;
		}
		
		//les durées des traitements : parties 1 et 2
        long debut = System.currentTimeMillis();
		//première partie
        nbFacesVisibles = partie_1();
		System.out.println(nbFacesVisibles);
        long duree = System.currentTimeMillis() - debut;
        System.out.println(duree + "ms");
        debut = System.currentTimeMillis();
        System.out.println("*****************************");
		//Deuxième partie
		System.out.println(partie_2());
		duree = (System.currentTimeMillis() - debut);
		System.out.println(duree + "ms");
	}
	
	private static int partie_2() 
	{
		//une goutelette est en trois dimensions et est composé de cubes pleins (PLEIN) et de trous
		//les trous peuvent être en contact avec l'extérieur (EXT) ou pas (VIDE)
		//tout cela est stocké dans un dictionnaire avec pour clé des coordonnées en dimension 3 et
		//pour valeur un état (PLEIN, VIDE, EXT)
		HashMap<Cube, Etat> goutelette = new HashMap<Cube, Etat>();

		//on commence par placer les cubes pleins
		for(int x = minX; x <= maxX; x++)
		{
			for(int y = minY; y <= maxY; y++)
			{
				for(int z = minZ; z <= maxZ; z++)
				{
					Cube c = new Cube(x,y,z);
					if (cubes.contains(c)) goutelette.put(c, Etat.PLEIN);
				}
			}
		}

		//on place les premiers cubes non pleins en contact avec l'extérieur
		for(int x = minX; x <= maxX; x++)
		{
			for(int y = minY; y <= maxY; y++)
			{
				Cube c = new Cube(x,y,minZ);
				if(goutelette.get(c) == null) goutelette.put(c, Etat.EXT);
				c = new Cube(x,y,maxZ);
				if(goutelette.get(c) == null) goutelette.put(c, Etat.EXT);
			}
			for(int z = minZ; z <= maxZ; z++)
			{
				Cube c = new Cube(x,minY,z);
				if(goutelette.get(c) == null) goutelette.put(c, Etat.EXT);
				c = new Cube(x,maxY,z);
				if(goutelette.get(c) == null) goutelette.put(c, Etat.EXT);
			}
		}
		for(int y = minY; y <= maxY; y++)
		{
			for(int z = minZ; z < maxZ; z++)
			{
				Cube c = new Cube(minX,y,z);
				if(goutelette.get(c) == null) goutelette.put(c, Etat.EXT);
				c = new Cube(maxX,y,z);
				if(goutelette.get(c) == null) goutelette.put(c, Etat.EXT);
			}
		}
		
		//on complète les cubes extérieurs : un cube est en contact avec l'extérieur s'il
		//est en contact avec un cube qui est lui-même en contact avec extérieur
		boolean ajout = true;
		while(ajout)
		{
			ajout = false;
			for(int x = minX + 1; x < maxX; x++)
			{
				for(int y = minY + 1; y < maxY; y++)
				{
					for(int z = minZ + 1; z < maxZ; z++)
					{
						Cube c = new Cube(x,y,z);	
						if (goutelette.get(c) == null)
						{
							if(isExterieur(goutelette, c))
							{
								goutelette.put(c, Etat.EXT);
								ajout = true;
							}
						}
					}
				}
			}
		}
		
		//Il ne reste plus qu'à placer les cubes vides (ceux qui ne sont ni pleins, ni extérieurs
		for(int x = minX; x <= maxX; x++)
		{
			for(int y = minY; y <= maxY; y++)
			{
				for(int z = minZ; z <= maxZ; z++)
				{
					Cube c = new Cube(x,y,z);
					if (goutelette.get(c) == null) 
					{
						goutelette.put(c,Etat.VIDE);
					}
				}
			}
		}

		//Pour savoir si une face doit être comptée ou non il y a deux méthodes
		//Compter toutes les faces (solution du 1) et retirer celles qui sont en
		//contact avec une cavité (Etat.VIDE)
		for(int x = minX; x <= maxX; x++)
		{
			for(int y = minY; y <= maxY; y++)
			{
				for(int z = minZ; z <= maxZ; z++)
				{
					Cube c = new Cube(x,y,z);
					if (goutelette.get(c) == Etat.PLEIN)
					{
						if (goutelette.get(c.getXmoins1()) == Etat.VIDE) nbFacesVisibles--;
						if (goutelette.get(c.getXplus1()) == Etat.VIDE) nbFacesVisibles--;
						if (goutelette.get(c.getYmoins1())== Etat.VIDE) nbFacesVisibles--;
						if (goutelette.get(c.getYplus1())== Etat.VIDE) nbFacesVisibles--;
						if (goutelette.get(c.getZplus1())== Etat.VIDE) nbFacesVisibles--;
						if (goutelette.get(c.getZmoins1())== Etat.VIDE) nbFacesVisibles--;
					}
				}
			}
		}

		//autre méthode
		//Pour chaque cube compter les faces en contact avec l'extérieur
		//C'était ma première idée mais je ne trouvais pas la bonne réponse
		//En fait je faisais l'erreur de traiter dans un  seul if les faces correspondant au minimum 
		//et au maximum des coordonnées. Mainetnant j'ai bien une seule réponse.
		int nbFacesExt = 0;
		for(int x = minX; x <= maxX; x++)
		{
			for(int y = minY; y <= maxY; y++)
			{
				for(int z = minZ; z <= maxZ; z++)
				{
					Cube c = new Cube(x,y,z);
					if (goutelette.get(c) == Etat.PLEIN)
					{
						if (x == minX) nbFacesExt++;
						else if(goutelette.get(c.getXmoins1()) == Etat.EXT) nbFacesExt++;
						if (x == maxX) nbFacesExt++;
						else if(goutelette.get(c.getXplus1()) == Etat.EXT) nbFacesExt++;
						
						if (y == minY) nbFacesExt++;
						else if(goutelette.get(c.getYmoins1()) == Etat.EXT) nbFacesExt++;
						if (y == maxY) nbFacesExt++;
						else if(goutelette.get(c.getYplus1()) == Etat.EXT) nbFacesExt++;

						if (z == minZ) nbFacesExt++;
						else if(goutelette.get(c.getZmoins1()) == Etat.EXT) nbFacesExt++;
						if (z == maxZ) nbFacesExt++;
						else if(goutelette.get(c.getZplus1()) == Etat.EXT) nbFacesExt++;
					}
				}
			}
		}

		System.out.println(nbFacesExt);
		return nbFacesVisibles;
		
		
	}

	private static boolean isExterieur(HashMap<Cube, Etat> goutelette, Cube c) 
	{
		return (goutelette.get(c.getXmoins1()) == Etat.EXT 
				|| goutelette.get(c.getXplus1()) == Etat.EXT
				|| goutelette.get(c.getYmoins1()) == Etat.EXT
				|| goutelette.get(c.getYplus1()) == Etat.EXT
				|| goutelette.get(c.getZplus1()) == Etat.EXT
				|| goutelette.get(c.getZmoins1()) == Etat.EXT);
	}

	private static int partie_1() 
	{
		int nbFacesVisibles = 6*cubes.size();
		for(int i = 0; i < cubes.size()-1; i++)
		{
			Cube c0 = cubes.get(i);
			for(int j = i+1; j < cubes.size(); j++)
			{
				Cube c1 = cubes.get(j);
				if (c1.contigu(c0)) nbFacesVisibles -= 2;
			}
		}
		return nbFacesVisibles;
	}

	static public class Cube implements Comparable<Cube>
	{
		int x, y, z;
		public Cube(String s)
		{
			String[] coordonnees = s.split(",");
			x = Integer.parseInt(coordonnees[0]);
			y = Integer.parseInt(coordonnees[1]);
			z = Integer.parseInt(coordonnees[2]);
		}
		
		public Cube(int x, int y, int z) 
		{
			this.x = x;
			this.y = y;
			this.z = z;
		}

		public boolean contigu(Cube c) 
		{
			return distance(c) == 1;
		}

		private int distance(Cube c) 
		{
			return Math.abs(x - c.x) + Math.abs(y - c.y) + Math.abs(z - c.z);
		}

		@Override
		public int hashCode()
		{
			return toString().hashCode();
		}
		
		@Override
		public boolean equals(Object o)
		{
			if (o == null) return false;
			if (!(o instanceof Cube))
			{
				throw new RuntimeException("La comparaison ne peut se faire entre un cube et un " + o);
			}
			Cube c = (Cube)o;
			return (x == c.x && y == c.y && z == c.z);
		}

		@Override
		public int compareTo(Cube c) 
		{
			if (c == null) return 1;
			if (x != c.x) return x-c.x;
			if (y != c.y) return y - c.y;
			return z-c.z;
		}
		
		Cube getXmoins1() 
		{
			return new Cube(x - 1, y, z);
		}

		Cube getXplus1() 
		{
			return new Cube(x + 1, y, z);
		}

		Cube getYmoins1() 
		{
			return new Cube(x, y - 1, z);
		}

		Cube getYplus1() 
		{
			return new Cube(x, y + 1, z);
		}

		Cube getZplus1() 
		{
			return new Cube(x, y, z + 1);
		}

		Cube getZmoins1() 
		{
			return new Cube(x, y, z - 1);
		}

		public String toString()
		{
			return "("+x+","+y+","+z+")";
		}
	}
}

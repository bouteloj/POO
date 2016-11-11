package src;

import io.LecteurDonnees;

import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.zip.DataFormatException;

public class DonneesSimulation {
	public Carte map;
<<<<<<< HEAD
	private LinkedList<Robot> robots;
	private LinkedList<Incendie> incendies;
=======
	public LinkedList<Robot> robots;
	public LinkedList<Incendie> incendies;
>>>>>>> d91f59a50a5580851e350ab31f6ed0047d2b1ba1
	
	public DonneesSimulation(String Path){
		map=new Carte();
		robots = new LinkedList<Robot>();
		incendies = new LinkedList<Incendie>();
		
		try {
			LecteurDonnees.lire(Path, map, robots, incendies);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DataFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}

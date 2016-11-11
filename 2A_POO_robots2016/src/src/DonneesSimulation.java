package src;

import io.LecteurDonnees;

import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.zip.DataFormatException;

public class DonneesSimulation {
	public Carte map;

	public LinkedList<Robot> robots;
	public LinkedList<Incendie> incendies;
	
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

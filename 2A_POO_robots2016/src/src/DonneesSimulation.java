package src;

import io.LecteurDonnees;

import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.zip.DataFormatException;

import robots.Robot;

public class DonneesSimulation {
	public Carte map;

	public LinkedList<Robot> robots;
	public LinkedList<Incendie> incendies;
	
	public DonneesSimulation(String Path){
		map=new Carte();
		map.ListeEau=new LinkedList<Case>();
		map.ListeVoisinEau=new LinkedList<Case>();
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
		for (int i=0; i<map.getNbLignes();i++){
			for (int j=0; j<map.getNbLignes();j++){
				if (!map.getCase(i, j).getNature().equals(NatureTerrain.EAU) && map.unVoisinEau(map.getCase(i, j))){
					map.ListeVoisinEau.add(map.getCase(i, j));
				}
				
			}
		}
		
	}
}

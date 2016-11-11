package simulateur;
import java.lang.String;
import java.awt.image.ImageObserver;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.LinkedList;
import java.util.ListIterator;
import gui.GUISimulator;
import gui.Rectangle;
import gui.Simulable;
import gui.Text;
import src.Case;
import src.DonneesSimulation;

public class Simulateur implements Simulable {


	public DonneesSimulation data;
	public GUISimulator gui;
	
	public Simulateur(String pathMap){
		int x = 100;
		data=new DonneesSimulation(pathMap);
		gui = new GUISimulator(x,x,Color.black,this);
		Afficher(x);
	}
	
	@Override
	public void next() {
		// TODO Auto-generated method stub

	}

	@Override
	public void restart() {
		// TODO Auto-generated method stub

	}
	// On affiche la simulation ayant pour données data
	public void Afficher(int x){
		gui.reset();
		int tailleCaseAffichage = x/data.map.getNbLignes();
		Incendie Temp;
		for(int i=0; i<data.map.getNbLignes();i++){
			for(int j=0; j<data.map.getNbColonnes();j++){
				switch(String.valueOf(data.map.map[i][j].getNature())){ 
				case "EAU":
					gui.addGraphicalElement(new Rectangle(i*tailleCaseAffichage, j*tailleCaseAffichage, Color.blue, Color.blue, tailleCaseAffichage));
					break;
				case "FORET":
					gui.addGraphicalElement(new Rectangle(i*tailleCaseAffichage, j*tailleCaseAffichage, Color.green, Color.green,tailleCaseAffichage));
					break;
				case "ROCHE":
					gui.addGraphicalElement(new Rectangle(i*tailleCaseAffichage, j*tailleCaseAffichage, Color.GRAY, Color.GRAY,tailleCaseAffichage));
					break;
				case "TERRAIN_LIBRE":
					gui.addGraphicalElement(new Rectangle(i*tailleCaseAffichage, j*tailleCaseAffichage, Color.white, Color.white,tailleCaseAffichage));
					break;
				case "HABITAT":
					gui.addGraphicalElement(new Rectangle(i*tailleCaseAffichage, j*tailleCaseAffichage, Color.black, Color.black,tailleCaseAffichage));
					Temp = IncendieIterator.next;			break;
				}
			}
		}
		Temp = data.incendies.getFirst();
		gui.addGraphicalElement(new ImageElement(data.incendies.position.getLigne(),data.incendies.position.getColonne() ,java.lang.String fileName,tailleCaseAffichage,tailleCaseAffichage, java.awt.image.ImageObserver obs));
		ListIterator<Incendie> IncendieIterator=data.incendies.listIterator;
		while (IncendieIterator.hasnext){
			Temp = IncendieIterator.next;
			gui.addGraphicalElement(new ImageElement(data.incendies.position.getLigne(),data.incendies.position.getColonne() ,java.lang.String fileName,tailleCaseAffichage,tailleCaseAffichage, java.awt.image.ImageObserver obs));
		}
	}
}

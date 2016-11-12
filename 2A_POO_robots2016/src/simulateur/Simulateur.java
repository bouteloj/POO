package simulateur;


import java.awt.Color;
import java.util.ListIterator;
import java.util.PriorityQueue;

import javax.swing.JFrame;

import events.Evenement;
import events.PrioEvent;

import gui.GUISimulator;
import gui.ImageElement;
import gui.Rectangle;
import gui.Simulable;
import src.DonneesSimulation;
import src.Incendie;
import src.Robot;




public class Simulateur implements Simulable {


	public DonneesSimulation data;
	public GUISimulator gui;
	private long time;
	private PriorityQueue<Evenement> events;
	int x;
	
	public Simulateur(String pathMap){
		x = 800;
		data=new DonneesSimulation(pathMap);
		gui = new GUISimulator(x,x,Color.black,this);
		time=0;
		events=new PriorityQueue<Evenement>(10,new PrioEvent());
		Afficher(x);
	}
	
	public long getTime(){
		return this.time;
	}
	
	@Override
	public void next() {
		this.time++;
		while(this.events.peek().getDate()<this.time){
			this.events.poll().execute();
		}
		Afficher(x);
	}

	@Override
	public void restart() {
		// TODO Auto-generated method stub

	}
	// On affiche la simulation ayant pour données data
	public void Afficher(int x){
		gui.reset();
		
		int tailleCaseAffichage = x/data.map.getNbLignes();
		java.lang.String fileNameincend= "img/feu.png";
		
		Incendie Temp;
		Robot Temp2;
		for(int i=0; i<data.map.getNbLignes();i++){
			for(int j=0; j<data.map.getNbColonnes();j++){
				switch(String.valueOf(data.map.map[i][j].getNature())){ 
				case "EAU":
					gui.addGraphicalElement(new Rectangle(i*tailleCaseAffichage+tailleCaseAffichage/2, j*tailleCaseAffichage+tailleCaseAffichage/2, Color.black, Color.blue, tailleCaseAffichage));
					break;
				case "FORET":
					gui.addGraphicalElement(new Rectangle(i*tailleCaseAffichage+tailleCaseAffichage/2, j*tailleCaseAffichage+tailleCaseAffichage/2, Color.black, Color.green,tailleCaseAffichage));
					break;
				case "ROCHE":
					gui.addGraphicalElement(new Rectangle(i*tailleCaseAffichage+tailleCaseAffichage/2, j*tailleCaseAffichage+tailleCaseAffichage/2, Color.black, Color.GRAY,tailleCaseAffichage));
					break;
				case "TERRAIN_LIBRE":
					gui.addGraphicalElement(new Rectangle(i*tailleCaseAffichage+tailleCaseAffichage/2, j*tailleCaseAffichage+tailleCaseAffichage/2, Color.black, Color.white,tailleCaseAffichage));
					break;
				case "HABITAT":
					gui.addGraphicalElement(new Rectangle(i*tailleCaseAffichage+tailleCaseAffichage/2, j*tailleCaseAffichage+tailleCaseAffichage/2, Color.black, Color.yellow,tailleCaseAffichage));
					break;
				}
			}
		}
		
		Temp = data.incendies.getFirst();
		ListIterator<Incendie> IncendieIterator=data.incendies.listIterator(0);
		while (IncendieIterator.hasNext()){
			gui.addGraphicalElement(new ImageElement(Temp.position.getLigne()*tailleCaseAffichage,Temp.position.getColonne()*tailleCaseAffichage,fileNameincend,tailleCaseAffichage,tailleCaseAffichage,new JFrame()));
			Temp = IncendieIterator.next();
		}
		gui.addGraphicalElement(new ImageElement(Temp.position.getLigne()*tailleCaseAffichage,Temp.position.getColonne()*tailleCaseAffichage,fileNameincend,tailleCaseAffichage,tailleCaseAffichage,new JFrame()));

		Temp2 = data.robots.getFirst();
		ListIterator<Robot> robotIterator=data.robots.listIterator(0);
		while (robotIterator.hasNext()){
			gui.addGraphicalElement(new ImageElement(Temp2.getPosition().getLigne()*tailleCaseAffichage,Temp2.getPosition().getColonne()*tailleCaseAffichage,Temp2.getpicname(),tailleCaseAffichage,tailleCaseAffichage,new JFrame()));
			Temp2 = robotIterator.next();
		}
		gui.addGraphicalElement(new ImageElement(Temp2.getPosition().getLigne()*tailleCaseAffichage,Temp2.getPosition().getColonne()*tailleCaseAffichage,Temp2.getpicname(),tailleCaseAffichage,tailleCaseAffichage,new JFrame()));

	}
	
	public void ajouteEvenement(Evenement event){
		this.events.add(event);
	}
}

package simulateur;


import events.DeplacerRobot;
import events.Evenement;
import events.PrioEvent;
import events.RobotArrive;
import gui.GUISimulator;
import gui.ImageElement;
import gui.Rectangle;
import gui.Simulable;

import java.awt.Color;
import java.util.ListIterator;
import java.util.PriorityQueue;

import javax.swing.JFrame;

import robots.Robot;
import src.DonneesSimulation;
import src.Incendie;
import staticF.Utilities;




public class Simulateur implements Simulable {

	public DonneesSimulation data; //donnees de la simulation
	public GUISimulator gui; //affichage
	private long time; //temps du programe (en secondes)
	private PriorityQueue<Evenement> events; //file de priorité des evenements a traiter
	int x; //taille de la fenetre
	String path; //chemin vers la carte a lire
	boolean fini=false; //true ssi tout les incendies ont été eteints.
	
	public Simulateur(String pathMap){
		x = 600;//modify for window size
		this.path=pathMap;
		data=new DonneesSimulation(pathMap);
		gui = new GUISimulator(x,x,Color.black,this);
		time=0;
		events=new PriorityQueue<Evenement>(10,new PrioEvent());
		Afficher();
	}
	
	public long getTime(){
		return this.time;
	}
	
	public void ajouteEvenement(Evenement event){
		this.events.add(event);
	}
	
	@Override
	public void next() {
		this.time++;
		chefPompier();
		if(this.events.size()==0){return;}
		while(this.events.peek().getDate()<this.time){
			this.events.poll().execute();
			if(this.events.size()==0){break;}

		}
		if (data.incendies.size()==0 && !fini){
			System.out.println("Tous les incendies ont ete eteints! \n \t WELL DONE!");
			fini=true;
		}
		Afficher();
	}

	@Override
	public void restart() {
		data=new DonneesSimulation(path);
		time=0;
		events=new PriorityQueue<Evenement>(10,new PrioEvent());
		Afficher();

	}
	// Affichage de l'etat courant de la simulation
	public void Afficher(){
		gui.reset();
		
		int tailleCaseAffichage = x/data.map.getNbLignes();
		java.lang.String fileNameincend= "img/feu.png";
		
		Incendie Temp;
		Robot Temp2;
		for(int j=0; j<data.map.getNbLignes();j++){
			for(int i=0; i<data.map.getNbColonnes();i++){
				switch(String.valueOf(data.map.map[i][j].getNature())){ 
				case "EAU":
					gui.addGraphicalElement(new Rectangle(j*tailleCaseAffichage+tailleCaseAffichage/2, i*tailleCaseAffichage+tailleCaseAffichage/2, Color.black, Color.blue, tailleCaseAffichage));
					break;
				case "FORET":
					gui.addGraphicalElement(new Rectangle(j*tailleCaseAffichage+tailleCaseAffichage/2, i*tailleCaseAffichage+tailleCaseAffichage/2, Color.black, Color.green,tailleCaseAffichage));
					break;
				case "ROCHE":
					gui.addGraphicalElement(new Rectangle(j*tailleCaseAffichage+tailleCaseAffichage/2, i*tailleCaseAffichage+tailleCaseAffichage/2, Color.black, Color.GRAY,tailleCaseAffichage));
					break;
				case "TERRAIN_LIBRE":
					gui.addGraphicalElement(new Rectangle(j*tailleCaseAffichage+tailleCaseAffichage/2, i*tailleCaseAffichage+tailleCaseAffichage/2, Color.black, Color.white,tailleCaseAffichage));
					break;
				case "HABITAT":
					gui.addGraphicalElement(new Rectangle(j*tailleCaseAffichage+tailleCaseAffichage/2, i*tailleCaseAffichage+tailleCaseAffichage/2, Color.black, Color.yellow,tailleCaseAffichage));
					break;
				}
			}
		}
		
		ListIterator<Incendie> IncendieIterator=data.incendies.listIterator(0);
		while (IncendieIterator.hasNext()){
			Temp = IncendieIterator.next();
			gui.addGraphicalElement(new ImageElement(Temp.position.getColonne()*tailleCaseAffichage,Temp.position.getLigne()*tailleCaseAffichage,fileNameincend,tailleCaseAffichage,tailleCaseAffichage,new JFrame()));
		}

		ListIterator<Robot> robotIterator=data.robots.listIterator(0);
		while (robotIterator.hasNext()){
			Temp2 = robotIterator.next();
			gui.addGraphicalElement(new ImageElement(Temp2.getPosition().getColonne()*tailleCaseAffichage,Temp2.getPosition().getLigne()*tailleCaseAffichage,Temp2.getpicname(),tailleCaseAffichage,tailleCaseAffichage,new JFrame()));
		}

	}
	
	
	/*
	 * Affectation des robots aux incendies
	 * Methode:
	 * On affecte chaque robot a l'incendie non deja attribué accessible le plus proche.
	 * si aucun incendie non deja attribué n'est accessible(ou existant), on affecte le 
	 * 		robot a l'incendie accessible le plus proche
	 */
	private void chefPompier(){
		ListIterator<Incendie> itrIncend;
		ListIterator<Robot> itrRob=data.robots.listIterator();
		Robot tempr;
		Incendie tempi;
		double dureeTrajetmin;
		double dureeTrajet;
		Incendie incendieChoisi=null;
		long dateinit;

		while (itrRob.hasNext()){
			tempr=itrRob.next();
			if (!tempr.getDeplacement()){
				incendieChoisi=null;
				dureeTrajetmin=Double.MAX_VALUE;
				itrIncend=data.incendies.listIterator();
				while (itrIncend.hasNext()){
					tempi=itrIncend.next();
					if(!tempi.getAssigne()){
						dureeTrajet=Utilities.poids(this,tempr,tempr.getPosition(),
								Utilities.dijkstra(this,tempr,tempi.getPosition()));
						if(dureeTrajet<dureeTrajetmin){
							dureeTrajetmin=dureeTrajet;
							incendieChoisi=tempi;
						}
					}
				}
				if(dureeTrajetmin==Double.MAX_VALUE){
					itrIncend=data.incendies.listIterator();
					while (itrIncend.hasNext()){
						tempi=itrIncend.next();
						dureeTrajet=Utilities.poids(this,tempr,tempr.getPosition(),
								Utilities.dijkstra(this,tempr,tempi.getPosition()));
						if(dureeTrajet<dureeTrajetmin){
							dureeTrajetmin=dureeTrajet;
							incendieChoisi=tempi;
						}
					}
				}
				if (incendieChoisi==null){
					return;
				}
				incendieChoisi.setAssigne(true);
				tempr.setDeplacement(true);
				tempr.destination=Utilities.dijkstra(this,tempr,incendieChoisi.getPosition());
				if(tempr.getDestination().size()==0){
					ajouteEvenement(new RobotArrive(this.time,tempr,this));
				} else {
					dateinit=(long) (this.time + tempr.getTempsDeplacement(tempr.getPosition(),
							data.map.getVoisin(tempr.getPosition(), tempr.getDestination().peek()),
							data.map.getTailleCases()));
					ajouteEvenement(new DeplacerRobot(dateinit, 
							tempr,data.map.getVoisin(tempr.getPosition(), 
									tempr.getDestination().poll()),this));
				}
			}
		}
	}
	
}

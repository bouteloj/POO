package robots;

import java.util.LinkedList;

import src.Carte;
import src.Case;
import src.Direction;
import src.Incendie;
import src.NatureTerrain;

/*
 * classe abstraite regroupant les robots pompiers
 */
public abstract class Robot {
	protected Case position;//du robot
    protected int capacite;//Quantité d'eau presente dans le reservoire != capacitéMax
    protected int vitesse; //par defaut, en m/s
    protected boolean enDeplacement=false;// true ssi le robot est occupé
    java.lang.String fileName;//fichier image, pour l'affichage
    public LinkedList<Direction> destination; //liste des directions des deplacements elemetaires du robot vers sa destination
    
    
    public Robot(Case pos, int vitesse){
    	this.position=pos;
    	this.vitesse=(int) (vitesse/3.6);
    	this.enDeplacement=false; 
    	this.destination=new LinkedList<Direction>();
    }
    
    
    //gets,sets
    public java.lang.String getpicname(){
    	return this.fileName;
    }
        
    public void setDeplacement(boolean b){
    	this.enDeplacement=b;
    }
    public boolean getDeplacement(){
    	return this.enDeplacement;
    }
   
    public LinkedList<Direction> getDestination(){
    	return this.destination;
    }
    
    public void setDestination(LinkedList<Direction> liste){
    	this.destination = liste;
    }
    
	public Case getPosition(){
		return this.position;
	}
	public void setPosition(Case pos){
		this.position = pos;
	}
	public int getCapacite(){
		return this.capacite;
	}
	public void setCapacite(int cap){
		this.capacite = cap;
	}
	
	//Faux accesseurs: renvoie des données statiques liées aux robots
	
	/*
	 * temps de deplacement d'une case a une autre
	 */
	public double getTempsDeplacement(Case depart, Case destination, int tailleCases){
		
		if (depart==destination){
			return 0;
		}
				
		if (getVitesse(destination.getNature())==0){
			return Double.MAX_VALUE;
		}
		return 2/(getVitesse(depart.getNature()) + getVitesse(destination.getNature()))*(tailleCases);
	}
	/*
	 * pour debug
	 */
	public String getType(){
        return this.getClass().getName().substring(14);
	}
	
	public abstract double getVitesse(NatureTerrain nature);
	
	public abstract  int getCapaciteMax();
	
	public abstract  int tempsRemplissage();
	
	public abstract  int tempsIntervention();
	
	public abstract  int quantiteIntervention();
	//Methodes
	
	/*
	 * versement d'une quantité elementaire d'eau, renvoie le booleen ReservoireNonVide
	 */
	public boolean deverserEau( Incendie incend){
		this.capacite -= quantiteIntervention();
		incend.verser(quantiteIntervention());
		
		return !(this.capacite==0);
	}
	 
		
	public abstract void remplirReservoir(Carte carte);

}

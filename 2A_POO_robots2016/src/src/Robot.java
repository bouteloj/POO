package src;

import java.util.LinkedList;





public abstract class Robot {
	protected Case position;
    protected int capacite;
    protected int vitesse;
    protected boolean enDeplacement;
    java.lang.String fileName;
    LinkedList<Direction> destination;
    
    public java.lang.String getpicname(){
    	return this.fileName;
    }
    
    public Robot(Case pos, int vitesse){
    	this.position=pos;
    	this.vitesse=vitesse;
    	this.enDeplacement=false; 
    	this.destination=new LinkedList<Direction>();
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
	public double getTempsDeplacement(Case depart, Case destination, int tailleCases){
		
		return 2/(getVitesse(depart.getNature()) + getVitesse(destination.getNature()))*(tailleCases);
	}
	
	public void deverserEau( Incendie incend){
		
		if (incend.getVerser() <= this.getCapaciteMax()) {
			if (this.getCapacite() >= incend.getVerser()) {
				this.capacite -= incend.getVerser();

			} else {
				incend.verser(incend.getVerser()-this.capacite);
				this.setCapacite(0);
				System.out.println("reservoir mis à 0");
			}
		} else {
			System.out.println("Le " + this.getType() + " ne peut se contenir ccette quantité ");
		}
	}
	 public String getType(){
	        return this.getClass().getName().substring(14);
	 }
	
	
	public abstract double getVitesse(NatureTerrain nature);
	
	public abstract  int getCapaciteMax();
	
	public abstract void remplirReservoir(Carte carte);
	public abstract  int tempsRemplissage();
	public abstract  int tempsIntervention();
	public abstract  int quantiteIntervention();
}

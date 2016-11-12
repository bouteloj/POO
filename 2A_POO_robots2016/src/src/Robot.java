package src;





public abstract class Robot {
	protected Case position;
	protected int volumeEau;
    protected int capacite;
    protected int vitesse;
    public void getsetVolume(int vol){
	this.volumeEau=vol;
    }
	public int getVolume(){
		return this.volumeEau;
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
	public void deverserEau(int vol){
		
		if (vol <= this.getCapaciteMax()) {
			if (this.getCapacite() >= vol) {
				this.capacite -= vol;

			} else {
				Incendie.verser(vol-this.capacite);
				this.setCapacite(0);
				System.out.println("reservoir mis à 0");
			}
		} else {
			System.out.println("Le " + this.getType() + " ne peut se contenir cccette quantité ");
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

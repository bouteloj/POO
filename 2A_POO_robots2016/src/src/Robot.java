package src;




public abstract class Robot {
	private Case position;
	private int volumeEau;

	
	public int getVolume(){
		return this.volumeEau;
	}
	public Case getPosition(){
		return this.position;
	}
	public void setPosition(Case pos){
		this.position = pos;
	}
	
	public abstract boolean move(Case destination);
	
	public abstract double getVitesse(NatureTerrain nature);
	
	public abstract void deverserEau(int vol);
	
	public abstract void remplirReservoir();
}

package src;

public class Robot_A_Pattes extends Robot {
	public Robot_A_Pattes(){
		
		this.capacite = Integer.MAX_VALUE;
		this.vitesse = 30;
	}

	@Override
	public double getVitesse(NatureTerrain natureDuTerrain){
		if (natureDuTerrain.equals(NatureTerrain.EAU)) {
			return 0;
		} else if (natureDuTerrain.equals(NatureTerrain.ROCHE)) {
			return 10;
		} else {
			return this.vitesse;
		}
	}

	
	@Override
	public void deverserEau(int vol){
		this.capacite = Integer.MAX_VALUE;
		
	}


	@Override
	public int getCapaciteMax(){
		return Integer.MAX_VALUE;
	}

	@Override
	public void remplirReservoir(Carte carte){
		this.capacite = Integer.MAX_VALUE;
		return;
	}


	@Override
	public int tempsRemplissage(){
		return 0;
	}

	@Override
	public int tempsIntervention(){
		return 1;
	}

	@Override
	public int quantiteIntervention(){
		return 10;
	}

}

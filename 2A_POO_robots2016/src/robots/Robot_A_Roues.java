package robots;

import src.Carte;
import src.Case;
import src.NatureTerrain;

public class Robot_A_Roues extends Robot{
	int i = 0;	

    public Robot_A_Roues(Case pos, int vitesse){
		super(pos,vitesse);
		this.fileName="img/roues.png";
		this.capacite = 5000;
	}



	@Override
	public double getVitesse(NatureTerrain natureDuTerrain){
		if (natureDuTerrain.equals(NatureTerrain.HABITAT) || natureDuTerrain.equals(NatureTerrain.TERRAIN_LIBRE)) {
			return this.vitesse;
		} else {
			return 0;
		}
	}


	@Override
	public int getCapaciteMax(){
		return 5000;
	}

	@Override
	public void remplirReservoir(Carte carte){
		if (carte.unVoisinEau(this.position)) {
			this.capacite=this.getCapaciteMax();
		}else {
			System.out.println("erreur:Le robot a roues ne peut pas remplir l'eau!");
		}
	}


	@Override
	public int tempsRemplissage(){
		return 600;
	}

	@Override
	public int tempsIntervention(){
		return 5;
	}

	@Override
	public int quantiteIntervention(){
		return 100;
	}
}
	
	



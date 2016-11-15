package robots;

import src.Carte;
import src.Case;
import src.NatureTerrain;

public class Robot_A_Chenilles extends Robot {

    public Robot_A_Chenilles(Case pos, int vitesse){
		super(pos,vitesse);
		this.fileName="img/chenille.png";
		this.setCapacite(2000);
	}



	@Override
	public double getVitesse(NatureTerrain natureDuTerrain){
		if (natureDuTerrain.equals(NatureTerrain.EAU) || natureDuTerrain.equals(NatureTerrain.ROCHE)) {
			return 0;
		} else if (natureDuTerrain.equals(NatureTerrain.FORET)) {
			return (this.vitesse) / 2;
		} else {
			return this.vitesse;
		}
	}


	@Override
	public int getCapaciteMax(){
		return 2000;
	}

	@Override
	public void remplirReservoir(Carte carte){
		if (carte.unVoisinEau(this.position)) {
			this.capacite=this.getCapaciteMax();
		}else {
			System.out.println("erreur:Le robot a chenille ne peut pas remplir l'eau!");
		}
	}


	@Override
	public int tempsRemplissage(){
		return 300;
	}

	@Override
	public int tempsIntervention(){
		return 8;
	}

	@Override
	public int quantiteIntervention(){
		return 100;
	}
}

package robots;

import src.Carte;
import src.Case;
import src.NatureTerrain;

public class Drone extends Robot {
	
		public Drone(Case pos, int vitesse){
			super(pos,vitesse);
			this.setCapacite(10000);
			this.fileName="img/drone.png";
		}


		@Override
		public double getVitesse(NatureTerrain natureDuTerrain){
			return this.vitesse;
		}


		@Override
		public int getCapaciteMax(){
			return 10000;
		}

		@Override
		public void remplirReservoir(Carte carte){
			if (position.getNature().equals(NatureTerrain.EAU)) {
				super.capacite=10000;
			} else {
				System.out.println("erreur:Le drône ne peut pas remplir l'eau!");
			}
		}


		@Override
		public int tempsRemplissage(){
			return 1800;
		}

		@Override
		public int tempsIntervention(){
			return 30;
		}

		@Override
		public int quantiteIntervention(){
			return 10000;
		}
}



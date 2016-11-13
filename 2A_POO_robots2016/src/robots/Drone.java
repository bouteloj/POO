package robots;

import src.Carte;
import src.Case;
import src.NatureTerrain;

public class Drone extends Robot {
	
		int n = 0;	

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
				if (n == 8) {
					n = 0;
				} else {
					super.setCapacite(getCapacite()+6);
					n++;
				}

				if (super.getCapacite() > 10000){
					super.setCapacite(10000);
				}
			} else {
				System.out.println("Le drône ne peut pas remplir l'eau!");
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



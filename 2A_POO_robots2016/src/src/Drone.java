package src;

public class Drone extends Robot {
	
		int n = 0;	

		public Drone(){
			
			this.setCapacite(10000);
			this.vitesse=100;
		}

		public Drone(int vitesse) {
			
			if(vitesse > 0) {
				if (vitesse >= 150) {
					this.vitesse=150;
				} else {
					this.vitesse = vitesse;
				}
			}
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
					ccc
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

}

package events;

import robots.Robot;
import simulateur.Simulateur;
import src.Carte;
/*
 * remplissage du reservoire
 */
public class remplirEau extends Evenement{
		private Simulateur simul;
		private Carte carte;//robot concerné
		private Robot robot;//simul.data.map, pour lisibilité

		public remplirEau(long date,Simulateur simul , Robot robot){
			super(date);
			this.simul=simul;
			this.carte = simul.data.map;
			this.robot = robot;
		}
		
		/*
		 * remplit le robot puis le rend disponible
		 */
        @Override
		public boolean execute(){
			if (robot.getCapacite() < robot.getCapaciteMax()) {
				robot.setDeplacement(false);
				this.robot.remplirReservoir(carte);
				System.out.println("Robot rempli");
	        }

			else {
				System.out.println("Robot plein et pret");
			}
			/*
			//cas scenario utilisé pour partie 2 scenario 1
			 * 			robot.getDestination().add(Direction.EST);
			long date=(long) (simul.getTime()+ (simul.data.map.getTailleCases()/
					robot.getVitesse(simul.data.map.getVoisin(robot.getPosition(), 
							Direction.EST).getNature())));
			simul.ajouteEvenement(new DeplacerRobot(date, robot, simul.data.map.getVoisin(robot.getPosition(), 
					Direction.EST), simul));
			robot.setDeplacement(false);
			*/
			return true;
        }
}
	



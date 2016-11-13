package events;

import robots.Robot;
import simulateur.Simulateur;
import src.Carte;
import src.Direction;

public class remplirEau extends Evenement{
	private Simulateur simul;
		private Carte carte;
		private Robot robot;

		public remplirEau(long date,Simulateur simul , Robot robot){
			super(date);
			this.simul=simul;
			this.carte = simul.data.map;
			this.robot = robot;
		}
        @Override
		public boolean execute(){
			if (robot.getCapacite() < robot.getCapaciteMax()) {
				this.robot.remplirReservoir(carte);
				System.out.println("Robot rempli");

	        }

			else {
				System.out.println("Robot plein et pret");
			}
			
			//cas scenario
			robot.getDestination().add(Direction.EST);
			long date=(long) (simul.getTime()+ (simul.data.map.getTailleCases()/
					robot.getVitesse(simul.data.map.getVoisin(robot.getPosition(), 
							Direction.EST).getNature())));
			simul.ajouteEvenement(new DeplacerRobot(date, robot, simul.data.map.getVoisin(robot.getPosition(), 
					Direction.EST), simul));
			
			return true;
        }
}
	


package events;

import robots.Robot;
import simulateur.Simulateur;
import src.Case;

/*
 * deplacement elementaire d'un robot
 */
public class DeplacerRobot extends Evenement{
	private Robot rob; //robot concerné
	private Case dest;//destination elementaire
	private Simulateur sim;
	
	public DeplacerRobot(long date,Robot rob,Case dest,Simulateur sim) {
		super(date);
		this.rob=rob;
		this.dest=dest;
		this.sim=sim;
	}
	
	/*
	 * deplace effectivement le robot
	 * cree le deplacament elementaire suivant ou l'evenement d'arrivee
	 */
	@Override
	public boolean execute(){
		rob.setPosition(dest);
		if (rob.getDestination().size() == 0){
			sim.ajouteEvenement(new RobotArrive(date,rob,sim));
		}else{
			rob.setDeplacement(true);
			long newdate=(long) (sim.getTime()+(sim.data.map.getTailleCases()/
					rob.getVitesse(sim.data.map.getVoisin(dest, rob.getDestination().peek()).getNature())));
			sim.ajouteEvenement(new DeplacerRobot(
					newdate,rob,sim.data.map.getVoisin(dest, rob.getDestination().poll()),sim));
		}
		return true;
		
		
		
	}

}

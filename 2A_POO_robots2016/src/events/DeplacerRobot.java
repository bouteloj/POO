package events;

import robots.Robot;
import simulateur.Simulateur;
import src.Case;

public class DeplacerRobot extends Evenement{
	private Robot rob;
	private Case dest;
	private Simulateur sim;
	
	public DeplacerRobot(long date,Robot rob,Case dest,Simulateur sim) {
		super(date);
		this.rob=rob;
		this.dest=dest;
		this.sim=sim;
	}
	
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
		rob.setDeplacement(false);
		return true;
		
		
		
	}

}

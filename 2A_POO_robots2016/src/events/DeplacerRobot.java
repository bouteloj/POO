package events;

import simulateur.Simulateur;
import src.Case;
import src.DonneesSimulation;
import src.Robot;

public class DeplacerRobot extends Evenement{
	private Robot rob;
	private Case dest;
	private Simulateur sim;
	
	public DeplacerRobot(long date) {
		super(date);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean execute(){
		rob.setPosition(dest);
		if (rob.getDestination().size() ==0){
			
		}else{
			sim.ajouteEvenement(new DeplacerRobot(newdate,rob,rob.sim.map.get(getDestination().poll()))
		}
		return true;
		
		
		
	}

}

package events; 

import robots.Robot;
import simulateur.Simulateur;
import src.Direction;
import src.Incendie;

public class VerserEau extends Evenement{
	private Incendie incendie;
	
	private Robot robot;
	private Simulateur simul;
	
	public VerserEau (long date, Incendie incendie, Robot robot, Simulateur simul){
		super(date);
		this.incendie=incendie;
		this.robot=robot;
	
		this.simul=simul;
	}
	
	@Override
	public boolean execute(){
	
		if ((this.incendie.getVerser() != 0 ) && (robot.deverserEau(incendie))){
			if (incendie.getVerser() <= 0) {
				simul.data.incendies.remove(incendie);
			}else{
				simul.ajouteEvenement(new VerserEau(date + robot.tempsIntervention(), incendie, robot, simul));
			}
		
		}else  {
			System.out.println("Robot vide");
			//cas scenario
			robot.getDestination().add(Direction.OUEST);
			long date=(long) (simul.getTime()+ (simul.data.map.getTailleCases()/
					robot.getVitesse(simul.data.map.getVoisin(robot.getPosition(), 
							Direction.OUEST).getNature())));
			simul.ajouteEvenement(new DeplacerRobot(date, robot, simul.data.map.getVoisin(robot.getPosition(), 
					Direction.OUEST), simul));
			//TODO Remplir lors de la prise de d�cision
		}
		return true;
	}
}

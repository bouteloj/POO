package events; 

import java.util.Iterator;
import java.util.LinkedList;

import robots.Robot;
import simulateur.Simulateur;
import src.Case;
import src.Direction;
import src.Incendie;
import staticF.Utilities;

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
				return true;
			}
		
		}
		if(robot.getCapacite() == 0){
			if (this.incendie.getVerser() > 0){
				System.out.println("Robot vide et le feu brule toujours. Vite aller se recharger");
			}else{
				System.out.println("Feu eteint et il ne reste plus d'eau dans le robot");
			}
			// On cherche le point d'eau auquel on va aller se recharger
			LinkedList<Case> L=new LinkedList<Case>();
			if (robot.getCapaciteMax()==10000){
				L  = simul.data.map.ListeEau;
			}else{
				L  = simul.data.map.ListeVoisinEau;
			}
			
			Iterator<Case> itrL = L.iterator();
			Case PointEauChoisi = L.getFirst();
			Case PointEauTeste;
			LinkedList<Direction> ListDir = Utilities.dijkstra(simul, robot, PointEauChoisi);
			double Temps;
				
			if (ListDir != null){
				Temps = Utilities.poids(simul, robot, robot.getPosition(), ListDir);
			}else{ // Cas ou le premier element de ListeEau n'est pas atteignable
				Temps = Integer.MAX_VALUE;
			}
			while (itrL.hasNext()){
				PointEauTeste = itrL.next();
				ListDir = Utilities.dijkstra(simul, robot, PointEauTeste);
				if ((ListDir != null) && Temps > Utilities.poids(simul, robot, robot.getPosition(), ListDir)){
					Temps = Utilities.poids(simul, robot, robot.getPosition(), ListDir);
					PointEauChoisi = PointEauTeste;
				}
			}
				robot.setDestination(Utilities.dijkstra(simul, robot, PointEauChoisi));
				if (simul.data.map ==null){System.out.println("simul");}

				long date = (long) (simul.getTime() + robot.getTempsDeplacement(robot.getPosition(),simul.data.map.getVoisin(robot.getPosition(),robot.getDestination().peek()),simul.data.map.getTailleCases()));
				simul.ajouteEvenement(new DeplacerRobot(date, robot,simul.data.map.getVoisin(robot.getPosition(),robot.getDestination().poll()),simul));
				
		}else { // Cas ou le robot a eteint le feu et qu'il lui reste de l'eau. 
				System.out.println("Feu eteint et il reste de l'eau dans le robot. Robot dispo pour chef");
				robot.setDeplacement(false);		
		}
			/*//cas scenario
			robot.getDestination().add(Direction.OUEST);
			long date=(long) (simul.getTime()+ (simul.data.map.getTailleCases()/
					robot.getVitesse(simul.data.map.getVoisin(robot.getPosition(), 
							Direction.OUEST).getNature())));
			simul.ajouteEvenement(new DeplacerRobot(date, robot, simul.data.map.getVoisin(robot.getPosition(), 
					Direction.OUEST), simul));*/
			return true;
			}
}



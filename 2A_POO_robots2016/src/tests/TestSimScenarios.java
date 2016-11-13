package tests;

import java.util.ListIterator;

import robots.Robot;
import simulateur.Simulateur;
import src.Direction;
import events.DeplacerRobot;

public class TestSimScenarios {

	/**
	 * no args needed
	 */
	public static void main(String[] args) {
		Simulateur simulate = new Simulateur ("cartes/carteSujet.map");
		initdepl(simulate,1);
		simulate.Afficher();
	}
	
	private static void initdepl(Simulateur s, int numscenario){
		Robot Temp;
		if(numscenario==0){
			for (int i=0; i<3; i++){
				s.data.robots.getFirst().getDestination().addLast(Direction.NORD);
				//s.data.robots.getFirst().getDestination().addLast(Direction.SUD);
			}
		}else{
			ListIterator<Robot> robotIterator1=s.data.robots.listIterator(0);
			robotIterator1.next();
			Temp = robotIterator1.next();
			Temp.getDestination().addLast(Direction.NORD);
		}
		long date=0;
		
		ListIterator<Robot> robotIterator2=s.data.robots.listIterator(0);
		while (robotIterator2.hasNext()){
			Temp = robotIterator2.next();

			if (Temp.getDestination().size()!=0){
				
				date=(long) (s.getTime()+(s.data.map.getTailleCases()/
						Temp.getVitesse(s.data.map.getVoisin(Temp.getPosition(), 
								Temp.getDestination().peek()).getNature())));
				
				s.ajouteEvenement(new DeplacerRobot(date,Temp,s.data.map.getVoisin(
						Temp.getPosition(), Temp.getDestination().poll()),s));
			}
			//Temp = robotIterator.next();

			
		}
		

	}

}

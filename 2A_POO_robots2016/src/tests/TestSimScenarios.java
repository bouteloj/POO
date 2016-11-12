package tests;

import java.util.ListIterator;

import events.DeplacerRobot;

import simulateur.Simulateur;
import src.Direction;
import src.Robot;

public class TestSimScenarios {

	/**
	 * no args needed
	 */
	public static void main(String[] args) {
		Simulateur simulate = new Simulateur ("cartes/carteSujet.map");
		initdepl(simulate,0);
		simulate.Afficher();
	}
	
	private static void initdepl(Simulateur s, int numscenario){
		if(numscenario==0){
			for (int i=0; i<3; i++){
				s.data.robots.getFirst().getDestination().addLast(Direction.NORD);
				//s.data.robots.getFirst().getDestination().addLast(Direction.SUD);
			}
		}else{
			
		}
		long date=0;
		Robot Temp = s.data.robots.getFirst();
		ListIterator<Robot> robotIterator=s.data.robots.listIterator(0);
		while (robotIterator.hasNext()){
			Temp = robotIterator.next();
			System.out.println(Temp.getDestination().size());

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

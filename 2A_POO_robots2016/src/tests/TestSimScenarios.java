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
		simulate.Afficher(800);
	}
	
	private static void initdepl(Simulateur s, int numscenario){
		if(numscenario==0){
			for (int i=0; i<4; i++)
				s.data.robots.getFirst().getDestination().addLast(Direction.NORD);
		}else{
			
		}
		long date=0;
		Robot Temp = s.data.robots.getFirst();
		ListIterator<Robot> robotIterator=s.data.robots.listIterator(0);
		while (robotIterator.hasNext()){
			if (Temp.getDestination().size()!=0)
				s.ajouteEvenement(new DeplacerRobot(date,Temp,s.data.map.getVoisin(Temp.getPosition(), Temp.getDestination().getFirst()),s));
			Temp = robotIterator.next();
		}

	}

}

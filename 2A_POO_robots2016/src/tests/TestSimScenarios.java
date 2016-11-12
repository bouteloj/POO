package tests;

import simulateur.Simulateur;
import src.Direction;
import src.Robot;

public class TestSimScenarios {

	/**
	 * no args needed
	 */
	public static void main(String[] args) {
		Simulateur simulate = new Simulateur (Cartes);
		initdepl(simulate,0);
		simulate.Afficher(800);
	}
	
	private static void initdepl(Simulateur s, int numscenario){
		if(numscenario==0){
			s.data.robots.getFirst().destinetion.addLast(Direction.NORD);
		}else{
			
		}
		
	}

}

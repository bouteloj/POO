package tests;

import java.util.ListIterator;


//Warning: non fonctionel apres l'implementation du chef robot
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
		if(numscenario==0){// cas sortie de map
			for (int i=0; i<3; i++){//on initialise 4 deplacements au nord pour le premier robor
				s.data.robots.getFirst().getDestination().addLast(Direction.NORD);
				//s.data.robots.getFirst().getDestination().addLast(Direction.SUD);
			}
		}else{//on initialise un deplacement au nord pour le 2eme robot
				//les evenements suivants sont introduits dans verserEau et RemplirEau
			ListIterator<Robot> robotIterator1=s.data.robots.listIterator(0);
			robotIterator1.next();
			Temp = robotIterator1.next();
			Temp.getDestination().addLast(Direction.NORD);
		}
		//On initialise le premier evenement de la file de deplacement
		long date=0;
		
		ListIterator<Robot> robotIterator2=s.data.robots.listIterator(0);
		while (robotIterator2.hasNext()){
			Temp = robotIterator2.next();

			if (Temp.getDestination().size()!=0){
				//calcul de la date d'arrivee
				date=(long) (s.getTime()+(s.data.map.getTailleCases()/
						Temp.getVitesse(s.data.map.getVoisin(Temp.getPosition(), 
								Temp.getDestination().peek()).getNature())));
				//insertion de l'evenement dans le simulateur
				s.ajouteEvenement(new DeplacerRobot(date,Temp,s.data.map.getVoisin(
						Temp.getPosition(), Temp.getDestination().poll()),s));
			}

			
		}
		

	}

}

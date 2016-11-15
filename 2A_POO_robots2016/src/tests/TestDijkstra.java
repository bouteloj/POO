package tests;

import simulateur.Simulateur;
import staticF.Utilities;
import events.DeplacerRobot;

/*
 * test simple de l'implementation du plus court chemin;
 */
public class TestDijkstra {
	public static void main(String[] args) {
		Simulateur simulate = new Simulateur ("cartes/carteSujet.map");
		initdepl(simulate);
		simulate.Afficher();
	}
	
	private static void initdepl(Simulateur s){
		s.data.robots.getFirst().destination=Utilities.dijkstra(s,s.data.robots.getFirst(), s.data.map.map[0][0]);
		s.ajouteEvenement(new DeplacerRobot(66,s.data.robots.getFirst(),s.data.map.getVoisin(
				s.data.robots.getFirst().getPosition(), s.data.robots.getFirst().getDestination().poll()),s));
	}
}

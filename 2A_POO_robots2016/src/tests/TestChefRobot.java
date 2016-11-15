package tests;

import simulateur.Simulateur;

/*
 * test final;
 */
public class TestChefRobot {

	/**
	 * @param fichier: chemin vers la carte a traiter
	 */
	public static void main(String[] fichier) {
		Simulateur simulate = new Simulateur (fichier[0]);
		simulate.Afficher();
	}

}

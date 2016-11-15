package tests;

import simulateur.Simulateur;

//identique au test final
public class TestSimulateurStatique {
	
	public static void main (String[] fichier){
		Simulateur simulate = new Simulateur (fichier[0]);
		simulate.Afficher();
		}
	}


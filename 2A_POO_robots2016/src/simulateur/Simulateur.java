package simulateur;

import java.awt.Color;

import gui.GUISimulator;
import gui.Simulable;
import src.DonneesSimulation;

public class Simulateur implements Simulable {


	public DonneesSimulation data;
	public GUISimulator gui;
	
	public Simulateur(String pathMap){
		data=new DonneesSimulation(pathMap);
		gui = new GUISimulator(100,100,Color.black,this);
	}
	
	@Override
	public void next() {
		// TODO Auto-generated method stub

	}

	@Override
	public void restart() {
		// TODO Auto-generated method stub

	}

}

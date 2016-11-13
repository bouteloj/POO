package events; 

import java.util.Iterator;

import robots.Robot;
import simulateur.Simulateur;
import src.Carte;
import src.Case;
import src.Incendie;

public class RobotArrive extends Evenement{
	
	private Simulateur simul;
	private Robot robot;
	private Incendie incendie;
	private Carte carte;
	
	public RobotArrive(long date, Robot r,Simulateur s) {
		super(date);
		this.robot=r;
		this.simul=s;
		this.carte=simul.data.map;
		Iterator<Incendie> itr=this.simul.data.incendies.iterator();
		Incendie Temp;
		while(itr.hasNext()){
			Temp=itr.next();
			if(robot.getPosition().equals(Temp.getPosition())){
				this.incendie=Temp;
			}			
		}
		if (this.incendie==null){
			this.incendie=new Incendie(new Case(-1,-1),1);
		}
	}

	@Override
	public boolean execute(){
		System.out.println("Le robot est arrive");
		System.out.println("Il va pouvoir commencer sa tache");
		long newdate;
		// Dans ce cas le robot est sur un incendie
		if (robot.getPosition().equals(incendie.getPosition())){
			
			// Dans ce cas il a vid� son eau et est sur un incendie (cette incendie est donc situe a 
			// cote d'un point d'eau.
			if(robot.getCapacite() == 0){ 
				if(carte.unVoisinEau(robot.getPosition())){
					newdate=simul.getTime()+robot.tempsRemplissage();
					simul.ajouteEvenement(new remplirEau(newdate, simul, robot));
				}
				
				// Il y a erreur car on ne doit pas avoir d'appel � RobotArrive alors que le robot n'a 
				// plus d'eau et qu'il n'est pas a cote ou sur un point d'eau
				else{
					System.out.println("Erreur");
					return false;
				}
			}
			// Dans ce cas le robot est sur un incendie et doit commencer � l'�teindre
			else{
				newdate=simul.getTime()+robot.tempsIntervention();
				simul.ajouteEvenement(new VerserEau(date, incendie, robot, simul));
			}
			// Dans ce cas le robot est � c�t� ou sur un point d'eau pour remplir son stock d'eau
		}else if (carte.unVoisinEau(robot.getPosition())){
			// On remplit donc le robot
			newdate=simul.getTime()+robot.tempsRemplissage();
			simul.ajouteEvenement(new remplirEau(newdate, simul, robot));
			
			/* Dans ce cas le robot est arrive sur un point ou il n'y a ni point d'eau a 
			 proximite ni d'incendie. Il y a certainement une erreur ou c'est la fin du programme*/
		}else{
			System.out.println("Erreur: le robot est arrive sur une case n'ayant ni point d'eau ou incendie. Soit le programme est termine, soit il y a un probleme");
			return false;
		}
		return true;
	}
}

package events; 

public class RobotArrive extends Evenement{
	
	private Simulateur simul;
	private Robot robot;
	private Incendie incendie;
	private Carte carte;
	
	public RobotArrive(long date) {
		super(date);
	}

	@Override
	public boolean execute(){
		System.out.println("Le robot est arrive");
		System.out.println("Il va pouvoir commencer sa tache");
		
		// Dans ce cas le robot est sur un incendie
		if (robot.getPosition().equals(incendie.getPosition())){
			
			// Dans ce cas il a vid� son eau et est sur un incendie (cette incendie est donc situ� � 
			// c�t� d'un point d'eau.
			if(robot.getCapacite() == 0){ 
				if(carte.unVoisinEau(robot.getPosition())){
					simul.ajouteEvenement(new remplirEau(date, carte, robot));
				}
				
				// Il y a erreur car on ne doit pas avoir d'appel � RobotArrive alors que le robot n'a 
				// plus d'eau et qu'il n'est pas � c�t� ou sur un point d'eau
				else{
					System.out.println("Erreur");
					return false;
				}
			}
			// Dans ce cas le robot est sur un incendie et doit commencer � l'�teindre
			else{
				simul.ajouteEvenement(new VerserEau(date, incendie, robot, simul));
			}
			// Dans ce cas le robot est � c�t� ou sur un point d'eau pour remplir son stock d'eau
		}else if (carte.unVoisinEau(robot.getPosition())){
			// On remplit donc le robot
			simul.ajouteEvenement(new remplirEau(date, carte, robot));
			
			/* Dans ce cas le robot est arriv� sur un point o� il n'y a ni point d'eau � 
			 proximit� ni d'incendie. Il y a certainement une erreur ou c'est la fin du programme*/
		}else{
			System.out.println("Erreur: le robot est arrive sur une case n'ayant ni point d'eau ou incendie. Soit le programme est termin�, soit il y a un probl�me");
			return false;
		}
		return true;
	}
}

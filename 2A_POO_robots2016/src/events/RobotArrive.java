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
			
			// Dans ce cas il a vidé son eau et est sur un incendie (cette incendie est donc situé à 
			// côté d'un point d'eau.
			if(robot.getCapacite() == 0){ 
				if(carte.unVoisinEau(robot.getPosition())){
					simul.ajouteEvenement(new remplirEau(date, carte, robot));
				}
				
				// Il y a erreur car on ne doit pas avoir d'appel à RobotArrive alors que le robot n'a 
				// plus d'eau et qu'il n'est pas à côté ou sur un point d'eau
				else{
					System.out.println("Erreur");
					return false;
				}
			}
			// Dans ce cas le robot est sur un incendie et doit commencer à l'éteindre
			else{
				simul.ajouteEvenement(new VerserEau(date, incendie, robot, simul));
			}
			// Dans ce cas le robot est à côté ou sur un point d'eau pour remplir son stock d'eau
		}else if (carte.unVoisinEau(robot.getPosition())){
			// On remplit donc le robot
			simul.ajouteEvenement(new remplirEau(date, carte, robot));
			
			/* Dans ce cas le robot est arrivé sur un point où il n'y a ni point d'eau à 
			 proximité ni d'incendie. Il y a certainement une erreur ou c'est la fin du programme*/
		}else{
			System.out.println("Erreur: le robot est arrive sur une case n'ayant ni point d'eau ou incendie. Soit le programme est terminé, soit il y a un problème");
			return false;
		}
		return true;
	}
}

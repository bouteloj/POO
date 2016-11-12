package events 

public class VerserEau extends Evenement{
	private Incendie incendie;
	private LinkedList<Incendie> listeIncendies;
	private Robot robot;
	private Simulateur simul;
	
	// On donne la date qui sera celle de fin de versement de l'eau 
	public VerserEau (long date, Incendie incendie, Robot robot, Simulateur simul){
		super(date);
		this.incendie=incendie;
		this.robot=robot;
		this.listeIncendies = listeDIncendies;
		this.simul=simul;
	}
	
	@Override
	public void execute(){
		int e;
		e= this.robot.quantiteIntervention();
		
		if (incendie.getVerser() > 0) {
            this.robot.deverserEau(e);
			incendie.Verser(e);
		} else {
			System.out.println(robot.getClass() + "vidé");
			
		}

		if (incendie.getVerser() <= 0) {
			listeIncendies.remove(incendie);
		}
		
	}
		//if ((this.incentie.getVerser != 0 ) && (robot.deverserEau(incendie))){
			//simul.ajouteEvenement(new VerserEau(date + robot.tempsIntervention, incendie, robot, simul));
		}
		
		else  {
			//TODO Remplir lors de la prise de dŽcision
		}
	}
}

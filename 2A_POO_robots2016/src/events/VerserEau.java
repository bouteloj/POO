package events 

public class VerserEau extends Evenement{
	private Incendie incendie;
	
	private Robot robot;
	private Simulateur simul;
	
	public VerserEau (long date, Incendie incendie, Robot robot, Simulateur simul){
		super(date);
		this.incendie=incendie;
		this.robot=robot;
	
		this.simul=simul;
	}
	
	@Override
	public void execute(){
	
	
		if ((this.incentie.getVerser != 0 ) && (robot.deverserEau(incendie))){
			if (incendie.getVerser() <= 0) {
				simul.data.incendies.remove(incendie);
			}else{
				simul.ajouteEvenement(new VerserEau(date + robot.tempsIntervention, incendie, robot, simul));
			}
		
		}else  {
			
			//TODO Remplir lors de la prise de dŽcision
		}
	}
}

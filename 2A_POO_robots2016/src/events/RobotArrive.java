package events; 

public class RobotArrive extends Evenement{
	
	
	public RobotArrive(long date) {
		super(date);
	}

	@Override
	public boolean execute(){
		System.out.println("Le robot est arrive");
		System.out.println("Il va pouvoir commencer sa tache");
		return true;
	}
}

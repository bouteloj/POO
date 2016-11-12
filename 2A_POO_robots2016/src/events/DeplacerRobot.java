package events;

import src.Case;
import src.Robot;

public class DeplacerRobot extends Evenement{
	private Robot rob;
	private Case dest;
	
	
	public DeplacerRobot(long date) {
		super(date);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean execute(){
		rob.setPosition(dest);
		if (rob.getDestination(). ==0)
		return true;
		
		
		
	}

}

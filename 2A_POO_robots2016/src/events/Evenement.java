package events;

public abstract class Evenement {
	long date; //Date de la fin de l'evenement;

	//gett/set
	public long getDate(){
		return this.date;
	}

	public Evenement(long date){
		this.date=date;
	}
	
	/*
	 * est appelee lorsque date==simulateur.time
	 * execute les actions liées a l'evenement
	 */
	public abstract boolean execute();
	
}

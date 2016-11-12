package events;

public abstract class Evenement {
	long date;

	public long getDate(){
		return this.date;
	}

	public Evenement(long date){
		this.date=date;
	}
	
	public abstract boolean execute();
	
}

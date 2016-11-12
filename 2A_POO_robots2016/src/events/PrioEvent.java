package events;

import java.util.Comparator;


public class PrioEvent implements Comparator<Evenement>{

	@Override
	public int compare(Evenement o1, Evenement o2) {
		return (int) (o1.getDate()-o2.getDate());
	}

	

}

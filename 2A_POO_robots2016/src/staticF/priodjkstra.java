package staticF;

import java.util.Comparator;

public class priodjkstra implements Comparator<CasePourDijkstra>{

	@Override
	public int compare(CasePourDijkstra arg0, CasePourDijkstra arg1) {
		return (int) (arg0.longueurTrajet-arg1.longueurTrajet);
	}

}

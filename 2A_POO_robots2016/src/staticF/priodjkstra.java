package staticF;

import java.util.Comparator;

/*
 * Comparateur de la file de priorite des noeuds a traiter par l'algorithme de Dijkstra
 */
public class priodjkstra implements Comparator<CasePourDijkstra>{

	@Override
	public int compare(CasePourDijkstra arg0, CasePourDijkstra arg1) {
		return (int) (arg0.longueurTrajet-arg1.longueurTrajet);
	}

}

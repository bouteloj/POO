package staticF;

/*
 * sert de noeuds dans le graphe utilisé par l'algorithme de Dijkstra
 * les arettes sont representees par un couple de cases voisines.
 */
public class CasePourDijkstra {
	int l,c;
	public int longueurTrajet;
	public CasePourDijkstra incidente;
	public boolean traite;
	
	public CasePourDijkstra(int l, int c){
		longueurTrajet=Integer.MAX_VALUE;
		incidente=null;
		traite=false;
		this.l=l;
		this.c=c;
	}

}

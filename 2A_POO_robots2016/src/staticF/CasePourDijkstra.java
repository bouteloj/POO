package staticF;

public class CasePourDijkstra {
	int l,c;
	public int longueurTrajet;
	public CasePourDijkstra incidente;
	public boolean traité;
	
	public CasePourDijkstra(int l, int c){
		longueurTrajet=Integer.MAX_VALUE;
		incidente=null;
		traité=false;
		this.l=l;
		this.c=c;
	}

}

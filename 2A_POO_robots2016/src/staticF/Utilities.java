package staticF;

import java.util.LinkedList;

import robots.Robot;
import simulateur.Simulateur;
import src.Case;
import src.Direction;
import java.util.Iterator;

public class Utilities {
	
	
	
	public static LinkedList<Direction> dijkstra(Simulateur simul, Robot rob, Case dest){
		//init graph
		CasePourDijkstra[][] graph= new CasePourDijkstra[simul.data.map.getNbLignes()][simul.data.map.getNbColonnes()];
		for (int i=0; i<simul.data.map.getNbLignes(); i++){
			for (int j=0; j<simul.data.map.getNbColonnes(); j++){
				graph[i][j]=new CasePourDijkstra();
			}
		}
		//init listes
		LinkedList<CasePourDijkstra> aTtraiter=new LinkedList<CasePourDijkstra>();
		LinkedList<CasePourDijkstra> traitees=new LinkedList<CasePourDijkstra>();
		//init case depart + sauvegarde case arrivee
		graph[rob.getPosition().getLigne()][rob.getPosition().getColonne()].longueurTrajet=0;
		CasePourDijkstra arrivee=graph[dest.getLigne()][dest.getColonne()];
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static double poids(Simulateur simul, Robot rob, Case c, LinkedList<Direction> list){
		Iterator<Direction> itr = list.iterator();
		Case c2 = simul.data.map.getVoisin(c, list.getFirst());
		double p = rob.getTempsDeplacement( c, c2, simul.data.map.getTailleCases());
		while(itr.hasNext()){
			c = c2;
			c2 = simul.data.map.getVoisin(c, itr.next());
			p += rob.getTempsDeplacement( c, c2, simul.data.map.getTailleCases());
		}
		return p;
	}
	
	

	
	public static LinkedList<Direction> plusCourtChemin(Simulateur simul, Robot rob, Case c1, Case c2){
		LinkedList<Direction> returnList=new LinkedList<Direction>();
		
		
		if (simul.data.map.estVoisin(c1, c2)){
			returnList.add(simul.data.map.directionVoisin(c1,c2));
			return returnList;
		} else {
			LinkedList<Direction> tempNord=new LinkedList<Direction>();
			LinkedList<Direction> tempSud=new LinkedList<Direction>();
			LinkedList<Direction> tempEst=new LinkedList<Direction>();
			LinkedList<Direction> tempOuest=new LinkedList<Direction>();
			int Nord, Sud, Est, Ouest, compar;
			if (simul.data.map.voisinExiste(c1,Direction.NORD)){
				tempNord=plusCourtChemin(simul, rob, simul.data.map.getVoisin(c1,Direction.NORD),c2);
				Nord=poids(simul,rob,tempNord);
			} else {
				Nord=Integer.MAX_VALUE;
			}
				
			if (simul.data.map.voisinExiste(c1,Direction.SUD)){
				tempSud=plusCourtChemin(simul, rob, simul.data.map.getVoisin(c1,Direction.SUD),c2);
				Sud=poids(simul,rob,tempSud);
			} else {
				Sud=Integer.MAX_VALUE;
			}
			
			if (simul.data.map.voisinExiste(c1,Direction.EST)){
				tempEst=plusCourtChemin(simul, rob, simul.data.map.getVoisin(c1,Direction.EST),c2);
				Est=poids(simul,rob,tempNord);
			} else {
				Est=Integer.MAX_VALUE;
			}
			
			if (simul.data.map.voisinExiste(c1,Direction.OUEST)){
				tempOuest=plusCourtChemin(simul, rob, simul.data.map.getVoisin(c1,Direction.OUEST),c2);
				Ouest=poids(simul,rob,tempNord);
			} else {
				Ouest=Integer.MAX_VALUE;
			}
			
			compar=java.lang.Math.min(java.lang.Math.min(Nord,Sud),java.lang.Math.min(Est,Ouest));
				
			if (compar==Integer.MAX_VALUE){
				return returnList;
			}
				if (compar==Nord){
					tempNord.addFirst(Direction.NORD);
					returnList=tempNord;
				}
				if (compar==Sud){
					tempSud.addFirst(Direction.SUD);
					returnList=tempSud;
				}
				if (compar==Est){
					tempEst.addFirst(Direction.EST);
					returnList=tempEst;
				}
				if (compar==Ouest){
					tempOuest.addFirst(Direction.OUEST);
					returnList=tempOuest;
				}
				
				return returnList;
		}
			
	}
	
}

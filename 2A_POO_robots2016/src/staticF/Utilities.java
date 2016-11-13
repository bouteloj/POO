package staticF;

import java.util.LinkedList;

import robots.Robot;
import simulateur.Simulateur;
import src.Case;
import src.Direction;

public class Utilities {
	
	private static int poids(Simulateur simul, Robot rob, LinkedList<Direction> list){
		
		
		return 0;
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
				int Nord, Sud, Est, Ouest;
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
				
				switch (java.lang.Math.min(java.lang.Math.min(Nord,Sud),java.lang.Math.min(Est,Ouest))){
				case Nord:
						tempNord.addFirst(Direction.NORD);
						return tempNord
					break;
				case Sud:
					
					break;
				case Est:
					
					break;
				case Ouest:
					
					break;
				default:
						
				
				}
				
			}
		
		
			
		return returnlist;
			
	}
	
}

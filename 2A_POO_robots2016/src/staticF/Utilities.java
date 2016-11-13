package staticF;

import java.util.LinkedList;
import java.util.PriorityQueue;

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
				graph[i][j]=new CasePourDijkstra(i,j);
			}
		}
		boolean trouve=false;
		//init file de prio
		PriorityQueue<CasePourDijkstra> aTraiter=new PriorityQueue<CasePourDijkstra>(1,new priodjkstra());
		//init case depart + sauvegarde case arrivee
		graph[rob.getPosition().getLigne()][rob.getPosition().getColonne()].longueurTrajet=0;
		aTraiter.add(graph[rob.getPosition().getLigne()][rob.getPosition().getColonne()]);
		CasePourDijkstra arrivee=graph[dest.getLigne()][dest.getColonne()];
		
		
		CasePourDijkstra courant=null;
		while(aTraiter.size()!=0 && !trouve){
			courant=aTraiter.poll();
			if(courant.equals(arrivee)){
				trouve=true;
			}
			if (courant.l>0){
				if (!graph[courant.l-1][courant.c].traite){
					graph[courant.l-1][courant.c].longueurTrajet=(int) (courant.longueurTrajet+
							rob.getTempsDeplacement(simul.data.map.getCase(courant.l, courant.c),
									simul.data.map.getCase(courant.l-1, courant.c),
									simul.data.map.getTailleCases()));
					graph[courant.l-1][courant.c].incidente=courant;
					aTraiter.add(graph[courant.l-1][courant.c]);
				}
			}
			
			if (courant.l<simul.data.map.getNbLignes()-1){
				if (!graph[courant.l+1][courant.c].traite){
					graph[courant.l+1][courant.c].longueurTrajet=(int) (courant.longueurTrajet+
							rob.getTempsDeplacement(simul.data.map.getCase(courant.l, courant.c),
									simul.data.map.getCase(courant.l+1, courant.c),
									simul.data.map.getTailleCases()));
					graph[courant.l+1][courant.c].incidente=courant;
					aTraiter.add(graph[courant.l+1][courant.c]);
				}
			}
			
			if (courant.c>0){
				if (!graph[courant.l][courant.c-1].traite){
					graph[courant.l][courant.c-1].longueurTrajet=(int) (courant.longueurTrajet+
							rob.getTempsDeplacement(simul.data.map.getCase(courant.l, courant.c),
									simul.data.map.getCase(courant.l, courant.c-1),
									simul.data.map.getTailleCases()));
					graph[courant.l][courant.c-1].incidente=courant;
					aTraiter.add(graph[courant.l][courant.c-1]);
				}
			}
			if (courant.c<simul.data.map.getNbColonnes()-1){
				if (!graph[courant.l][courant.c+1].traite){
					graph[courant.l][courant.c+1].longueurTrajet=(int) (courant.longueurTrajet+
							rob.getTempsDeplacement(simul.data.map.getCase(courant.l, courant.c),
									simul.data.map.getCase(courant.l, courant.c+1),
									simul.data.map.getTailleCases()));
					graph[courant.l][courant.c+1].incidente=courant;
					aTraiter.add(graph[courant.l][courant.c+1]);
				}
			}
			courant.traite=true;
		}
		
		if (courant!=arrivee){
			return null;
		}
		
		LinkedList<Direction> resultat=new LinkedList<Direction>();
		while (courant.incidente!=null){
			resultat.addFirst(case2Dir(courant.incidente,courant));
			courant=courant.incidente;
		}
		return resultat;
		
	}

	//direction pour aller de c1 a c2
	private static Direction case2Dir(CasePourDijkstra c1,CasePourDijkstra c2){
		if (c1.l-c2.l==1){
			return Direction.NORD;
		}
		if (c1.l-c2.l==-1){
			return Direction.SUD;
		}
		if (c1.c-c2.c==1){
			return Direction.OUEST;
		}
		if (c1.c-c2.c==-1){
			return Direction.EST;
		}
		
		return null;//only accessible in error case

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*
	
	
	
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
	*/
}

package staticF;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;

import robots.Robot;
import simulateur.Simulateur;
import src.Case;
import src.Direction;

public class Utilities {
	
	
	/*
	 * Algorithme du plus court chemin de Dijkstra
	 * Renvoie la liste des directions des deplacements elementaires du robot vers sa destination
	 * @param simul: simulateur
	 * @param rob: robot a deplacer;
	 * @param dest: case de destination;
	 */
	
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
		
		//calcul iteratif des plus courts chemins
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
		
		if (courant!=arrivee || courant.longueurTrajet==Integer.MAX_VALUE){
			return null;
		}
		
		//Remontee du graphe et generation de la liste resultat;
		LinkedList<Direction> resultat=new LinkedList<Direction>();
		while (courant.incidente!=null){
			resultat.addFirst(case2Dir(courant.incidente,courant));
			courant=courant.incidente;
		}
		return resultat;
		
	}

	/*
	 * direction pour aller de c1 a c2
	 */
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
	 * temps nessecaire au robot rob pour parcourir la liste de 
	 * 		deplacements elementaires partant de la case c
	 */
	public static double poids(Simulateur simul, Robot rob, Case c, LinkedList<Direction> list){
		if(list==null){
			return Double.MAX_VALUE;
		}
		if(list.size()==0){
			return 0;
		}
		Iterator<Direction> itr = list.iterator();
		Case c2 = simul.data.map.getVoisin(c, itr.next());
		double p = rob.getTempsDeplacement( c, c2, simul.data.map.getTailleCases());
		while(itr.hasNext()){
			Case c1 = c2;
			c2 = simul.data.map.getVoisin(c1, itr.next());
			p += rob.getTempsDeplacement( c1, c2, simul.data.map.getTailleCases());
		}
		return p;
	}
	
}

package src;

import java.util.LinkedList;


public class Carte {
	private int nbLignes;
	private int nbColonnes;
	public Case map[][] ;
	private int tailleCases;
	public LinkedList<Case> ListeEau; //liste des cases d'eau presentes sur la carte
	public LinkedList<Case> ListeVoisinEau;//liste des cases voisines d'une case d'eau presentes sur la carte

	public int getNbLignes(){
		return this.nbLignes;
	}
	public int getNbColonnes(){
		return this.nbColonnes;
	}
	public int getTailleCases(){
		return this.tailleCases;
	}
	public Case getCase(int x, int y){
		return map[x][y];
	}


	public void setNbLignes(int nb){
		this.nbLignes=nb;
	}
	public void setNbColonnes(int nb){
		this.nbColonnes=nb;
	}
	public void setTailleCases(int taille){
		this.tailleCases=taille;
	}
	
	/*
	 * return true ssi la case c a au moins une voisine de nature EAU
	 */
	public boolean unVoisinEau(Case c) {
		boolean retour=false;
		if (this.voisinExiste(c, Direction.NORD)){
			if (this.getVoisin(c, Direction.NORD).getNature() == NatureTerrain.EAU){
				retour=true;
			}
		}
		if (this.voisinExiste(c, Direction.SUD)){
			if (this.getVoisin(c, Direction.SUD).getNature() == NatureTerrain.EAU){
				retour=true;
			}
		}
		if (this.voisinExiste(c, Direction.EST)){
			if (this.getVoisin(c, Direction.EST).getNature() == NatureTerrain.EAU){
				retour=true;
			}
		}
		if (this.voisinExiste(c, Direction.OUEST)){
			if (this.getVoisin(c, Direction.OUEST).getNature() == NatureTerrain.EAU){
				retour=true;
			}
		}
		
		
		return retour;
		
	}

	/*
	 *return true ssi la case pos n'est pas sur le bord 'dir' de la carte 
	 */
	public boolean voisinExiste(Case pos, Direction dir){
		switch (dir) {
		case EST:
			return (pos.getColonne()<this.nbColonnes-1);
		case NORD:
			return (pos.getLigne()>0);
		case OUEST:
			return (pos.getColonne()>0);
		case SUD:
			return (pos.getLigne()<this.nbLignes-1);
		default:
			return false;
		
		}
	}
	
	/*
	 * renvoie la case voisine de pos dans la direction dir
	 * Warning: requiert voisinExiste(pos,dir)
	 */
	public Case getVoisin(Case pos, Direction dir){
		switch (dir){
		case EST:
			return map[pos.getLigne()][pos.getColonne()+1];
		case NORD:
			return map[pos.getLigne()-1][pos.getColonne()];
		case OUEST:
			return map[pos.getLigne()][pos.getColonne()-1];
		case SUD:
			return map[pos.getLigne()+1][pos.getColonne()];
		default:
			return new Case(0, 0);		//unreachable
		}
	}
	
	/*
	 * renvoie true ssi c1 et c2 sont voisines.
	 */
	public boolean estVoisin(Case c1, Case c2){
		return (((c1.getLigne()-c2.getLigne()==1 || c1.getLigne()-c2.getLigne()==-1) && c1.getColonne()==c2.getColonne())
				|| ((c1.getColonne()-c2.getColonne()==1 || c1.getColonne()-c2.getColonne()==-1) && c1.getLigne()==c2.getLigne()));
	}
	
	
	/*
	 * renvoie la direction a prendre pour aller de c1 a c2
	 * requiert c1 voisin de c2
	 */
	public Direction directionVoisin(Case c1, Case c2){
		if(c1.getLigne()-c2.getLigne()==1){
			return Direction.NORD;
		}
		if(c1.getLigne()-c2.getLigne()==-1){
			return Direction.SUD;
		}
		if(c1.getColonne()-c2.getColonne()==-1){
			return Direction.EST;
		}
		if(c1.getColonne()-c2.getColonne()==1){
			return Direction.OUEST;
		}
		return null;//reacheable only in error case
	}
}

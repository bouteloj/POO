package src;


public class Carte {
	private int nbLignes;
	private int nbColonnes;
	public Case map[][] ;
	private int tailleCases;
	
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

	public boolean unVoisinEau(Case c) {
		if((this.voisinExiste(c, Direction.NORD) && this.getVoisin(c, Direction.NORD).getNature() == NatureTerrain.EAU)
			|| (this.voisinExiste(c, Direction.SUD) && this.getVoisin(c, Direction.SUD).getNature() == NatureTerrain.EAU)
			|| (this.voisinExiste(c, Direction.EST) && this.getVoisin(c, Direction.EST).getNature() == NatureTerrain.EAU)
			|| (this.voisinExiste(c, Direction.OUEST)  && this.getVoisin(c, Direction.OUEST).getNature() == NatureTerrain.EAU)
			|| (c.getNature() == NatureTerrain.EAU)){
				return true;
		} else {
			return false;
		}
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
	

	public boolean voisinExiste(Case pos, Direction dir){
		switch (dir) {
		case EST:
			return (pos.getColonne()<this.nbColonnes-1);
		case NORD:
			return (pos.getLigne()<this.nbLignes-1);
		case OUEST:
			return (pos.getColonne()>0);
		case SUD:
			return (pos.getLigne()>0);
		default:
			return false;
		
		}
	}
	
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

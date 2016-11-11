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
			return map[pos.getLigne()+1][pos.getColonne()];
		case OUEST:
			return map[pos.getLigne()][pos.getColonne()-1];
		case SUD:
			return map[pos.getLigne()-1][pos.getColonne()];
		default:
			return new Case(0, 0);		//unreachable
		}
	}
}

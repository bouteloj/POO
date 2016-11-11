package src;


public class Case {
	private int ligne;
	private int colonne;
	private NatureTerrain nature;
	
	public Case(int l, int c, NatureTerrain n){
		this.colonne=c;
		this.ligne=l;
		this.nature=n;
	}
	
	public int getLigne(){
		return this.ligne;
	}
	public int getColonne(){
		return this.colonne;
	}
	
	public NatureTerrain getNature(){
		return this.nature;
	}

}

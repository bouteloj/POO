package src;


public class Case {
	private int ligne;
	private int colonne;
	private NatureTerrain nature;
	
	public Case(int l, int c){
		this.colonne=c;
		this.ligne=l;
	}
	
	public void setNature(NatureTerrain nat){
		this.nature=nat;
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

package src;


public class Incendie {
	public Case position;
	private int litresAVerser;// nombre de litres d'eau restant a verser pour eteindre l'incendie
	private boolean estAssigne=false; //true ssi un robot a ete assigné a cet incendi
	
	public Incendie(Case pos,int lit){
		this.litresAVerser=lit;
		this.position=pos;
	}
	
	public Case getPosition(){
		return this.position;
	}
	public void setPosition(Case pos){
		this.position = pos;
	}
	public int getVerser(){
		return this.litresAVerser;
	}
	
	public void setAssigne(boolean b){
		this.estAssigne=b;
	}
	
	public boolean getAssigne(){
		return this.estAssigne;
	}
	
	/*
	 * repercute le versement de 'verse' litres d'eau sur l'incendie
	 */
	public int verser(int verse){
		if (this.litresAVerser > verse){
			this.litresAVerser-= verse;
		}else{
			this.litresAVerser=0;
			System.out.println("incendie éteint");
		}
		return this.litresAVerser;
	}

}

package src;


public class Incendie {
	public Case position;
	private int litresAVerser;
	
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

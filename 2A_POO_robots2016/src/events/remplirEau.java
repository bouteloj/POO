package events;
public class remplirEau extends Evenement{
		private Carte carte;
		private Robot robot;

		public remplirEau(long date, Carte carte, Robot robot){
			super(date);
			this.carte = carte;
			this.robot = robot;
		}
        @Override
		public void execute(){
			if (robot.getCapacite() < robot.getCapaciteMax()) {
				this.robot.remplirEau(carte);
	        }

			else {
				System.out.println(robot.getClass() + "plein et pret");
			}
		}
	}
	

}

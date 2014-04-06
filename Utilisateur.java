import java.util.LinkedList;


public class Utilisateur {

	int num;
	int poids;
	LinkedList<Arete> route;
	int latence;
	
	public Utilisateur(int num, int poids) {
		
		this.num = num;
		this.poids = poids;
		
		this.latence = 0;
		
	}
	
}

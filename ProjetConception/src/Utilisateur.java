import java.util.Iterator;
import java.util.LinkedList;


public class Utilisateur {

	int num;
	double poids;
	LinkedList<Arete> route;
	LinkedList<Arete> meilleureRoute;
	double latence;
	double meilleureLatence;
	
	public Utilisateur(int num, double poids) {
		
		this.num = num;
		this.poids = poids;
		this.latence = 0;
		
	}
	
	public String toString() {
		
		return "Utilisateur " + this.num + " : " + this.meilleureRoute.toString();
		
	}
	
	
}

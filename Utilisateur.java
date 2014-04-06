import java.util.LinkedList;


public class Utilisateur {

	int num;
	int poids;
	LinkedList<Arete> route;
	int latence;
	Graphe graphe;
	
	public Utilisateur(int num, int poids, Graphe graphe) {
		
		this.num = num;
		this.poids = poids;

		this.latence = 0;
		this.graphe = graphe;
		
	}
	
}

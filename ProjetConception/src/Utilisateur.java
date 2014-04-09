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
		
		String chemin = "Utilisateur " + this.num + " : 1";
		int numPrec = 1;
		boolean erreur = false;
		for (Iterator<Arete> i = this.route.iterator(); i.hasNext();) {
			Arete a = i.next();
			if (a.noeudDep.num == numPrec) {
				if (numPrec != 1)
					chemin = chemin + "->" + numPrec;
				numPrec = a.noeudFin.num;
			}
			else
				erreur = true;
		}
		if (erreur)
			return "Utilisateur " + this.num + " : Erreur";
		else
			return chemin;
		
	}
	
	
}

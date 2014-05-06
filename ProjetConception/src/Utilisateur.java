import java.util.Iterator;
import java.util.LinkedList;


public class Utilisateur {

	int num; // identifiant
	double poids;
	LinkedList<Arete> route; // route actuelle de l'utilisateur
	LinkedList<Arete> meilleureRoute; // sauvegarde de la route de l'utilisateur dans la meilleure situation 
	double latence;
	double meilleureLatence; // sauvegarde de la latence de l'utilisateur dans la meilleure situation
	
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
	
	// Méthode attribuant aléatoirement une route à l'utilisateur
	public void  attribuerRouteAleatoire(Routage routage){
		
		LinkedList<Arete> route=new LinkedList<Arete>();
		int num=1;
		for (int i=1 ; i<=routage.graphe.n+1 ; i++){
			
			LinkedList<Arete> cheminsPossibles = routage.graphe.aretesCommencantParNoeud(num);
			Arete cheminChoisi = cheminsPossibles.get((int) (Math.random()*cheminsPossibles.size()));
			num = cheminChoisi.noeudFin.num;
			route.add(cheminChoisi);
			cheminChoisi.cout += this.poids;
			
		}
		this.route=route;
	}
	
	// Calcul de la latence de l'utilisateur (somme des coûts des arêtes empruntées)
	public void calculerLatence(){
		int latence=0;
		for (Iterator<Arete> i = this.route.iterator(); i.hasNext();){
			Arete arete = i.next();
			latence += arete.cout;
		}
		this.latence = latence;
	}
	
	// Modification de la route de l'utilisateur en effectuant une déviation (un noeud est remplacé par un autre)
	public void changerChemin(Modification modif) {
		
		// Mise à jour des coûts des arêtes concernées
		modif.areteSup1.cout -= this.poids;
		modif.areteSup2.cout -= this.poids;
		modif.areteAj1.cout += this.poids;
		modif.areteAj2.cout += this.poids;
		
		// Suppression des anciennes arêtes et attribution des nouvelles
		int i = this.route.indexOf(modif.areteSup1);
		this.route.remove(modif.areteSup1);
		this.route.add(i, modif.areteAj1);
		
		i = this.route.indexOf(modif.areteSup2);
		this.route.remove(modif.areteSup2);
		this.route.add(i, modif.areteAj2);
		
	}
	
}

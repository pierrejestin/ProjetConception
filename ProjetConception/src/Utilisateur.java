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
	
	public void calculerLatence(){
		int latence=0;
		for (Iterator<Arete> i = this.route.iterator(); i.hasNext();){
			Arete arete = i.next();
			latence += arete.cout;
		}
		this.latence = latence;
	}
	
	public void changerChemin(Modification modif) {
		
		modif.areteSup1.cout -= this.poids;
		modif.areteSup2.cout -= this.poids;
		modif.areteAj1.cout += this.poids;
		modif.areteAj2.cout += this.poids;
		int i = this.route.indexOf(modif.areteSup1);
		this.route.remove(modif.areteSup1);
		this.route.add(i, modif.areteAj1);
		i = this.route.indexOf(modif.areteSup2);
		this.route.remove(modif.areteSup2);
		this.route.add(i, modif.areteAj2);
		
	}
	
}

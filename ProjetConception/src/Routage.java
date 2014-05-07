import java.util.Iterator;
import java.util.LinkedList;

/*
 * Probl�me du routage
 * Cette classe impl�mente la classe abstraite Probleme
 */

public class Routage extends Probleme {

	// Param�tres du probl�me
	public LinkedList<Utilisateur> utilisateurs;
	public Graphe graphe;
	
	// Sauvegarde de la derni�re modification effectu�e
	public Modification derniereModif;
	
	
	public Routage(Graphe graphe, LinkedList<Utilisateur> utilisateurs, Energie E, Mutation mutation) {
		
		this.graphe = graphe;
		this.utilisateurs = utilisateurs;
		this.derniereModif = new Modification();
		this.E = E;
		this.mutation = mutation;
		
	}
	
	
	// Initialisation du probl�me: affectation de routes al�atoires
	public void initialiser(){
		
		this.graphe.initialiser();
		
		for(Iterator<Utilisateur> i = this.utilisateurs.iterator(); i.hasNext();){
			Utilisateur u = i.next();
			u.attribuerRouteAleatoire(this);
			u.meilleureRoute=new LinkedList<Arete>();
		}
	}
	

	// Sauvegarde du routage actuel dans une variable
	@SuppressWarnings("unchecked")
	public void sauvegarderSolution(){
		for(Iterator<Utilisateur> i = this.utilisateurs.iterator(); i.hasNext();){
			Utilisateur u = i.next();
			u.meilleureLatence=u.latence;
			u.meilleureRoute=(LinkedList<Arete>) u.route.clone();
		}
	}
	
}

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
	
	
	public Routage(Graphe graphe, LinkedList<Utilisateur> utilisateurs) {
		
		this.graphe = graphe;
		this.utilisateurs = utilisateurs;
		this.derniereModif = new Modification();
		
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
	
	
	// Calcule de la latence moyenne
	public double calculerEnergie() {
		
		double sommePoids = 0;
		double latenceTot=0.0;
		for(Iterator<Utilisateur> i = utilisateurs.iterator(); i.hasNext();){
			Utilisateur u = i.next();
			u.calculerLatence();
			latenceTot += u.poids * u.latence;
			sommePoids += u.poids;
		}
		return (latenceTot/sommePoids);
		
	}
	
	// D�termination al�atoire d'une mutation �l�mentaire � effectuer. Utilis�e dans modifElem()
	public Modification determinerModifElem(Utilisateur utilisateurModifie) {
		
		LinkedList<Arete> aretesPossibles=new LinkedList<Arete>();
		Arete areteSup1 = new Arete();
		Arete areteSup2 = new Arete();
		
		while(aretesPossibles.size()<=1){
			int index = (int) (Math.random()*this.graphe.n);
			areteSup1 = utilisateurModifie.route.get(index);
			areteSup2 = utilisateurModifie.route.get(index+1);
			Noeud noeudPrec = areteSup1.noeudDep;
			aretesPossibles = this.graphe.aretesCommencantParNoeud(noeudPrec.num);
		}
		
		aretesPossibles.remove(areteSup1);
		Arete areteAj1 = aretesPossibles.get((int) (Math.random()*aretesPossibles.size()));
		Arete areteAj2 = this.graphe.areteEntreNoeuds(areteAj1.noeudFin.num , areteSup2.noeudFin.num );
		
		return new Modification(areteSup1, areteSup2, areteAj1, areteAj2, utilisateurModifie);
		
	}
	
	
	// D�termination et ex�cution d'une mutation �l�mentaire
	public void modifElem() {

		Utilisateur utilisateurModifie = this.utilisateurs.get((int) (Math.random()*this.utilisateurs.size()));
		Modification modif = this.determinerModifElem(utilisateurModifie);
		this.derniereModif = modif;
		utilisateurModifie.changerChemin(modif);
		for (Iterator<Utilisateur> j = utilisateurs.iterator(); j.hasNext();) {
			j.next().calculerLatence();
		}
		
	}
	
	// Annulation de la derni�re mutation �l�mentaire effectu�e
	public void annulerModif() {
		
		Modification modif = this.derniereModif;
		modif = new Modification(modif.areteAj1, modif.areteAj2, modif.areteSup1, modif.areteSup2, modif.utilisateurModifie);
		modif.utilisateurModifie.changerChemin(modif);
		for (Iterator<Utilisateur> j = utilisateurs.iterator(); j.hasNext();) {
			j.next().calculerLatence();
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

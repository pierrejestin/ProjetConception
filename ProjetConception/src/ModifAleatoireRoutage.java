import java.util.Iterator;
import java.util.LinkedList;


public class ModifAleatoireRoutage implements Mutation {

	// Ex�cution de la modification al�atoire �l�mentaire sur le routage
	public void faire(Probleme probleme) {
		
		Routage routage = (Routage) probleme; 
		
		// D�termination al�atoire d'une mutation �l�mentaire � effectuer.
			Utilisateur utilisateurModifie = routage.utilisateurs.get((int) (Math.random()*routage.utilisateurs.size()));
			LinkedList<Arete> aretesPossibles=new LinkedList<Arete>();
			Arete areteSup1 = new Arete();
			Arete areteSup2 = new Arete();
			
			while(aretesPossibles.size()<=1){
				int index = (int) (Math.random()*routage.graphe.n);
				areteSup1 = utilisateurModifie.route.get(index);
				areteSup2 = utilisateurModifie.route.get(index+1);
				Noeud noeudPrec = areteSup1.noeudDep;
				aretesPossibles = routage.graphe.aretesCommencantParNoeud(noeudPrec.num);
			}
			
			aretesPossibles.remove(areteSup1);
			Arete areteAj1 = aretesPossibles.get((int) (Math.random()*aretesPossibles.size()));
			Arete areteAj2 = routage.graphe.areteEntreNoeuds(areteAj1.noeudFin.num , areteSup2.noeudFin.num );
			
			Modification modif = new Modification(areteSup1, areteSup2, areteAj1, areteAj2, utilisateurModifie);
		
		// Ex�cution de la mutation �l�mentaire			
			routage.derniereModif = modif;
			utilisateurModifie.changerChemin(modif);
			for (Iterator<Utilisateur> j = routage.utilisateurs.iterator(); j.hasNext();) {
				j.next().calculerLatence();
			}
			
		}
		

	// Annulation de la derni�re mutation �l�mentaire effectu�e
	public void defaire(Probleme probleme) {
		
		Routage routage = (Routage) probleme; 
		Modification modif = routage.derniereModif;
		modif = new Modification(modif.areteAj1, modif.areteAj2, modif.areteSup1, modif.areteSup2, modif.utilisateurModifie);
		modif.utilisateurModifie.changerChemin(modif);
		for (Iterator<Utilisateur> j = routage.utilisateurs.iterator(); j.hasNext();) {
			j.next().calculerLatence();
		}
		
	}





	
	
}

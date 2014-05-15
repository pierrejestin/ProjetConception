import java.util.Iterator;
import java.util.Set;


public class MutationAleatoireRoutage implements IMutation {

	// Ex�cution de la modification al�atoire �l�mentaire sur le routage
	public void faire(Probleme probleme) {
		
		Routage routage = (Routage) probleme; 
		
		// D�termination al�atoire d'une mutation �l�mentaire � effectuer.
			Utilisateur utilisateurModifie = routage.utilisateurs.get((int) (Math.random()*routage.utilisateurs.size()));
			int tailleRoute = utilisateurModifie.route.size();
			Noeud noeudAj = null;
			Noeud noeudSup = null;
			Boolean modifPossible=false;
			while (!modifPossible){
				int random = (int) (Math.random()*(tailleRoute-1)) + 1;
				noeudSup = utilisateurModifie.route.get(random);
								
				Noeud noeudPrec = utilisateurModifie.route.get(random-1);
				Set<Noeud> noeudsPossibles = noeudPrec.couts.keySet();
				noeudAj = noeudSup;
				if (noeudsPossibles.size()>=2){
					while (noeudAj.equals(noeudSup)){
						random = (int) (Math.random()*noeudsPossibles.size())+1;
						int compteur = 1;
						Iterator<Noeud> it = noeudsPossibles.iterator();
						while(it.hasNext()){
						  Noeud v = it.next();
						  if (compteur==random){
							  noeudAj = v;
						  }
						  compteur++;
						}
						modifPossible=true;
					}
				}
			}
			Modification modif = new Modification(noeudSup, noeudAj, utilisateurModifie);
		
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
		modif = new Modification(modif.noeudAj, modif.noeudSup, modif.utilisateurModifie);
		modif.utilisateurModifie.changerChemin(modif);
		for (Iterator<Utilisateur> j = routage.utilisateurs.iterator(); j.hasNext();) {
			j.next().calculerLatence();
		}
		
	}





	
	
}

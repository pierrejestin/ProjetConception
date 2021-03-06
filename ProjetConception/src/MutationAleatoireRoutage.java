import java.util.Iterator;
import java.util.Set;

// Impl�mentation de l'interface IMutation sous la forme d'une mutation al�atoire du routage

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
				int rnd = (int) (Math.random()*(tailleRoute-1)) + 1;
				noeudSup = utilisateurModifie.route.get(rnd);
								
				Noeud noeudPrec = utilisateurModifie.route.get(rnd-1);
				Set<Noeud> noeudsPossibles = noeudPrec.couts.keySet();
				noeudAj = noeudSup;
				if (noeudsPossibles.size()>=2){
					while (noeudAj.equals(noeudSup)){
						rnd = (int) (Math.random()*noeudsPossibles.size())+1;
						int compteur = 1;
						Iterator<Noeud> it = noeudsPossibles.iterator();
						while(it.hasNext()){
						  Noeud v = it.next();
						  if (compteur==rnd){
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

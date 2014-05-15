/* Une modification est une mutation �l�mentaire de la situation du probl�me
 * Un noeud de la route de l'utilisateur est remplac� par un autre, ce qui revient
 * � supprimer 2 ar�tes cons�cutives de sa route et en rajouter 2 autres 
 */
public class Modification {

	public Utilisateur utilisateurModifie;
	// Noeud supprim� de la route
	public Noeud noeudSup;
	// Noeud ajout� � la route
	public Noeud noeudAj;
	
	public Modification(Noeud noeudSup,Noeud noeudAj,Utilisateur utilisateurModifie) {
		
		this.noeudSup = noeudSup;
		this.noeudAj = noeudAj;
		this.utilisateurModifie = utilisateurModifie;
		
	}

}

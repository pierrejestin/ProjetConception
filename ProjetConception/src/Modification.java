/* Une modification est une mutation �l�mentaire de la situation du probl�me
 * Un noeud de la route de l'utilisateur est remplac� par un autre, ce qui revient
 * � supprimer 2 ar�tes cons�cutives de sa route et en rajouter 2 autres 
 */
public class Modification {

	public Utilisateur utilisateurModifie;
	// Ar�tes supprim�es de la route
	public Arete areteSup1;
	public Arete areteSup2;
	// Ar�tes ajout�es � la route
	public Arete areteAj1;
	public Arete areteAj2;
	
	public Modification(Arete areteSup1, Arete areteSup2, Arete areteAj1, Arete areteAj2, Utilisateur utilisateurModifie) {
		
		this.areteSup1 = areteSup1;
		this.areteSup2 = areteSup2;
		this.areteAj1 = areteAj1;
		this.areteAj2 = areteAj2;
		this.utilisateurModifie = utilisateurModifie;
		
	}
	
	public Modification() {
		
		this.areteSup1 = null;
		this.areteSup2 = null;
		this.areteAj1 = null;
		this.areteAj2 = null;
		this.utilisateurModifie = null;
		
	}
}

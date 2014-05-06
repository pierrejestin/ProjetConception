
public class Modification {

	public Arete areteSup1;
	public Arete areteSup2;
	public Arete areteAj1;
	public Arete areteAj2;
	public Utilisateur utilisateurModifie;
	
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

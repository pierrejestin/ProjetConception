

public class Gradient {

	// Param�tres de l'algorithme du gradient
	int N; // nombre d'it�rations
	double energie;
	
	public Gradient(int N) {
		
		this.N=N;
		
	}
	
	public Probleme iterer(Probleme probleme) {
		
		// Initialisation
		// this.energie = probleme.calculerEnergie();
		// this.meilleureEnergie = this.energie;	
						
		// It�rations
		for(int j=1 ; j<=this.N ; j++) {
			
			// Mutation �l�mentaire
			probleme.modifElem();
			this.energie = probleme.calculerEnergie();
			// Impression de l'�nergie courante
			System.out.println("E = "+(double)((int)(this.energie*1000))/1000);
			
		}

		// Retour de la solution
		return probleme; 
	
	}
}



public class IterationRecuit {

	// Paramètres de l'algorithme de recuit
	int N;
	double T;
	double k;
	double TDeb;
	double TFin;
	double probaDeb;
	double probaFin;
	double energie;
	double meilleureEnergie;
	
	public IterationRecuit(int N) {
		
		this.probaDeb=0.5;
		this.probaFin=0.1;
		this.N=N;
		this.TDeb = -1/(Math.log(this.probaDeb));
		this.TFin = -1/(Math.log(this.probaFin));
		this.T=TDeb;
		this.k = 0;
		this.energie=0;
		
	}

	
	public Probleme iterer(Probleme probleme) {
		
		// Initialisation
		probleme.initialiser();
		this.energie = probleme.calculerEnergie();
		this.meilleureEnergie = this.energie;
		
		// Itérations
		for(int j=1 ; j<=this.N ; j++) {
			// Mutation élémentaire
			probleme.modifElem();
			double nouvelleEnergie = probleme.calculerEnergie();
			double probaAcceptation = Math.exp(-(nouvelleEnergie-this.energie)/this.k*this.T);
			
			// Examen de l'effet de la modification effectuée
			if (!(nouvelleEnergie<this.energie || Math.random() < probaAcceptation)) {
				probleme.annulerModif();
			}		
			else { 
				this.energie = nouvelleEnergie;
				if(nouvelleEnergie<this.meilleureEnergie) {
					this.meilleureEnergie=nouvelleEnergie;
					probleme.sauvegarderSolution();
				} 
			}
			
			// Mise à jour de la température
			this.T= this.T * Math.pow(this.TFin/this.TDeb,1/(N-1));
			
			// Mise à jour de k
			this.k = (j*this.k + this.energie)/(j+1);
			
			// Impression de la latence moyenne
			System.out.println(this.meilleureEnergie);
			
		}
		
		// Retour de la solution
		return probleme; 
	
	}

	
}



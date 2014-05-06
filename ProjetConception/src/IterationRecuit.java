

public class IterationRecuit {

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
		
		probleme.initialiser();
		this.energie = probleme.calculerEnergie();
		this.meilleureEnergie = this.energie;
		
		for(int j=1 ; j<=this.N ; j++) {
			probleme.modifElem();
			double nouvelleEnergie = probleme.calculerEnergie();
			double probaAcceptation = Math.exp(-(nouvelleEnergie-this.energie)/this.k*this.T);
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
			
			// Maj de la température
			this.T= this.T * Math.pow(this.TFin/this.TDeb,1/(N-1));
			
			// Maj de k
			this.k = (j*this.k + this.energie)/(j+1);
			
			// Print de la latence moyenne
			System.out.println(this.meilleureEnergie);
			
		}
		
		return probleme;
	
	}

	
}



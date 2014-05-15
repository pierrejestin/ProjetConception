
/* Premi�re version simple de l'algorithme de recuit
 * La temp�rature est abaiss�e entre chaque it�ration
 */

public class RecuitSimple {

	// Param�tres de l'algorithme de recuit
	int N;
	double T;
	double k;
	double TDeb;
	double TFin;
	double probaDeb;
	double probaFin;
	double energie;
	double meilleureEnergie;
	
	public RecuitSimple(int N) {
		
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
		
		// It�rations
		for(int j=1 ; j<=this.N ; j++) {
			// Mutation �l�mentaire
			probleme.modifElem();
			double nouvelleEnergie = probleme.calculerEnergie();
			double probaAcceptation = Math.exp(-(nouvelleEnergie-this.energie)/this.k*this.T);
			
			// Examen de l'effet de la modification effectu�e
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
			
			// Mise � jour de la temp�rature
			this.T= this.T * Math.pow(this.TFin/this.TDeb,1/(N-1));
			
			// Mise � jour de k
			this.k = (j*this.k + this.energie)/(j+1);
			this.k = this.meilleureEnergie/j;
			
			// Impression de l'�nergie courante et de la meilleure �nergie
	//		System.out.print("E = "+(double)((int)(this.energie*100))/100+"   ");
	//		System.out.println("Ebest = "+(double)((int)(this.meilleureEnergie*100))/100);

			
		}
		
		// Retour de la solution
		
		return probleme; 
	
	}

	
}



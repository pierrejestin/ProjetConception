/* Variante de l'algorithme de recuit
 * On proc�de par parlliers de temp�ratures ie on effectue un certain nombre (M) d'it�rations
 * � la m�me temp�rature avant de la modifier 
 */

public class RecuitParPalliers {

	// Param�tres de l'algorithme de recuit
	int N; // nombre de palliers
	int M; // nombre d'it�rations � chaque pallier
	double T;
	double k;
	double TDeb;
	double TFin;
	double probaDeb;
	double probaFin;
	double energie;
	double meilleureEnergie;
	
	public RecuitParPalliers(int N, int M) {
		
		this.probaDeb=0.5;
		this.probaFin=0.1;
		this.N=N;
		this.M=M;
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
		
		int compteur = 0; // Compte le nombre d'it�rations effectu�es � un pallier de temp�rature
		
		// It�rations
		for(int j=1 ; j<=this.N*this.M ; j++) {
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
			
			compteur++;			

			
			// Mise � jour de T et k au bout de M it�rations sur le m�me pallier
			if (compteur==this.M) {
			
				// Mise � jour de la temp�rature
				this.T= this.T * Math.pow(this.TFin/this.TDeb,1/(this.N-1));

				// Mise � jour de k
			//	this.k = (j/M*this.k + this.energie)/(j/M+1);
				this.k = this.meilleureEnergie/j;
				
				// Remise � z�ro du compteur
				compteur = 0;
			
			}
			
			// Impression de l'�nergie courante et de la meilleure �nergie
	//		System.out.print("E = "+(double)((int)(this.energie*100))/100+"   ");
	//		System.out.println("Ebest = "+(double)((int)(this.meilleureEnergie*100))/100);

			
		}
		
		// Retour de la solution
		return probleme; 
	
	}
}

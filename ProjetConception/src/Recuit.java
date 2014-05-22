/*
 * Variante de l'algorithme de recuit
 * On procède par parlliers de températures ie on effectue un certain nombre (M) d'itérations
 * à la même température avant de la modifier 
 */

public class Recuit {

	// Paramètres de l'algorithme de recuit
	int N; // nombre de palliers
	int M; // nombre d'itérations à chaque pallier
	double T;
	double k;
	double TDeb;
	double TFin;
	double probaDeb;
	double probaFin;
	double energie;
	double meilleureEnergie;
	
	public Recuit(int N, int M) {
		
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
		
		int compteur = 0; // Compte le nombre d'itérations effectuées à un pallier de température
		
		// Itérations
		for(int j=1 ; j<=this.N*this.M ; j++) {
			// Mutation élémentaire
			probleme.modifElem();
			double nouvelleEnergie = probleme.calculerEnergie();
			double probaAcceptation = Math.exp(-(nouvelleEnergie-this.energie)/(this.k*this.T));
			
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
			
			compteur++;			
			
		//	this.k = (j*this.k + this.energie)/(j+1);
			
			// Mise à jour de T et k au bout de M itérations sur le même pallier
			if (compteur==this.M) {
			
				// Mise à jour de la température
				//this.T= this.T * Math.pow(this.TFin/this.TDeb,1/(this.N-1));
				this.T = j/(j+1)*this.T * Math.pow(this.TFin/this.TDeb,1/(this.N-1));

				// Mise à jour de k				
			    this.k = this.meilleureEnergie;
				
				// Remise à zéro du compteur
				compteur = 0;
			
			}
			
			System.out.println((double)((int)(this.energie*1000))/1000);
			
			// Impression de l'énergie courante et de la meilleure énergie
	//		System.out.print("E = "+(double)((int)(this.energie*1000))/1000+"   ");
	//		System.out.println("Ebest = "+(double)((int)(this.meilleureEnergie*1000))/1000);

			
		}
		
		// Retour de la solution
		return probleme; 
	
	}
}

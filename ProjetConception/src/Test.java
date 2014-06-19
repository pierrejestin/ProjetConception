import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.Random;


public class Test {

	public static void main(String[] args) {
		
		// Paramètres
		int longueurGraphe = 10;
		int hauteurGraphe = 6;
		int nbUtilisateurs = 500;
		int nbIterations = 1; // K
		int N = 10000;
		int M = 1;

		
		// Seeds des générateurs aléatoires
		long seed1 = 156645125; 				// Seed pour les poids des utilisateurs
		long seed2 = 44656887;					// Seed pour l'initialisation
		Random random1 = new Random(seed1);
		Random random2 = new Random(seed2);
				
		// Création du graphe et des utilisateurs
		Graphe graphe = new Graphe(longueurGraphe,hauteurGraphe);
		
		LinkedList<Utilisateur> utilisateurs = new LinkedList<Utilisateur>();
		for (int i = 0; i < nbUtilisateurs; i++) {
			
		// Choisir une des populations possibles
			
			//utilisateurs.add(new Utilisateur(i+1, 0.5)); 
			
			//utilisateurs.add(new Utilisateur(i+1,((double)(i%5)/5.0)+0.1));
			
			utilisateurs.add(new Utilisateur(i+1,random1.nextDouble()));
			
			/*
			if (i<449) utilisateurs.add(new Utilisateur(i+1, 0.444 ) );
			else utilisateurs.add(new Utilisateur(i+1, 1 ) ); 
			 */
			

		}
		
		
		// Création des classes métier
		Routage routage = new Routage(graphe , utilisateurs, new LatenceMoyenne(), new MutationAleatoireRoutage());		
		routage.initialiser(random2);
		Recuit recuit1 = new Recuit(N,M);  // avec paliers
		Recuit recuit2 = new Recuit(N*M,1); // continu
		Recuit recuit3 = new Recuit(N,1); // avec réchauffement
		
	// Choisir une des expériences
		
		
		/* Itération multiple de l'algortihme de recuit simple + recuit par palliers + recuit avec réchauffement 
		 * pour les comparer */
		
		/* Simple */
		Routage routage1 = routage;
		System.out.println("EBestS");
		for (int j=0; j < nbIterations ; j++){  // K*N itérations au total
			routage1.initialiser(random2);
			recuit1.energie = routage1.calculerEnergie();
			recuit1.meilleureEnergie = recuit1.energie;
			routage1 = (Routage) recuit1.iterer(routage1);
			// Affichage de la solution trouvée par une itération de l'algorithme de recuit
			System.out.println(recuit1.meilleureEnergie); 
		}
		
		/* Avec paliers*/
		Routage routage2 = routage;
		System.out.println("EBestPP");
		for (int j=0; j < nbIterations ; j++){  // K*N itérations au total
			routage2.initialiser(random2);
			recuit2.energie = routage2.calculerEnergie();
			recuit2.meilleureEnergie = recuit2.energie;
			routage2 = (Routage) recuit2.iterer(routage2);
			// Affichage de la solution trouvée par une itération de l'algorithme de recuit
			System.out.println(recuit2.meilleureEnergie); 
		}
		
		
		/* Avec réchauffement
		Routage routage3 = routage;
		System.out.println("EBestR");			// Avec réchauffement
		for (int j=0; j < nbIterations ; j++){  // K*N itérations au total
			routage3.initialiser(random2);
			for (int l=0; l < M; l++) {
				routage3 = (Routage) recuit3.iterer(routage3);
			}
			// Affichage de la solution trouvée par une itération de l'algorithme de recuit
			System.out.println(recuit3.meilleureEnergie); 
		}
		*/
		
		
		
		/* Itérations multiples de l'algortihme de recuit simple pour faire des courbes moyennes
		
		for (int j=0; j < nbIterations ; j++){  // nbIterations*N itérations au total
			routage.initialiser(new Random(seed2));
			recuit1.energie = routage.calculerEnergie();
			recuit1.meilleureEnergie = recuit1.energie;
			routage = (Routage) recuit1.iterer(routage);
		}
		
			// Impression des energies moyennes pour chaque itération
			for (int i = 0; i < N*M; i++) {
				System.out.println((double)((int)(recuit1.energieMoy[i]/nbIterations*1000))/1000); 
	
			}
		
		*/
	}
	
}

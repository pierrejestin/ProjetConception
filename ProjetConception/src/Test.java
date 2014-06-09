import java.util.LinkedList;
import java.util.Random;


public class Test {

	public static void main(String[] args) {
		
		// Param�tres
		int longueurGraphe = 10;
		int hauteurGraphe = 6;
		int nbUtilisateurs = 200;
		int nbIterations = 1; // K
		int N = 100;
		int M = 1;

		
		// Seeds de l'al�atoire
		long seed1 = 156645125; 				// Seed pour les poids des utilisateurs
		long seed2 = 44656887;					// Seed pour l'initialisation
		Random random1 = new Random(seed1);
		Random random2 = new Random(seed2);

		// Cr�ation du graphe et des utilisateurs
		Graphe graphe = new Graphe(longueurGraphe,hauteurGraphe);
		
		LinkedList<Utilisateur> utilisateurs = new LinkedList<Utilisateur>();
		for (int i = 0; i < nbUtilisateurs; i++) {
			utilisateurs.add(new Utilisateur(i+1, random1.nextDouble()));
		}
		
		
		// Cr�ation des classes m�tier
		// Recuit
		Routage routage = new Routage(graphe , utilisateurs, new LatenceMoyenne(), new MutationAleatoireRoutage());		
		routage.initialiser(random2);
		Recuit recuit1 = new Recuit(N,M);
		Recuit recuit2 = new Recuit(N*M,1);
		Recuit recuit3 = new Recuit(N,1);
		
		// Gradient
		Routage routage4 = new Routage(graphe , utilisateurs, new LatenceMoyenne(), new MutationGradient());		
		routage.initialiser(random2);
		Gradient gradient = new Gradient(N*M);
		
		/* Tests pour Excel : graphes sur un recuit
		Routage solution = (Routage) recuit1.iterer(routage,random2);
		 */
		
		
		/* It�ration multiple de l'algortihme de recuit simple + recuit par palliers + recuit avec r�chauffement
		
		Routage routage1 = routage;
		System.out.println("EBestS");
		for (int j=0; j < nbIterations ; j++){  // K*N it�rations au total
			routage1.initialiser(random2);
			recuit1.energie = routage1.calculerEnergie();
			recuit1.meilleureEnergie = recuit1.energie;
			routage1 = (Routage) recuit1.iterer(routage1);
			// Affichage de la solution trouv�e par une it�ration de l'algorithme de recuit
			System.out.println(recuit1.meilleureEnergie); 
		}
		
		Routage routage2 = routage;
		System.out.println("EBestPP");
		for (int j=0; j < nbIterations ; j++){  // K*N it�rations au total
			routage2.initialiser(random2);
			recuit2.energie = routage2.calculerEnergie();
			recuit2.meilleureEnergie = recuit2.energie;
			routage2 = (Routage) recuit2.iterer(routage2);
			// Affichage de la solution trouv�e par une it�ration de l'algorithme de recuit
			System.out.println(recuit2.meilleureEnergie); 
		}
		
		Routage routage3 = routage;
		System.out.println("EBestR");			// Avec r�chauffement
		for (int j=0; j < nbIterations ; j++){  // K*N it�rations au total
			routage3.initialiser(random2);
			for (int l=0; l < M; l++) {
				routage3 = (Routage) recuit3.iterer(routage3);
			}
			// Affichage de la solution trouv�e par une it�ration de l'algorithme de recuit
			System.out.println(recuit3.meilleureEnergie); 
		}
		*/
		
		/* It�ration de l'algorithme du gradient */
		System.out.println("EBestG");
		for (int j=0; j < nbIterations ; j++){  // K*N it�rations au total
			routage4.initialiser(random2);
			gradient.energie = routage4.calculerEnergie();
			routage4 = (Routage) gradient.iterer(routage4);
			// Affichage de la solution trouv�e par une it�ration de l'algorithme de recuit
			System.out.println(gradient.energie); 
		}
		
		/* It�ration multiple de l'algortihme de recuit simple		
		
		// nbIterations it�rations de l'algorithme de recuit
		
		for (int j=0; j < nbIterations ; j++){  // K*N it�rations au total
			recuit1.energie = routage.calculerEnergie();
			recuit1.meilleureEnergie = recuit1.energie;
			routage = (Routage) recuit1.iterer(routage);
			// Affichage de la solution trouv�e par une it�ration de l'algorithme de recuit
			System.out.println(recuit1.meilleureEnergie); 
		}
		*/
		
		/* It�ration du recuit avec r�chauffement
		
		Routage routage3 = routage;
		routage3.initialiser(random2);
		recuit3.energie = routage3.calculerEnergie();
		recuit3.meilleureEnergie = recuit3.energie;
		for (int l=0; l < M; l++) {
			routage3 = (Routage) recuit3.iterer(routage3);
			System.out.println("Rechauffement");
		}
		
		*/
		
	}
	
}

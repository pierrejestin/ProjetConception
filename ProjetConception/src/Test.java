import java.util.LinkedList;
import java.util.Random;


public class Test {

	public static void main(String[] args) {
		
		// Param�tres
		int longueurGraphe = 10;
		int hauteurGraphe = 6;
		int nbUtilisateurs = 50;
		int nbIterations = 1; // K
		int N = 100;
		int M = 10;

		
		// Seeds de l'al�atoire
		long seed1 = 156645125; 				// Seed pour les poids des utilisateurs
		long seed2 = 44656887;					// Seed pour l'initialisation
		Random random1 = new Random(seed1);
		Random random2 = new Random(seed2);
				
		// Cr�ation du graphe et des utilisateurs
		Graphe graphe = new Graphe(longueurGraphe,hauteurGraphe);
		
		LinkedList<Utilisateur> utilisateurs = new LinkedList<Utilisateur>();
		for (int i = 0; i < nbUtilisateurs; i++) {
			utilisateurs.add(new Utilisateur(i+1, random1.nextDouble()*10));
		}
		
		
		// Cr�ation des classes m�tier
		Routage routage = new Routage(graphe , utilisateurs, new LatenceMax(), new MutationAleatoireRoutage());		
		routage.initialiser(random2);
		Recuit recuit1 = new Recuit(N,M);
		Recuit recuit2 = new Recuit(N*M,1);
		Recuit recuit3 = new Recuit(N,1);
		
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
		
		/* It�ration du recuit avec r�chauffement */
		
		Routage routage3 = routage;
		routage3.initialiser(random2);
		recuit3.energie = routage3.calculerEnergie();
		recuit3.meilleureEnergie = recuit3.energie;
		for (int l=0; l < M; l++) {
			routage3 = (Routage) recuit3.iterer(routage3);
			System.out.println("Rechauffement");
		}
		
		
	}
	
}

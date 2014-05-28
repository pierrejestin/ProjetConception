import java.util.LinkedList;
import java.util.Random;


public class Test {

	public static void main(String[] args) {
		
		// Paramètres
		int longueurGraphe = 10;
		int hauteurGraphe = 6;
		int nbUtilisateurs = 30;
		int nbIterations = 100; // K
		int N = 100;
		int M = 10;
		
		// Seeds de l'aléatoire
		long seed1 = 156645125; 				// Seed pour les poids des utilisateurs
		long seed2 = 44656887;					// Seed pour l'initialisation
		Random random1 = new Random(seed1);
		Random random2 = new Random(seed2);
				
		// Création du graphe et des utilisateurs
		Graphe graphe = new Graphe(longueurGraphe,hauteurGraphe);
		
		LinkedList<Utilisateur> utilisateurs = new LinkedList<Utilisateur>();
		for (int i = 0; i < nbUtilisateurs; i++) {
			utilisateurs.add(new Utilisateur(i+1, random1.nextDouble()*10));
		}
		
		
		// Création des classes métier
		Routage routage = new Routage(graphe , utilisateurs, new LatenceMoyenne(), new MutationAleatoireRoutage());		
		Recuit recuit1 = new Recuit(N,M);
		Recuit recuit2 = new Recuit(N*M,1);
		
		/* Tests pour Excel 
		Routage solution = (Routage) recuitSimple.iterer(routage);
		*/
		
		
		// Itération multiple de l'algortihme de recuit simple + recuit par palliers
		
		Routage routage1 = routage;
		System.out.println("EBestS");
		for (int j=0; j < nbIterations ; j++){  // K*N itérations au total
			routage1 = (Routage) recuit1.iterer(routage1, random2);
			// Affichage de la solution trouvée par une itération de l'algorithme de recuit
			System.out.println(recuit1.meilleureEnergie); 
		}
		
		Routage routage2 = routage;
		System.out.println("EBestPP");
		for (int j=0; j < nbIterations ; j++){  // K*N itérations au total
			routage2 = (Routage) recuit2.iterer(routage2, random2);
			// Affichage de la solution trouvée par une itération de l'algorithme de recuit
			System.out.println(recuit2.meilleureEnergie); 
		}
		
		/* Itération multiple de l'algortihme de recuit simple
		
		// nbIterations itérations de l'algorithme de recuit
		for (int j=0; j < nbIterations ; j++){  // K*N itérations au total
			routage = (Routage) recuit.iterer(routage);
			// Affichage de la solution trouvée par une itération de l'algorithme de recuit
			System.out.println(recuit.meilleureEnergie); 
		}
		*/
		
			
	}
	
}

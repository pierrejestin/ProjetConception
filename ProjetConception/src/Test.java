import java.util.LinkedList;


public class Test {

	public static void main(String[] args) {
		
		// Paramètres
		int longueurGraphe = 10;
		int hauteurGraphe = 6;
		int nbUtilisateurs = 30;
		int nbIterations = 100; // K
		int N = 1000;
		int M = 1;
		
		// Création du graphe et des utilisateurs
		Graphe graphe = new Graphe(longueurGraphe,hauteurGraphe);
		
		LinkedList<Utilisateur> utilisateurs = new LinkedList<Utilisateur>();
		for (int i = 0; i < nbUtilisateurs; i++) {
			utilisateurs.add(new Utilisateur(i+1, i+1)); //Math.random()*10
		}
		
		
		// Création des classes métier
		Routage routage = new Routage(graphe , utilisateurs, new LatenceMoyenne(), new MutationAleatoireRoutage());		
		Recuit recuit = new Recuit(N,M);
		
		/* Tests pour Excel 
		Routage solution = (Routage) recuitSimple.iterer(routage);
		*/
		
		
		/* Test de la différence entre le recuit simple et le recuit par palliers 
		Routage routage1 = (Routage) recuitSimple.iterer(routage);
		System.out.println("Ebest Recuit simple = "+recuitSimple.meilleureEnergie+"   kfin= "+recuitSimple.k); 
	
		Routage routage2= (Routage) recuitParPalliers.iterer(routage);
		System.out.println("Ebest Recuit par palliers = "+recuitParPalliers.meilleureEnergie+"   kfin= "+recuitParPalliers.k); 
		*/
		
		
		
		/* Itération multiple de l'algortihme de recuit simple */
		
		// nbIterations itérations de l'algorithme de recuit
		for (int j=0; j < nbIterations ; j++){  // K*N itérations au total
			routage = (Routage) recuit.iterer(routage);
			// Affichage de la solution trouvée par une itération de l'algorithme de recuit
			System.out.println(recuit.meilleureEnergie); 
		}
		
		
			
	}
	
}

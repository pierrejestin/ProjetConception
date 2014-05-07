import java.util.LinkedList;


public class Test {

	public static void main(String[] args) {
		
		// Paramètres
		int longueurGraphe = 5;
		int hauteurGraphe = 4;
		int nbUtilisateurs = 15;
		int nbIterations = 1; // M
		int N = 100;
		
		// Création du graphe et des utilisateurs
		Graphe graphe = new Graphe(longueurGraphe,hauteurGraphe);
		System.out.println(graphe.aretes);
		
		LinkedList<Utilisateur> utilisateurs = new LinkedList<Utilisateur>();
		for (int i = 0; i < nbUtilisateurs; i++) {
			utilisateurs.add(new Utilisateur(i+1, 1)); //Math.random()*10
		}
		
		
		// Création des classes métier
		Routage routage = new Routage(graphe , utilisateurs, new LatenceMoyenne(), new ModifAleatoireRoutage());		
		IterationRecuit iteration = new IterationRecuit(N);
		
		// nbIterations itérations de l'algorithme de recuit
		for (int j=0; j < nbIterations ; j++){  // M*N itérations au total
			routage = (Routage) iteration.iterer(routage);
			// Affichage de la solution trouvée par une itération de l'algorithme de recuit
			System.out.println(iteration.meilleureEnergie); 
		}
			
	}
	
}

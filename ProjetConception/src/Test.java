import java.util.LinkedList;


public class Test {

	public static void main(String[] args) {
		
		// Param�tres
		int longueurGraphe = 5;
		int hauteurGraphe = 4;
		int nbUtilisateurs = 15;
		int nbIterations = 1; // M
		int N = 10000;
		
		// Cr�ation du graphe et des utilisateurs
		Graphe graphe = new Graphe(longueurGraphe,hauteurGraphe);
		
		LinkedList<Utilisateur> utilisateurs = new LinkedList<Utilisateur>();
		for (int i = 0; i < nbUtilisateurs; i++) {
			utilisateurs.add(new Utilisateur(i+1, 1)); //Math.random()*10
		}
		
		
		// Cr�ation des classes m�tier
		Routage routage = new Routage(graphe , utilisateurs, new LatenceMoyenne(), new MutationAleatoireRoutage());		
		RecuitSimple iteration = new RecuitSimple(N);
		
		// nbIterations it�rations de l'algorithme de recuit
		for (int j=0; j < nbIterations ; j++){  // M*N it�rations au total
			routage = (Routage) iteration.iterer(routage);
			// Affichage de la solution trouv�e par une it�ration de l'algorithme de recuit
			System.out.println("Ebest"+(j+1)+" = "+iteration.meilleureEnergie); 
		}
			
	}
	
}

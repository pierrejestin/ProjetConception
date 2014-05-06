import java.util.LinkedList;


public class Test {

	public static void main(String[] args) {
		
		int longueurGraphe = 105;
		int hauteurGraphe = 182;
		int nbUtilisateurs = 60;
		int nbIterations = 1;
		int N = 100;
		
		Graphe graphe = new Graphe(longueurGraphe,hauteurGraphe);
		System.out.println(graphe.aretes);
		
		LinkedList<Utilisateur> utilisateurs = new LinkedList<Utilisateur>();
		for (int i = 0; i < nbUtilisateurs; i++) {
			utilisateurs.add(new Utilisateur(i+1, 1)); //Math.random()*10
		}
		
		Routage routage = new Routage(graphe,utilisateurs);
		
		IterationRecuit iteration = new IterationRecuit(N);
		
		for (int j=0; j < nbIterations ; j++){
			routage.reinitialiser();
			routage = (Routage) iteration.iterer(routage);
			System.out.println(iteration.meilleureEnergie);
		}
			
	}
	
}

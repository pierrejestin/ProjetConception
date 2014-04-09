import java.util.LinkedList;


public class Test {

	public static void main(String[] args) {
		
		Graphe graphe = new Graphe(4,5);
		System.out.println(graphe.aretes);
		
		LinkedList<Utilisateur> utilisateurs = new LinkedList<Utilisateur>();
		for (int i = 0; i < 4; i++) {
			utilisateurs.add(new Utilisateur(i+1, Math.random()*10));
		}
		
		int N = 500;
		Iteration iteration = new Iteration(N);
		
		for (int j=0; j <=0 ; j++){
			iteration.reinitialiser(graphe, utilisateurs);
			utilisateurs = iteration.itererAleatoire(graphe, utilisateurs);
			System.out.println(utilisateurs);
			System.out.println(iteration.calculerMeilleureLatenceMoyenne(utilisateurs));
		}
			
	}
	
}

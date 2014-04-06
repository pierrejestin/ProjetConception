import java.util.LinkedList;


public class Graphe {

	LinkedList<Arete> aretes;
	int nbAretes;
	
	public Graphe(int n, int m) {
		
		Noeud source = new Noeud(1);
		Noeud[][] noeuds = new Noeud[n][m];
		int num = 2;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j <= Math.random()*(m-1); j++) {
				noeuds[i][j] = new Noeud(num);
				num++;
			}
		}
		Noeud puits = new Noeud(num);
		
		num = 1;
		LinkedList<Arete> aretes = new LinkedList<Arete>();
		for (int i = 0; i < m; i++) {
			if (noeuds[0][i] != null) {
				aretes.add(new Arete(source, noeuds[0][i], num));
				num++;
			}
		}
		for (int i = 0; i < (n-1); i++) {
			for (int j = 0; j < m; j++) {
				for (int k = 0; k < m; k++) {
					if (noeuds[i][j] != null && noeuds[i+1][k] != null) {
						aretes.add(new Arete(noeuds[i][j], noeuds[i+1][k], num));
						num++;
					}
				}
			}
		}
		for (int i = 0; i < m; i++) {
			if (noeuds[n-1][i] != null) {
				aretes.add(new Arete(noeuds[n-1][i], puits, num));
				num++;
			}
		}
		
		this.aretes = aretes;
		this.nbAretes = aretes.size();
		
	}
	
}

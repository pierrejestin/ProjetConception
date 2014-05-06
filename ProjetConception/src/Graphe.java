import java.util.Iterator;
import java.util.LinkedList;


public class Graphe {

	LinkedList<Arete> aretes; // ensemble des arêtes constituant le graphe
	int nbAretes; 
	int n; // longueur du graphe
	int m; // hauteur du graphe
	
	public Graphe(int n, int m) {
		
		this.n=n;
		this.m=m;
		
		// Création des noeuds
			Noeud source = new Noeud(1);
			
			Noeud[][] noeuds = new Noeud[n][m]; 
			
			int num = 2;
			for (int i = 0; i < n; i++) {
			//	for (int j = 0; j < Math.random()*m; j++) {
				for (int j = 0; j < m; j++) {
					noeuds[i][j] = new Noeud(num);
					num++;
				}
			}
			
			Noeud puits = new Noeud(num);
		
		// Attribution des noeuds aux arêtes	
			
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
	
	// Retourne les arêtes commençant par le noeud 'num'
	public LinkedList<Arete> aretesCommencantParNoeud(int num) {
		
		LinkedList<Arete> aretesNoeud = new LinkedList<Arete>();
		for (Iterator<Arete> i = this.aretes.iterator(); i.hasNext();) {
			Arete arete = i.next();
			if (arete.noeudDep.num == num) {
				aretesNoeud.add(arete);
			}
		}
		return aretesNoeud;
		
	}
	
	// Retourne les arêtes finissant par le noeud 'num'	
	public LinkedList<Arete> aretesFinissantParNoeud(int num) {
		
		LinkedList<Arete> aretesNoeud = new LinkedList<Arete>();
		for (Iterator<Arete> i = this.aretes.iterator(); i.hasNext();) {
			Arete arete = i.next();
			if (arete.noeudFin.num == num) {
				aretesNoeud.add(arete);
			}
		}
		return aretesNoeud;
		
	}
	
	// Retourne l'arête reliant le noeud 'num1' au noeud 'num2'
	public Arete areteEntreNoeuds(int num1, int num2){
		for (Iterator<Arete> i = this.aretes.iterator(); i.hasNext();) {
			Arete arete = i.next();
			if (arete.noeudFin.num == num2 && arete.noeudDep.num==num1) {
				return arete;
			}
		}
		return null;
	}
	
	// Réinitialisation du coût des arêtes à 0
	public void initialiser(){
		for (Iterator<Arete> i = this.aretes.iterator(); i.hasNext();) {
			i.next().cout=0;
		}
	}
		
	
}

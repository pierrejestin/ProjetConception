
public class Arete {

	Noeud noeudDep;
	Noeud noeudFin;
	int num;
	double cout; // somme des poids des utilisateurs utilisant l'ar�te
	
	public Arete(){
		
	}
	
	public Arete(Noeud noeudDep, Noeud noeudFin, int num) {
		
		this.noeudDep = noeudDep;
		this.noeudFin = noeudFin;
		this.num = num;
		this.cout = 0;
		
	}
	
	public String toString() {
		
		return noeudDep.toString() + "->" + noeudFin.toString();
		
	}
	
}

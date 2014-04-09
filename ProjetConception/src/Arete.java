
public class Arete {

	Noeud noeudDep;
	Noeud noeudFin;
	int num;
	double cout;
	
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

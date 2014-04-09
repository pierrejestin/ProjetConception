import java.util.Iterator;
import java.util.LinkedList;


public class Iteration {

	int N;
	double T;
	double TDeb;
	double TFin;
	double probaDeb;
	double probaFin;
	double latenceMoy;
	double meilleureLatenceMoy;
	
	public Iteration(int N){
		this.probaDeb=0.5;
		this.probaFin=0.1;
		this.N=N;
		this.TDeb = -1/(Math.log(this.probaDeb));
		this.TFin = -1/(Math.log(this.probaFin));
		this.T=TDeb;
		this.latenceMoy=0;
	}
	
	public void reinitialiser(Graphe graphe, LinkedList<Utilisateur> utilisateurs){
		graphe.reinitialiser();
		for(Iterator<Utilisateur> i = utilisateurs.iterator(); i.hasNext();){
			Utilisateur u = i.next();
			u.meilleureRoute=new LinkedList<Arete>();
		}
	}
	
	public LinkedList<Utilisateur> itererAleatoire(Graphe graphe, LinkedList<Utilisateur> utilisateurs){
		
	// Initialisation	

		for(Iterator<Utilisateur> i = utilisateurs.iterator(); i.hasNext();){
			Utilisateur u = i.next();
			attribuerRouteAleatoire(graphe,u);

		}
		

	// Itérations
		for(int j=1 ; j<=this.N ; j++) {
			
			this.latenceMoy = calculerLatenceMoyenne(utilisateurs);
			if (j == 1) this.meilleureLatenceMoy = this.latenceMoy;
				
		
			// Détermination d'une modification élémentaire aléatoire à réaliser
			Utilisateur utilisateurModifie = utilisateurs.get((int) (Math.random()*utilisateurs.size()));
			LinkedList<Arete> aretesPossibles=new LinkedList<Arete>();
			Arete areteSup1 = new Arete();
			Arete areteSup2 = new Arete();
			while(aretesPossibles.size()<=1){
				int index = (int) (Math.random()*graphe.n);
				areteSup1 = utilisateurModifie.route.get(index);
				areteSup2 = utilisateurModifie.route.get(index+1);
				Noeud noeudPrec = areteSup1.noeudDep;
				aretesPossibles = graphe.aretesCommencantParNoeud(noeudPrec.num);
			}
		//	System.out.println("Possibles: "+aretesPossibles);
			aretesPossibles.remove(areteSup1);
			Arete areteAj1 = aretesPossibles.get((int) (Math.random()*aretesPossibles.size()));
			Arete areteAj2 = graphe.areteEntreNoeuds(areteAj1.noeudFin.num , areteSup2.noeudFin.num );
			
			// On effectue la modification
			changerChemin(areteSup1,areteSup2, areteAj1, areteAj2, utilisateurs, utilisateurModifie);
			
			// 
			double nouvelleLatenceMoy = calculerLatenceMoyenne(utilisateurs);
			double probaAcceptation = Math.exp(-(nouvelleLatenceMoy-this.latenceMoy)/this.latenceMoy*this.T);
			if (!(nouvelleLatenceMoy<this.latenceMoy || Math.random() < probaAcceptation)) {
				changerChemin(areteAj1 , areteAj2 , areteSup1 , areteSup2 , utilisateurs, utilisateurModifie);
			}
			
			else if(nouvelleLatenceMoy<this.meilleureLatenceMoy) {
				this.meilleureLatenceMoy=nouvelleLatenceMoy;
				for(Iterator<Utilisateur> i = utilisateurs.iterator(); i.hasNext();){
					Utilisateur u = i.next();
					u.meilleureLatence=u.latence;
					u.meilleureRoute=(LinkedList<Arete>) u.route.clone();
				}
				
			}
			
			// Maj de la température
			this.T= this.T * Math.pow(this.TFin/this.TDeb,1/(N-1));		
			
			// Print de la latence moyenne
			System.out.println(this.meilleureLatenceMoy);
		}
		
		return utilisateurs;
		
	}
	
	public void  attribuerRouteAleatoire(Graphe graphe, Utilisateur utilisateur){
		
		LinkedList<Arete> route=new LinkedList<Arete>();
		int num=1;
		for (int i=1 ; i<=graphe.n+1 ; i++){
			
			LinkedList<Arete> cheminsPossibles = graphe.aretesCommencantParNoeud(num);
			Arete cheminChoisi = cheminsPossibles.get((int) (Math.random()*cheminsPossibles.size()));
			num = cheminChoisi.noeudFin.num;
			route.add(cheminChoisi);
			cheminChoisi.cout += utilisateur.poids;
			
		}
		utilisateur.route=route;
	}
	
	public void calculerLatence(Utilisateur utilisateur){
		int latence=0;
		for (Iterator<Arete> i = utilisateur.route.iterator(); i.hasNext();){
			Arete arete = i.next();
			latence += arete.cout;
		}
		utilisateur.latence = latence;
	}
	
	public double calculerLatenceMoyenne(LinkedList<Utilisateur> utilisateurs){
		double sommePoids = 0;
		double latenceMoy=0.0;
		for(Iterator<Utilisateur> i = utilisateurs.iterator(); i.hasNext();){
			Utilisateur u = i.next();
			calculerLatence(u);
			latenceMoy += u.poids * u.latence;
			sommePoids += u.poids;
		}
		return (latenceMoy/sommePoids);
		
	}
	
	public double calculerMeilleureLatenceMoyenne(LinkedList<Utilisateur> utilisateurs){
		double sommePoids = 0;
		double latenceMoy=0.0;
		for(Iterator<Utilisateur> i = utilisateurs.iterator(); i.hasNext();){
			Utilisateur u = i.next();
			calculerLatence(u);
			latenceMoy += u.poids * u.meilleureLatence;
			sommePoids += u.poids;
		}
		return (latenceMoy/sommePoids);
		
	}
	
	public void changerChemin(Arete areteSup1, Arete areteSup2, Arete areteAj1, Arete areteAj2, LinkedList<Utilisateur> utilisateurs, Utilisateur u) {
		
		areteSup1.cout -= u.poids;
		areteSup2.cout -= u.poids;
		areteAj1.cout += u.poids;
		areteAj2.cout += u.poids;
		int i = u.route.indexOf(areteSup1);
		u.route.remove(areteSup1);
		u.route.add(i, areteAj1);
		i = u.route.indexOf(areteSup2);
		u.route.remove(areteSup2);
		u.route.add(i, areteAj2);
		
		for (Iterator<Utilisateur> j = utilisateurs.iterator(); j.hasNext();) {
			calculerLatence(j.next());
		}
		
	}

	
	
}

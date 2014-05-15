import java.util.Iterator;


public class LatenceMoyenne implements IEnergie {

	
	public double calculer(Probleme probleme) {
		
		Routage routage = (Routage)	probleme;
		double sommePoids = 0;
		double latenceTot=0.0;
		for(Iterator<Utilisateur> i = routage.utilisateurs.iterator(); i.hasNext();){
			Utilisateur u = i.next();
			u.calculerLatence();
			latenceTot += u.poids * u.latence;
			sommePoids += u.poids;
		}
		return (latenceTot/sommePoids);

	}

	
	
}

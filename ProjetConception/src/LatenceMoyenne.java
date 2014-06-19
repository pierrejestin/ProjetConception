
import java.util.Iterator;

// Impl�mentation de l'interface IEnergie pour faire le lien entre Energie et Latence Moyenne

public class LatenceMoyenne implements IEnergie {

	// Calcul de la latence moyenne dans le probl�me de routage
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

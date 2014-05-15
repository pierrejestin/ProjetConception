import java.util.Iterator;

public class LatenceMax implements IEnergie {

		public double calculer(Probleme probleme) {
			
			Routage routage = (Routage)	probleme;
			double latenceMax= 0.0;		
			for(Iterator<Utilisateur> i = routage.utilisateurs.iterator(); i.hasNext();){
				Utilisateur u = i.next();
				u.calculerLatence();
				if (u.latence > latenceMax) latenceMax = u.latence;
			}
			return latenceMax;

		}

	}


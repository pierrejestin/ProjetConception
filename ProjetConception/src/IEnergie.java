// Interface repr�sentant le concept abstrait de l'Energie dans l'algorithme de recuit

public interface IEnergie {

	// Le probl�me doit �tre capable de calculer son energie dans une situation donn�e
	public double calculer(Probleme probleme);
		
}

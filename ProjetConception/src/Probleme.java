/*
 * Classe abstraite repr�sentant un probl�me g�n�rique soluble par recuit simul�
 * Pour r�soudre un probl�me gr�ce au recuit il faut faire une classe fille qui impl�mente
 * les diff�rentes m�thodes abstraites utilis�es par l'algorithme
 */

public abstract class Probleme {

	public abstract void initialiser(); // initialisation du probl�me: attribution de routes al�atoires
	public abstract double calculerEnergie(); // calcule et retourne l'�nergie (ex: la latance)
	public abstract void modifElem(); // effectue une mutation �l�mentaire du probl�me
	public abstract void annulerModif(); // annule la derni�re mutation �l�mentaire effectu�e
	public abstract void sauvegarderSolution(); // sauvegarde la solution actuelle dans une variable
	
}

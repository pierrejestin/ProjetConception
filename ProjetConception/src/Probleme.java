/*
 * Classe abstraite représentant un problème générique soluble par recuit simulé
 * Pour résoudre un problème grâce au recuit il faut faire une classe fille qui implémente
 * les différentes méthodes abstraites utilisées par l'algorithme
 */

public abstract class Probleme {

	public abstract void initialiser(); // initialisation du problème: attribution de routes aléatoires
	public abstract double calculerEnergie(); // calcule et retourne l'énergie (ex: la latance)
	public abstract void modifElem(); // effectue une mutation élémentaire du problème
	public abstract void annulerModif(); // annule la dernière mutation élémentaire effectuée
	public abstract void sauvegarderSolution(); // sauvegarde la solution actuelle dans une variable
	
}

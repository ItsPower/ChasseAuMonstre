package entites.items;

/**
 * La classe �toile d�finit une �toile que le chasseur peut utiliser pour augmenter le nombre de longues vues qu'il utilise 
 * @author Sylvain
 *
 */
public class Etoile extends Item {
	
	/**
	 * Constructeur de la classe Etoile qui d�finit la variable recup�rable � true car une �toile est r�cup�rable
	 */
	public Etoile() {
		this.recuperable=true;
	}
	
	/**
	 * M�thode toString informant que l'�toile a �t� r�cup�r�e par le joueur
	 */
	public String toString() {
		return "Etoile r�cup�r�e";
	}
}
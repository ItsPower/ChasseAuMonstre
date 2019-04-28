package plateau;

import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Queue;

import entites.items.Etoile;
import entites.items.Item;
import entites.items.LongueVue;
import entites.personnage.chasseur.Chasseur;
import entites.personnage.monstre.Monstre;

/**
 * Classe du plateau, it�rable sur ses cases 
 * @author Benoit
 */
public class Plateau implements Iterable<Case>  {

	private int tailleX, tailleY;
	private Case[][] cases;
	private int nbCases;
	private Queue<LongueVue> longueVues;

	/**
	 * Constructeur en fonction de la configuration pass�e en param�tre
	 * @param cfg : la configuration de la parties
	 */
	public Plateau(int x, int y) {
		this.tailleX = x;
		this.tailleY = y;
		this.longueVues = new PriorityQueue<LongueVue>();
		this.cases = new Case[this.tailleX][this.tailleY];
		for (int i = 0; i < this.tailleX; i++) {
			for (int j = 0; j < this.tailleY; j++) {
				cases[i][j] = new Case();
				nbCases++;
			}
		}
	}
	
	/**
	 * Affichage du plateau du point de vue du chasseur
	 * @param chasseur
	 */
	public void afficherPlateau(Chasseur chasseur) {

		for(int j=0; j<tailleY;j++) {
			//AFFICHAGE BORD HAUT
			for(int i=0; i<tailleX*4+1;i++){
				System.out.print("-");
			}
			System.out.print("\n");

			//AFFICHAGE LIGNES
			for(int i=0; i<tailleX; i++) {
				if(chasseur.getPosition().getX() == i && chasseur.getPosition().getY() == j) {
					System.out.print("| C ");
				} else {
					for(Item h:cases[i][j].getDedans()) {
						if(h instanceof Etoile && cases[i][j].getDedans().size() < 2) {
							System.out.print("| * ");
						}
						else if(h instanceof LongueVue) {
							System.out.print("| L ");
						}
					}
					if(cases[i][j].getDedans().isEmpty()) {
						System.out.print("|   ");
					}
				}
			}
			System.out.print("|\n");
		}
		//AFFICHAGE BORD BAS
		for(int i=0; i<tailleX*4+1;i++){
			System.out.print("-");
		}
		System.out.print("\n");
	}
	/**
	 * Affichage du plateau du point de vue du monstre
	 * @param monstre
	 */
	public void afficherPlateau(Monstre monstre) {

		for(int j=0; j<tailleY;j++) {
			//AFFICHAGE BORD HAUT
			for(int i=0; i<tailleX*4+1;i++){
				System.out.print("-");
			}
			System.out.print("\n");

			//AFFICHAGE LIGNES
			for(int i=0; i<tailleX; i++) {
				if(monstre.getPosition().getX() == i && monstre.getPosition().getY() == j) {
					System.out.print("| M ");
				}
				else if(cases[i][j].getTourPassage() != -1) {
					System.out.print("| - ");
				}
				else {
					for(Item h:cases[i][j].getDedans()) {
						if(h instanceof Etoile) {
							System.out.print("| * ");
						}
						else if(h instanceof LongueVue && cases[i][j].getDedans().size() < 2) {
							System.out.print("|   ");
						}
					}
					if(cases[i][j].getDedans().isEmpty()) {
						System.out.print("|   ");
					}
				}
			}
			System.out.print("|\n");
		}
		//AFFICHAGE BORD BAS
		for(int i=0; i<tailleX*4+1;i++){
			System.out.print("-");
		}
		System.out.print("\n");
	}
	
	/**
	 * @return le tableau de cases
	 */
	public Case[][] getCases() {
		return cases;
	}

	public Queue<LongueVue> getLongueVues() {
		return longueVues;
	}

	/**
	 * Donne une case en fonction d'une Position pass�e en param�tre
	 * @param p = la position de la case d�sir�e
	 * @return la case � la position demand�e
	 */
	public Case getCase(Position p) {
		return getCase(p.getX(), p.getY());
	}
	/**
	 * Donne une case en fonction de ses coordonn�es d'abscisse et d'ordonn�e
	 * @param x = l'abscisse
	 * @param y = l'ordonn�e
	 * @return la Case � la position demand�e
	 */
	public Case getCase(int x, int y) {
		if(x > tailleX || y > tailleY) return null;
		return this.cases[x][y];
	}
	/**
	 * Donne la taille en abscisse du plateau
	 * @return un entier repr�sentant la taille en abscisse du plateau
	 */
	public int getTailleX() {
		return tailleX;
	}
	/**
	 * Donne la taille en ordonn�e du plateau
	 * @return un entier repr�sentant la taille en ordonn�e du plateau
	 */
	public int getTailleY() {
		return tailleY;
	}
	/**
	 * Donne le nombre de cases total du tableau
	 * @return un entier repr�sentant le nb de cases
	 */
	public int getNbCases() {
		return nbCases;
	}

	/**
	 * Retourne l'it�rateur du plateau qui passe par toutes les cases
	 */
	@Override
	public Iterator<Case> iterator() {
		return new ItPlateau(cases);
	}

	//TODO faire les emplacements de base du chasseur et monstre

}


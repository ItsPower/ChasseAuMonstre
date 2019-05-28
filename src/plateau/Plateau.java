package plateau;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import personnage.Personnage;
import personnage.chasseur.Chasseur;
import personnage.monstre.Monstre;
	
/**
 * Classe du plateau, itérable sur ses cases 
 * @author Sylvain
 */
public class Plateau implements Iterable<Case>, Serializable {
	private static final long serialVersionUID = 42;

	private int tailleX, tailleY;
	private Case[][] cases;
	private int nbCases;
	public int tour;

	private Monstre monstre;
	private Chasseur chasseur;

	/**
	 * Constructeur en fonction de la configuration passée en paramètre
	 * @param cfg : la configuration de la parties
	 */
	public Plateau(int x, int y) {
		this.tailleX = x;
		this.tailleY = y;
		this.tour = 0;
		this.cases = new Case[this.tailleX][this.tailleY];
		for (int i = 0; i < this.tailleX; i++) {
			for (int j = 0; j < this.tailleY; j++) {
				cases[i][j] = new Case();
				nbCases++;
			}
		}
	}

	public void placerEtoiles() {
		ArrayList<Position> tmp = new ArrayList<Position>();
		int nbEtoile = 0;
		while(nbEtoile != 3) {
			Position p = new Position(new Random().nextInt(this.tailleX), new Random().nextInt(this.tailleY));
			if(!tmp.contains(p)) {
				tmp.add(p);
				cases[p.getX()][p.getY()].placerEtoile();
				nbEtoile++;
			}
		}
	}

	/**
	 * @return le tableau de cases
	 */
	public Case[][] getCases() {
		return cases;
	}

	/**
	 * Donne une case en fonction d'une Position passée en paramètre
	 * @param p = la position de la case désirée
	 * @return la case à la position demandée
	 */
	public Case getCase(Position p) {
		return getCase(p.getX(), p.getY());
	}
	/**
	 * Donne une case en fonction de ses coordonnées d'abscisse et d'ordonnée
	 * @param x = l'abscisse
	 * @param y = l'ordonnée
	 * @return la Case à la position demandée
	 */
	public Case getCase(int x, int y) {
		if(x >= tailleX || y >= tailleY) return null;
		return this.cases[x][y];
	}
	/**
	 * Donne la taille en abscisse du plateau
	 * @return un entier représentant la taille en abscisse du plateau
	 */
	public int getTailleX() {
		return tailleX;
	}
	/**
	 * Donne la taille en ordonnée du plateau
	 * @return un entier représentant la taille en ordonnée du plateau
	 */
	public int getTailleY() {
		return tailleY;
	}

	/**
	 * Retourne le gagnant de la partie
	 * @return 
	 */
	public Personnage getWinner() {

		if(this.monstre.getPosition().equals(this.chasseur.getPosition())) {
			return this.chasseur;
		}

		Iterator<Case> itr = iterator();
		int nbParcoursMonstre = 0;
		while(itr.hasNext()) {
			Case c = itr.next();
			if(c.getTourPassage() != -1) {
				nbParcoursMonstre++;
			}
		}
		if(nbParcoursMonstre >= getNbCases()*0.75) {
			return this.chasseur;
		}

		return null;
	}


	/**
	 * Retourne l'itérateur du plateau qui passe par toutes les cases
	 */
	@Override
	public Iterator<Case> iterator() {
		return new ItPlateau(cases);
	}


	public double getNbCases() {
		return nbCases;
	}

}


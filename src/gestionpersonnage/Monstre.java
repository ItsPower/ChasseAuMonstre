package gestionpersonnage;

import util.Clavier;

public class Monstre extends Personnage {

	/**
	 * Constructeur de Monstre
	 * @param p = la position � laquelle le monstre commence
	 */
	public Monstre(Position p) {
		super(p);
	}

	@Override
	public String toString() {
		return "Monstre [sac=" + sac + ", pos=" + pos + ", getDirectionVoulue()=" + getDirectionVoulue()
		+ ", aEtoile()=" + aEtoile() + ", getPosition()=" + getPosition() + ", getSac()=" + getSac()
		+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
		+ "]";
	}

	/**
	 * M�thode qui demande au joueur dans quelle direction il veut aller
	 * @return La direction dans laquelle le chasseur joueur veut aller
	 */
	public Direction getDirectionVoulue() {
		System.out.println("Printer les directions et leurs num�ros de monstre");
		char c=0;
		do {
			System.out.println("Dis moi o� tu veux aller :");
			c = (char) Clavier.lireString().charAt(0);
		} while(c<='1' || c>='9' || c=='5');
		return Direction.byNumero(Character.getNumericValue(c));
	}

}

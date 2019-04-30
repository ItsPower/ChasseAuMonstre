package launcher;

import entites.personnage.Type;

public class ConfigurationPartie {

	private Type joueur1;
	private Type joueur2;
	
	private int tailleX = 9;
	private int tailleY = 9;
	
	public ConfigurationPartie(Type j1, Type j2) {
		this.joueur1 = j1;
		this.joueur2 = j2;
	}

	public ConfigurationPartie() {
	}

	public Type getJoueur1() {
		return joueur1;
	}

	public void setJoueur1(Type joueur1) {
		this.joueur1 = joueur1;
	}

	public Type getJoueur2() {
		return joueur2;
	}

	public void setJoueur2(Type joueur2) {
		this.joueur2 = joueur2;
	}

	public int getTailleX() {
		return tailleX;
	}

	public void setTailleX(int tailleX) {
		this.tailleX = tailleX;
	}

	public int getTailleY() {
		return tailleY;
	}

	public void setTailleY(int tailleY) {
		this.tailleY = tailleY;
	}
}

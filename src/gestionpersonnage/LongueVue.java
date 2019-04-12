package gestionpersonnage;

public class LongueVue extends Item{
	public final int NB_MAX = 5;
	
	/**
	 * Constructeur de la classe LongueVue qui d�finit la variable recup�rable � false car une longue vue n'est pas r�cup�rable
	 */
	public LongueVue() {
		this.recuperable=false;
	}
	
	/**
	 * M�thode toString informant que le monstre a �t� d�tect� par la longue-vue
	 */
	public String toString() {
		return "Monstre d�tect�";
	}
}

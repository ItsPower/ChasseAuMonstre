package testsGestionPersonnage;

import static org.junit.Assert.*;

import org.junit.*;

import gestionpersonnage.*;

public class MainTest {
	
	public Plateau p;
	public Personnage chasseur,monstre;
	
	
	@BeforeClass
	public static void beforeAll() {
		System.out.println("D�but des tests");
	}	
	@Before
	public void beforeEach() {
		System.out.println("Initialisation...");
		p = new Plateau(10, 10);
		chasseur = new Chasseur(new Position(0, 0));
		monstre = new Monstre(new Position(5, 5));
		p.chasseur=chasseur;
		p.monstre=monstre;
		
		
		System.out.println("D�but du test");
	}

	@Test
	public void testPosition() {
		Position p1 = new Position(0, 0);
		assertEquals(0,p1.getX());
		assertEquals(0,p1.getY());
		assertTrue(p1.equals(p1));
		assertTrue(new Position(0,0).equals(p1));
		assertFalse(new Position(1,1).equals(p1));
	}
	@Test
	public void testPersonnage() {
		assertFalse(chasseur.aEtoile());
	}
	@After
	public void afterEach() {
		System.out.println("Suivant...");
	}
	@AfterClass
	public static void endTests() {
		System.out.println("Fin des tests");
	}
}

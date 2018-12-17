package inscriptions.test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import inscriptions.Competition;
import inscriptions.Equipe;
import inscriptions.Inscriptions;
import inscriptions.Personne;

class CompetitionTest {

	
//	private String nomRobert = "Robert";
	private String nomTony = "Tony";
	private Inscriptions inscriptions;
	private Personne tony ;
	private Competition testCompet ;
	private Competition testCompet2 ;
	private Equipe lesManouches;
	
	@BeforeEach
	protected void setUp() throws Exception
	{
		inscriptions = Inscriptions.getInscriptions();
		tony = inscriptions.createPersonne(nomTony, "lala", "azerty");
		lesManouches = inscriptions.createEquipe("Les Manouches");
		testCompet = inscriptions.createCompetition("test1", LocalDate.parse
				("2019-02-10"), false);
		testCompet2 = inscriptions.createCompetition("test2", LocalDate.parse
				("2019-02-12"), true);
	}
	
	
	@Test
	void testCompetition() {
		Competition testCompet3 = inscriptions.createCompetition("handball", LocalDate.parse("2019-04-10"), true );
		assertEquals("handball", testCompet3.getNom());
		assertEquals(LocalDate.parse("2019-04-10"), testCompet3.getDateCloture());
		assertTrue(testCompet3.estEnEquipe() );
	}

	@Test
	void testGetNom() {
		assertEquals("test1", testCompet.getNom());
		
	}

	@Test
	void testSetNom() {
		testCompet.setNom("reussi");
		assertEquals("reussi", testCompet.getNom());
	}

	@Test
	void testInscriptionsOuvertes() {
		assertTrue(testCompet.inscriptionsOuvertes() );
	}

	@Test
	void testGetDateCloture() {
		assertEquals(LocalDate.parse("2019-02-10"), testCompet.getDateCloture());
	}

	@Test
	void testEstEnEquipe() {
		assertTrue(testCompet2.estEnEquipe());
	}

	@Test
	void testSetDateCloture() {
		testCompet.setDateCloture(LocalDate.parse("2019-05-10"));
		assertEquals(LocalDate.parse("2019-05-10"), testCompet.getDateCloture());
		testCompet2.setDateCloture(LocalDate.parse("2019-01-10"));
		assertTrue(LocalDate.parse("2019-01-10") != testCompet2.getDateCloture());
	}

	@Test
	void testGetCandidats() {
		
		testCompet.add(tony);
		testCompet2.add(lesManouches);
		assertTrue(testCompet.getCandidats().contains(tony));
		assertTrue(testCompet2.getCandidats().contains(lesManouches));
		
	}

	@Test
	void testAddPersonne() {
		testCompet.add(tony);
		assertTrue(testCompet.getCandidats().contains(tony));
	}

	@Test
	void testAddEquipe() {
		testCompet2.add(lesManouches);
		assertTrue(testCompet2.getCandidats().contains(lesManouches));
	}

	@Test
	void testGetPersonnesAInscrire() {
		assertTrue(testCompet.getPersonnesAInscrire().contains(tony));
	}

	@Test
	void testRemove() {
		testCompet.add(tony);
		testCompet.remove(tony);
		assertEquals(false, testCompet.getCandidats().contains(tony));
	}

	@Test
	void testDelete() {
		testCompet.delete();
		assertEquals(false,inscriptions.getCompetitions().contains(testCompet));
		
	}
	
	@Test
	void testCompareTo() {
		assertEquals(0,testCompet.compareTo(testCompet));
		assertEquals(-1,testCompet.compareTo(testCompet2));
		
	}

	@Test
	void testToString() {
		assertEquals("test1", testCompet.toString());
	}

}

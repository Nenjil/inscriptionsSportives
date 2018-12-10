package inscriptions.test;

import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import inscriptions.Competition;
import inscriptions.Equipe;
import inscriptions.Inscriptions;
import inscriptions.Personne;

class CompetitionTest {

	
	private String nomRobert = "Robert";
	private String nomTony = "Tony";
	private Inscriptions inscriptions;
	private Personne tony ;
	private Competition testCompet ;
	private Competition testCompet2 ;
	
	@BeforeEach
	protected void setUp() throws Exception
	{
		inscriptions = Inscriptions.getInscriptions();
		tony = inscriptions.createPersonne(nomTony, "lala", "azerty");
		testCompet = inscriptions.createCompetition("test", LocalDate.parse
				("2018-02-10"), false);
		testCompet2 = inscriptions.createCompetition("test", LocalDate.parse
				("2018-02-10"), false);
	}
	
	
	@Test
	void testCompetition() {
		fail("Not yet implemented");
	}

	@Test
	void testGetNom() {
		fail("Not yet implemented");
	}

	@Test
	void testSetNom() {
		fail("Not yet implemented");
	}

	@Test
	void testInscriptionsOuvertes() {
		fail("Not yet implemented");
	}

	@Test
	void testGetDateCloture() {
		fail("Not yet implemented");
	}

	@Test
	void testEstEnEquipe() {
		fail("Not yet implemented");
	}

	@Test
	void testSetDateCloture() {
		fail("Not yet implemented");
	}

	@Test
	void testGetCandidats() {
		fail("Not yet implemented");
	}

	@Test
	void testAddPersonne() {
		fail("Not yet implemented");
	}

	@Test
	void testAddEquipe() {
		fail("Not yet implemented");
	}

	@Test
	void testGetPersonnesAInscrire() {
		fail("Not yet implemented");
	}

	@Test
	void testRemove() {
		fail("Not yet implemented");
	}

	@Test
	void testDelete() {
		fail("Not yet implemented");
	}

	@Test
	void testCompareTo() {
		fail("Not yet implemented");
	}

	@Test
	void testToString() {
		fail("Not yet implemented");
	}

}

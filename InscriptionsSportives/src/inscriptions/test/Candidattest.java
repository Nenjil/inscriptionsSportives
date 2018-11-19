package inscriptions.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import inscriptions.Competition;
import inscriptions.Equipe;
import inscriptions.Inscriptions;
import inscriptions.Personne;

class Candidattest 
{	
	private Inscriptions inscriptions = Inscriptions.getInscriptions();
	Competition flechettes = null;
	String nom = null;

	@BeforeEach
	void setUp() throws Exception 
	{
		nom = "Bernard";
		flechettes = inscriptions.createCompetition("flechette", null, true);
		
	}

	@Test
	void testCandidat() {
		fail("Not yet implemented");
	}

	@Test
	void testGetNom() {
		assertTrue(this.nom.equals("Bernard") );
	}

	@Test
	void testSetNom() {
		fail("Not yet implemented");
	}

	@Test
	void testGetCompetitions() {
		fail("Not yet implemented");
	}

	@Test
	void testAdd() {
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

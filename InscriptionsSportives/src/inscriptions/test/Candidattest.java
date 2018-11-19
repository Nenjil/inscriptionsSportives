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
	Inscriptions inscriptions = Inscriptions.getInscriptions();
	Competition flechettes = inscriptions.createCompetition("Mondial de fléchettes", null, false);
	Personne tony = inscriptions.createPersonne("Tony", "Dent de plomb", "azerty"), 
			boris = inscriptions.createPersonne("Boris", "le Hachoir", "ytreza");
	
	@BeforeEach
	void setUp() throws Exception 
	{
		
	}

	@Test
	void testCandidat() {
		
	}

	@Test
	void testGetNom() {
		assertTrue(tony.getNom().equals("Tony") );
	}

	@Test
	void testSetNom() {
		tony.setNom("Tonio");
		assertTrue(tony.getNom().equals("Tonio") );
	}

	@Test
	void testGetCompetitions() {
		;
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

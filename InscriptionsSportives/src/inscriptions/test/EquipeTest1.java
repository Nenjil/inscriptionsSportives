package inscriptions.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import inscriptions.Equipe;
import inscriptions.Inscriptions;
import inscriptions.Personne;

class EquipeTest1 {

	Inscriptions inscriptions = Inscriptions.getInscriptions();
	Personne tony = null;
	Personne boris = null;
	Equipe lesManouches = null;
	
	
	@BeforeEach
	void setUp() throws Exception {
		tony = inscriptions.createPersonne("Tony", "Dent de plomb", "azerty");
		boris = inscriptions.createPersonne("Boris", "le Hachoir", "ytreza");
		lesManouches = inscriptions.createEquipe("Les Manouches");
	}


	@Test
	public void testEquipe() {
		Equipe lesPatriks = inscriptions.createEquipe("Les patriks");
		assertEquals("Les patriks", lesPatriks.getNom());
	}
	
	@Test
	public void testDelete() {
		Equipe equipe3 = inscriptions.createEquipe("RegisTeam");
		assertTrue(inscriptions.getEquipes().contains(equipe3));
		int nbequipe = inscriptions.getEquipes().size();
		equipe3.delete();
		assertFalse(inscriptions.getEquipes().contains(equipe3));
		assertEquals(nbequipe -1,inscriptions.getEquipes().size());
	}
	
	@Test
	public void testGetMembres() {
		lesManouches.add(boris);
		lesManouches.add(tony);

		assertTrue(lesManouches.getMembres().contains(boris) &&  lesManouches.getMembres().contains(tony));
		
	}

	@Test
	public void testAddPersonne() {
		lesManouches.add(tony);
		assertTrue(lesManouches.getMembres().contains(tony));
		assertTrue(tony.getEquipes().contains(lesManouches));
	}

	@Test
	public void testRemove() {
		lesManouches.add(tony);
		lesManouches.remove(tony);
		assertEquals(false, lesManouches.getMembres().contains(tony));
	}

}

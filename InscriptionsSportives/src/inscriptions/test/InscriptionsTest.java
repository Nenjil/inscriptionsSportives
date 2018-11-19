package inscriptions.test;

import static org.junit.jupiter.api.Assertions.*;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import inscriptions.Competition;
import inscriptions.Equipe;
import inscriptions.Inscriptions;
import inscriptions.Personne;

class InscriptionsTest {

	// on cree avant tout une nouvelle inscription avec nos variables 
	private Inscriptions inscriptions = Inscriptions.getInscriptions();
	Personne tony,boris = null;
	Competition flechettes = null;
	Equipe lesManouches = null;
	 
	
	@BeforeEach
	void setUp() throws Exception {
		//set up avant chaque test 
		tony = inscriptions.createPersonne("Tony", "Dent de plomb","azerty");
		boris = inscriptions.createPersonne("Boris", "le Hachoir", "ytreza");
		flechettes = inscriptions.createCompetition("flechette", null, true);
		lesManouches = inscriptions.createEquipe("Les Manouches");
		
	}

	@Test
	void testGetCompetitions() {
		assertTrue(inscriptions.getCompetitions().contains(flechettes));
			
		
	}

	@Test
	void testGetCandidats() {
		assertTrue(inscriptions.getCandidats().contains(boris) && inscriptions.getCandidats().contains(tony) 
				   && inscriptions.getCandidats().contains(lesManouches) );
	}

	@Test
	void testGetPersonnes() {
		assertTrue(inscriptions.getPersonnes().contains(boris) && inscriptions.getPersonnes().contains(tony)) ;

	}

 
	@Test
	void testGetEquipes() {
	assertTrue(	inscriptions.getEquipes().contains(lesManouches)) ;
	}
	

	@Test
	void testCreateCompetition() {
		assertTrue(inscriptions.getCompetitions().contains(flechettes));
		Competition basket = inscriptions.createCompetition("basket", null, true);
		 assertTrue(inscriptions.getCompetitions().contains(basket));
		 basket.delete();	
	}

	@Test
	void testCreatePersonne() {
		assertTrue(inscriptions.getPersonnes().contains(boris) && inscriptions.getPersonnes().contains(tony)) ;
		Personne josias = inscriptions.createPersonne("Josias","Futur Programmeur de qualité =D","ass@jo.fr");
		assertTrue(inscriptions.getPersonnes().contains(josias)) ;
		 josias.delete();	
	}
	
	
	@Test
	void testCreateEquipe() {
		assertTrue(inscriptions.getEquipes().contains(lesManouches)); 
		Equipe LesRuskov = inscriptions.createEquipe("Les Ruskov");
		assertTrue(inscriptions.getEquipes().contains(LesRuskov));
		LesRuskov.delete();
	}

	
	
	@Test
	
	void testDeleteCompetition()  {
		assertTrue(inscriptions.getCompetitions().contains(flechettes));
		flechettes.delete();
		assertTrue(!inscriptions.getCompetitions().contains(flechettes));
		
	}
	
	
	
	@Test
	void testDeleteCandidat() {
		assertTrue(inscriptions.getCandidats().contains(boris));
		boris.delete();
		assertTrue(!inscriptions.getCandidats().contains(boris));
	}

	
	/*
	@Test
	void testReinitialiser() {
		fail("Not yet implemented");
	}*/
	
	
	
	
	@Test
	void testGetInscriptions() {
		// sachant que la variable inscriptions contient justement un appel de la methode getinscritions
		// renvoie l'instance de l'inscriptions en cours si n'est pas vide
		assertEquals(inscriptions.toString(),"Candidats : " + inscriptions.getCandidats().toString()
				+ "\nCompetitions  " + inscriptions.getCompetitions().toString());
		// on va la vider et l'afficher pour verifier
		inscriptions=inscriptions.reinitialiser() ; 
		inscriptions=Inscriptions.getInscriptions(); 
		// logiquement une fois vidée elle ne contient plus aucun candidat verifions 
		assertTrue(inscriptions.getCandidats().isEmpty());
	}

	/*
	@Test
	void testReinitialiser() {
		fail("Not yet implemented");
	}

	@Test
	void testRecharger() {
		fail("Not yet implemented");
	}

	@Test
	void testSauvegarder() {
		fail("Not yet implemented");
	}

	@Test
	void testToString() {
		fail("Not yet implemented");
	}

	@Test
	void testMain() {
		fail("Not yet implemented");
	}
*/
}

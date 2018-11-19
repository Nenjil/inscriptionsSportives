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

class InscriptionsTest {

	// on cree avant tout une nouvelle inscription avec nos variables 
	private Inscriptions inscriptions = Inscriptions.getInscriptions();
	Personne tony,boris = null;
	Competition flechettes,basket = null;
	Equipe lesManouches = null;
	 
	
	@BeforeEach
	void setUp() throws Exception {
		//set up avant chaque test 
		tony = inscriptions.createPersonne("Tony", "Dent de plomb","azerty");
		boris = inscriptions.createPersonne("Boris", "le Hachoir", "ytreza");
		flechettes = inscriptions.createCompetition("flechette", LocalDate.of(2019, Month.JANUARY, 1), false);
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

	
	
	@Test
	void testReinitialiser() {
		// Avant reinitialisation il y des candidats
		assertFalse(inscriptions.getCandidats().isEmpty());
		// on va la vider et l'afficher pour verifier
		inscriptions=inscriptions.reinitialiser() ; 
		inscriptions=Inscriptions.getInscriptions(); 
		// logiquement une fois vidée elle ne contient plus aucun candidat verifions 
		assertTrue(inscriptions.getCandidats().isEmpty());
	}
	
	
	
	
	
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

	
	

	@Test
	void testRecharger() {
		
		// suppresion de jojo intrusif ( au cas ou ) 
		/*Personne jojo = inscriptions.createPersonne("Jojo", "ejfi", "dcdc");
		flechettes.add(jojo);
		assertTrue(inscriptions.getCandidats().contains(jojo));
		flechettes.remove(jojo);
		jojo.delete();
		 try {
				inscriptions.sauvegarder();
			} catch (IOException e) {
			}
		System.out.println(inscriptions);*/
		// Creation d'une "Personne" jojo
		Personne jojo = inscriptions.createPersonne("Jojo", "ejfi", "dcdc");
		//ajout dans la compétition jojo
		flechettes.add(jojo);
		assertTrue(inscriptions.getCandidats().contains(jojo));
		//test de la methode inscription 
		inscriptions = inscriptions.recharger();
		assertFalse(inscriptions.getCandidats().contains(jojo));
	}

	@Test
	void testSauvegarder() {
		
		Personne jojo = inscriptions.createPersonne("Jojo", "ejfi", "dcdc");
		flechettes.add(jojo);
		//test de la methode "Sauvegarde"
		assertTrue(inscriptions.getCandidats().contains(jojo));
		 try {
			inscriptions.sauvegarder();
		} catch (IOException e) {
		}
		assertTrue(inscriptions.getCandidats().contains(jojo));
		//Suppression de la personnne jojo pour cohésion des autres tests
		flechettes.remove(jojo);
		jojo.delete();
		//enregistrement de la suppression
		 try {
				inscriptions.sauvegarder();
			} catch (IOException e) {
			}
		 // Chargement de l'inscription par defaut
		 inscriptions= inscriptions.recharger();
	}

}

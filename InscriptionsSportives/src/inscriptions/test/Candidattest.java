package inscriptions.test ;


import java.time.LocalDate;

import org.junit.Test;



import inscriptions.Competition;
import inscriptions.Inscriptions;
import inscriptions.Personne;
import junit.framework.TestCase;

public class Candidattest  extends TestCase{

	private String nomRobert = "Robert";
	private String nomTony = "Tony";
	private Inscriptions inscriptions;
	private Personne tony ;
	private Competition testCompet ;
	private Competition testCompet2 ;


	@Override
	protected void setUp() throws Exception
	{
		super.setUp();
		inscriptions = Inscriptions.getInscriptions();
		tony = inscriptions.createPersonne(nomTony, "lala", "azerty");
		testCompet = inscriptions.createCompetition("test", LocalDate.parse
				("2018-02-10"), false);
		testCompet2 = inscriptions.createCompetition("test", LocalDate.parse
				("2018-02-10"), false);
	}
	
	
	@Test
	public void testCandidat() throws Exception {
		
		Personne jean = inscriptions.createPersonne("pougetoux", "jean", "spyroo913@hotmail.com");
		assertEquals("pougetoux", jean.getNom());
		
	}
	
	@Test
	public void testGetNom() throws Exception {
		assertEquals(nomTony, tony.getNom());
	}

	@Test
	public void testSetNom() {
		tony.setNom(nomRobert);
		assertEquals(nomRobert, tony.getNom());
	}

	@Test
	public void testGetCompetitions() {
		assertTrue(inscriptions.getCompetitions().contains(testCompet) && inscriptions.getCompetitions().contains(testCompet2) );	}

	@Test
	public void testDelete() {
	Competition test3 = inscriptions.createCompetition("test3", null, true); 
	assertTrue(inscriptions.getCompetitions().contains(test3));
	test3.delete();
	assertFalse(inscriptions.getCompetitions().contains(test3));
	}

	@Test
	public void testCompareTo() {
		Personne a = inscriptions.createPersonne("abcd", "test", "a");
		Personne b = inscriptions.createPersonne("efgh", "test", "b");
		assertTrue( a.compareTo(b) < 0 );		
		Personne c =  inscriptions.createPersonne("abcd", "test", "a");
		assertTrue(b.compareTo(c)> 0);
		assertTrue( a.compareTo(c) == 0 );
	}
	
}
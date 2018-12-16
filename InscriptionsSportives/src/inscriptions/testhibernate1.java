package inscriptions;

import javax.persistence.*;

public class testhibernate1 {

public static void main(String[] args)  {
	
	EntityManagerFactory emf = null ; 
	EntityManager em = null ; 
	
	try {
	emf = Persistence.createEntityManagerFactory("Test");
	em = emf.createEntityManager();
	
	Competition c = em.find(Competition.class,1);
	System.out.println(c);

	}
	
	
	
	catch(PersistenceException exception){
	     System.out.println("Problem de persistence");
	     exception.printStackTrace();
	}
	
	
	finally {
		if (em != null) em.close();
		if(emf !=null) emf.close();
	}
	
	
	
}	
	
}

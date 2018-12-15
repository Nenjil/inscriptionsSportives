package inscriptions;

import javax.persistence.*;

import org.hibernate.HibernateException;

public class testhibernate1 {

public static void main(String[] args)  {
	
	EntityManagerFactory emf = null ; 
	EntityManager em = null ; 
	
	try {
	emf = Persistence.createEntityManagerFactory("Test");
	em = emf.createEntityManager();
	
	/*Competition c = em.find(Competition.class, 2);
	System.out.println(c);*/
	
	}
	
	
	
	catch(HibernateException exception){
	     System.out.println("Problem creating session factory");
	     exception.printStackTrace();
	}
	
	
	finally {
		if (em != null) em.close();
		if(emf !=null) emf.close();
	}
	
	
	
}	
	
}

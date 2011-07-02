package business;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Query;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


import controller.studentCtrl;

import model.*;


@Stateless
public class DbPerson {

	
    @PersistenceContext
    private EntityManager em;
	
	
    public DbPerson() {
		// TODO Auto-generated constructor stub
	}
    
    
    public void createPerson(
    String gender, String firstname, String lastname, String adresse,
    String email, String phone, String mobil, String keyword){
    	
    	
    	Student s = new Student( gender, firstname, lastname, adresse, email, phone, mobil, keyword);
    	em.persist(s);
    }
    
    public List<Student> getAllStudents(){
    	return em.createNamedQuery(Student.FIND_ALL, Student.class).getResultList();
    }
    
    //--------------------------------------------JL---------------------------------------------------------------------
    public boolean deletePerson(int nds){
    	Logger.getLogger(DbPerson.class.getName())
	    .log(Level.INFO, 
	    "DbPersonbefore----------------------------------------------------------------------------------------------------------------------"+nds);
    	Query query = em.createQuery("Delete FROM Student WHERE nds= :ndsquery");
    		query.setParameter("ndsquery", nds );
    		
    	Logger.getLogger(DbPerson.class.getName())
	    .log(Level.INFO, 
	    "DbPersonafter----------------------------------------------------------------------------------------------------------------------"+nds);
    	return true;
    }
    
    
}

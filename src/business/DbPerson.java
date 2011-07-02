
package business;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;


import model.*;


@Stateless
public class DbPerson {

	
    @PersistenceContext
    private EntityManager em;
	
	
    public DbPerson() {
		// TODO Auto-generated constructor stub
	}
    
    
    public void createStudent(
    String gender, String firstname, String lastname, String adresse,
    String email, String phone, String mobil, String keyword){
    		
    	Student s = new Student( gender, firstname, lastname, adresse, email, phone, mobil, keyword);
    	em.persist(s);
    }
    
    public Student findStudentByName(String name){
        Query query = em.createQuery("select c from Student c where c.name = :name");
        query.setParameter("name", name);
        Student student = (Student)query.getSingleResult();
        return student;
    }
    
    public List<Student> getAllStudents() {
        TypedQuery<Student> query = em.createQuery("select c from Student c", Student.class);
        return query.getResultList();
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

package business;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
    
    
}

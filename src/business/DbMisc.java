
package business;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import model.FieldOfStudy;
import model.Student;

@Stateless
public class DbMisc {

	@PersistenceContext
	private EntityManager em;
	
	public void createFieldsOfStudy(String name){
	
		FieldOfStudy f = new FieldOfStudy(name);
		em.persist(f);	
	}
	
	public List<FieldOfStudy> getAllFieldsOfStudy(){
		
	  TypedQuery<FieldOfStudy> query = em.createQuery("select f from FieldOfStudy f", FieldOfStudy.class);
	  return query.getResultList();
	    
	}
	
}

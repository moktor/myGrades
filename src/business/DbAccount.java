package business;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import controller.LoggedIn;

import model.Student;


@Stateless
public class DbAccount {
 @LoggedIn Student student;	
 @PersistenceContext
 private EntityManager em;
	
	
	
	 public int validate(String userName, String password){
		 Logger.getLogger(DbAccount.class.getName())
		    .log(Level.INFO, 
		    "validate 2 "+userName+" "+password);
		 
		 return 1;
		 
		 
	 }
	 
	 public List<Student> loginQuery(String nds, String password){
		
		        TypedQuery<Student> query = em.createQuery("select u from Student u where u.nds=:nds and u.keyword=:password", Student.class)

		         .setParameter("nds", nds)

		         .setParameter("password", password);
		        return query.getResultList();

		 
	 }
}

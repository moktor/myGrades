package business;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Stateless
public class DbAccount {
	
 @PersistenceContext
 private EntityManager em;
	
	
	
	 public int validate(String userName, String password){
		 Logger.getLogger(DbAccount.class.getName())
		    .log(Level.INFO, 
		    "validate 2 "+userName+" "+password);
		 
		 return 1;
		 
		 
	 }

}

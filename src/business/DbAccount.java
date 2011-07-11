package business;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import controller.LoggedIn;

import model.*;


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
  
  public List<Student> loginQuery(String nds){
     
          TypedQuery<Student> query = em.createQuery("select u from Student u where u.nds=:nds", Student.class)

           .setParameter("nds", nds);

          return query.getResultList();

   
  }
  public List<ExamAuth> loginauthQuery(String nds){
   
      TypedQuery<ExamAuth> query = em.createQuery("select u from ExamAuth u where u.nds=:nds", ExamAuth.class)

       .setParameter("nds", nds);

      return query.getResultList();


}  
public List<Admin> loginadminQuery(String nds){
   
      TypedQuery<Admin> query = em.createQuery("select u from Admin u where u.nds=:nds", Admin.class)

       .setParameter("nds", nds);

      return query.getResultList();


}  
}
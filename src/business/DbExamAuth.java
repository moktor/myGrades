package business;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import model.*;
import comparators.*;
import controller.dummy;

@Stateless
public class DbExamAuth {

	
	ExamAuth examAuth = null; // examAuth help pointer
	Admin admin = null; // admin help pointer
	
    @PersistenceContext
    private EntityManager em;
	
	
    public DbExamAuth() {
		// TODO Auto-generated constructor stub
	}
    
    // ====================================== ExamAuth =====================================================

 // ------------------------------------------ LS -----------------------------createExamAuth-------------
    // Adds a new ExamAuth to db, with all parameters
    
    public void createExamAuth( String nds, 
    	    String gender,String titel, String firstname, String name, String adresse,
    	    String email, String phone, String fieldOfStudy, String mobil, String keyword, String salt){
    	    		
    	ExamAuth ea = new ExamAuth(nds, gender,titel, firstname, name, adresse, email, phone, keyword, salt);
    	    	em.persist(ea);
    	    }
    	    
    
    // ------------------------------------------ LS -------------------------findExamAuthByNds---------------
    // find a ExamAuth by nds
    public ExamAuth findExamAuthByNds(String nds){
        
    	Query query = em.createQuery("select c from ExamAuth c where c.nds = :nds");
        query.setParameter("nds", nds);
        try{
        examAuth = (ExamAuth)query.getSingleResult();
        return examAuth;
        }catch(NoResultException e){
        	return examAuth;        	
        }
    }
       
    
    // ------------------------------------------ LS ------------------------findExamAuthByName---------------
    // find a ExamAuth by lastname 
    public ExamAuth findExamAuthByName(String name){
        Query query = em.createQuery("select c from ExamAuth c where c.name = :name");
        query.setParameter("name", name);
        try{
        examAuth = (ExamAuth)query.getSingleResult();
        return examAuth;
        }catch(NoResultException e){
        return examAuth;	
        }   
    }
       
    
    // ------------------------------------------ LS ---------------------getAllExamAuths----------------------
    // Returns a List of all ExamAuths in the db
    public List<ExamAuth> getAllExamAuths() {
        TypedQuery<ExamAuth> query = em.createQuery("select c from ExamAuth c", ExamAuth.class);
        return query.getResultList();
    }
    
    
    //--------------------------------------------LS----------------------deleteExamAuthByNds--------------------
    // deletes ExamAuth from db using nds
    public boolean deleteExamAuthByNds(String nds){
        Query query = em.createQuery("select c from ExamAuth c where c.nds = :ndsquery");
        query.setParameter("ndsquery", nds);
        try {
            
        	ExamAuth examAuth = (ExamAuth)query.getSingleResult();
        	em.remove(examAuth);
        	return true;
        } catch (NoResultException e) {
          return false;  
        }
  
        
    }
    
     
    // ------------------------------------------ LS ---------------------editExamAuth-------------------------
    // edits some parameter of given ExamAuth
    
    public boolean editExamAuth(ExamAuth examAuth){
    	
    	if(examAuth != null){
    	em.merge(examAuth);	
    	return true;
    	}else{
    	return false;	
    	}
   	}
    
    
 // ------------------------------------------ LS ---------------------deleteMultipleExamAuths-------------------------
    // deletes selected objects of a list
    
    public boolean deleteMultipleExamAuths(List<ExamAuth> list){

    	for(ExamAuth examAuth: list){
    		if(examAuth.isDeleteInc()){
    			examAuth = (ExamAuth)em.merge(examAuth);
    			em.remove(examAuth);
    		}
    	}	
    	return true;
    }

  //------------------------------------------- FM -------------- ExamAuth Filter ----
    
    public List<ExamAuth> filter(List<ExamAuth> list, String name, String nds){

     
     Logger.getLogger(dummy.class.getName())
     .log(Level.INFO, 
     "uebergabe: "+name+" .."+nds);
  
     List<ExamAuth> temp = new ArrayList<ExamAuth>();

     if(name == null){
      name = "";
     }
     if(nds == null){
      nds = "";
     }

     
     
     // ---------- no filters ------------------
     if(name.equals("") && nds.equals("")){
      
         Logger.getLogger(dummy.class.getName())
         .log(Level.INFO, 
         "kein Filter");
         return list;
     }
     
     // -------------- Name Filter ------------------
     
     if (!name.equals("") && nds.equals("")){
      
         Logger.getLogger(dummy.class.getName())
         .log(Level.INFO, 
         "Name Filter");
      
         for (Iterator iter = list.iterator(); iter.hasNext();)
         {
        	 ExamAuth o = (ExamAuth) iter.next();
             if (!name.equalsIgnoreCase(o.getName()) && !name.equals("")) // hier die gewünschte Bedingung einfügen
             {
              if(list.contains(o)){
                 iter.remove();
              }
             }
         }
      return list;
     }
     
     // --------------- Nds Filter -------------------------
     
     if(name.equals("")&& !nds.equals("")){
      
         Logger.getLogger(dummy.class.getName())
         .log(Level.INFO, 
         "Nds Filter");
      
         for (Iterator iter = list.iterator(); iter.hasNext();)
         {
        	 ExamAuth o = (ExamAuth) iter.next();
             if (!nds.equalsIgnoreCase(o.getNds()) && nds != "") // hier die gewünschte Bedingung einfügen
             {
              if(list.contains(o)){
                 iter.remove();
              }
             }
         }
      return list;
     }
     
     
     
     // ------------ Both Filters --------------------
     if(!name.equals("") && !nds.equals("")){
         Logger.getLogger(dummy.class.getName())
         .log(Level.INFO, 
         "Both Filters");
         
         for (Iterator iter = list.iterator(); iter.hasNext();)
         {
        	 ExamAuth o = (ExamAuth) iter.next();
             if (!nds.equalsIgnoreCase(o.getNds()) || !name.equalsIgnoreCase(o.getName())) // hier die gewünschte Bedingung einfügen
             {
                 Logger.getLogger(dummy.class.getName())
                 .log(Level.INFO, 
                 "Both Filters -------------------------"+o.getName());
              
              
              if(list.contains(o)){
                 iter.remove();
              }
             }
         }
         
         
     }
     
     
     return list;
     
 }
    
    
   	
    // ----------------- LS ---------------------- sorting Methods -------------------
       
       public List<ExamAuth> sortByIdExamAuth(List<ExamAuth> list){
       	Collections.sort(list, new ExamAuthId());
       	return list;
         } 
       
      
       
       public List<ExamAuth> sortByNdsExamAuth(List<ExamAuth> list){
       	Collections.sort(list, new ExamAuthNds());
       	return list;
         } 

       
       public List<ExamAuth> sortByNameExamAuth(List<ExamAuth> list){
       	Collections.sort(list, new ExamAuthName());
       	return list;
       	
         } 
       
       
       public List<ExamAuth> sortByFirstExamAuth(List<ExamAuth> list){
       	Collections.sort(list, new ExamAuthFirst());
       	return list;
         } 
       
       
}

    
// ====================================== Admin ======================================================

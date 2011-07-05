package business;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import controller.studentCtrl;


import model.*;


@Stateless
public class DbPerson {

	
	Student student = null;	// student help pointer
	ExamAuth examAuth = null; // examAuth help pointer
	
    @PersistenceContext
    private EntityManager em;
	
	
    public DbPerson() {
		// TODO Auto-generated constructor stub
	}
    
    // ====================================== Student ======================================================
    
    // ------------------------------------------ FM -----------------------------createStudent-------------
    // Adds a new Student to db, with all parameters
    
    public void createStudent( String nds, 
    String gender, String firstname, String lastname, String adresse,
    String email, String phone, String mobil, String keyword, int fachsemester){
    		
    	Student s = new Student(nds,  gender, firstname, lastname, adresse, email, phone, mobil, keyword, fachsemester);
    	em.persist(s);
    }
    
    
    // ------------------------------------------ FM -------------------------findStudentByNds---------------
    // find a student by nds
    public Student findStudentByNds(String nds){
        
    	Query query = em.createQuery("select c from Student c where c.nds = :nds");
        query.setParameter("nds", nds);
        try{
        student = (Student)query.getSingleResult();
        return student;
        }catch(NoResultException e){
        	return student;        	
        }
    }
       
    // ------------------------------------------ FM ------------------------findStudentByName---------------
    // find a student by lastname 
    public Student findStudentByName(String name){
        Query query = em.createQuery("select c from Student c where c.name = :name");
        query.setParameter("name", name);
        try{
        student = (Student)query.getSingleResult();
        return student;
        }catch(NoResultException e){
        return student;	
        }   
    }
        
    // ------------------------------------------ FM ---------------------getAllStudents----------------------
    // Returns a List of all Students in the db
    public List<Student> getAllStudents() {
        TypedQuery<Student> query = em.createQuery("select c from Student c", Student.class);
        return query.getResultList();
    }
    
    //--------------------------------------------JL----------------------deleteStudentByNds--------------------
    // deletes student from db using nds
    public boolean deleteStudentByNds(String nds){
        Query query = em.createQuery("select c from Student c where c.nds = :ndsquery");
        query.setParameter("ndsquery", nds);
        try {
            
        	Student student = (Student)query.getSingleResult();
        	em.remove(student);
        	return true;
        } catch (NoResultException e) {
          return false;  
        }
  
        
    }
    
    // ------------------------------------------ FM ---------------------editStudent-------------------------
    // edits some parameter of given student
    
    public boolean editStudent(Student student){
    	
    	if(student != null){
    	em.merge(student);	
    	return true;
    	}else{
    	return false;	
    	}
   	}
    
 // ------------------------------------------ FM ---------------------deleteMultipleStudents-------------------------
    // deletes selected objects of a list
    
    public boolean deleteMultipleStudents(List<Student> list){
    	
    	for(Student student: list){
    		if(student.isDeleteInc()){
    			student = (Student)em.merge(student);
    			em.remove(student);
    		}
    	}
    	
    	
    	return true;
    }
    
    
    
    
    
 // ------------------------------------------ LS -----------------------------createExamAuth-------------
    // Adds a new Student to db, with all parameters
    
    public void createExamAuth( String nds, 
    	    String gender,String titel, String firstname, String lastname, String adresse,
    	    String email, String phone, String mobil, String keyword){
    	    		
    	ExamAuth ea = new ExamAuth(nds, gender,titel, firstname, lastname, adresse, email, phone, mobil, keyword);
    	    	em.persist(ea);
    	    }
    	    
    
    
    // ------------------------------------------ LS -------------------------findStudentByNds---------------
    // find a student by nds
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
       
    // ------------------------------------------ LS ------------------------findStudentByName---------------
    // find a student by lastname 
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
        
    // ------------------------------------------ LS ---------------------getAllStudents----------------------
    // Returns a List of all Students in the db
    public List<ExamAuth> getAllExamAuths() {
        TypedQuery<ExamAuth> query = em.createQuery("select c from ExamAuth c", ExamAuth.class);
        return query.getResultList();
    }
    
    //--------------------------------------------LS----------------------deleteStudentByNds--------------------
    // deletes student from db using nds
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
    
    
    
    
    // ------------------------------------------ LS ---------------------editStudent-------------------------
    // edits some parameter of given student
    
    public boolean editExamAuth(ExamAuth examAuth){
    	
    	if(examAuth != null){
    	em.merge(examAuth);	
    	return true;
    	}else{
    	return false;	
    	}
   	}
    
 // ------------------------------------------ LS ---------------------deleteMultipleStudents-------------------------
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
    
    
    
    
    
    
    // =====END==Student=====
    
    
    //===========================================
    
    
}


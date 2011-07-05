package business;

import java.util.Collection;
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

import controller.studentCtrl;


import model.*;
import comparators.*;

@Stateless
public class DbPerson {

	
	Student student = null;	// student help pointer
	
	
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

    
 // ----------------- FM ---------------------- sorting Methods -------------------
    
    public List<Student> sortById(List<Student> list){
    	//StudentName n = new StudentName();
    	Collections.sort(list, new StudentId());
    	return list;
      } 
    
    public List<Student> sortByNds(List<Student> list){
    	Collections.sort(list, new StudentNds());
    	return list;
      } 
    
    
    public List<Student> sortByName(List<Student> list){
    	Collections.sort(list, new StudentName());
    	return list;
    	
      } 
    public List<Student> sortByFirst(List<Student> list){
    	Collections.sort(list, new StudentFirst());
    	return list;
      } 
    public List<Student> sortByAdress(List<Student> list){
    	Collections.sort(list, new StudentAdress());
    	return list;
      } 
    public List<Student> sortByFieldOfStudy(List<Student> list){
    	//Collection<Student> coList = list;
    	//Collections.sort(list, new StudentFieldOfStudy()); TODO
    	return list;
    	
      } 


    	
    
    
    
    
    
    // =====END==Student=====
    
    
    //===========================================
    
    
}


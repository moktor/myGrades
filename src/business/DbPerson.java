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
public class DbPerson {

	
	Student student = null;	// student help pointer
	ExamAuth examAuth = null; // examAuth help pointer
	Admin admin = null; // admin help pointer
	
    @PersistenceContext
    private EntityManager em;
	
	
    public DbPerson() {
		// TODO Auto-generated constructor stub
	}
    
    // ====================================== Student ======================================================

    public void createStudent( String nds, 
    String gender, String firstname, String lastname, String adresse,
    String email, String phone, String mobil, String keyword, int fachsemester, String salt){
    		
    	Student s = new Student(nds,  gender, firstname, lastname, adresse, email, phone, mobil, keyword, fachsemester, salt);
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
    
 // ------------------------------------------ MH -------------------------findStudentById---------------
    // find a student by nds
    public Student findStudentById(int id){
        
    	Query query = em.createQuery("select c from Student c where c.id = :id");
        query.setParameter("id", id);
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

    
  //------------------------------------------- FM -------------- Student Filter ----
    
    public List<Student> filter(List<Student> list, String name, String nds){

     
     Logger.getLogger(dummy.class.getName())
     .log(Level.INFO, 
     "uebergabe: "+name+" .."+nds);
  
     List<Student> temp = new ArrayList<Student>();

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
             Student o = (Student) iter.next();
             if (!name.equalsIgnoreCase(o.getName()) && !name.equals("")) // hier die gew�nschte Bedingung einf�gen
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
             Student o = (Student) iter.next();
             if (!nds.equalsIgnoreCase(o.getNds()) && nds != "") // hier die gew�nschte Bedingung einf�gen
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
             Student o = (Student) iter.next();
             if (!nds.equalsIgnoreCase(o.getNds()) || !name.equalsIgnoreCase(o.getName())) // hier die gew�nschte Bedingung einf�gen
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

    
// ====================================== Admin ======================================================
    
    // ------------------------------------------ LS -----------------------------createAdmin-------------
    // Adds a new Admin to db, with all parameters
    
    public void createAdmin( String nds, 
    String gender, String firstname, String lastname, String adresse,
    String email, String phone, String mobil,String keyword, String salt){
    		
    	Admin s = new Admin(nds,  gender, firstname, lastname, adresse, email, phone, mobil, keyword, salt);
    	em.persist(s);
    }
    
    
    // ------------------------------------------ LS ------------------------findAdminByName---------------
    // find a Admin by lastname 
    public Admin findAdminByName(String name){
        Query query = em.createQuery("select c from Admin c where c.name = :name");
        query.setParameter("name", name);
        try{
        admin = (Admin)query.getSingleResult();
        return admin;
        }catch(NoResultException e){
        return admin;	
        }   
    }
       
    
    // ------------------------------------------ LS ---------------------getAllAdmins----------------------
    // Returns a List of all Admins in the db
    public List<Admin> getAllAdmins() {
        TypedQuery<Admin> query = em.createQuery("select c from Admin c ", Admin.class);
      
        return query.getResultList();
    }
    
    
    //--------------------------------------------LS----------------------deleteAdminByNds--------------------
    // deletes admin from db using nds
    public boolean deleteAdminByNds(String nds){
        Query query = em.createQuery("select c from Admin c where c.nds = :ndsquery");
        query.setParameter("ndsquery", nds);
        Admin admin = (Admin)query.getSingleResult();
    	em.remove(admin);
    	return true;
    }

    
//------------------------------------- LS ---------------------editAdmin-------------------------
    // edits some parameter of given Admin
    
    public boolean editAdmin(Admin admin){
    	
    	if(admin != null){
    	em.merge(admin);	
    	return true;
    	}else{
    	return false;	
    	}
   	}
    
    
 // ------------------------------------------ LS ---------------------deleteMultipleAdmins-------------------------
    // deletes selected objects of a list
    
    public boolean deleteMultipleAdmins(List<Admin> list){

    	for(Admin admin: list){
    		if(admin.isDeleteInc()){
    			admin = (Admin)em.merge(admin);
    			em.remove(admin);
    		}
    	}	
    	return true;
    }
   

   // ----------------- LS ---------------------- sorting Methods Admin-------------------
       
       public List<Admin> sortByIdAdmin(List<Admin> list){
       	Collections.sort(list, new AdminId());
       	return list;
         } 
       
       public List<Admin> sortByNdsAdmin(List<Admin> list){
       	Collections.sort(list, new AdminNds());
       	return list;
         } 
       
       
       public List<Admin> sortByNameAdmin(List<Admin> list){
       	Collections.sort(list, new AdminName());
       	return list;
       	
         } 
       public List<Admin> sortByFirstAdmin(List<Admin> list){
       	Collections.sort(list, new AdminFirst());
       	return list;
         } 
       public List<Admin> sortByAdressAdmin(List<Admin> list){
       	Collections.sort(list, new AdminAdress());
       	return list;
         } 
     
    
}


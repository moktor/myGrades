
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

import controller.dummy;


import model.*;
 //================================= AS ===========================================

@Stateless
public class DbCourses {
	
	Course course = null;
	
	@PersistenceContext
	private EntityManager em;
	
	public DbCourses() {
		// TODO Auto-generated constructor stub
	}

	public void createCourse( String name, int studentCount){
		    		
		Course c = new Course (name, studentCount);
		    	em.persist(c);
		    }
}


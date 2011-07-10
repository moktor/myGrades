
package business;

import java.sql.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import controller.courseCtrl;

import model.*;
 //================================= AS ===========================================

@Stateless
public class DbCourse {
	
	Course course = null;
	
	@PersistenceContext
	private EntityManager em;
	
	public DbCourse() {
		// TODO Auto-generated constructor stub
	}

	public void createCourse(int studentcount, String name, String fieldofstudy, Date date){
		    		
		Course c = new Course (studentcount, name, fieldofstudy, date);
		Logger.getLogger(courseCtrl.class.getName())
	    .log(Level.INFO,"DbCourse  /////////////////////////////////////////////");
    	
		    	em.persist(c);
		}

	public List<Course> getAllCourses() {
		TypedQuery<Course> query = em.createQuery("select c from Course c", Course.class);
        return query.getResultList();
	}

	public boolean editCourse(Course course) {
		if(course != null){
	    	em.merge(course);
	    	return true;
	    } else {
	    return false;	
	    }
	}
	
    
    
	public List<Course> sortById(List<Course> list) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Course> sortByStudentCount(List<Course> list) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Course> sortByName(List<Course> list) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Course> sortByFieldOfStudy(List<Course> list) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Course> sortByDate(List<Course> list) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean deleteStudentById(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	public void deleteMultipleCourses(List<Course> courseList) {
		// TODO Auto-generated method stub
		
	}

	
}


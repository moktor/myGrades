
package business;

import java.sql.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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

	public Enrollment joinTest() {
		TypedQuery<Enrollment> query = em.createQuery("select e from Enrollment e where e.id = 53", Enrollment.class);
        return query.getSingleResult();
	}

	public List<Enrollment> getStudentsByCourseId(int courseId) {
		TypedQuery<Enrollment> query = em.createQuery("select e from Enrollment e where e.parentCourse.id = :courseId", Enrollment.class);
		query.setParameter("courseId", courseId);
		List<Enrollment> enrollment = null;
		try{
        	enrollment = query.getResultList();
        	Logger.getLogger(courseCtrl.class.getName()).log(Level.INFO, "return object");
        return enrollment;
        } catch(NoResultException e){
        	Logger.getLogger(courseCtrl.class.getName()).log(Level.INFO, "return null");
        	return enrollment;        	
        }
	}

	public boolean editEnrollment(List<Enrollment> enrollmentList) {
		if(enrollmentList != null)  {
	    	em.merge(enrollmentList);
	    	return true;
	    } else {
	    return false;	
	    }
	}

	public List<Course> coursesToEnroll(int id) {
		TypedQuery<Course> query = em.createQuery("select distinct c from Course c, NOT IN (select e from Enrollment e where e.parentStudent.id = :id)", Course.class);
        return query.getResultList();
	}

	public List<Enrollment> allEnrollments(int id) {
		TypedQuery<Enrollment> query = em.createQuery("select e from Enrollment e where e.parentStudent.id = :id AND e.grade = 0", Enrollment.class);
		query.setParameter("id", id);
		List<Enrollment> enrollment = null;
		try{
        	enrollment = query.getResultList();
        	Logger.getLogger(courseCtrl.class.getName()).log(Level.INFO, "return object");
        return enrollment;
        } catch(NoResultException e){
        	Logger.getLogger(courseCtrl.class.getName()).log(Level.INFO, "return null");
        	return enrollment;        	
        }
	}
	
}


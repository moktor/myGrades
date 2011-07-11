
package business;

import java.sql.Date;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import controller.courseCtrl;
import controller.emailCtrl;

import model.*;
 //================================= AS ===========================================

@Stateless
public class DbCourse {
	
	Course course = null;
	DbPerson dbP = new DbPerson();
	
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
	
	public void createEnrollments(){
		    		
		TypedQuery<Student> studentQuery = em.createQuery("select s from Student s", Student.class);
        List<Student> studentList = studentQuery.getResultList();
        
        TypedQuery<Course> courseQuery = em.createQuery("select c from Course c", Course.class);
        List<Course> courseList = courseQuery.getResultList();
		
        
		for (Student student: studentList) {
			for (Course course: courseList) {
				Random random = new Random();
				boolean doIt = random.nextBoolean(); // random boolean
				if (doIt) {
					//int grade = (int) Math.random() * 14; //generate random grade
					int grade = 0; // all Students ungraded;
					Enrollment enrollment = new Enrollment(student, course, grade);
					em.persist(enrollment);
				}
			}
		}
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
	
	public boolean isValidTan(String tan) {
		TypedQuery<Tan> query = em.createQuery("select t from Tan t where t.tan = :tan", Tan.class);
		query.setParameter("tan", tan);
        
		try {
			if (query.getSingleResult() != null);
			return true;
		} catch (Exception e) {
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

	 // ------------------------------------------ FM ---------------------deleteMultipleStudents-------------------------
    // deletes selected objects of a list
    
    public boolean deleteSelectedCourses(List<Course> list){
    	
    	for(Course course: list){
    		if(course.isSelected()){
    			course = (Course)em.merge(course);
    			em.remove(course);
    			course.setSelected(false);
    		}
    	}
    	return true;
    }
	
	//Methoden für die Enrollment Klasse

	//Alle Studenten, die zu einem bestimmten Kurs eingeschrieben sind
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

	// Anmelden/Abmelden/Benoten
	public boolean updateGrades(List<Enrollment> enrollmentList) {
		emailCtrl emailCtrl = new emailCtrl();
		
		for(Enrollment e: enrollmentList){
			int id = e.getId();
			TypedQuery<Enrollment> query = em.createQuery("select e from Enrollment e where e.id = :id", Enrollment.class);
			query.setParameter("id", id);
			Enrollment enrollment = query.getSingleResult();
			int oldGrade = enrollment.getGrade();
			
			em.merge(e);
    		
			if (e.getGrade() != oldGrade) {
				emailCtrl.sendEmail(e.getParentStudent().getEmail(), e.getParentCourse().getName());
			}
    	}
		
		return true;
	}

	// Alle Kurse, zu denen der Student noch nicht eingeschrieben ist, also kein Enrollment hat
	public List<Course> coursesToEnroll(int id) {
		TypedQuery<Course> query = em.createQuery("select c from Course c where c.id NOT IN (select e.parentCourse.id from Enrollment e where e.parentStudent.id = :id)", Course.class);
		query.setParameter("id", id);
		List<Course> enrollment = null;
		try{
        	enrollment = query.getResultList();
        	Logger.getLogger(courseCtrl.class.getName()).log(Level.INFO, "return object");
        return enrollment;
        } catch(NoResultException e){
        	Logger.getLogger(courseCtrl.class.getName()).log(Level.INFO, "return null");
        	return enrollment;        	
        }
	}
	
	// Zu ausgewählten Kursen einschreiben
	public void enrollToSelected(List<Course> list, int id, String tan){
		Logger.getLogger(courseCtrl.class.getName()).log(Level.INFO, "Übergebene Kurse " + list);
		
		Query tanQuery = em.createQuery("select t from Tan t where t.tan = :tan", Tan.class);
		tanQuery.setParameter("tan", tan);
		Tan tanObject = (Tan)tanQuery.getSingleResult();
		tanObject = (Tan)em.merge(tanObject);
		em.remove(tanObject);
		
		Student student = null;
		Query query = em.createQuery("select s from Student s where s.id = :id", Student.class);
        query.setParameter("id", id);
        try {
        	student = (Student)query.getSingleResult();
        } catch(NoResultException e) {}
		
		Logger.getLogger(courseCtrl.class.getName()).log(Level.INFO, "Student gefunden " + id + " " + student);
		
    	for(Course course: list){
    		if(course.isSelected()){
    			Enrollment enrollment = new Enrollment(student, course, 0);
    			em.persist(enrollment);
    			course.setSelected(false);
    		}
    	}
    }
	
	
	// Alle Kurse eines betimmten Studenten, die noch nicht bewertet wurden!
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
	
	//Von ausgewählten Kursen abmelden
	public void signOffSelected(List<Enrollment> list, int studentId, String tan){
		Logger.getLogger(courseCtrl.class.getName()).log(Level.INFO, "Liste: " + list);
		
		Query tanQuery = em.createQuery("select t from Tan t where t.tan = :tan", Tan.class);
		tanQuery.setParameter("tan", tan);
		Tan tanObject = (Tan)tanQuery.getSingleResult();
		tanObject = (Tan)em.merge(tanObject);
		em.remove(tanObject);
		
		for(Enrollment enrollment: list){
			
    		
			if(enrollment.isSelected()){
    			//Suche alle Enrollments zum jeweiligen Kurs
    			Logger.getLogger(courseCtrl.class.getName()).log(Level.INFO, "checkpoint 3");
    			int enrollmentId = enrollment.getId();
    			List<Enrollment> enrollmentList = null;
    			
    			TypedQuery<Enrollment> query = em.createQuery("select e from Enrollment e where e.parentStudent.id = :studentId AND e.id = :enrollmentId", Enrollment.class);
    			query.setParameter("studentId", studentId);
    			query.setParameter("enrollmentId", enrollmentId);
    			
    			Logger.getLogger(courseCtrl.class.getName()).log(Level.INFO, "checkpoint 4");
    			enrollmentList = query.getResultList();
    			
    			//Iteriere durch die gefundene Liste, und lösche alle Enrollment-Einträge aus der DB
    			for(Enrollment e: enrollmentList){
    				Logger.getLogger(courseCtrl.class.getName()).log(Level.INFO, "checkpoint 5");
    				e = (Enrollment)em.merge(e);
        			em.remove(e);
    			}
    			
    			Logger.getLogger(courseCtrl.class.getName()).log(Level.INFO, "checkpoint 6");
    			//Setze den Selected State des Kurses wieder auf falsch
				enrollment.setSelected(false);
    		}
    	}
    }
	
	// Die Kurse eines Studenten, die schon bewertet wurden, Note 0 steht dabei für unbewertet
	public List<Enrollment> getGradedCourses(int id) {
		TypedQuery<Enrollment> query = em.createQuery("select e from Enrollment e where e.parentStudent.id = :id AND e.grade <> 0", Enrollment.class);
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

	// Durchschnittsnote berechnen
	public String getAverage(int id) {
		TypedQuery<Enrollment> query = em.createQuery("select e from Enrollment e where e.parentStudent.id = :id AND e.grade <> 0", Enrollment.class);
		query.setParameter("id", id);
		List<Enrollment> enrollment = null;
		
		double sum = 0;
		int count = 0;
		courseCtrl courseCtrl = new courseCtrl();
		
		try{
        	enrollment = query.getResultList();
        	
        	for(Enrollment e: enrollment) {
    			sum = sum + courseCtrl.convertGrade(e.getGrade());
    			count++;
    		}
        	
        	if (count == 0)
        		return "Durchschnittsnote: Kein Eintrag gefunden";        
        	
        	return "Durchschnittsnote: " + sum/count;
        	
        } catch(Exception e){
        	
        	return "Kein Eintrag gefunden";        	
        }
	}
	
	
	
}


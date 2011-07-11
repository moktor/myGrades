package model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Enrollment
 *
 */
@Entity
public class Enrollment implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5743572355634097500L;

	
	@Transient
	private boolean selected;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@ManyToOne(optional=false)
	private Student parentStudent; //Fremdschlüssel
	
	@ManyToOne(optional=false)
	private Course parentCourse; //Fremdschlüssel
	
	private int grade;

	public Enrollment() {
		
	}
	
	public Enrollment(Student student, Course course, int grade) {
		this.parentStudent = student;
		this.parentCourse = course;
		this.grade = grade;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public void setParentStudent(Student parentStudent) {
		this.parentStudent = parentStudent;
	}

	public Student getParentStudent() {
		return parentStudent;
	}

	public void setParentCourse(Course parentCourse) {
		this.parentCourse = parentCourse;
	}

	public Course getParentCourse() {
		return parentCourse;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}
   
}

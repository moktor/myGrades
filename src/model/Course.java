package model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: course
 *
 */
@Entity

//=============================================AS=======================================

public class Course implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8647744441303798605L;

	// ------------------ attributes of the Entity ----------------	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private int studentCount;
	private String name;
	private String fieldOfStudy;
	private Date date;
	
	// ------------------------ ctors -----------------------------------
	
	public Course(){}
	
	public Course(int studentCount, String name, String fieldOfStudy, Date date) {
		super();
		this.studentCount = studentCount;
		this.name = name;
		this.fieldOfStudy = fieldOfStudy;
		this.date = date;
	}
	

	// --------------------- getter / setter ----------------------------
	
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getStudentCount() {
		return studentCount;
	}

	public void setStudentCount(int studentCount) {
		this.studentCount = studentCount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFieldOfStudy() {
		return fieldOfStudy;
	}

	public void setFieldOfStudy(String fieldOfStudy) {
		this.fieldOfStudy = fieldOfStudy;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	

   
}

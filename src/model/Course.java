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
	private int studentcount;
	private String name;
	private String fieldofstudy;
	private Date date;
	
	// ------------------------ ctors -----------------------------------
	
	public Course(){}
	
	public Course(int studentCount, String name, String fieldofstudy, Date date) {
		super();
		this.studentcount = studentCount;
		this.name = name;
		this.fieldofstudy = fieldofstudy;
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
		return studentcount;
	}

	public void setStudentCount(int studentCount) {
		this.studentcount = studentCount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFieldOfStudy() {
		return fieldofstudy;
	}

	public void setFieldOfStudy(String fieldOfStudy) {
		this.fieldofstudy = fieldOfStudy;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	

   
}

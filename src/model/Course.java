package model;

import java.io.Serializable;
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
	private String name;
	private int studentCount;
	
	// ------------------------ ctors -----------------------------------
	
	public Course(){}
	
	public Course (String name, int studentCount){
		
		this.name = name;
		this.studentCount = 0;
	}
	
	// --------------------- getter / setter ----------------------------
	
	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStudentCount() {
		return studentCount;
	}

	public void setStudentCount(int studentCount) {
		this.studentCount = studentCount;
	}

   
}

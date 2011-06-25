package model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: course
 *
 */
@Entity

public class Course implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8647744441303798605L;

	@Id
	private int id;

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}
	
	
   
}

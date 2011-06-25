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

	@Id
	private int id;

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}
   
}

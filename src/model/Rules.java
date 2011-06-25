package model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Rules
 *
 */
@Entity

public class Rules implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6953346039449585581L;

	@Id
	private int id;

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

   
}

package model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Accomplishment
 *
 */
@Entity

public class Accomplishment implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5057198559222999014L;

	@Id
	private int id;

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

   
}

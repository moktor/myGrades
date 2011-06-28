package model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Location
 *
 */
@Entity

public class Location implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6562744950368502216L;

	@Id
	private int id;

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

   
}

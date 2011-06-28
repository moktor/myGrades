package model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Faculty
 *
 */
@Entity

public class Faculty implements Serializable {

	private static final long serialVersionUID = 7173890857490699120L;

	@Id
	private int id;
	
	private String name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
   
}

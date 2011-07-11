package model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Tan
 *
 */
@Entity

public class Tan implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7850431363177298130L;

	@Id
	private int id;
	private String tan;

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public String getTan() {
		return tan;
	}

	public void setTan(String tan) {
		this.tan = tan;
	}
   
}

package model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: AcceptTest
 *
 */
@Entity

public class AcceptTest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 886017274417090807L;


	@Id
	private int id;
	private String name;
	


	public void setId(int id) {
		this.id = id;
	}


	public int getId() {
		return id;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getName() {
		return name;
	}
	
	
   
}

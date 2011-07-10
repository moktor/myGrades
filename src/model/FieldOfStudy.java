package model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: FieldOfStudy
 *
 */
@Entity

public class FieldOfStudy implements Serializable {

	private static final long serialVersionUID = -1114601378177859227L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String name;

	
	
	public FieldOfStudy(){}
	
	public FieldOfStudy(String name){
		this.name = name;
	}
	
	
	
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

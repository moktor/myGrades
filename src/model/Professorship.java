package model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Professorship
 *
 */
@Entity

public class Professorship implements Serializable {

	private static final long serialVersionUID = -7967433664630217813L;
	
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

	@Id
	private int id;
	
	private String name;   
}

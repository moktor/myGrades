package controller;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.Student;

import javax.ejb.EJB;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;
import business.*;



@Named
@SessionScoped
public class studentCtrl implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5184733444596178113L;
	private String firstname;
	private String lastname;
	private String adresse;
	

	@EJB
	DbPerson a;
	

	
	
	public String createStudent(){
	Logger.getLogger(studentCtrl.class.getName())
    .log(Level.INFO, 
    "studentctrl"+firstname+" "+lastname+" "+adresse, firstname);

		a.createStudent(firstname, lastname, adresse);
		return "addStudent";
	}
	
	
	
	
	public String test(){
		
		
		return "delStudent";
	}
	
	
	//----------------- Getter / Setter ------------------------------------


	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}


	public String getFirstname() {
		return firstname;
	}


	public void setLastname(String lastname) {
		this.lastname = lastname;
	}


	public String getLastname() {
		return lastname;
	}


	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}


	public String getAdresse() {
		return adresse;
	}
	
	
	
	
	
}

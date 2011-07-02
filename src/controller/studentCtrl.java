package controller;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import business.*;
import model.Student;


@Named
@SessionScoped
public class studentCtrl implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5184733444596178113L;
	private int nds;
	private String gender;
	private String firstname;
	private String lastname;
	private String adresse;
	private String email;
	private String phone;
	private String mobil;
	private String keyword;
	

	@EJB
	DbPerson dbP;
	

	
	
	public String createStudent(){
	Logger.getLogger(studentCtrl.class.getName())
    .log(Level.INFO, 
    "studentctrl"+firstname+" "+lastname+" "+adresse, firstname);
	 
		dbP.createPerson( gender, firstname, lastname, adresse, email, phone, mobil, keyword);		
		return "addStudent";
	}
	
	
	
	public List<Student>getAllStudents(){
		
		Logger.getLogger(studentCtrl.class.getName())
	    .log(Level.INFO, 
	    dbP.getAllStudents().toString()+" dinge", firstname);
		
		
		return dbP.getAllStudents();	
	}
	
	
	
	
	
	
	
	
	public String test(){
		
		
		return "delStudent";
	}
	
	//-------------------------------------JL-----------------------------------------------------
	public String deleteStudent(){
		Logger.getLogger(studentCtrl.class.getName())
	    .log(Level.INFO, 
	    "studentctrl----------------------------------------------------------------------------------------------------------------------"+nds);
		if(dbP.deletePerson(this.nds) == true){
			Logger.getLogger(studentCtrl.class.getName())
		    .log(Level.INFO, 
		    "studentctrl-------------------------------------------!!!!!!!!!!!!!!!!!!!--------------------------------------------"+nds);
		return "delSuccess";
		}
		else
		return "delError";
		
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

//------------------ Delete Setter for empty input text field by JL -------------------------------
	public int ndsSetEmpty(int nds){
		
		//this.nds = nds;
		
		return nds;
		
	}


	public void setNds(int nds) {
		this.nds = nds;
	}




	public int getNds() {
		return nds;
	}




	public String getGender() {
		return gender;
	}




	public void setGender(String gender) {
		this.gender = gender;
	}




	public String getEmail() {
		return email;
	}




	public void setEmail(String email) {
		this.email = email;
	}




	public String getPhone() {
		return phone;
	}




	public void setPhone(String phone) {
		this.phone = phone;
	}




	public String getMobil() {
		return mobil;
	}




	public void setMobil(String mobil) {
		this.mobil = mobil;
	}




	public String getKeyword() {
		return keyword;
	}




	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	

	
	
	
	
}

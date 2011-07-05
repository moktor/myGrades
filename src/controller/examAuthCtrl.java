package controller;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Iterator;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;


import business.*;
import model.ExamAuth;
import model.Student;



@Named
@SessionScoped
public class examAuthCtrl implements Serializable {

	/**
	 * 
	 */
	private String nds;
	private String gender;
	private String titel;
	private String firstname;
	private String lastname;
	private String adresse;
	private String email;
	private String phone;
	private String mobil;
	private String keyword;
	
	
	private ExamAuth currentExamAuth;
	
	private ExamAuth examAuth; // temp ExamAuth
	private List<ExamAuth> examAuthList;
	

	

	

	@EJB
	DbPerson dbP;
	

	
	// ------------------------- LS -------------------------createExamAuth ---------------------
	// Creates a new ExamAuth using all db col params
	
	public String createExamAuth(){
		dbP.createExamAuth( nds, gender, titel, firstname, lastname, adresse, email, phone, mobil, keyword);		
		return "addExamAuth";
	}
	
	public String addExamAuthHelper(){
		  nds = "";
		  gender= "";
		  titel="";
		  firstname= "";
		  lastname= "";
		  adresse= "";
		  email= "";
		  phone= "";
		  mobil= "";
		  keyword= "";	 
		return "addExamAuth";
	}
	
	
	// ------------------------------ LS --------------------getExamAuth--------------------------
	// Finds a single ExamAuth from db, searchparam: lastname
	//additional test logger and ExamAuth object / TODO
	public void getExamAuth(){
		
		ExamAuth a = dbP.findExamAuthByName("lastname");
		ExamAuth b = dbP.findExamAuthByNds("24");
			
		if(b !=null){
			String nds = b.getFirstname();			
		}else{
			//no object
		}
		String lastname = a.getFirstname();
		

	}

	// --------------------- LS ------------------------getAllExamAuths----------------------
	// Returns a list of all ExamAuths
	//additional test logger for view of all ExamAuths
	public List getAllExamAuths(){
		
		List<ExamAuth> list = dbP.getAllExamAuths();
		
		examAuthList = list;
		
		return list;
	}
	
	//-------------------------LS----------------deleteExamAuth-----------------
	//deletes a ExamAuth from db, using nds (primary key)
	public String deleteExamAuth(){

		if(dbP.deleteExamAuthByNds(this.nds) == true){
		return "delSuccess";
		}
		else
		return "delError";
		
	}
	// ----------------------- LS ---------------------deleteSelected----------------
    // deletes selected ExamAuths
	
	public String deleteSelected(){
	
		dbP.deleteMultipleExamAuths(examAuthList);
		
		return "auth_delSuccess";
	}
	
	
    // ----------------------- LS ---------------------editExamAuth----------------
    // edits some parameter of given ExamAuth
        
    public String edit(ExamAuth ea){
    	
    	setCurrentExamAuth(ea);
    	
    	Logger.getLogger(examAuthCtrl.class.getName())
	    .log(Level.INFO, 
	    "examAuthCtrl Liste  "+ea.getFirstname());
    	
    	return "editExamAuth";
    }
    
 // ----------------------- LS ---------------------updateExamAuth----------------  
    public String updateExamAuth(){
    	
    	
    if( dbP.editExamAuth(currentExamAuth)){
    	return "auth_examinerdata";
    }else	
    {
    	return "auth_editError";
    }
    	
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
	
	public void setNds(String nds) {
		this.nds = nds;
	}

	public String getNds() {
		return nds;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public String getTitel() {
		return titel;
	}

	public void setTitel(String titel) {
		this.titel = titel;
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

	public void setCurrentExamAuth(ExamAuth currentExamAuth) {
		this.currentExamAuth = currentExamAuth;
	}
	
	
	public ExamAuth getCurrentExamAuth() {
		return currentExamAuth;
	}
	



}






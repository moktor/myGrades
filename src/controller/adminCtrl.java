package controller;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;


import business.*;
import model.Admin;




@Named
@SessionScoped
public class adminCtrl implements Serializable {

	/**
	 * 
	 */
	private int id;
	private String nds;
	private String gender;
	private String firstname;
	private String lastname;
	private String adresse;
	private String email;
	private String phone;
	private String mobil;
	private String keyword;
	private String salt;
	
	
	//---------------- LS ------------- sorting variables ---------------------------
	private boolean sortId;
	private boolean sortNds;
	private boolean sortName;
	private boolean sortFirst;
	
	
	private Admin currentAdmin;
	
	private Admin admin; 
	private List<Admin> adminList;
	

	

	

	@EJB
	DbPerson dbP;
	

	
	// ------------------------- LS -------------------------createAdmin ---------------------
	// Creates a new Admin using all db col params
	
	public String createAdmin(){
		dbP.createAdmin( nds, gender, firstname, lastname, adresse, email, phone, mobil, keyword, salt);		
		return "auth_examinerdata.xhtml";
	}
	
	public String addAdminAuthHelper(){
		  nds = "";
		  gender= "";
		  firstname= "";
		  lastname= "";
		  adresse= "";
		  email= "";
		  phone= ""; 
		  mobil= "";
		  keyword= "";	 
		return "addAdmin";
	}
	

	// --------------------- LS ------------------------Admins----------------------

	public List<Admin> getAllAdmins(){
		List<Admin> list = dbP.getAllAdmins();
		adminList = sortListAdmin(list);
		return list;
	}
	
	// --------------------- LS ------------------------sortStudents----------------------
	
	public List<Admin> sortListAdmin(List<Admin> list){
		
		if(sortId){
			list = dbP.sortByIdAdmin(list);
		}
		if(sortNds){
			list = dbP.sortByNdsAdmin(list);
		}
		
		
		if (sortName){
		list = dbP.sortByNameAdmin(list);
		}
		
		if(sortFirst){
		list = dbP.sortByFirstAdmin(list);
		}
		
	
		return list;
		
	}
	
	
	public void sortAdminsByID(){
		sortId = true;
		sortNds = false;
		sortName = false;
		sortFirst = false;
	
	}
	
	public void sortAdminsByNds(){
		sortId = false;
		sortNds = true;
		sortName = false;
		sortFirst = false;
	}
	
	public void sortAdminsByName(){
		sortId = false;
		sortNds = false;
		sortName = true;
		sortFirst = false;
		
	}
	
	public void sortAdminsByFirst(){
		sortId = false;
		sortNds = false;
		sortName = false;
		sortFirst = true;
	}
	

	
	
	//-------------------------LS----------------deleteAdmin-----------------
	//deletes a Admin from db, using nds (primary key)
	public String deleteAdmin(){

		if(dbP.deleteAdminByNds(this.nds) == true){
		return "delSuccess";
		}
		else
		return "delError";
		
	}
	// ----------------------- LS ---------------------deleteSelected----------------
    // deletes selected Admins
	
	public String deleteSelected(){

		
		dbP.deleteMultipleAdmins(adminList);
		
		return "admin_delSuccess";
	}
	
	
    // ----------------------- LS ---------------------editAdmin----------------
    // edits some parameter of given Admin
        
    public String edit(Admin ea){
    	
    	setCurrentAdmin(ea);
    	
    	Logger.getLogger(adminCtrl.class.getName())
	    .log(Level.INFO, 
	    "adminCtrl Liste  "+ea.getFirstname());
    	
    	return "editAdmin";
    }
    
 // ----------------------- LS ---------------------updateAdmin----------------  
    public String updateAdmin(){
    	
    	
    if( dbP.editAdmin(currentAdmin)){
    	return "auth_examinerdata";
    }else	
    {
    	return "admin_editError";
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

	public void setCurrentAdmin(Admin currentAdmin) {
		this.currentAdmin = currentAdmin;
	}
	
	
	public Admin getCurrentAdmin() {
		return currentAdmin;
	}
	
	


}






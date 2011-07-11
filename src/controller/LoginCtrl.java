package controller;



import java.io.Serializable;
import java.security.MessageDigest;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Named;
import javax.faces.context.FacesContext;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;


import model.*;

import business.*;
@Named
@SessionScoped
public class LoginCtrl implements Serializable {
 
 /**
  * 
  */
 private static final long serialVersionUID = 5186306447159703311L;
 
 private String nds, password, firstname, lastname, email, salt, keyword;

 private int id;
 


private boolean loggedIn = false;
private boolean loggedInadmin = false;
private boolean loggedInauth = false;


 private Person loggedinstudent;

 @EJB
 DbAccount dba;
 @EJB
 DbPerson dbP;
 
  
 
 public String getnds() {
  return nds;
 }



 public void setnds(String nds) {
  this.nds = nds;
 }



 public String getPassword() {
  return password;
 }



	//======================================= AS ==========================================


public void setPassword(String password)throws Exception {
	 MessageDigest md = MessageDigest.getInstance("MD5");
	 md.update(password.getBytes());
	 byte byteData[] = md.digest();
	 StringBuffer sb = new StringBuffer();
	 
	 //Method for hashing password Strings
  for (int i = 0; i < byteData.length; i++) {
   sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
  }
this.password = sb.toString();
}

public String login() {
	List<Student> results = dba.loginQuery(nds);
        if ( !results.isEmpty() ) {
        	loggedinstudent = results.get(0);
        	id = ((Student) loggedinstudent).getId();
        	firstname = ((Student) loggedinstudent).getFirstname();
        	lastname =  ((Student) loggedinstudent).getName();
        	email = 	((Student) loggedinstudent).getEmail();
        	salt = 		((Student) loggedinstudent).getSalt();
        	keyword = 	((Student) loggedinstudent).getKeyword();
        	password = password+salt;
        	
        	if(password.equals(keyword)){
         
         		if (loggedinstudent instanceof Student){
            
         			setLoggedIn();
         			return "_student/stud_welcome";
            
            
         		}
         }}
         
         List<ExamAuth> authresults = dba.loginauthQuery(nds);
         if ( !authresults.isEmpty() ){
        	 loggedinstudent = authresults.get(0);
         	 firstname = ((ExamAuth) loggedinstudent).getFirstname();
         	 lastname =  ((ExamAuth) loggedinstudent).getName();
         	 email = 	 ((ExamAuth) loggedinstudent).getEmail();
         	 salt = 	 ((ExamAuth) loggedinstudent).getSalt();
         	 keyword =   ((ExamAuth) loggedinstudent).getKeyword();
         	 password = password+salt;
         	 
         	 if(password.equals(keyword)){
         		if (loggedinstudent instanceof ExamAuth){
         			setLoggedInauth();
         			return "_authority/auth_welcome";
        
         		}
         }}
         
         List<Admin> adminresults = dba.loginadminQuery(nds);
         
         if ( !adminresults.isEmpty() ){
        	loggedinstudent = adminresults.get(0);
         	firstname = ((Admin) loggedinstudent).getFirstname();
         	lastname = 	((Admin) loggedinstudent).getLastname();
         	email = 	((Admin) loggedinstudent).getEmail();
         	salt = 		((Admin) loggedinstudent).getSalt();
        	keyword = 	((Admin) loggedinstudent).getKeyword();
        	password = password+salt;
        	
        	if(password.equals(keyword)){
         	if(loggedinstudent instanceof Admin){
        	 		setLoggedInadmin();
        	 		return "_admin/admin_welcome";
        	 	}
        }}
        
        return "index";
		

    }
public void setLoggedIn(){
	Logger.getLogger(LoginCtrl.class.getName())
    .log(Level.INFO, 
    "studentctrl Liste:");
 loggedIn = true;
}
public void setLoggedInauth(){
	Logger.getLogger(LoginCtrl.class.getName())
    .log(Level.INFO, 
    "studentctrl Liste:");
 loggedInauth = true;
}
public void setLoggedInadmin(){
	Logger.getLogger(LoginCtrl.class.getName())
    .log(Level.INFO, 
    "studentctrl Liste:");
 loggedInadmin = true;
}


public String logout() {
    loggedIn = false;
    loggedInadmin = false;
    loggedInauth = false;
    loggedinstudent = null;
    FacesContext.getCurrentInstance().getCurrentInstance().getExternalContext().invalidateSession();
    return "/succ_logout.xhtml?faces-redirect=true";

}
public boolean isLoggedIn() {
	if(loggedIn == true)
		return true;
	else
		return false;

 }
public boolean isLoggedInadmin() {
	if(loggedInadmin == true)
		return true;
	else
		return false;

 }
public boolean isLoggedInauth() {
	if(loggedInauth == true)
		return true;
	else
		return false;

 }
@Produces 
@LoggedIn
public Person getCurrentUser() {

        return loggedinstudent;

}

public String getFirstname() {
	return firstname;
}



public void setFirstname(String firstname) {
	this.firstname = firstname;
}



public String getLastname() {
	return lastname;
}



public void setLastname(String lastname) {
	this.lastname = lastname;
}
public String getEmail() {
	return email;
}



public void setEmail(String email) {
	this.email = email;
}
public String updateStudent(){
	
	
    if( dbP.editStudent(((Student)loggedinstudent))){
    	return "stud_welcome";
    }else
    {
    	return "stud_editError";
    }
    	
    }



public int getId() {
	return id;
}



public void setId(int id) {
	this.id = id;
}
	
 
}
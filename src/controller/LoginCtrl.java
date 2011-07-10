package controller;



import java.io.Serializable;
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


import model.*;

import business.*;
@Named
@SessionScoped
public class LoginCtrl implements Serializable {
 
 /**
  * 
  */
 private static final long serialVersionUID = 5186306447159703311L;
 private String nds, password, firstname, lastname, email;

 


private boolean loggedIn = false;
private boolean loggedInadmin = false;
private boolean loggedInauth = false;
 
 
 private Student loggedinstudent;
 private ExamAuth loggedinperson;
 private Admin loggedinadmin;
 @EJB
 DbAccount dba;
 
  
 
 public String getnds() {
  return nds;
 }



 public void setnds(String nds) {
  this.nds = nds;
 }



 public String getPassword() {
  return password;
 }



 public void setPassword(String password) {
  this.password = password;
 }

 //TODO JL Exam. Auth. dba.validate() == 2
public String login() {
	List<Student> results = dba.loginQuery(nds, password);
        if ( !results.isEmpty() ) {
        	loggedinstudent = results.get(0);
        	firstname = loggedinstudent.getFirstname();
        	lastname = loggedinstudent.getName();
         
         		if (loggedinstudent instanceof Student){
            
         			setLoggedIn();
         			return "_student/stud_welcome";
            
            
         		}
         }
         
         List<ExamAuth> authresults = dba.loginauthQuery(nds, password);
         
         if ( !authresults.isEmpty() ){
         loggedinperson = authresults.get(0);
         firstname = loggedinperson.getFirstname();
         lastname = loggedinperson.getName();
         
         		if (loggedinperson instanceof ExamAuth){
         			setLoggedInauth();
         			return "_authority/auth_welcome";
        
         		}
         }
         
         List<Admin> adminresults = dba.loginadminQuery(nds, password);
         
         if ( !adminresults.isEmpty() ){
        	 loggedinadmin = adminresults.get(0);
        	 firstname = loggedinadmin.getFirstname();
        	 lastname = loggedinadmin.getLastname();
         
        	 	if(loggedinadmin instanceof Admin){
        	 		setLoggedInadmin();
        	 		return "_admin/admin_welcome";
        	 	}
        }
        
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
@Produces @LoggedIn Student getCurrentUser() {

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

 
}
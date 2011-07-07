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


import model.Student;

import business.*;
@Named
@SessionScoped
public class LoginCtrl implements Serializable {
 
 /**
  * 
  */
 private static final long serialVersionUID = 5186306447159703311L;
 private String nds, password, nameError;

 private boolean loggedIn = false;
 
 
 private Student loggedinstudent;
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
/* public void validateName(ValueChangeEvent e) {
	    UIComponent nameInput = e.getComponent();
	   // String name = nameInput.getValue();
	    
	    if (name.contains("_"))   nameError = "Name cannot contain underscores";
	    else if (name.equals("")) nameError = "Name cannot be blank";
	    else                      nameError = "";
	  }
*/
 //TODO JL Exam. Auth. dba.validate() == 2
public String login() {
        List<Student> results = dba.loginQuery(nds, password);
        Logger.getLogger(LoginCtrl.class.getName())
        .log(Level.INFO, 
        "studentctrl Liste:");
        if ( !results.isEmpty() ) {
         loggedinstudent = results.get(0);
        if (loggedinstudent.getLoginvalue() == true){
         Logger.getLogger(LoginCtrl.class.getName())
         .log(Level.INFO, 
         "studentctrl Liste:--------------------------------------------");
         setLoggedIn();
         return "_authority/auth_welcome";
        }
         else{
        	 Logger.getLogger(LoginCtrl.class.getName())
             .log(Level.INFO, 
             "studentctrl Liste:-----------------asdasdasdasdas---------------------------");
             setLoggedIn();
        	 return "_student/stud_welcome";
        }}
        else
         return "index";

    }
public void setLoggedIn(){
	Logger.getLogger(LoginCtrl.class.getName())
    .log(Level.INFO, 
    "studentctrl Liste:");
 loggedIn = true;
}

public String logout() {
    loggedIn = false;
    loggedinstudent = null;
    return "/index.xhtml?faces-redirect=true";

}
public boolean isLoggedIn() {
	if(loggedIn == true)
		return true;
	else
		return false;

 }
@Produces @LoggedIn Student getCurrentUser() {

        return loggedinstudent;

    }
 
}
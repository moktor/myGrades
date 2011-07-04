package controller;



import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;

import model.Student;

import business.*;
@Named
@SessionScoped
public class LoginCtrl implements Serializable {
 
 /**
  * 
  */
 private static final long serialVersionUID = 5186306447159703311L;
 private String nds;
 private String password;
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

 //TODO JL Exam. Auth. dba.validate() == 2
public String login() {
        List<Student> results = dba.loginQuery(nds, password);
        Logger.getLogger(LoginCtrl.class.getName())
        .log(Level.INFO, 
        "studentctrl Liste:");
        if ( !results.isEmpty() ) {
         loggedinstudent = results.get(0);
         Logger.getLogger(LoginCtrl.class.getName())
         .log(Level.INFO, 
         "studentctrl Liste:");
         setLoggedIn();
         return "_authority/auth_welcome";
        }
        else
         return "index";

    }
public void setLoggedIn(){
	Logger.getLogger(LoginCtrl.class.getName())
    .log(Level.INFO, 
    "studentctrl Liste:");
 loggedIn = true;
}

public void logout() {
    loggedIn = false;
    loggedinstudent = null;

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
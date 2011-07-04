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
 private int loggedIn;
 
 
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
        if ( !results.isEmpty() ) {
         loggedinstudent = results.get(0);
         LoginCtrl ctrl = new LoginCtrl();
         ctrl.setLoggedIn();
         return "_authority/auth_welcome";
        }
        else
         return "index";

    }
public void setLoggedIn(){
 
 loggedIn = 1;
}

public void logout() {
    loggedIn = 0;
    loggedinstudent = null;

}
public boolean isLoggedIn() {

    return loggedinstudent!=null;

 }
 @Produces @LoggedIn Student getCurrentUser() {

        return loggedinstudent;

    }
 

}
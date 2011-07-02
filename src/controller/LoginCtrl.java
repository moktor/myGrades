package controller;



import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import business.*;

@Named
@SessionScoped
public class LoginCtrl implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5186306447159703311L;
	private String userName = "abc12345";
	private String password = "12345678";
	
	@EJB
	DbAccount dba;
	
	
	
	public String getUserName() {
		return userName;
	}



	public void setUserName(String userName) {
		this.userName = userName;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}

	//TODO JL Exam. Auth. dba.validate() == 2
	public String login(){
		
		
	if(dba.validate(userName, password) == 1){
		Logger.getLogger(LoginCtrl.class.getName())
	    .log(Level.INFO, 
	    "loginCtrl"+userName+" "+password);
	
	return "succsessLoginStudent";
	}
	else
	return "errorLogin";
		
	}
	

}

package controller;
import java.io.Serializable;

import model.Student;

import javax.ejb.EJB;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;
import business.*;



@Named
@SessionScoped
public class studentCtrl implements Serializable {

	@EJB
	DbPerson a;
	
	public studentCtrl(){
		
	}
	
	
	public String createStudent(){
		a.createStudent();
		return "addStudent";
	}
	
	
	
	
	public String test(){
		
		
		return "delStudent";
	}
	
	
	
	
	
}

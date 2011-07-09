package controller;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
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
	private String nds;
	private String gender;
	private String firstname;
	private String lastname;
	private String adresse;
	private String email;
	private String phone;
	private String mobil;
	private String keyword;
	private int fachsemester;

	//---------------- FM ------------- sorting variables ---------------------------
	private boolean sortId;
	private boolean sortNds;
	private boolean sortName;
	private boolean sortFirst;
	private boolean sortAdress;
	private boolean sortFieldOfStudy;
	
	// ----------------- FM ---------------- filter attribute
	private String filter;
	
	private Student currentStudent;
	
	private Student student; // temp student
	private List<Student> studentList;
	

	@EJB
	DbPerson dbP;
	

	

	
	
	// ------------------------- FM -------------------------createStudent---------------------
	// Creates a new student using all db col params
	public String createStudent(){
		dbP.createStudent( nds, gender, firstname, lastname, adresse, email, phone, mobil, keyword, fachsemester);		
		return "addStudent";
	}
	
	public String addStudentHelper(){
		  nds = "";
		  gender= "";
		  firstname= "";
		  lastname= "";
		  adresse= "";
		  email= "";
		  phone= "";
		  mobil= "";
		  keyword= "";
		  fachsemester= 0;
		return "addStudent";
	}
	
	
	// ------------------------------ FM --------------------getStudent--------------------------
	// Finds a single Student from db, searchparam: lastname
	//additional test logger and student object / TODO
	public void getStudent(){
		
		Student a = dbP.findStudentByName("lastname");
		Student b = dbP.findStudentByNds("24");
			
		if(b !=null){
			String nds = b.getFirstname();			
		}else{
			//no object
		}
		String lastname = a.getFirstname();
		

	}
	
	// ------------------------- FM ----------------------- listFilter
	
	
	// --------------------- FM ------------------------getAllStudents----------------------
	// Returns a list of all Students
	//additional test logger for view of all students
	public List<Student> getAllStudents(){
		List<Student> list = dbP.getAllStudents();
		studentList = sortList(list);
		return list;
	}
	
	// --------------------- FM ------------------------sortStudents----------------------
	
	public List<Student> sortList(List<Student> list){
		if(sortId){
			list = dbP.sortById(list);
		}
		if(sortNds){
			list = dbP.sortByNds(list);
		}
		if (sortName){
		list = dbP.sortByName(list);
		}
		if(sortFirst){
		list = dbP.sortByFirst(list);
		}
		if(sortAdress){
		list = dbP.sortByAdress(list);
		}
		return list;
	}
	
	
	public void sortStudentsById(){
		sortId = true;
		sortNds = false;
		sortName = false;
		sortFirst = false;
		sortAdress = false;
		sortFieldOfStudy = false;
	}
	
	public void sortStudentsByNds(){
		sortId = false;
		sortNds = true;
		sortName = false;
		sortFirst = false;
		sortAdress = false;
		sortFieldOfStudy = false;
	}
	
	public void sortStudentsByName(){
		sortId = false;
		sortNds = false;
		sortName = true;
		sortFirst = false;
		sortAdress = false;
		sortFieldOfStudy = false;
	}
	
	public void sortStudentsByFirst(){
		sortId = false;
		sortNds = false;
		sortName = false;
		sortFirst = true;
		sortAdress = false;
		sortFieldOfStudy = false;
	}
	
	public void sortStudentsByFieldOfStudy(){
		sortId = false;
		sortNds = false;
		sortName = false;
		sortFirst = false;
		sortAdress = false;
		sortFieldOfStudy = true;
	}
	
	public void sortStudentsByAdress(){
		sortId = false;
		sortNds = false;
		sortName = false;
		sortFirst = false;
		sortAdress = true;
		sortFieldOfStudy = false;
	}

	
	
	
	
	//-------------------------JL----------------deleteStudent-----------------
	//deletes a student from db, using nds (primary key)
	public String deleteStudent(){

		if(dbP.deleteStudentByNds(this.nds) == true){
		return "delSuccess";
		}
		else
		return "delError";
		
	}
	// ----------------------- FM ---------------------deleteSelected----------------
    // deletes selected Students
	
	public String deleteSelected(){
	
		dbP.deleteMultipleStudents(studentList);
		
		return "auth_delSuccess";
	}
	
	
    // ----------------------- FM ---------------------editStudent----------------
    // edits some parameter of given student
        
    public String edit(Student s){
    	
    	setCurrentStudent(s);
    	
    	Logger.getLogger(studentCtrl.class.getName())
	    .log(Level.INFO, 
	    "studentCtrl Liste  "+s.getFirstname());
    	
    	return "editStudent";
    }
    
 // ----------------------- FM ---------------------updateStudent----------------  
    public String updateStudent(){
    	
    	
    if( dbP.editStudent(currentStudent)){
    	return "auth_studentdata";
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

	public void setCurrentStudent(Student currentStudent) {
		this.currentStudent = currentStudent;
	}

	public Student getCurrentStudent() {
		return currentStudent;
	}
	

	public int getFachsemester() {
		return fachsemester;
	}
	public void setFachsemester(int fachsemester) {
		this.fachsemester = fachsemester;
	}
	
	
	




}

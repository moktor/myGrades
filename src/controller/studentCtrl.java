package controller;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;


import business.*;
import model.FieldOfStudy;
import model.Student;

@ManagedBean
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
	
	
	// ------------ FM ------------ checkBox marker ----
	private boolean mark = true;
	private String marked = "mark all";
	
		
	// ----------------- FM ---------------- filter attribute
	private String criteria;
	


	private Student currentStudent;
	
	private Student student; // temp student
	private List<Student> studentList;
	
	private List<FieldOfStudy> studyList;
	private FieldOfStudy fieldOfStudy;

	

	@EJB
	DbPerson dbP;
	@EJB
	DbMisc dbM;

	

	

	
	
	// ------------------------- FM -------------------------createStudent---------------------
	// Creates a new student using all db col params
	public String createStudent(){
		
		dbP.createStudent( nds, gender, firstname, lastname, adresse, email, phone, mobil, keyword, fachsemester);		
		return "auth_studentdata";
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
	
	
	
	
	// --------------------- FM ------------------------getAllStudents----------------------
	// Returns a list of all Students
	//additional test logger for view of all students
	public List<Student> getAllStudents(){
		List<Student> list = dbP.getAllStudents();

		
		Logger.getLogger(dummy.class.getName())
	    .log(Level.INFO, 
	    "getAllStudents()");
		
		studentList = sortList(list);				// sort
		
		studentList = dbP.filter(studentList, criteria);		// filter
				
		studentList = checkAll(list);
			
		return list;
	}
	
// --------------------- FM ----------- mark all Checkboxes -----	
	//sets the mark state
	public String markIt(){	
				
		if (mark){
			mark = false;
			setMarked("mark all");
		}else{
			mark = true;
    		
    		setMarked("unmark all");
		}	
		return "auth_studentdata";
	}
	
	
 
    //------------ edits the list of students
    private List<Student> checkAll(List<Student> list){
    	  	
    	if(mark){
    	for (Student student : list) {
			student.setDeleteInc(true);
		}
    	}else{
    		for (Student student : list) {
    			student.setDeleteInc(false);
    		}	
    	}    	
    	return list;
    }
    

	
	//------------------------ FM --------------------- getAllStudyFields -------------
	public List<FieldOfStudy> getAllStudyFields(){
		List<FieldOfStudy> list = dbM.getAllFieldsOfStudy();
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
	
	public String sortStudentsByNds(){
		sortId = false;
		sortNds = true;
		sortName = false;
		sortFirst = false;
		sortAdress = false;
		sortFieldOfStudy = false;
		return "auth_studentdata";
	}
	
	public String sortStudentsByName(){
		sortId = false;
		sortNds = false;
		sortName = true;
		sortFirst = false;
		sortAdress = false;
		sortFieldOfStudy = false;
		
		return "auth_studentdata";
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

	public void setMarked(String marked) {
		this.marked = marked;
	}

	public String getMarked() {
		return marked;
	}
	
	public String getCriteria() {
		return criteria;
	}

	public void setCriteria(String criteria) {
		this.criteria = criteria;
	}
	




}

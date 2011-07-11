package controller;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Iterator;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;


import business.*;
import model.ExamAuth;
import model.Student;



@Named
@SessionScoped
public class examAuthCtrl implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private String nds;
	private String gender;
	private String titel;
	private String firstname;
	private String name;
	private String adresse;
	private String email;
	private String phone;
	private String mobil;
	private String keyword;
	private String salt;
	
	//---------------- FM ------------- sorting variables ---------------------------
	private boolean sortId;
	private boolean sortNds;
	private boolean sortName;
	private boolean sortFirst;
	private boolean sortAdress;
	
	// ------------ FM ------------ checkBox marker ----

	private boolean mark = false;
	private String marked = "Alles auswählen";
	private String markClass = "select_all";
	
	// ----------------- FM ---------------- filter attribute
	private String criteria;
	private String criteriaNds;
	
	
	
	
	private ExamAuth currentExamAuth;
	
	private ExamAuth examAuth; // temp ExamAuth
	private List<ExamAuth> examAuthList;
	

	

	

	@EJB
	DbExamAuth dbE;
	

	
	// ------------------------- LS -------------------------createExamAuth ---------------------
	// Creates a new ExamAuth using all db col params
	
	public String createExamAuth(){
		dbE.createExamAuth( nds, gender, titel, firstname, name, adresse, email, phone, mobil, keyword, adresse, salt);		
		return "auth_examinerdata";
	}
	
	public String addExamAuthHelper(){
		  nds = "";
		  gender= "";
		  titel="";
		  firstname= "";
		  name= "";
		  adresse= "";
		  email= "";
		  phone= "";
		  mobil= "";
		  keyword= "";	 
		return "addExamAuth";
	}
	
	
	// ------------------------------ LS --------------------getExamAuth--------------------------
	// Finds a single ExamAuth from db, searchparam: lastname
	//additional test logger and ExamAuth object / TODO
	

	// --------------------- LS ------------------------ExamAuths----------------------
	// Returns a list of all ExamAuths
	//additional test logger for view of all ExamAuths
	public List getAllExamAuths(){
		List<ExamAuth> list = dbE.getAllExamAuths();
		
		examAuthList = sortListExamAuth(list);
		
		examAuthList = dbE.filter(examAuthList, criteria, criteriaNds);		// filter
		
		return list;
	}
	
// --------------------- LS ------------------------sortStudents----------------------
	
	public List<ExamAuth> sortListExamAuth(List<ExamAuth> list){
		
		if(sortId){
			list = dbE.sortByIdExamAuth(list);
		}
		if(sortNds){
			list = dbE.sortByNdsExamAuth(list);
		}
		
		
		if (sortName){
		list = dbE.sortByNameExamAuth(list);
		}
		
		if(sortFirst){
		list = dbE.sortByFirstExamAuth(list);
		}
	
		return list;
		
	}
	
	
	public void sortExamAuthsByID(){
		sortId = true;
		sortNds = false;
		sortName = false;
		sortFirst = false;
		sortAdress = false;
	}
	
	public void sortExamAuthsByNds(){
		sortId = false;
		sortNds = true;
		sortName = false;
		sortFirst = false;
		sortAdress = false;
	}
	
	public void sortExamAuthsByName(){
		sortId = false;
		sortNds = false;
		sortName = true;
		sortFirst = false;
		sortAdress = false;
	}
	
	public void sortExamAuthsByFirst(){
		sortId = false;
		sortNds = false;
		sortName = false;
		sortFirst = true;
		sortAdress = false;
	}
	

	

	//-------------------------LS----------------deleteExamAuth-----------------
	//deletes a ExamAuth from db, using nds (primary key)
	public String deleteExamAuth(){

		if(dbE.deleteExamAuthByNds(this.nds) == true){
		return "delSuccess";
		}
		else
		return "delError";
		
	}
	// ----------------------- LS ---------------------deleteSelected----------------
    // deletes selected ExamAuths
	
	public String deleteSelected(){

		
		dbE.deleteMultipleExamAuths(examAuthList);
		
		return "auth_delSuccess";
	}
	
	
    // ----------------------- LS ---------------------editExamAuth----------------
    // edits some parameter of given ExamAuth
        
    public String edit(ExamAuth ea){
    	
    	setCurrentExamAuth(ea);
    	
    	Logger.getLogger(examAuthCtrl.class.getName())
	    .log(Level.INFO, 
	    "examAuthCtrl Liste  "+ea.getFirstname());
    	
    	return "editExamAuth";
    }
    
 // ----------------------- LS ---------------------updateExamAuth----------------  
    public String updateExamAuth(){
    	
    	
    if( dbE.editExamAuth(currentExamAuth)){
    	return "auth_examinerdata";
    }else	
    {
    	return "auth_editError";
    }
    	
    }
    
    // --------------------- FM ----------- mark all Checkboxes -----
    
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
      
   // --------------------- FM ----------- mark all Checkboxes ----- 
    //sets the mark state
    public String markIt(){ 
       
     if (mark){
      mark = false;
      setMarked("Alles auswählen");
      setMarkClass("select_all");
     }else{
      mark = true;
      setMarked("Alles abwählen");
      setMarkClass("deselect_all");
     } 
     return "auth_studentdata";
    }
    
    
	

	
	//----------------- Getter / Setter ------------------------------------


	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
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
	
	public String getTitel() {
		return titel;
	}

	public void setTitel(String titel) {
		this.titel = titel;
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

	public void setCurrentExamAuth(ExamAuth currentExamAuth) {
		this.currentExamAuth = currentExamAuth;
	}
	
	
	public ExamAuth getCurrentExamAuth() {
		return currentExamAuth;
	}

	public void setMarked(String marked) {
		this.marked = marked;
	}

	public String getMarked() {
		return marked;
	}

	public void setMarkClass(String markClass) {
		this.markClass = markClass;
	}

	public String getMarkClass() {
		return markClass;
	}

	public void setCriteria(String criteria) {
		this.criteria = criteria;
	}

	public String getCriteria() {
		return criteria;
	}

	public void setCriteriaNds(String criteriaNds) {
		this.criteriaNds = criteriaNds;
	}

	public String getCriteriaNds() {
		return criteriaNds;
	}

	public void setExamAuth(ExamAuth examAuth) {
		this.examAuth = examAuth;
	}

	public ExamAuth getExamAuth() {
		return examAuth;
	}
	



}






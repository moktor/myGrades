package controller;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;


import business.*;
import model.Course;
import model.Enrollment;
import model.Student;

@Named
@SessionScoped
public class courseCtrl implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5184733444596178113L;
	private int id;
	private int studentcount;
	private String name;
	private String fieldofstudy;
	private String date;
	
	private String day;
	private String month;
	private String year;

	//---------------- MH ------------- sorting variables ---------------------------
	private boolean sortId;
	private boolean sortStudentCount;
	private boolean sortName;
	private boolean sortFieldOfStudy;
	private boolean sortDate;
	
	private Course currentCourse;
	private Course currentcourse;
	
	private List<Enrollment> enrollmentList;
	
	private Course course; // temp course
	private List<Course> courseList;
	

	@EJB
	DbCourse dbC;
	

	

	
	
	// ------------------------- MH -------------------------createCourse---------------------
	// Creates a new course using all db col params
	 public String createCourse(){
	       date = this.year+this.month+this.day;
	       Logger.getLogger(courseCtrl.class.getName()).log(Level.INFO,"Daum: "+date);
	       Logger.getLogger(courseCtrl.class.getName())
	       .log(Level.INFO, 
	       "studentCtrl Liste  "+date);
	       Date datum = java.sql.Date.valueOf(date);
	       dbC.createCourse(studentcount, name, fieldofstudy, datum);
	       return "auth_examdata";
	}
	 
	public String addCourseHelper(){
		  studentcount = 0;
		  name = "";
		  fieldofstudy = "";
		  date = null;
		  day = "01";
		  month = "01-";
		  year = "2011-";
		return "addCourse";
	}
	
	// --------------------- MH ------------------------getAllCourses----------------------
	// Returns a list of all Courses
	//additional test logger for view of all courses
	public List<Course> getAllCourses(){
		List<Course> list = dbC.getAllCourses();
		setCourseList(list);
		return list;
	}
	
	// --------------------- MH ------------------------sortCourses----------------------
	
	public List<Course> sortList(List<Course> list){
		if(sortId){
			list = dbC.sortById(list);
		}
		if(sortStudentCount){
			list = dbC.sortByStudentCount(list);
		}
		if (sortName){
		list = dbC.sortByName(list);
		}
		if (sortFieldOfStudy){
		list = dbC.sortByFieldOfStudy(list);
		}
		if(sortDate){
		list = dbC.sortByDate(list);
		}
		return list;
	}
	
	
	public void sortCoursesById(){
		sortId = true;
		sortStudentCount = false;
		sortName = false;
		sortFieldOfStudy = false;
		sortDate = false;
	}
	
	public void sortCoursesByStudentCount(){
		sortId = false;
		sortStudentCount = true;
		sortName = false;
		sortFieldOfStudy = false;
		sortDate = false;
	}
	
	public void sortCoursesByName(){
		sortId = false;
		sortStudentCount = false;
		sortName = true;
		sortFieldOfStudy = false;
		sortDate = false;
	}
	
	public void sortCoursesByFieldOfStudy(){
		sortId = false;
		sortStudentCount = false;
		sortName = false;
		sortFieldOfStudy = true;
		sortDate = false;
	}
	
	public void sortCoursesByDate(){
		sortId = false;
		sortStudentCount = false;
		sortName = false;
		sortFieldOfStudy = false;
		sortDate = true;
	}
	
	
	// ----------------------- MH ---------------------deleteSelected----------------
    // deletes selected Courses
		
    
    public String deleteSelectedCourses(){
    	
		dbC.deleteSelectedCourses(courseList);
		
		return "auth_examdata";
	}
	
	
    // ----------------------- MH ---------------------editCourse----------------
    // edits some parameter of given student
        
    public String edit(Course s){
    	
    	setCurrentCourse(s);
    	
    	Logger.getLogger(courseCtrl.class.getName())
	    .log(Level.INFO, 
	    "studentCtrl Liste  "+s.getName());
    	
    	return "editCourse";
    }
    
 // ----------------------- MH ---------------------updateCourse----------------  
    public String updateCourse(){
    
    	String dateTempString = this.year+this.month+this.day;
    	Date dateTemp = java.sql.Date.valueOf(dateTempString);
    	currentCourse.setDate(dateTemp);
    	
    if( dbC.editCourse(currentCourse)){
    	return "auth_examdata";
    }else
    {
    	return "auth_editError";
    }
    	
    }
    
    //-------------------------------------------------- Datum überprüfen
    
    public String checkDate(Course c) {
    	
    	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    	Date courseDate = c.getDate();
    	
    	Date currentDate =  java.sql.Date.valueOf(dateFormat.format(new java.util.Date()));
    	
    	
    	if ( courseDate.before(currentDate) )
    		return "yes";
    	if ( courseDate.after(currentDate) ) 
    		return "no";
    	else
    		return "fehler";
    }
    

    //-------------------------------------------------- Status überprüfen
    
    public String checkDateReversed(Course c) {
    	
    	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    	Date courseDate = c.getDate();
    	
    	Date currentDate =  java.sql.Date.valueOf(dateFormat.format(new java.util.Date()));
    	
    	
    	if ( courseDate.before(currentDate) )
    		return "no";
    	if ( courseDate.after(currentDate) ) 
    		return "yes";
    	else
    		return "fehler";
    }
    
    // ------------------------------------------------- 
    
    public boolean written(Course c) {
    	
    	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    	Date courseDate = c.getDate();
    	Date currentDate =  java.sql.Date.valueOf(dateFormat.format(new java.util.Date()));
    	
    	if ( courseDate.before(currentDate) )
    		return true;
    	if ( courseDate.after(currentDate) ) 
    		return false;
    	else
    		return false;
    }
    
    //-------------
    
    public String changeDateFormat(Course c) {
    	DateFormat dateFormatDay = new SimpleDateFormat("dd");
		DateFormat dateFormatMonth = new SimpleDateFormat("MM");
		DateFormat dateFormatYear = new SimpleDateFormat("yyyy");
		
		String dayString = dateFormatDay.format(c.getDate());
		String monthString = dateFormatMonth.format(c.getDate());
		String yearString = dateFormatYear.format(c.getDate());
		
    	
    	
    	String dateString = dayString + "." + monthString + "." + yearString;
    	
    	return dateString;
    }
    
    public String getStudentsToGrade(Course c) {
    	
    	/*
    	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    	Date courseDate = c.getDate();
    	Date currentDate =  java.sql.Date.valueOf(dateFormat.format(new java.util.Date()));

    	setCurrentCourse(c);
    	
    	if (courseDate.after(currentDate)) 
    		return "invalid";
    	*/
    	
    	setCurrentCourse(c);
    	
    	return "auth_gradestudents";
    }

    //------------------------------------- Alle Studenten, die zu einem bestimmten Kurs angemeldet sind
    
    public List<Enrollment> getStudentsByCourse() {
    	
    	int courseId = currentCourse.getId();
    	enrollmentList = dbC.getStudentsByCourseId(courseId);
    	Logger.getLogger(courseCtrl.class.getName()).log(Level.INFO, "Liste" + enrollmentList.toString());
    	return  enrollmentList;
    }

    
    //------------------------------------- Noten eintragen
    
    public String updateGrades(){
    	
    if( dbC.updateGrades(enrollmentList)){
    	return "auth_gradestudents";
    }else
    {
    	return "auth_editError";
    }
    	
    }
    //------------------------------------- Kurse, zu denen man sich noch anmelden kann
    
    public List<Course> coursesToEnroll(int id) {
    	List<Course> list = dbC.coursesToEnroll(id);
		courseList = sortList(list);
		return list;
    }
    
    //------------------------------------- Zu ausgewählten Kursen anmelden
    
    public String enrollToSelected(int id){
    	Logger.getLogger(courseCtrl.class.getName()).log(Level.INFO, "checkpoint 1");
		dbC.enrollToSelected(courseList, id);
		Logger.getLogger(courseCtrl.class.getName()).log(Level.INFO, "checkpoint 7");
		return "stud_enroll";
	}
    
     //------------------------------------- Zu ausgewählten Kursen abmelden
    
    public String signOffSelected(int id){
    	Logger.getLogger(courseCtrl.class.getName()).log(Level.INFO, "checkpoint 1");
		dbC.signOffSelected(enrollmentList, id);
		Logger.getLogger(courseCtrl.class.getName()).log(Level.INFO, "checkpoint 7");
		return "stud_examoverview";
	} 
    
   //------------------------------------- Alle Kurse, zu denen der Student eingeschrieben ist
    
    public List<Enrollment> allEnrollments(int id) {
    	List<Enrollment> list = dbC.allEnrollments(id);
    	setEnrollmentList(list);
		return list;
    }
    
    //-------------------------------------- Alle Kurse, die schon bewertet wurden
    
    public List<Enrollment> getGradedCourses(int id) {
    	List<Enrollment> list = dbC.getGradedCourses(id);
    	setEnrollmentList(list);
    	return list;
    }
    
    //--------------------------------------  Durchschnittsnote berechnen
    
    public String getAverage(int id) {
    	double avg = dbC.getAverage(id);
    	return "Durchschnittsnote: " + avg;
    }
    
    //--------------------------------------  Note umrechnen
    
    public double convertGrade(int grade) {
    	double convertedGrade = 0;
		switch (grade) {
		case 0: convertedGrade = 0; break;
		case 1: convertedGrade = 1.0; break;
		case 2: convertedGrade = 1.3; break;
		case 3: convertedGrade = 1.7; break;
		case 4: convertedGrade = 2.0; break;
		case 5: convertedGrade = 2.3; break;
		case 6: convertedGrade = 2.7; break;
		case 7: convertedGrade = 3.0; break;
		case 8: convertedGrade = 3.3; break;
		case 9: convertedGrade = 3.7; break;
		case 10: convertedGrade = 4.0; break;
		case 11: convertedGrade = 4.3; break;
		case 12: convertedGrade = 4.7; break;
		case 13: convertedGrade = 5.0; break;
		}
    	return convertedGrade;
    }
	//----------------- Getter / Setter ------------------------------------
    
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getStudentcount() {
		return studentcount;
	}

	public void setStudentcount(int studentcount) {
		this.studentcount = studentcount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFieldofstudy() {
		return fieldofstudy;
	}

	public void setFieldofstudy(String fieldofstudy) {
		this.fieldofstudy = fieldofstudy;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public List<Course> getCourseList() {
		return courseList;
	}

	public void setCourseList(List<Course> courseList) {
		this.courseList = courseList;
	}

	public Course getCurrentCourse() {
		return currentCourse;
	}

	public void setCurrentCourse(Course currentCourse) {
		DateFormat dateFormatDay = new SimpleDateFormat("dd");
		DateFormat dateFormatMonth = new SimpleDateFormat("MM");
		DateFormat dateFormatYear = new SimpleDateFormat("yyyy");
		
		this.day = dateFormatDay.format(currentCourse.getDate());
		this.month = dateFormatMonth.format(currentCourse.getDate()) + "-";
		this.year = dateFormatYear.format(currentCourse.getDate()) + "-";
		
		this.currentCourse = currentCourse;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public Course getCurrentcourse() {
		return currentcourse;
	}

	public void setCurrentcourse(Course currentcourse) {
		this.currentcourse = currentcourse;
	}

	public List<Enrollment> getEnrollmentList() {
		return enrollmentList;
	}

	public void setEnrollmentList(List<Enrollment> enrollmentList) {
		this.enrollmentList = enrollmentList;
	}
	
}

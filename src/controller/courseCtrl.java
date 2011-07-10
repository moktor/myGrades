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
	
	List<Enrollment> enrollmentList = null;
	
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
		  id = 0;
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
		courseList = sortList(list);
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
	
	
	
	//-------------------------JL----------------deleteCourse-----------------
	//deletes a course from db, using nds (primary key)
	public String deleteStudent(){

		if(dbC.deleteStudentById(this.id) == true){
		return "delSuccess";
		}
		else
		return "delError";
		
	}
	// ----------------------- MH ---------------------deleteSelected----------------
    // deletes selected Students
	
	public String deleteSelected(){
	
		dbC.deleteMultipleCourses(courseList);
		
		return "auth_delSuccess";
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
    
    //--------------------------------------------------
    
    public String checkDate(Course course) {
    	
    	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    	Date courseDate = course.getDate();
    	
    	Date currentDate =  java.sql.Date.valueOf(dateFormat.format(new java.util.Date()));
    	
    	
    	if ( courseDate.before(currentDate) )
    		return "yes";
    	if ( courseDate.after(currentDate) ) 
    		return "no";
    	else
    		return "fehler";
    	
    }
    
    // -------------------------------------------------
    
    public String getStudentsToGrade(Course course) {
    	
    	setCurrentCourse(course);
    	
    	return "auth_gradestudents";
    }

    //-------------------------------------
    
    public List<Enrollment> getStudentsByCourse() {
    	
    	int courseId = currentCourse.getId();
    	enrollmentList = dbC.getStudentsByCourseId(courseId);
    	Logger.getLogger(courseCtrl.class.getName()).log(Level.INFO, "Liste" + enrollmentList.toString());
    	return  enrollmentList;
    }

    //-------------------------------------
    
    public void testMethod() {
    	
    	Logger.getLogger(courseCtrl.class.getName()).log(Level.INFO, "Checkpoint 1");
    	
    	Student student = new Student();
    	Course course = new Course();
    	student.setName("Hans");
    	course.setName("ADP");
    	//dbC.testMethod(student, course, 1);
    	Enrollment e = dbC.joinTest();
    	e.getParentStudent().getName();
    	Logger.getLogger(courseCtrl.class.getName()).log(Level.INFO, "Checkpoint 3" + e.getParentStudent().getName());
    }
    
    //-------------------------------------
    
    public String updateGrades(){
    	
    if( dbC.editEnrollment(enrollmentList)){
    	return "auth_gradestudents";
    }else
    {
    	return "auth_editError";
    }
    	
    }
    //-------------------------------------
    
    public List<Course> coursesToEnroll() {
    	int id = 5; //Wie kommen wir an die ID?
    	List<Course> list = dbC.coursesToEnroll(id);
		courseList = sortList(list);
		return list;
    }
    
    //-------------------------------------
    
    public List<Enrollment> allEnrollments() {
    	int id = 5; 
    	List<Enrollment> list = dbC.allEnrollments(id);
		return list;
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
	
}

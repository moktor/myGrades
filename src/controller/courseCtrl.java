package controller;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;


import business.*;
import model.Course;


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

	//---------------- MH ------------- sorting variables ---------------------------
	private boolean sortId;
	private boolean sortStudentCount;
	private boolean sortName;
	private boolean sortFieldOfStudy;
	private boolean sortDate;
	
	private Course currentCourse;
	
	private Course course; // temp course
	private List<Course> courseList;
	

	@EJB
	DbCourse dbC;
	

	

	
	
	// ------------------------- MH -------------------------createCourse---------------------
	// Creates a new course using all db col params
	public String createCourse(){
		date = java.sql.Date.valueOf("2010-01-30");
		Logger.getLogger(courseCtrl.class.getName())
	    .log(Level.INFO,"courseCtrl Liste  /////////////////////////////////////////////");
		
		dbC.createCourse(studentcount, name, fieldofstudy, date);
		return "addCourse";
	}
	
	public String addCourseHelper(){
		  id = 0;
		  studentcount = 0;
		  name = "";
		  fieldofstudy = "";
		  date = null;
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
    public String updateStudent(){
    	
    	
    if( dbC.editCourse(currentCourse)){
    	return "auth_studentdata";
    }else
    {
    	return "auth_editError";
    }
    	
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
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
		this.currentCourse = currentCourse;
	}
	
}

package controller;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Date;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import business.*;

@Named
@SessionScoped
public class dummy implements Serializable{

	/**
	 * 
	 */
	
	//======================================= AS ==========================================
	
	private static final long serialVersionUID = -6621583646102081922L;
	@EJB
	DbCourse dbC;
	@EJB
	DbPerson dbP;
	@EJB
	DbAdmin dbA;
	@EJB
	DbExamAuth dbE;
	
	public String action() {
		generateCourses();
		generateStudents();
		generateAdmin();
		generateExamAuth();
		generateEnrollments();
		return "index";
	}
	
	public void generateAdmin(){
		String nds = "auth";
		String gender = "male";
		String firstname = "Herr";
		String lastname = "Hilz";
		String adresse = "Bernau Saturnring 16";
		String email = "herrhilz@meckerkasten.de";
		String phone = "0941-12541858";
		String mobil = "0176-12541858";
		String keyword = "dfgbdffgdfgh";
		String salt = "auth";
		
		dbA.createAdmin(nds, gender, firstname, lastname,adresse,email,phone,mobil,keyword,salt);
	}
	
	public void generateExamAuth(){
		String nds = "auth";
		String title = "Sir";
		String gender = "male";
		String firstname = "Herr";
		String lastname = "Hilz";
		String fieldofstudy = "Alles";
		String adresse = "Bernau Saturnring 16";
		String email = "herrhilz@meckerkasten.de";
		String phone = "0941-12541858";
		String mobil = "0176-12541858";
		String keyword = "auth";
		String salt = "sdfsdfsdfsdfk";
		
		dbE.createExamAuth(nds, title,gender, firstname, lastname,fieldofstudy,adresse,email,phone,mobil,keyword,salt);
	}
	
	public void generateEnrollments(){
		dbC.createEnrollments();
	}
	
	public void generateCourses(){
		String name = "", fieldofstudy = "";
		int studentCount = 0;
		Date date = java.sql.Date.valueOf("2010-05-10");
		
		String [] nameList = {"Praxis des Programmierens","IT-Security I","IT-Finance II","Unternehmensmodellierung","Internettechnologien","IT-Security II","Objektorientierte Programmierung","Buchhaltung","Kosten- und Leistungsrechnung ","Privatrecht"};
		String [] fieldofstudyList = {"Winfo", "Winfo", "Winfo", "Winfo", "Winfo", "Winfo", "Winfo", "BWL", "BWL", "Recht"};
		int [] studentCountList = {56,45,76,34,65,76,87,98,67,78};
		Date[] dateList = {	java.sql.Date.valueOf("2011-06-30"), 
							java.sql.Date.valueOf("2011-06-27"),
							java.sql.Date.valueOf("2012-02-27"), 
							java.sql.Date.valueOf("2012-02-17"), 
							java.sql.Date.valueOf("2010-08-15"), 
							java.sql.Date.valueOf("2011-07-25"), 
							java.sql.Date.valueOf("2011-08-30"), 
							java.sql.Date.valueOf("2011-07-12"), 
							java.sql.Date.valueOf("2011-07-21"), 
							java.sql.Date.valueOf("2010-08-12")};
		
		for (int i = 0; i < nameList.length; i++){
			name = nameList[i];
			fieldofstudy = fieldofstudyList[i];
			studentCount = studentCountList[i];
			date = dateList[i];
			dbC.createCourse(studentCount, name, fieldofstudy, date);	
			
			Logger.getLogger(dummy.class.getName())
		    .log(Level.INFO, 
		    "studentctrl Liste:   "+name+ " "+studentCount);
		}
	}

	public void generateStudents (){
		String nds = "14";
		String gender = "transe";
		String firstname;
		String lastname;
		String adresse;
		String email;
		String phone;
		String mobil;
		String keyword;		
		int fachsemester;
		
		
		Logger.getLogger(dummy.class.getName())
	    .log(Level.INFO, 
	    "studentctrl Liste:   "+nds+ " "+gender);
		
		String [] ndsList = {"student", "sca55117", "poi85742", "her74645","hju87448","kdf90873","urt45627","uru94858","lko87474","hdg35462","jhg74645","wes65710","scg64642"};
		String [] genderList = {"student", "male","female","male","female","male","female","male","female","male","female","male","female"};
		String [] firstnameList = {"student", "Hans", "Berta", "Alexander","Manuela","Peter","Barbara","Richard","Nicola","Mike","Jane","Ludwich","Sandra"};
		String [] lastnameList = {"student", "Huber", "Walter", "Mayer","Schšnfeld","Brunner","Bauer","Goldmann","Fischer","Gold","Schuhmacher","Spielmann","MŸller"};
		String [] adresseList = {"student", "Kleinestraße 14", "Großestraße 34", "Schönfließ Dorfstraße 35","Bernau Saturnring16","Bernau Rollenhagenstr.29","Werneuchen Löhmerdorfstr","Zühlsdorf Wandlitzer Chaussee 14","Panketal Bernauerstraße 41","Werneuchen Marxstraße 32","Zühlsdorf Basdorferstr.50","Lindenberg Karl-Marx-Str.20","Werneuchen Löhmerdorfstr. 50a"};
		String [] emailListe = {"student", "ertzdf@gmx.net", "sdfsdfsdf@gmx.net", "hbork4@aol.com","thomas-bussdorf@web.de","werbegestalter@web.de","wilhelm0804@web.de","moniwill@t-online.de","holger.Lusche@t-online.de","ronnyundute@t-online.de","jg.rauchhaus@freenet.de","RoszkiewiczW@aol.com","R.Santer@t-online.de"};
		String [] phoneList =  {"student", "015464654", "0654654605465", "033056/82400","03338/766528","03476/854111","03338/38531","033398/76478","033397/72709","033398/87522","030/4744614","033397/61285","0178/4909813"};
		String [] keywordList = {"student", "hurenfink", "trhtrhrthtrh", "rthrthtrhtrh","ösdkkfjg","södgh","uiouior","diordigh","kjdfgss","pweornb","mdflkgdf","dfgdfg","ödkfgjsdäflg"};
		String [] mobilList = {"student", "01762584545", "032154875454", "033056/82400","03338/766528","03476/854111","03338/38531","033398/76478","033397/72709","033398/87522","030/4744614","033397/61285","0178/4909813"};
		int [] fachsemesterList = {6,3,3,2,3,5,1,6,3,4,4,5,6};
		
		
		Logger.getLogger(dummy.class.getName())
	    .log(Level.INFO, 
	    "studentctrl Liste: 2   "+nds+ " "+gender);
		
		for (int i = 0; i < 7; i++){
			nds = ndsList[i];
			gender = genderList[i];
			firstname = firstnameList[i];
			lastname = lastnameList[i];
			adresse = adresseList[i];
			email = emailListe[i];
			phone = phoneList[i];
			mobil = mobilList[i];
			keyword = keywordList[i];
			fachsemester = fachsemesterList[i];
			
			
			Logger.getLogger(dummy.class.getName())
		    .log(Level.INFO, 
		    "studentctrl Liste: 3 "+nds+ " "+gender);
			
			dbP.createStudent( nds, gender, firstname, lastname, adresse, email, phone,mobil , keyword,fachsemester);
			
			Logger.getLogger(dummy.class.getName())
		    .log(Level.INFO, 
		    "studentctrl Liste:   "+nds+ " "+gender);
		}
	}
}

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
	DbMisc dbM;
	
	public String action() {
		generateCourses();
		generateStudents();
		return "index";
	}
	
	public String tuEs(){
		String a = "peter";
		String b = "hans";
		Logger.getLogger(dummy.class.getName())
	    .log(Level.INFO, 
	    "studentctrl Liste:   "+a+ " "+b);
		return "index";
	}
	
	public void generateCourses(){
		String name = "hans";
		int studentCount = 12;
		
		
		String [] nameList = {"Praxis des Programmierens","IT-Security I","IT-Finance II","Unternehmensmodellierung","Internettechnologien und Network-Computing","IT-Security II","Objektorientierte Programmierung","Buchhaltung","Kosten- und Leistungsrechnung ","Grundzüge des Privatrechts"};
		int [] studentCountList = {56,45,76,34,65,76,87,98,67,78};
		int id;
		Date date = java.sql.Date.valueOf("2010-01-30");
		String fieldOfStudy = "Winfo";
		for (int i = 1; i <nameList.length; i++){
			id = i;
			name = nameList[i];
			studentCount = studentCountList[i];
			dbC.createCourse(studentCount, name, fieldOfStudy, date);	
			
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
		
		String [] ndsList = {"123","admin", "auth", "student","hju87448","kdf90873","urt45627","uru94858","lko87474","hdg35462","jhg74645","wes65710","scg64642"};
		String [] genderList = {"male","male","female","male","female","male","female","male","female","male","female","male","female"};
		String [] firstnameList = {"123","admin", "auth", "Alexander","Manuela","Peter","Barbara","Hans","Nicola","Mike","Jane","Ludwich","Sandra"};
		String [] lastnameList = {"123","admin", "auth", "Mayer","Schönefeld","Brummer","Tannenbaum","Goldmann","Fischermann","Silbermann","Schuhmacher","Holzmann","Wassermann"};
		String [] adresseList = {"123","admin", "123", "Schönfließ Dorfstraße 35","Bernau Saturnring16","Bernau Rollenhagenstr.29","Werneuchen Löhmerdorfstr","Zühlsdorf Wandlitzer Chaussee 14","Panketal Bernauerstraße 41","Werneuchen Marxstraße 32","Zühlsdorf Basdorferstr.50","Lindenberg Karl-Marx-Str.20","Werneuchen Löhmerdorfstr. 50a"};
		String [] emailListe = {"123","admin", "123", "hbork4@aol.com","thomas-bussdorf@web.de","werbegestalter@web.de","wilhelm0804@web.de","moniwill@t-online.de","holger.Lusche@t-online.de","ronnyundute@t-online.de","jg.rauchhaus@freenet.de","RoszkiewiczW@aol.com","R.Santer@t-online.de"};
		String [] phoneList =  {"123","admin", "123", "033056/82400","03338/766528","03476/854111","03338/38531","033398/76478","033397/72709","033398/87522","030/4744614","033397/61285","0178/4909813"};
		String [] keywordList = {"123","admin", "auth", "student","ösdkkfjg","södgh","uiouior","diordigh","kjdfgss","pweornb","mdflkgdf","dfgdfg","ödkfgjsdäflg"};
		String [] mobilList = {"123","admin", "123", "033056/82400","03338/766528","03476/854111","03338/38531","033398/76478","033397/72709","033398/87522","030/4744614","033397/61285","0178/4909813"};
		int [] fachsemesterList = {1,3,3,2,3,5,1,6,3,4,4,5,6};
		int [] loginvaluelist = {0,0,1,2,2,2,2,2,2,2,2,2,2};

		
		Logger.getLogger(dummy.class.getName())
	    .log(Level.INFO, 
	    "studentctrl Liste: 2   "+nds+ " "+gender);
		
		for (int i = 0; i <ndsList.length; i++){
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
	
	public String generateFieldsOfStudy(){
		String name;
				
		String [] nameList = {"Bwl","Vwl", "Wirtschaftsinformatik","iVwl", "Jura"};
		for (int i = 1; i <nameList.length; i++){
			name = nameList[i];
			dbM.createFieldsOfStudy(name);	
			
		}
		return "addCourse";
	}
	
	
}

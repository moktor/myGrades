package controller;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Iterator;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import model.*;
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
	DbCourses dbC;
	@EJB
	DbPerson dbP;
	
	public String tuEs(){
		String a = "peter";
		String b = "hans";
		Logger.getLogger(dummy.class.getName())
	    .log(Level.INFO, 
	    "studentctrl Liste:   "+a+ " "+b);
		return "addCourse";
	}
	
	public String genarateCourses(){
		String name = "hans";
		int studentCount = 12;
				
		String [] nameList = {"Praxis des Programmierens","IT-Security I","IT-Finance II","Unternehmensmodellierung","Internettechnologien und Network-Computing","IT-Security II","Objektorientierte Programmierung","Buchhaltung","Kosten- und Leistungsrechnung ","Grundz�ge des Privatrechts"};
		int [] studentCountList = {56,45,76,34,65,76,87,98,67,78};
		for (int i = 1; i <nameList.length; i++){
			name = nameList[i];
			studentCount = studentCountList[i];
			dbC.createCourse(name,studentCount);	
			
			Logger.getLogger(dummy.class.getName())
		    .log(Level.INFO, 
		    "studentctrl Liste:   "+name+ " "+studentCount);
		}
		return "addCourse";
	}

	public String generateStudents (){
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
		boolean loginvalue;
		
		Logger.getLogger(dummy.class.getName())
	    .log(Level.INFO, 
	    "studentctrl Liste:   "+nds+ " "+gender);
		
		String [] ndsList = {"admin", "123", "sdg12345","hju87448","kdf90873","urt45627","uru94858","lko87474","hdg35462","jhg74645","wes65710","scg64642"};
		String [] genderList = {"male","female","male","female","male","female","male","female","male","female","male","female"};
		String [] firstnameList = {"admin", "123", "Alexander","Manuela","Peter","Barbara","Hans","Nicola","Mike","Jane","Ludwich","Sandra"};
		String [] lastnameList = {"admin", "123", "Mayer","Sch�nefeld","Brummer","Tannenbaum","Goldmann","Fischermann","Silbermann","Schuhmacher","Holzmann","Wassermann"};
		String [] adresseList = {"admin", "123", "Sch�nflie� Dorfstra�e 35","Bernau Saturnring16","Bernau Rollenhagenstr.29","Werneuchen L�hmerdorfstr","Z�hlsdorf Wandlitzer Chaussee 14","Panketal Bernauerstra�e 41","Werneuchen Marxstra�e 32","Z�hlsdorf Basdorferstr.50","Lindenberg Karl-Marx-Str.20","Werneuchen L�hmerdorfstr. 50a"};
		String [] emailListe = {"admin", "123", "hbork4@aol.com","thomas-bussdorf@web.de","werbegestalter@web.de","wilhelm0804@web.de","moniwill@t-online.de","holger.Lusche@t-online.de","ronnyundute@t-online.de","jg.rauchhaus@freenet.de","RoszkiewiczW@aol.com","R.Santer@t-online.de"};
		String [] phoneList =  {"admin", "123", "033056/82400","03338/766528","03476/854111","03338/38531","033398/76478","033397/72709","033398/87522","030/4744614","033397/61285","0178/4909813"};
		String [] keywordList = {"admin", "123", "sdkl�g","�sdkkfjg","s�dgh","uiouior","diordigh","kjdfgss","pweornb","mdflkgdf","dfgdfg","�dkfgjsd�flg"};
		String [] mobilList = {"admin", "123", "033056/82400","03338/766528","03476/854111","03338/38531","033398/76478","033397/72709","033398/87522","030/4744614","033397/61285","0178/4909813"};
		int [] fachsemesterList = {3,3,2,3,5,1,6,3,4,4,5,6};
		boolean [] loginvaluelist = {true,false,true,false,true,false,true,false,true,false,true,false};
		
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
			loginvalue = loginvaluelist[i];
			
			Logger.getLogger(dummy.class.getName())
		    .log(Level.INFO, 
		    "studentctrl Liste: 3 "+nds+ " "+gender);
			
			dbP.createStudent( nds, gender, firstname, lastname, adresse, email, phone,mobil ,loginvalue, keyword,fachsemester);
			
			Logger.getLogger(dummy.class.getName())
		    .log(Level.INFO, 
		    "studentctrl Liste:   "+nds+ " "+gender);
		}
		return "addCourse";
	}
}

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
	
	public void generateAdmin(){
		String nds = "admin";
		String gender = "male";
		String firstname = "Herr";
		String lastname = "Hilz";
		String adresse = "Bernau Saturnring 16";
		String email = "herrhilz@meckerkasten.de";
		String phone = "0941-12541858";
		String mobil = "0176-12541858";
		String keyword = "21232f297a57a5a743894a0e4a801fc335d61feb7f";
		String salt = "35d61feb7f";
		
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
		String keyword = "fa53b91ccc1b78668d5af58e1ed3a485556c203a56";
		String salt = "556c203a56";
		
		dbE.createExamAuth(nds, title,gender, firstname, lastname,fieldofstudy,adresse,email,phone,mobil,keyword,salt);
	}
	
	public void generateCourses(){
		String name = "hans";
		int studentCount = 12;
		
		
		String [] nameList = {"Praxis des Programmierens","IT-Security I","IT-Finance II","Unternehmensmodellierung","Internettechnologien und Network-Computing","IT-Security II","Objektorientierte Programmierung","Buchhaltung","Kosten- und Leistungsrechnung ","Grundzüge des Privatrechts"};
		int [] studentCountList = {56,45,76,34,65,76,87,98,67,78};
		Date date = java.sql.Date.valueOf("2010-01-30");
		String fieldOfStudy = "Winfo";
		for (int i = 1; i <nameList.length; i++){
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
		String salt;
		
		
		Logger.getLogger(dummy.class.getName())
	    .log(Level.INFO, 
	    "studentctrl Liste:   "+nds+ " "+gender);
		//auth=fa53b91ccc1b78668d5af58e1ed3a485 + 556c203a56
		//student = cd73502828457d15655bbd7a63fb0bc8 + a1e43b5335
		//admin = 21232f297a57a5a743894a0e4a801fc3 + 35d61feb7f
		//lej55128 = c64662bb019696c945bf641da015cfe4 + e48e132073
		//hom59678 = 4a04676a8c18ef5baad45cd5e5e78d63 + 41b6bffb7f
		String [] ndsList = {"jhg83745", "student", "hwe95867","lej55128","hom59678","urt45627","uru94858","lko87474","hdg35462","jhg74645","wes65710","scg64642"};
		String [] genderList = {"male","female","male","male","male","female","male","female","male","female","male","female"};
		String [] firstnameList = {"Hans", "Manuela", "Alexander","Jonas","Mathias","Barbara","Hans","Nicola","Mike","Jane","Ludwich","Sandra"};
		String [] lastnameList = {"Goldmannen", "Jungens", "Mayer","Lehmann","Hofer","Tannenbaum","Goldmann","Fischermann","Silbermann","Schuhmacher","Holzmann","Wassermann"};
		String [] adresseList = {"Kleinestraße 14", "Großestraße 34", "Schönfließ Dorfstraße 35","Hemauerstra&szlig;e 20","Stra&szlig;e der Freiheit","Werneuchen Löhmerdorfstr","Zühlsdorf Wandlitzer Chaussee 14","Panketal Bernauerstraße 41","Werneuchen Marxstraße 32","Zühlsdorf Basdorferstr.50","Lindenberg Karl-Marx-Str.20","Werneuchen Löhmerdorfstr. 50a"};
		String [] emailListe = {"ertzdf@gmx.net", "sdfsdfsdf@gmx.net", "hbork4@aol.com","j.lehmann@bondis.de","werbegestalter@web.de","wilhelm0804@web.de","moniwill@t-online.de","holger.Lusche@t-online.de","ronnyundute@t-online.de","jg.rauchhaus@freenet.de","RoszkiewiczW@aol.com","R.Santer@t-online.de"};
		String [] phoneList =  {"015464654", "0654654605465", "033056/82400","0176988521444","03476/854111","03338/38531","033398/76478","033397/72709","033398/87522","030/4744614","033397/61285","0178/4909813"};
		String [] keywordList = {"fa53b91ccc1b78668d5af58e1ed3a485556c203a56", "cd73502828457d15655bbd7a63fb0bc8a1e43b5335", "21232f297a57a5a743894a0e4a801fc335d61feb7f","c64662bb019696c945bf641da015cfe4e48e132073","4a04676a8c18ef5baad45cd5e5e78d6341b6bffb7f","uiouior","diordigh","kjdfgss","pweornb","mdflkgdf","dfgdfg","ödkfgjsdäflg"};
		String [] mobilList = {"01762584545", "032154875454", "033056/82400","0176988521444","03476/854111","03338/38531","033398/76478","033397/72709","033398/87522","030/4744614","033397/61285","0178/4909813"};
		int [] fachsemesterList = {3,3,2,6,5,1,6,3,4,4,5,6};
		String [] saltList = {"556c203a56","a1e43b5335","35d61feb7f","e48e132073","41b6bffb7f","b162228224","ea122a1357","471e84ecca","d7e1f3102e","ea122a1357","471e84ecca","d7e1f3102e"};
		
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
			salt = saltList [i];
			
			Logger.getLogger(dummy.class.getName())
		    .log(Level.INFO, 
		    "studentctrl Liste: 3 "+nds+ " "+gender);
			
			dbP.createStudent( nds, gender, firstname, lastname, adresse, email, phone,mobil , keyword, fachsemester,salt);
			
			Logger.getLogger(dummy.class.getName())
		    .log(Level.INFO, 
		    "studentctrl Liste:   "+nds+ " "+gender);
		}
	}
}

package login;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.UserTransaction;

import model.Student;


@ManagedBean(name = "login")
@SessionScoped
public class User {
	
	private String nds;
	private String keyword;
	private boolean loggedin;
	private Student student;
	
	@PersistenceContext
	private EntityManager em;
	

	
	public User(){
		Logger.getLogger(User.class.getName()).log(Level.INFO,"Instanz erzeugt");
	}
	
	
	public String login(){
		Logger.getLogger(User.class.getName()).log(Level.INFO,"nds:"+nds+" pw: "+keyword);
		
		Query query = em.createQuery("select s from Student s where s.nds = :nds and s.keyword = :keyword");
		query.setParameter("nds", nds);
		query.setParameter("keyword", keyword);
		List<Student> students = query.getResultList();
		Logger.getLogger(User.class.getName()).log(Level.INFO,"testlogger");
		if (students.size() == 1) {
			Logger.getLogger(User.class.getName()).log(Level.INFO,"User gefunden");
			student = students.get(0);
			setLoggedin(true);
			return "_authority/auth_welcome.xhtml?faces-redirect=true";
		} else {
			Logger.getLogger(User.class.getName()).log(Level.INFO,"User nicht gefunden");
			setLoggedin(false);
			return "loginError";
		}
	}
	
	
	
    public String logout() {
    	student = null;
    	setLoggedin(false);
    	Logger.getLogger(User.class.getName()).log(Level.INFO,"ausgeloggt");
    	// oder richtig:
    	return "/index.xhtml";
    }
	

	
	
	public void setNds(String nds) {
		this.nds = nds;
	}
	public String getNds() {
		return nds;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getKeyword() {
		return keyword;
	}

	public void setLoggedin(boolean loggedin) {
		this.loggedin = loggedin;
	}

	public boolean isLoggedin() {
		return loggedin;
	}


}

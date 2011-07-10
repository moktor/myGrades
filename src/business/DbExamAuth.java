package business;

import java.sql.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import controller.courseCtrl;

import model.*;

//======================================= AS ==========================================

@Stateless
public class DbExamAuth {

	@PersistenceContext
	private EntityManager em;
	
	public DbExamAuth(){}
	
	public void createExamAuth(String nds, String title,String gender, String firstname,
			String name, String fieldofstudy, String adresse, String email,
			String phone, String mobil, String keyword, String salt) {
		
		ExamAuth e = new ExamAuth(nds, gender,title, firstname, name,fieldofstudy,adresse,email,phone,mobil,keyword, salt);
		em.persist(e);
	}

}

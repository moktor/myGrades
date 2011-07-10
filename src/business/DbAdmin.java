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
import controller.dummy;

import model.*;

//======================================= AS ==========================================

@Stateless
public class DbAdmin {

	@PersistenceContext
	private EntityManager em;
	
	public DbAdmin(){}
	
	public void createAdmin(String nds, String gender, String firstname,
			String lastname, String adresse, String email, String phone,
			String mobil, String keyword, String salt) {
		Logger.getLogger(dummy.class.getName())
	    .log(Level.INFO, 
	    "studentctrl Liste: 3 "+nds+ " "+gender);
		Admin a = new Admin (nds, gender, firstname, lastname,adresse,email,phone,mobil,keyword,salt);
		em.persist(a);
	}

}

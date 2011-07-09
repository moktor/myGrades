package model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: admin
 *
 */
@Entity

public class Admin implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5975597567059046416L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private int nds;
	private String gender;
	private String firstname;
	private String lastname;
	private String adresse;
	private String email;
	private String phone;
	private String mobil;
	private String keyword;
	private String salt;
	
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the nds
	 */
	public int getNds() {
		return nds;
	}
	/**
	 * @param nds the nds to set
	 */
	public void setNds(int nds) {
		this.nds = nds;
	}
	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}
	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}
	/**
	 * @return the firstname
	 */
	public String getFirstname() {
		return firstname;
	}
	/**
	 * @param firstname the firstname to set
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	/**
	 * @return the lastname
	 */
	public String getLastname() {
		return lastname;
	}
	/**
	 * @param lastname the lastname to set
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	/**
	 * @return the adresse
	 */
	public String getAdresse() {
		return adresse;
	}
	/**
	 * @param adresse the adresse to set
	 */
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * @return the mobil
	 */
	public String getMobil() {
		return mobil;
	}
	/**
	 * @param mobil the mobil to set
	 */
	public void setMobil(String mobil) {
		this.mobil = mobil;
	}
	/**
	 * @return the keyword
	 */
	public String getKeyword() {
		return keyword;
	}
	/**
	 * @param keyword the keyword to set
	 */
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	/**
	 * @return the salt
	 */
	public String getSalt() {
		return salt;
	}
	/**
	 * @param salt the salt to set
	 */
	public void setSalt(String salt) {
		this.salt = salt;
	}
	
	
	
	
   
}

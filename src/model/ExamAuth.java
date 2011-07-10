package model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: examAuth
 *
 */
@Entity

public class ExamAuth extends Person implements Serializable {

	
	/**
	 * 
	 */
	
	// ------------ Generated UID --------------
	private static final long serialVersionUID = -711688575570835551L;
	@Transient
	private boolean deleteInc;
	
	
	
	// ------------------ attributes of the Entity ----------------	

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String nds;
	private String gender;
	private String titel;
	private String firstname;
	private String name;
	private String fieldOfStudy;
	private String adresse;
	private String email;
	private String phone;
	private String mobil;
	private String keyword;
	private String salt;
	
	
	

	// ------------------------ ctors -----------------------------------
	public ExamAuth(){}

	
	public  ExamAuth(String nds,String titel, String gender, String firstname, String name,
			String adresse, String email, String phone, String fieldOfStudy, String mobil,
			String keyword, String salt) {
	
		this.nds = nds;
		this.gender = gender;
		this.titel = titel;
		this.firstname = firstname;
		this.name = name;
		this.adresse = adresse;
		this.email = email;
		this.phone = phone;
		this.mobil = mobil;
		this.keyword = keyword;
		this.salt = salt;
		this.fieldOfStudy = fieldOfStudy;
	}
	
	
	
	// --------------------- getter / setter ----------------------------
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the nds to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	
	/**
	 * @return the nds
	 */
	public String getNds() {
		return nds;
	}
	/**
	 * @param nds the nds to set
	 */
	public void setNds(String nds) {
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
	 * @return the titel
	 */
	public String getTitel() {
		return titel;
	}
	/**
	 * @param gender the gender to set
	 */
	public void setTitel(String titel) {
		this.titel = titel;
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param lastname the name to set
	 */
	public void setName(String lastname) {
		this.name = lastname;
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
	
	
	public void setDeleteInc(boolean deleteInc) {
		this.deleteInc = deleteInc;
	}


	public boolean isDeleteInc() {
		return deleteInc;
	}
	
	public String getFieldOfStudy() {
		return fieldOfStudy;
	}


	public void setFieldOfStudy(String fieldOfStudy) {
		this.fieldOfStudy = fieldOfStudy;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((adresse == null) ? 0 : adresse.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result
				+ ((firstname == null) ? 0 : firstname.hashCode());
		result = prime * result + ((gender == null) ? 0 : gender.hashCode());
		result = prime * result + id;
		result = prime * result + ((keyword == null) ? 0 : keyword.hashCode());
		result = prime * result + ((mobil == null) ? 0 : mobil.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((nds == null) ? 0 : nds.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		result = prime * result + ((salt == null) ? 0 : salt.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ExamAuth other = (ExamAuth) obj;
		if (adresse == null) {
			if (other.adresse != null)
				return false;
		} else if (!adresse.equals(other.adresse))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstname == null) {
			if (other.firstname != null)
				return false;
		} else if (!firstname.equals(other.firstname))
			return false;
		if (gender == null) {
			if (other.gender != null)
				return false;
		} else if (!gender.equals(other.gender))
			return false;
		if (id != other.id)
			return false;
		if (keyword == null) {
			if (other.keyword != null)
				return false;
		} else if (!keyword.equals(other.keyword))
			return false;
		if (mobil == null) {
			if (other.mobil != null)
				return false;
		} else if (!mobil.equals(other.mobil))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (nds == null) {
			if (other.nds != null)
				return false;
		} else if (!nds.equals(other.nds))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		if (salt == null) {
			if (other.salt != null)
				return false;
		} else if (!salt.equals(other.salt))
			return false;
		return true;
	}

	
}




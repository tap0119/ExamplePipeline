package models;

public class Userdata {
	
	//Userdata bean with data from ers_users table
	private Integer esr_user_id;
	private String esr_username;
	private String esr_password;
	private String esr_firstname;
	private String esr_lastname;
	private String user_email;
	private Integer user_role_id;
	

	//hashCode
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((esr_firstname == null) ? 0 : esr_firstname.hashCode());
		result = prime * result + ((esr_lastname == null) ? 0 : esr_lastname.hashCode());
		result = prime * result + ((esr_password == null) ? 0 : esr_password.hashCode());
		result = prime * result + ((esr_user_id == null) ? 0 : esr_user_id.hashCode());
		result = prime * result + ((esr_username == null) ? 0 : esr_username.hashCode());
		result = prime * result + ((user_email == null) ? 0 : user_email.hashCode());
		result = prime * result + ((user_role_id == null) ? 0 : user_role_id.hashCode());
		return result;
	}

	//.equals
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Userdata other = (Userdata) obj;
		if (esr_firstname == null) {
			if (other.esr_firstname != null)
				return false;
		} else if (!esr_firstname.equals(other.esr_firstname))
			return false;
		if (esr_lastname == null) {
			if (other.esr_lastname != null)
				return false;
		} else if (!esr_lastname.equals(other.esr_lastname))
			return false;
		if (esr_password == null) {
			if (other.esr_password != null)
				return false;
		} else if (!esr_password.equals(other.esr_password))
			return false;
		if (esr_user_id == null) {
			if (other.esr_user_id != null)
				return false;
		} else if (!esr_user_id.equals(other.esr_user_id))
			return false;
		if (esr_username == null) {
			if (other.esr_username != null)
				return false;
		} else if (!esr_username.equals(other.esr_username))
			return false;
		if (user_email == null) {
			if (other.user_email != null)
				return false;
		} else if (!user_email.equals(other.user_email))
			return false;
		if (user_role_id == null) {
			if (other.user_role_id != null)
				return false;
		} else if (!user_role_id.equals(other.user_role_id))
			return false;
		return true;
	}

	//toString 
	@Override
	public String toString() {
		return "userdata1 [esr_user_id=" + esr_user_id + ", esr_username=" + esr_username + ", esr_password="
				+ esr_password + ", esr_firstname=" + esr_firstname + ", esr_lastname=" + esr_lastname + ", user_email="
				+ user_email + ", user_role_id=" + user_role_id + "]";
	}

	//constructor with all fields
	public Userdata(Integer esr_user_id, String esr_username, String esr_password, String esr_firstname,
			String esr_lastname, String user_email, Integer user_role_id) {
		super();
		this.esr_user_id = esr_user_id;
		this.esr_username = esr_username;
		this.esr_password = esr_password;
		this.esr_firstname = esr_firstname;
		this.esr_lastname = esr_lastname;
		this.user_email = user_email;
		this.user_role_id = user_role_id;
	}
	
	//constructor without fields
	public Userdata() {
		super();
	}
	
	//Getters and Setters
	public Integer getEsr_user_id() {
		return esr_user_id;
	}
	public void setEsr_user_id(Integer esr_user_id) {
		this.esr_user_id = esr_user_id;
	}
	
	public String getEsr_username() {
		return esr_username;
	}
	public void setEsr_username(String esr_username) {
		this.esr_username = esr_username;
	}
	
	public String getEsr_password() {
		return esr_password;
	}
	public void setEsr_password(String esr_password) {
		this.esr_password = esr_password;
	}
	
	public String getEsr_firstname() {
		return esr_firstname;
	}
	public void setEsr_firstname(String esr_firstname) {
		this.esr_firstname = esr_firstname;
	}
	
	public String getEsr_lastname() {
		return esr_lastname;
	}
	public void setEsr_lastname(String esr_lastname) {
		this.esr_lastname = esr_lastname;
	}
	
	public String getUser_email() {
		return user_email;
	}
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
	
	public Integer getUser_role_id() {
		return user_role_id;
	}
	public void setUser_role_id(Integer user_role_id) {
		this.user_role_id = user_role_id;
	}


}

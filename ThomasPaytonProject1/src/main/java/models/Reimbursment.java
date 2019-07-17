package models;

import java.io.InputStream;


public class Reimbursment {
	
	//from the ers_reimbursment table
	
	private Integer reimb_id;
	private Double reimb_amount;
	private String reimb_submitted;
	private String reimb_resolved;
	private String reimb_description;
	private InputStream reimb_receipt; //blob picture
	private Integer reimb_author;
	private Integer reimb_resolver;
	private Integer reimb_status_id;
	private Integer reimb_type_id;
	
	//hashCode
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((reimb_amount == null) ? 0 : reimb_amount.hashCode());
		result = prime * result + ((reimb_author == null) ? 0 : reimb_author.hashCode());
		result = prime * result + ((reimb_description == null) ? 0 : reimb_description.hashCode());
		result = prime * result + ((reimb_receipt == null) ? 0 : reimb_receipt.hashCode());
		result = prime * result + ((reimb_resolved == null) ? 0 : reimb_resolved.hashCode());
		result = prime * result + ((reimb_resolver == null) ? 0 : reimb_resolver.hashCode());
		result = prime * result + ((reimb_status_id == null) ? 0 : reimb_status_id.hashCode());
		result = prime * result + ((reimb_submitted == null) ? 0 : reimb_submitted.hashCode());
		result = prime * result + ((reimb_type_id == null) ? 0 : reimb_type_id.hashCode());
		result = prime * result + ((reimb_id == null) ? 0 : reimb_id.hashCode());
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
		Reimbursment other = (Reimbursment) obj;
		if (reimb_amount == null) {
			if (other.reimb_amount != null)
				return false;
		} else if (!reimb_amount.equals(other.reimb_amount))
			return false;
		if (reimb_author == null) {
			if (other.reimb_author != null)
				return false;
		} else if (!reimb_author.equals(other.reimb_author))
			return false;
		if (reimb_description == null) {
			if (other.reimb_description != null)
				return false;
		} else if (!reimb_description.equals(other.reimb_description))
			return false;
		if (reimb_receipt == null) {
			if (other.reimb_receipt != null)
				return false;
		} else if (!reimb_receipt.equals(other.reimb_receipt))
			return false;
		if (reimb_resolved == null) {
			if (other.reimb_resolved != null)
				return false;
		} else if (!reimb_resolved.equals(other.reimb_resolved))
			return false;
		if (reimb_resolver == null) {
			if (other.reimb_resolver != null)
				return false;
		} else if (!reimb_resolver.equals(other.reimb_resolver))
			return false;
		if (reimb_status_id == null) {
			if (other.reimb_status_id != null)
				return false;
		} else if (!reimb_status_id.equals(other.reimb_status_id))
			return false;
		if (reimb_submitted == null) {
			if (other.reimb_submitted != null)
				return false;
		} else if (!reimb_submitted.equals(other.reimb_submitted))
			return false;
		if (reimb_type_id == null) {
			if (other.reimb_type_id != null)
				return false;
		} else if (!reimb_type_id.equals(other.reimb_type_id))
			return false;
		if (reimb_id == null) {
			if (other.reimb_id != null)
				return false;
		} else if (!reimb_id.equals(other.reimb_id))
			return false;
		return true;
	}

	//toString
	@Override
	public String toString() {
		return "Reimbursment [reimd_id=" + reimb_id + ", reimb_amount=" + reimb_amount + ", reimb_submitted="
				+ reimb_submitted + ", reimb_resolved=" + reimb_resolved + ", reimb_description=" + reimb_description
				+ ", reimb_receipt=" + reimb_receipt + ", reimb_author=" + reimb_author + ", reimb_resolver="
				+ reimb_resolver + ", reimb_status_id=" + reimb_status_id + ", reimb_type_id=" + reimb_type_id + "]";
	}

	//constructor with fields
	public Reimbursment(Integer reimd_id, Double amount, String reimb_submitted, String reimb_resolved,
			String reimb_description, InputStream blob, Integer reimb_author, Integer reimb_resolver,
			Integer reimb_status_id, Integer reimb_type_id) {
		super();
		this.reimb_id = reimd_id;
		this.reimb_amount = amount;
		this.reimb_submitted = reimb_submitted;
		this.reimb_resolved = reimb_resolved;
		this.reimb_description = reimb_description;
		this.reimb_receipt = blob;
		this.reimb_author = reimb_author;
		this.reimb_resolver = reimb_resolver;
		this.reimb_status_id = reimb_status_id;
		this.reimb_type_id = reimb_type_id;
	}

	//constructor from super class
	public Reimbursment() {
		super();
	}

	
	//Getters and setters
	public Integer getReimd_id() {
		return reimb_id;
	}
	public void setReimd_id(Integer reimd_id) {
		this.reimb_id = reimd_id;
	}

	public Double getReimb_amount() {
		return reimb_amount;
	}
	public void setReimb_amount(Double reimb_amount) {
		this.reimb_amount = reimb_amount;
	}

	public String getReimb_submitted() {
		return reimb_submitted;
	}
	public void setReimb_submitted(String reimb_submitted) {
		this.reimb_submitted = reimb_submitted;
	}

	public String getReimb_resolved() {
		return reimb_resolved;
	}
	public void setReimb_resolved(String reimb_resolved) {
		this.reimb_resolved = reimb_resolved;
	}

	public String getReimb_description() {
		return reimb_description;
	}
	public void setReimb_description(String reimb_description) {
		this.reimb_description = reimb_description;
	}

	public InputStream getReimb_receipt() {
		return reimb_receipt;
	}
	public void setReimb_receipt(InputStream reimb_receipt) {
		this.reimb_receipt = reimb_receipt;
	}

	public Integer getReimb_author() {
		return reimb_author;
	}
	public void setReimb_author(Integer reimb_author) {
		this.reimb_author = reimb_author;
	}

	public Integer getReimb_resolver() {
		return reimb_resolver;
	}
	public void setReimb_resolver(Integer reimb_resolver) {
		this.reimb_resolver = reimb_resolver;
	}

	public Integer getReimb_status_id() {
		return reimb_status_id;
	}
	public void setReimb_status_id(Integer reimb_status_id) {
		this.reimb_status_id = reimb_status_id;
	}

	public Integer getReimb_type_id() {
		return reimb_type_id;
	}
	public void setReimb_type_id(Integer reimb_type_id) {
		this.reimb_type_id = reimb_type_id;
	}

}

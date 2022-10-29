package com.rabeh.demo.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Entity
public class Contact {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty
	@NotBlank
	private String name;
	
	@Email
	private String email;
	
	@NotEmpty
	private String message;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;
	
	private boolean readFlag;
	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public boolean isReadFlag() {
		return readFlag;
	}
	public void setReadFlag(boolean readFlag) {
		this.readFlag = readFlag;
	}
	@Override
	public String toString() {
		return "Contact [id=" + id + ", name=" + name + ", email=" + email + ", message=" + message + "]";
	}
	public Contact(@NotEmpty @NotBlank String name, @Email String email, @NotEmpty String message, boolean readFlag) {
		super();
		this.name = name;
		this.email = email;
		this.message = message;
		this.readFlag = readFlag;
		this.setCreateDate(new Date());
	}
	public Contact() {
		this.setCreateDate(new Date());
	}
	
	

}

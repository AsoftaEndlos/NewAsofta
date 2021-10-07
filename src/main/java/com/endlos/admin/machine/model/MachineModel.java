package com.endlos.admin.machine.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.*;
import org.springframework.format.annotation.DateTimeFormat;

import com.endlos.admin.user.model.User;

@Table(name = "Machine")
@Entity
@JsonIdentityInfo( generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")

public class MachineModel implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "machine_id", unique = true)
	private String machineid;

	@Column(name = "loaction")
	private String location;
//	@Column(name = "customer_id")
//	private int user;
	@Column(name = "machine_password")
	private String password;

	@Column(name = "customer_pin")
	private int customerpin;

	@Column(name = "superuser_pin")
	private int superuserpin;
	@Column(name = "status")
	private boolean status;
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private Date datetime;

	@PrePersist
	private void Createdate() {
		datetime = new Date();
	}

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "model"})
	private User user;
	
	@ManyToMany(mappedBy = "machinedtails")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Set<User> Superuser;
	
	public MachineModel() {
	}

	public MachineModel(Long id, String machineid, String location, String password, int customerpin, int superuserpin,
			boolean status, Date datetime) {
		super();
		this.id = id;
		this.machineid = machineid;
		this.location = location;
		this.password = password;
		this.customerpin = customerpin;
		this.superuserpin = superuserpin;
		this.status = status;
		this.datetime = datetime;

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMachineid() {
		return machineid;
	}

	public void setMachineid(String machineid) {
		this.machineid = machineid;
	}

	public int getCustomerpin() {
		return customerpin;
	}

	public void setCustomerpin(int customerpin) {
		this.customerpin = customerpin;
	}

	public int getSuperuserpin() {
		return superuserpin;
	}

	public void setSuperuserpin(int superuserpin) {
		this.superuserpin = superuserpin;
	}

//	public User getUser() {
//		return user;
//	}
//
//	public void setUser(User user) {
//		this.user = user;
//	}

	public Date getDatetime() {
		return datetime;
	}



	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Set<User> getSuperuser() {
		return Superuser;
	}

	public void setSuperuser(Set<User> superuser) {
		Superuser = superuser;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


	public void assignCustomer(User user) {
		this.user=user;
	}
}

package com.example.demo.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.example.demo.Machine.MachineModel;
import com.example.demo.fileuploading.Fileinfomodel;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "users", uniqueConstraints = { @UniqueConstraint(columnNames = "username"),
		@UniqueConstraint(columnNames = "email") })
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Size(max = 20)
	private String username;

	@NotBlank
	@Size(max = 50)
	@Email
	private String email;

	@NotBlank
	@Size(max = 120)
	private String password;
	@NotBlank
	private String address;
	@NotBlank
	private String phoneno;

//	@Column(unique = true,length = 6)
//	private int pin;


	private int status;
	@NotBlank
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private Date datetime;

	@PrePersist
	private void Createdate() {
		datetime = new Date();
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();

	// Machine Related Joins query
//	@OneToMany(mappedBy = "user",fetch = FetchType.EAGER,
//			cascade = CascadeType.ALL,
//			orphanRemoval = true)
//	@JoinColumn(referencedColumnName = "user_id")
//	@JsonIgnoreProperties("user")
//	private List<MachineModel> model=new ArrayList<MachineModel>();

	@ManyToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	@JoinTable(name = "suassign_machine", joinColumns = @JoinColumn(name = "su_id"), inverseJoinColumns = @JoinColumn(name = "msu_id"))
	@JsonIgnoreProperties
	private List<MachineModel> machinemodel = new ArrayList<MachineModel>();
	
	@OneToOne(targetEntity = Fileinfomodel.class, cascade = CascadeType.ALL)
	private Fileinfomodel fileinfomodel;

	public User() {
	}

	public User(String username, String email, String password, String address, String phoneno,  int status
			) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
		this.address = address;
		this.phoneno = phoneno;
		this.status = status;
		//his.datetime = datetime;
	}

//	public List<MachineModel> getModel() {
//		return model;
//	}
//
//	public void setModel(List<MachineModel> model) {
//		this.model = model;
//	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneno() {
		return phoneno;
	}

	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getDatetime() {
		return datetime;
	}

	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}

	public List<MachineModel> getMachinemodel() {
		return machinemodel;
	}

	public void setMachinemodel(List<MachineModel> machinemodel) {
		this.machinemodel = machinemodel;
	}

	public Fileinfomodel getFileinfomodel() {
		return fileinfomodel;
	}

	public void setFileinfomodel(Fileinfomodel fileinfomodel) {
		this.fileinfomodel = fileinfomodel;
	}

	public void assignmachine(MachineModel machinemodel2) {
		// TODO Auto-generated method stub
		machinemodel.add(machinemodel2);
	}



//	public int getPin() {
//		return pin;
//	}
//
//	public void setPin(int pin) {
//		this.pin = pin;
//	}

}
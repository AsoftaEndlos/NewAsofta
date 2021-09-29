package com.example.demo.user.model;

import java.io.Serializable;
import java.util.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.*;
import org.springframework.format.annotation.DateTimeFormat;

import com.example.demo.machine.model.MachineModel;
import com.example.demo.file.model.Fileinfomodel;

@Entity
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = "username"),
        @UniqueConstraint(columnNames = "email")})
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "id")
public class User implements Serializable {
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
    @JsonIgnore
    private Set<Role> roles = new HashSet<>();

    // Machine Related Joins query
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER,
            orphanRemoval = true,
            cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = "user")
    private List<MachineModel> model = new ArrayList<>();

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(name = "suassign_machine", joinColumns = @JoinColumn(name = "su_id"), inverseJoinColumns = @JoinColumn(name = "msu_id"))
    @JsonIgnoreProperties(value = "Superuser",ignoreUnknown = false)
    private Set<MachineModel> machinedtails = new HashSet<>();


    @OneToOne(targetEntity = Fileinfomodel.class, cascade = CascadeType.ALL)
    private Fileinfomodel fileinfomodel;

    public User() {
    }

    public User(String username, String email, String password, String address, String phoneno, int status
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

    public Set<MachineModel> getMachinedtails() {
        return machinedtails;
    }

    public void setMachinedtails(Set<MachineModel> machinedtails) {
        this.machinedtails = machinedtails;
    }

    public Fileinfomodel getFileinfomodel() {
        return fileinfomodel;
    }

    public void setFileinfomodel(Fileinfomodel fileinfomodel) {
        this.fileinfomodel = fileinfomodel;
    }


    public List<MachineModel> getModel() {
        return model;
    }

    public void setModel(List<MachineModel> model) {
        this.model = model;
    }

    public void assignmachine(MachineModel machinemodel2) {
        machinedtails.add(machinemodel2);
    }

    public void assignCustomer(MachineModel model2) {
        // model
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getStatus() == user.getStatus() && Objects.equals(getId(), user.getId()) && Objects.equals(getUsername(), user.getUsername()) && Objects.equals(getEmail(), user.getEmail()) && Objects.equals(getPassword(), user.getPassword()) && Objects.equals(getAddress(), user.getAddress()) && Objects.equals(getPhoneno(), user.getPhoneno()) && Objects.equals(getDatetime(), user.getDatetime()) && Objects.equals(getRoles(), user.getRoles()) && Objects.equals(getMachinedtails(), user.getMachinedtails()) && Objects.equals(getFileinfomodel(), user.getFileinfomodel());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUsername(), getEmail(), getPassword(), getAddress(), getPhoneno(), getStatus(), getDatetime(), getRoles(), getMachinedtails(), getFileinfomodel());
    }

    public void Deletemachine(MachineModel machinemodel) {
        machinedtails.remove(machinemodel);
    }


//	public int getPin() {
//		return pin;
//	}
//
//	public void setPin(int pin) {
//		this.pin = pin;
//	}

}
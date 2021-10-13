package com.endlos.admin.user.model;

import com.endlos.admin.file.model.Fileinfomodel;
import com.endlos.admin.machine.model.MachineModel;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = "username"),
        @UniqueConstraint(columnNames = "email")})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "please fill Unique Name ")
    @Size(min = 3, max = 20, message = " required Username is not accept  < 3 or > 20")
    private String username;
    @Value("{validation.mail.notEmpty}")
    @NotNull(message = "Enter Your Valid Email")
    @Size(max = 50)
    @Email
    private String email;


    @NotEmpty
    @Length(min = 5, message = "*Your password must have at least 5 characters")
    private String password;

    @NotBlank(message = "please fill your Address")
    private String address;

    @NotBlank(message = "please fill your Number")
    @Column(unique = true)
    private String phoneno;

//	@Column(unique = true,length = 6)
//	private int pin;

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
    @OneToMany(targetEntity = MachineModel.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "c_id", referencedColumnName = "id")
    @JsonIgnoreProperties("Customer")
    private Set<MachineModel> CustomerMAchineDetails = new HashSet<>();


    @ManyToMany(targetEntity = MachineModel.class)
//    @JoinTable(name = "suassign_machine", joinColumns = @JoinColumn(name = "su_id"), inverseJoinColumns = @JoinColumn(name = "msu_id"))
    @JsonIgnoreProperties("superuser")
    private Set<MachineModel> machinedtails = new HashSet<>();


    @OneToOne(targetEntity = Fileinfomodel.class, cascade = CascadeType.ALL)
    private Fileinfomodel fileinfomodel;

    public User() {
    }

    public User(String username, String email, String password, String address, String phoneno) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.address = address;
        this.phoneno = phoneno;
        //   this.status = status;
        //    this.datetime = datetime;
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
        System.out.println("model setter getter ");
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

    public Set<MachineModel> getCustomerMAchineDetails() {
        return CustomerMAchineDetails;
    }

    public void setCustomerMAchineDetails(Set<MachineModel> customerMAchineDetails) {
        CustomerMAchineDetails = customerMAchineDetails;
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
        return Objects.equals(getId(), user.getId()) && Objects.equals(getUsername(), user.getUsername()) && Objects.equals(getEmail(), user.getEmail()) && Objects.equals(getPassword(), user.getPassword()) && Objects.equals(getAddress(), user.getAddress()) && Objects.equals(getPhoneno(), user.getPhoneno()) && Objects.equals(getDatetime(), user.getDatetime()) && Objects.equals(getRoles(), user.getRoles()) && Objects.equals(getMachinedtails(), user.getMachinedtails()) && Objects.equals(getFileinfomodel(), user.getFileinfomodel());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUsername(), getEmail(), getPassword(), getAddress(), getPhoneno(), getDatetime(), getRoles(), getMachinedtails(), getFileinfomodel());
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
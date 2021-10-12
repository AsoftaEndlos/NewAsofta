package com.endlos.admin.machine.model;

import com.endlos.admin.user.model.User;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Table(name = "Machine")
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class MachineModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotEmpty(message = "machine id cannot be Empty")
    @Column(name = "machine_id", unique = true)
    private String machineid;

    @Column(name = "loaction")
    private String location;
    //	@Column(name = "customer_id")
//	private int user;
    @NotEmpty(message = "Machine Password cannot be Empty")
    @Column(name = "machine_password", nullable = false)
    private String password;

    @NotNull
    @DecimalMin(value = "100000", message = "Customer Pin length must be 6")
    @DecimalMax("999999")
    @Column(name = "customer_pin")
    private int customerpin;

    @NotNull
    @DecimalMin(value = "100000", message = "super user pin length must be 6")
    @DecimalMax("999999")
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

    @ManyToOne(targetEntity = User.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "m_id", referencedColumnName = "id")
    @JsonIgnoreProperties("CustomerMAchineDetails")
    //private  Set<User> ;
    private User Customer;

    @ManyToMany(targetEntity = User.class, mappedBy = "machinedtails")
    @JsonIgnoreProperties("machinedtails")
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

//	public Set<User> getUser() {
//		return user;
//	}
//
//	public void setUser(Set<User> user) {
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

    public User getCustomer() {
        return Customer;
    }

    public void setCustomer(User customer) {
        Customer = customer;
    }

    public void assignCustomer(User user) {
        this.Customer = user;
    }

    @Override
    public String toString() {
        return "MachineModel{" +
                "id=" + id +
                ", machineid='" + machineid + '\'' +
                ", location='" + location + '\'' +
                ", password='" + password + '\'' +
                ", customerpin=" + customerpin +
                ", superuserpin=" + superuserpin +
                ", status=" + status +
                ", datetime=" + datetime +
                ", Customer=" + Customer +
                ", Superuser=" + Superuser +
                '}';
    }
}

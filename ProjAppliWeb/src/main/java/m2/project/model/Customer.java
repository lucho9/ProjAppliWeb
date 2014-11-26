package m2.project.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import m2.project.model.serialization.CustomerSerializer;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@Table(
	uniqueConstraints={@UniqueConstraint(columnNames={"firstName", "lastName"})}
)
@JsonSerialize(using = CustomerSerializer.class)
public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@OneToMany(mappedBy="c")
	private List<Facture> lf;
	
	@NotNull
	@Size(min = 2, max = 30)
	private String firstName;
	
	@NotNull
	@Size(min = 2, max = 30)
	private String lastName;

	private String address;
	
	@ManyToMany(fetch=FetchType.EAGER)
	private List<CustomerGroup> customerGroups;
	
	public Customer() {
	}

	public Customer(String firstName, String lastName, String address, List<CustomerGroup> customerGroups) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.customerGroups = customerGroups;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<CustomerGroup> getCustomerGroups() {
		return customerGroups;
	}
	public void setCustomerGroups(List<CustomerGroup> customerGroups) {
		this.customerGroups = customerGroups;
	}
	
	public List<Facture> getLf() {
		return lf;
	}

	public void setLf(List<Facture> lf) {
		this.lf = lf;
	}
	
	@Override
	public String toString() {
		return String.format("Customer[id=%d, firstName='%s', lastName='%s']", id, firstName, lastName);
	}
	
	public String getCustomerName() {
		return String.format(lastName + " " + firstName);
	}
}

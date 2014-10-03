package m2.project.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

@Entity
public class Customer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@NotNull
	@Size(min = 2, max = 30)
	private String firstName;
	
	@NotNull
	@Size(min = 2, max = 30)
	private String lastName;

	@NotNull
	@ManyToOne
	private CustomerGroup customerGroup;
	
	public Customer() {
	}

	public Customer(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.customerGroup = null;
	}

	public Customer(String firstName, String lastName, CustomerGroup group) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.customerGroup = group;
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
	public CustomerGroup getCustomerGroup() {
		return customerGroup;
	}
	public void setCustomerGroup(CustomerGroup group) {
		this.customerGroup = group;
	}
	
	@Override
	public String toString() {
		return String.format("Customer[id=%d, firstName='%s', lastName='%s']",
				id, firstName, lastName);
	}
}
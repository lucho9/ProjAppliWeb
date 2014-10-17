package m2.project.model;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Facture implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	private long id;
	
	/*
	@OneToMany(mappedBy="facture")
	private Collection<Product> products ;
	
	@OneToOne
	private Customer customer;
	
	*/
}
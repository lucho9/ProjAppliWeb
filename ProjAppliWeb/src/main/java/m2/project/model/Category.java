package m2.project.model;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import m2.project.model.serialization.CategorySerializer;
import m2.project.model.serialization.CustomerSerializer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;


@JsonSerialize(using = CategorySerializer.class)
@Entity
public class Category {


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	
	@Column(unique=true)
	@NotNull
	@Size(min=2, max=20)
	public String name;

	private String img;
	//private String color;
	
	public Category() {
	
	}

	
	//La TVA représente la taxe sur la valeur ajoutée. 
	//Différents taux de TVA sont possibles : 19,6% dans la plupart des cas, 5.5% pour les denrées alimentaires et les livres 
	//et 2,1% pour les médicaments et les journaux.
	
	@ManyToOne(fetch=FetchType.EAGER)
	private TVA TVA;
	


	public String getImg() {
		return img;
	}



	public void setImg(String img) {
		this.img = img;
	}



	public TVA getTVA() {
		return TVA;
	}



	public void setTVA(TVA tVA) {
		TVA = tVA;
	}



	public Category(String name, Collection<Product> products) {
		super();
		this.name = name;
		this.products = products;
	}



	
	public Category(String name) {
		super();
		this.name = name;
	}



	

	public Category(String name, String img, TVA tva) {

		this.name = name;
		this.img = img;
		this.TVA = tva;
	}

	@OneToMany(mappedBy="category")
	private Collection<Product> products ;


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@JsonIgnore
	public Collection<Product> getProducts() {
		return products;
	}

	public void setProducts(Collection<Product> products) {
		this.products = products;
	}
	/*
	public String getColor() {
		return color;
	}


	public void setColor(String color) {
		this.color = color;
	}*/
}

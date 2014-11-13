package m2.project.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/*
@Entity
public class TVA {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	private long id;
	

	//La TVA représente la taxe sur la valeur ajoutée. 
	//Différents taux de TVA sont possibles : 19,6% dans la plupart des cas, 5.5% pour les denrées alimentaires et les livres 
	//et 2,1% pour les médicaments et les journaux.
	
	private int TVA;
	
	@OneToMany(mappedBy="category")
	private List<Category> lc;
	
	
	
	
}
*/
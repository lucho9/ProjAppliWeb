package m2.project.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class QuantiteCommande {

	@Id
	@GeneratedValue
	private Long Id;
	
	private Integer qte;
	
	
	public Integer getQte() {
		return qte;
	}

	public void setQte(Integer qte) {
		this.qte = qte;
	}

	@ManyToMany(mappedBy="lq")
	List<Facture> lf_qte;

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public List<Facture> getLf_qte() {
		return lf_qte;
	}

	public void setLf_qte(List<Facture> lf_qte) {
		this.lf_qte = lf_qte;
	}

	public QuantiteCommande(Long id, Integer qte, List<Facture> lf_qte) {
		super();
		Id = id;
		this.qte = qte;
		this.lf_qte = lf_qte;
	}

	public QuantiteCommande() {
		super();
	}

	
	
	
	
	
}

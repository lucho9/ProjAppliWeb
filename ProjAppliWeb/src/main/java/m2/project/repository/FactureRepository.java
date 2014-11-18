package m2.project.repository;




import m2.project.model.Facture;



import m2.project.model.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface FactureRepository extends JpaRepository<Facture, Long>{

}

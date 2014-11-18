package m2.project.service.impl;

import java.util.List;

import m2.project.model.Facture;
import m2.project.repository.FactureRepository;
import m2.project.repository.ProductRepository;
import m2.project.service.FactureService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;




@Service
public class FactureServiceImpl implements FactureService{

	
	@Autowired
	FactureRepository frep;


	
	public void save(Facture f) {
		f.ref=getRef(f);
		frep.save(f);
		
		
	}
	
	
	
	//créer une référence produit, voir comment la récupérer dans le .html
	public String getRef(Facture f) {
		StringBuilder s = new StringBuilder();
		s.append("#FACT");
		
		String id=String.valueOf(f.getId());
		s.append(id);
		
		
		return s.toString();
		
	}

}

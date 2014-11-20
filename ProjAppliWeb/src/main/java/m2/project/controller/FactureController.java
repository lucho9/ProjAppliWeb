package m2.project.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import m2.project.model.Customer;
import m2.project.model.Facture;
import m2.project.model.Product;
import m2.project.model.QuantiteCommande;
import m2.project.repository.CustomerRepository;
import m2.project.repository.FactureRepository;
import m2.project.repository.ProductRepository;
import m2.project.repository.QuantiteCommandeRepository;
import m2.project.service.FactureService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;




@Controller
public class FactureController {

	@Autowired
	private FactureRepository factureRepository;
	
	@Autowired
	private FactureService factureService;
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private QuantiteCommandeRepository quantiteCommandeRepository;
	
	@RequestMapping(value = "/facture/validationCommande", method = RequestMethod.POST)
	public String validationPanier(Model model,@ModelAttribute Facture facture, HttpSession session) {
		Map<Long, Product> panier = (Map<Long, Product>)session.getAttribute("panier");
		if(panier == null)
			panier = new HashMap<Long, Product>();
	
		List<Product> valueList = new ArrayList<Product>(panier.values());
		
		Map<Long, Integer> qty = (Map<Long, Integer>)session.getAttribute("qty");
		if(qty == null)
			qty = new HashMap<Long, Integer>();
		
		double prixTotal =  (Double) session.getAttribute("prixTotal");
	
		List<Integer> quantites = new ArrayList<Integer>(qty.values());
		List<QuantiteCommande> valueQte = new ArrayList<QuantiteCommande>();
		
		for(int i=0; i<quantites.size();i++)
		{	
			QuantiteCommande q = new QuantiteCommande();
		q.setQte(quantites.get(i));
		System.out.println(quantites.get(i) + "-----");
		valueQte.add(q);
		quantiteCommandeRepository.save(q);
		System.out.println("obj"+q.getQte() + "-----");
		}
			
		for (int i = 0; i < valueQte.size(); i++) {
			System.out.println("quantité : ---- "+valueQte.get(i).getQte() + " -----");
		}
	
		//session.setAttribute("cust", client);
		//session.setAttribute("produitsCommandes", panier);
		
		facture.setLp(valueList);
		facture.setLq(valueQte);
		facture.setPrixTotal(prixTotal);
		//facture.setLq(qteList);
		
		factureRepository.save(facture);
		//model.addAttribute("facture", facture);
		
		//model.addAttribute("factures", factureRepository.findAll());
		
	    session.removeAttribute("panier");
	    session.removeAttribute("qty");
		session.removeAttribute("prixTotal");
		return "redirect:/caisse";
	}
	
	@RequestMapping(value = "/facture/delete", method = RequestMethod.GET)
	public String deleteProduct(@RequestParam("id") Long id, Model model) {
		
		factureRepository.delete(id);
		
		return "redirect:/factures";
	}
	
	
	@RequestMapping(value = "/factures", method = RequestMethod.GET)
	public String listProducts(Model model, HttpSession session,  Pageable pageable) {

		model.addAttribute("factures", factureRepository.findAll());
		
	   
		return "/facture/facture";
		
	}
}

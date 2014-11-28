package m2.project.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import m2.project.model.Facture;
import m2.project.model.Panier;
import m2.project.model.Product;
import m2.project.model.QuantiteCommande;
import m2.project.service.FactureService;
import m2.project.service.ProductService;

import org.hibernate.mapping.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FactureController {

	@Autowired
	private ProductService productService;
	
	@Autowired
	private FactureService factureService;
	

	
	
	
	@RequestMapping(value = "/facture/validationCommande", method = RequestMethod.POST)
	public String validationPanier(HttpSession session) {
		Panier panier = (Panier)session.getAttribute("panier");
		if (panier != null) {
			factureService.save(panier.getFacture());
		
		
		}
		
		int newstock;
		for (QuantiteCommande p : panier.getProductQuantities().values()) {
			if (panier.getProductQuantities().containsKey(p.getProduct().getId())) {	
				
				newstock=panier.getquantiteFinale(p);
				Product prod=productService.findOne(p.getProduct().getId());
				prod.setStock(newstock);
				productService.save(prod);
				
			}
		}
		
	    session.removeAttribute("panier");
		return "redirect:/caisse";
	}
	
	
	
	@RequestMapping(value = "/facture/delete", method = RequestMethod.GET)
	public String deleteFacture(@RequestParam("id") Long id, Model model) {
		
		factureService.delete(id);
		
		return "redirect:/factures";
	}
	
	@RequestMapping(value = "/facture/display", method = RequestMethod.GET)
	public String displayFacture(@RequestParam("id") Long id, Model model) {
		model.addAttribute("facture", factureService.findOne(id));
		return "/facture/facture";
	}
	
	@RequestMapping(value = "/factures", method = RequestMethod.GET)
	public String listProducts(Model model, HttpSession session,  Pageable pageable, @RequestParam(value="filtername", required = false) String filterName) {
		if (filterName != null && !filterName.isEmpty()) {
			String[] searchTerms = filterName.split(" ");
			String searchTerm1 = searchTerms[0].toLowerCase();
			String searchTerm2 = "<NO TERM>";
			if (searchTerms.length > 1 && searchTerms[1] != null && !searchTerms[1].isEmpty())
				searchTerm2 = searchTerms[1].toLowerCase();

			List<Facture> factures = factureService.findByCustomerNames(searchTerm1, searchTerm2);
			
			model.addAttribute("factures", factures);
			model.addAttribute("filtername", filterName);
			
		}
		else {
			model.addAttribute("factures", factureService.findAll());
			model.addAttribute("filtername", null);
		}
	   
		return "/facture/factures";
		
	}
	
	
}

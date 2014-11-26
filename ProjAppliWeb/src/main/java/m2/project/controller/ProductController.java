package m2.project.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import m2.project.model.Customer;
import m2.project.model.CustomerGroup;
import m2.project.model.ErrorMessage;
import m2.project.model.Facture;
import m2.project.model.JsonResponse;
import m2.project.model.Panier;
import m2.project.model.Product;
import m2.project.repository.CategoryRepository;
import m2.project.repository.CustomerRepository;
import m2.project.service.CustomerService;
import m2.project.service.ProductService;
import m2.project.utils.PageWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
//import m2.project.repository.ProductPredicates;




@Controller
public class ProductController {
	@Autowired
	private CustomerService customerService;

	@Autowired
	private ProductService productService;

	@Autowired
	private CategoryRepository categoryRepository;
	
	String categorie = "";
	String temp = "";
	
	@RequestMapping(value = "/product", method = RequestMethod.GET)
	public String productsList(Model model,Pageable pageable,@ModelAttribute Product product,@RequestParam(value="recherche",required=false)String rch,@RequestParam(value="Min",required=false)String Min,@RequestParam(value="Max",required=false)String Max) {
		// for the customers list
		final PageRequest pageRequest = new PageRequest(pageable.getPageNumber(), 5, Direction.ASC, "name");
		
		Page<Product> curPage = productService.findAll(pageRequest);
		PageWrapper<Product> page = new PageWrapper<Product>(curPage, "/product");
		model.addAttribute("page", page);
		
		if((product.getName()!=null)&&!(product.getName().equals(""))&&rch!=null){
			if(rch.equals("case2")){
				System.out.println(product.getName());
				
				String searchTerm=product.getName();
				model.addAttribute("products", productService.find(searchTerm));
				model.addAttribute("cats", categoryRepository.findAll());
		
				return "/product/listproduct";

			}else{
				if(rch.equals("case3")){

				String searchTerm=product.getName();
				model.addAttribute("products", productService.findByCat(searchTerm));
				model.addAttribute("cats", categoryRepository.findAll());
				return "/product/listproduct";
			}
				else{
					if(rch.equals("case4")){
						String searchTerm=product.getName();
						int Mini, Maxi;
					    try {
							Mini=Integer.parseInt(Min); 
							Maxi=Integer.parseInt(Max); 
					    } catch (NumberFormatException e) {
					    	Mini=0; 
							Maxi=1; 
					    }
			
						model.addAttribute("products", productService.findByPrix(searchTerm,Mini,Maxi));
						model.addAttribute("cats", categoryRepository.findAll());
						return "/product/listproduct";
					}
				}
			}
		}
		else{
			if(rch!=null){
				if(rch.equals("case3")){

					String searchTerm=product.getName();
					model.addAttribute("products", productService.findByCat(searchTerm));
					model.addAttribute("cats", categoryRepository.findAll());
					return "/product/listproduct";
				}
					else{
						if(rch.equals("case4")){
							String searchTerm=product.getName();
							int Mini, Maxi;
						    try {
								Mini=Integer.parseInt(Min); 
								Maxi=Integer.parseInt(Max); 
						    } catch (NumberFormatException e) {
						    	Mini=0; 
								Maxi=1; 
						    }
						    model.addAttribute("products", productService.findByPrix(searchTerm,Mini,Maxi));
							model.addAttribute("cats", categoryRepository.findAll());
							return "/product/listproduct";
	
			}
		}
			}
		}
		model.addAttribute("products", productService.findAll());
		model.addAttribute("cats", categoryRepository.findAll());
		return "/product/listproduct";
	
		
	}
	

	// Submit create / edit product form - Ajax 
	@RequestMapping(value = "/product", method = RequestMethod.POST, produces={"application/json"})
	@ResponseBody
	public JsonResponse ajaxSubmitCustomerForm(Model model, @ModelAttribute(value = "product") @Valid Product product, BindingResult result) {
		JsonResponse res = new JsonResponse();
		if (!result.hasErrors()) {
		

			if(!(productService.findOne(product.name).isEmpty()))
			productService.save(product);
			res.setStatus("SUCCESS");

			//if(productService.findOne(product.name)==null)
			try {
				productService.save(product);
				res.setStatus("SUCCESS");
			}
			catch(Exception e) {
				res.setStatus("FAIL");
			}
		}
		else {
			res.setStatus("FAIL");
			List<FieldError> allErrors = result.getFieldErrors();
			List<ErrorMessage> errorMesages = new ArrayList<ErrorMessage>();
			for (FieldError objectError : allErrors) {
				errorMesages.add(new ErrorMessage(objectError.getField(), objectError.getDefaultMessage()));
			}
			res.setErrorMessageList(errorMesages);
		}
		
		return res;
	}
	
	
	@RequestMapping(value = "/product/edit", method = RequestMethod.GET, produces={"application/json"})
	public @ResponseBody Product ajaxEditCustomerForm(@RequestParam("id") Long id) {
		return productService.findOne(id);
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String deleteProduct(@RequestParam("id") Long id, Model model) {
		productService.delete(id);
		return "redirect:/product";
	}
	
	@RequestMapping(value = "/caisse", method = RequestMethod.GET)
	public String listProducts(Model model, HttpSession session,  Pageable pageable,@RequestParam(value="filtreCat",required=false) String cat) {
		categorie = cat;
		
		if(categorie == null) {
			categorie = temp;
		}
		temp = categorie;
		
		session.setAttribute("panier", getPanier(session));
		
		if(cat != null) {
			model.addAttribute("products", productService.findByCat(cat));
			model.addAttribute("filtreCat", cat);
		}
		if(categorie != null) {
			model.addAttribute("products", productService.findByCat(categorie));
			model.addAttribute("filtreCat", categorie);
		}
		else {
			model.addAttribute("products", productService.findAll());
			model.addAttribute("filtreCat", "");
		}
		model.addAttribute("product", new Product());
		model.addAttribute("facture", new Facture());
		model.addAttribute("cust", new Customer());
		model.addAttribute("custs", customerService.findAll());
		model.addAttribute("cats",categoryRepository.findAll());
		return "/product/caisse";
	}
	
	
	public Panier getPanier(HttpSession session)
	{
		Panier panier = (Panier)session.getAttribute("panier");
		if(panier == null) {
			panier = new Panier();
		}
		
		return panier;
	}

	@RequestMapping(value = "/caissee", method = RequestMethod.GET)
	public String submitCaisse(@RequestParam("id") Long id, Model model, HttpSession session) {
		Product product;
		product = productService.findOne(id);
		
		Panier p = getPanier(session);
		p.addProduct(product);
		session.setAttribute("panier", p);

		return "redirect:/caisse";
	}
	
	@RequestMapping(value = "/deletePanier", method = RequestMethod.GET)
	public String deletePanier(@RequestParam("id") Long id, Model model, HttpSession session) {
		Panier p = getPanier(session);
		p.removeProduct(id);
		session.setAttribute("panier", p);
		
		return "redirect:/caisse";
	}

	
	@RequestMapping(value = "/custCaisse", method = RequestMethod.GET)
	public String custChoise(@RequestParam("id") Long id, Model model, HttpSession session) {
		Customer c = customerService.findOne(id);
		Panier p = getPanier(session);
		p.setClient(c);
		session.setAttribute("panier", p);
		
		return "redirect:/caisse";
	}

	@RequestMapping(value = "/cbCaisse", method = RequestMethod.GET)
	public String cbChoise(@RequestParam("id") String id, Model model, HttpSession session) {
		
		
		String choix = id;
		Panier p = getPanier(session);
		if(choix == "Espèces")
		{
			choix = id;
		}
		else
		if(choix == "Chèque")
		{
			choix = id;
		}
		else
		if(choix == "Carte-bancaire")
		{
			choix = id;
		}
		
		p.setMoyenPaiement(choix);		
		session.setAttribute("panier", p);
		
		
		return "redirect:/caisse";
		
	}
	
	
	
}

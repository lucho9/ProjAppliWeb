package m2.project.controller;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import m2.project.model.Category;
import m2.project.model.Customer;
import m2.project.model.Product;
import m2.project.repository.CategoryRepository;
import m2.project.repository.CustomerRepository;
import m2.project.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;




@Controller
public class ProductController {

	@Autowired
	private ProductRepository productRepository;

	
	@Autowired
	private CategoryRepository categoryRepository;
	
	
	
	@RequestMapping(value = "/product", method = RequestMethod.GET)
	public String productsList(Model model,@ModelAttribute Product product,@RequestParam(value="recherche",required=false)String rch) {
	
		if((product.getName()!=null)&&!(product.getName().equals(""))&&rch!=null){
			
				if(rch.equals("case2")){
					System.out.println(product.getName());
		model.addAttribute("products", productRepository.findByName(product.getName()));
			}
		}
		else{
			model.addAttribute("products", productRepository.findAll());
			
		}
	
		return "/product/listproduct";
	}
	
	@RequestMapping(value = "/product", method = RequestMethod.POST)
	public String productsListBis(Model model,@ModelAttribute Product product) {
		model.addAttribute("product", new Product());
		productRepository.save(product);
		
		return "redirect:/product";
	}
	
	@RequestMapping(value = "/product/create", method = RequestMethod.GET)
	public String createProductForm(Model model) {
		model.addAttribute("product", new Product());
		model.addAttribute("cats",categoryRepository.findAll());
		
		
		
		return "/product/create";
	}

	@RequestMapping(value = "/product/create", method = RequestMethod.POST)
	public String submitCreateProductForm(@Valid Product product,BindingResult bindingResult, Model model)  {
	
		 if (bindingResult.hasErrors()) {
	            return "/product/create";
	        }
	    
		productRepository.save(product);
		return "redirect:/product";
	}

	
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String editForm(@RequestParam("id") Long id, Model model) {
		
		model.addAttribute("product", productRepository.findOne(id));
		model.addAttribute("cats",categoryRepository.findAll());
	
		return "/product/create";
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String checkProductInfo(@Valid Product product,BindingResult bindingResult, Model model) {
		 if (bindingResult.hasErrors()) {
	            return "redirect:/product/create";
	        }
	        
		productRepository.save(product);
		return "redirect:/product";
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String deleteProduct(@RequestParam("id") Long id, Model model) {
		
		productRepository.delete(id);
		
		return "redirect:/product";
	}
	

	
	@RequestMapping(value = "/panier", method = RequestMethod.GET)
	public String Panier(Model model) {
		
		model.addAttribute("products",productRepository.findAll());
		return "/product/panier";
	}

	@RequestMapping(value = "/panier/add", method = RequestMethod.GET)
	public String addProduit(HttpSession session, @RequestParam("id") Long id) {
		
		List<Product> sessionPanier = (List<Product>)session.getAttribute("panier");
		if (sessionPanier == null) {
			sessionPanier = new ArrayList<Product>();
		}
			
		
		sessionPanier.add(productRepository.findOne(id));
		session.setAttribute("panier", sessionPanier);
		
		
		return "redirect:/panier";
	}
	
	
}
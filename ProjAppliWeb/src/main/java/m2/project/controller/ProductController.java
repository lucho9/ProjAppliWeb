package m2.project.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import m2.project.model.Customer;
import m2.project.model.Facture;
import m2.project.model.Product;
import m2.project.repository.CustomerRepository;

import m2.project.repository.ProductRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class ProductController {

	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private CustomerRepository customerRepository;
	double total = 0;
	
	@RequestMapping(value = "/product", method = RequestMethod.GET)
	public String productsList(Model model,@ModelAttribute Product product,@RequestParam(value="recherche",required=false)String rch) {
		//System.out.println(product.getName());
	//	System.out.println(rch);
		if((product.getName()!=null)&&!(product.getName().equals(""))&&rch!=null){
			//if(rch=="case1"){
		//model.addAttribute("products", productRepository.findById(product.getId()));
			//}else{
				if(rch.equals("case2")){
					System.out.println(product.getName());
		model.addAttribute("products", productRepository.findByName(product.getName()));
			}else{model.addAttribute("products", productRepository.findByCategory(product.getName()));
			System.out.println("categopry");
			}
				
		//	}
		}
		else{
			model.addAttribute("products", productRepository.findAll());
			System.out.println("else");
		}
		//if(product.getName()!=""){
	//	model.addAttribute("products", productRepository.findByName(product.getName()));
		//}
		//else
			//model.addAttribute("products", productRepository.findAll());
		return "/product/listproduct";
	}
	
	@RequestMapping(value = "/product", method = RequestMethod.POST)
	public String productsListBis(Model model,@ModelAttribute Product product) {
		model.addAttribute("product", new Product());
		productRepository.save(product);
		
		return "redirect:/product/listproduct";
	}
	
	/*@RequestMapping(value = "/product/create", method = RequestMethod.GET)
	public String createProductForm(Model model) {
		model.addAttribute("product", new Product());
		return "/product/create";
	}*/

	@RequestMapping(value = "/product/create", method = RequestMethod.POST)
	public String submitCreateProductForm(@ModelAttribute Product product) {
		productRepository.save(product);
		return "redirect:/product";
	}

	
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String editForm(@RequestParam("id") Long id, Model model) {
		
		model.addAttribute("product", productRepository.findOne(id));
		return "/product/create";
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String editPost(@ModelAttribute Product product, Model model) {
		productRepository.save(product);
		return "redirect:/product";
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String deleteProduct(@RequestParam("id") Long id, Model model) {
		
		productRepository.delete(id);
		
		return "redirect:/product";
	}
	
	@RequestMapping(value = "/caisse", method = RequestMethod.GET)
	public String listProducts(Model model, HttpSession session,  Pageable pageable) {
		
		model.addAttribute("products", productRepository.findAll());
		model.addAttribute("product", new Product());
		model.addAttribute("facture", new Facture());
		model.addAttribute("cust", new Customer());
		model.addAttribute("custs",customerRepository.findAll());
		
		return "/product/caisse";
	}
	
	
	public Map<Long, Product> getPanier(HttpSession session)
	{
		Map<Long, Product> panier = (Map<Long, Product>)session.getAttribute("panier");
		if(panier == null)
		{	panier = new HashMap<Long, Product>();
		if(total != 0)
		{
			total = 0;
		}
		}
		return panier;
	}
	public Map<Long, Integer> getQty(HttpSession session)
	{
		Map<Long, Integer> qty = (Map<Long, Integer>)session.getAttribute("qty");
		if(qty == null)
			qty = new HashMap<Long, Integer>();
		
		return qty;
	}
	
	@RequestMapping(value = "/caissee", method = RequestMethod.GET)
	public String editForm(@RequestParam("id") Long id, Model model, HttpSession session) {
		
		Product product;
		product = productRepository.findOne(id);
		//model.addAttribute("product", productRrepository.findOne(id));
		
		
		Map<Long, Integer> qty = getQty(session);
		Map<Long, Product> panier = getPanier(session);
		
		if (!qty.containsKey(product.getId())) {
			panier.put(product.getId(), product);
			qty.put(product.getId(), 1);
			total = total + product.getPrix();
			
		}
		else {
			qty.put(product.getId(), qty.get(product.getId()) + 1);
			total = total + product.getPrix();
		}
		
		session.setAttribute("panier", panier);
		session.setAttribute("qty", qty);
		
		
		session.setAttribute("prixTotal", total);
		return "redirect:/caisse";
	}
	
	@RequestMapping(value = "/deletePanier", method = RequestMethod.GET)
	public String deletePanier(@RequestParam("id") Long id, Model model, HttpSession session) {
		
		Map<Long, Integer> qty = getQty(session);
		Map<Long, Product> panier = getPanier(session);
		total = total - (qty.get(id) * panier.get(id).getPrix());
		if (qty.containsKey(id))
			qty.remove(id);
		
		if (panier.containsKey(id))
			panier.remove(id);
		
		session.setAttribute("panier", panier);
		session.setAttribute("qty", qty);
		session.setAttribute("prixTotal", total);
		//panier.remove();
		return "redirect:/caisse";
	}
	
	
	
}

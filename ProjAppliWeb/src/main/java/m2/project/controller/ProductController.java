package m2.project.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import m2.project.model.Category;
import m2.project.model.Customer;
import m2.project.model.Gamme;
import m2.project.model.Product;
import m2.project.repository.CategoryRepository;
import m2.project.repository.CustomerRepository;
import m2.project.repository.GammeRepository;
import m2.project.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
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
	private GammeRepository gammeRepository;
	
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
			//model.addAttribute("gammes", gammeRepository.findAll());
			//System.out.println("else");
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
		model.addAttribute("gams",gammeRepository.findAll());
		
		
		return "/product/create";
	}

	@RequestMapping(value = "/product/create", method = RequestMethod.POST)
	public String submitCreateProductForm(@ModelAttribute Product product,@RequestParam(value="laGamme",required=false)String laGamme,@RequestParam(value="laCategory",required=false)String laCategory)  {
		
		//teste le cas ou le produit correspond à une nouvelle gamme, le cas échéant : nouveau stock
		//for(int i=0;i<=)	
		//if(product.getGamme()==laGamme)
		Category c= new Category(laCategory);
		product.setCategory(c);
		Gamme g= new Gamme(laGamme);
		product.setGamme(g);
		product.setStock(g.get);
		productRepository.save(product);
		return "redirect:/product";
	}

	
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String editForm(@RequestParam("id") Long id, Model model) {
		
		model.addAttribute("product", productRepository.findOne(id));
		model.addAttribute("cats",categoryRepository.findAll());
		model.addAttribute("gams",gammeRepository.findAll());
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
	
	
	
	
	
	
	
	
	@RequestMapping(value = "/panier", method = RequestMethod.GET)
	public String fruitForm(Model model) {
		model.addAttribute("fruit", new Product());
		return "/product/panier";
	}

	@RequestMapping(value = "/panier", method = RequestMethod.POST)
	public String fruitSubmit(@ModelAttribute Product fruit, HttpSession session) {
		
		List<Product> panier = (List<Product>)session.getAttribute("panier");
		if(panier == null)
			panier = new ArrayList<Product>();
		
		panier.add(fruit);
		session.setAttribute("panier", panier);
		
		return "redirect:/panier";
	}
	
	
}

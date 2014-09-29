package m2.project.controller;

import m2.project.model.Customer;
import m2.project.model.Product;
import m2.project.repository.CustomerRepository;
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
	
	
	@RequestMapping(value = "/product", method = RequestMethod.GET)
	public String productsList(Model model,@ModelAttribute Product product) {
		System.out.println(product.getName());
		//if(product.getName()!=""){
	//	model.addAttribute("products", productRepository.findByName(product.getName()));
		//}
		//else
			model.addAttribute("products", productRepository.findAll());
		return "/product/listproduct";
	}
	
	@RequestMapping(value = "/product", method = RequestMethod.POST)
	public String productsListBis(Model model,@ModelAttribute Product product) {
		//if(RequestMethod.POST.name()=="form2"){
		System.out.println(product.getName());
		if(product.getName()!=""){
		model.addAttribute("products", productRepository.findByName(product.getName()));
		}
		else{
			model.addAttribute("products", productRepository.findAll());
		}
		//}
		//else
		//{
			//model.addAttribute("product", new Product());
			productRepository.save(product);
		//}
		return "/product/listproduct";
	}
	
	@RequestMapping(value = "/product/create", method = RequestMethod.GET)
	public String createProductForm(Model model) {
		model.addAttribute("product", new Product());
		return "/product/create";
	}

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
	
	
}

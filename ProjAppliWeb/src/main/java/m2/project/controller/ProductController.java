package m2.project.controller;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import m2.project.model.Category;
import m2.project.model.Customer;
import m2.project.model.ErrorMessage;
import m2.project.model.JsonResponse;
import m2.project.model.Product;
import m2.project.repository.CategoryRepository;
import m2.project.repository.CustomerRepository;
//import m2.project.repository.ProductPredicates;
import m2.project.repository.ProductRepository;
import m2.project.service.CustomerGroupService;
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

import com.mysema.query.types.Predicate;




@Controller
public class ProductController {

	@Autowired
	private ProductRepository productRepository;

	//@Autowired
	//private ProductService productService;

	
	@Autowired
	private CategoryRepository categoryRepository;
	
	
	
	@RequestMapping(value = "/product", method = RequestMethod.GET)
	public String productsList(Model model,Pageable pageable,@ModelAttribute Product product,@RequestParam(value="recherche",required=false)String rch) {
		// for the customers list
		final PageRequest pageRequest = new PageRequest(pageable.getPageNumber(), 5, Direction.ASC, "name");
		
		//Page<Product> curPage = productService.findAll(pageRequest);
		Page<Product> curPage = productRepository.findAll(pageRequest);
		PageWrapper<Product> page = new PageWrapper<Product>(curPage, "/product");
		model.addAttribute("page", page);
		if((product.getName()!=null)&&!(product.getName().equals(""))&&rch!=null){
			
				if(rch.equals("case2")){
					System.out.println(product.getName());
					//Predicate predicate = ProductPredicates.nameIsLike(product.getName());
					//String predicateAsString = predicate.toString();
					String searchTerm=product.getName();
					model.addAttribute("products", productRepository.find(searchTerm));
					model.addAttribute("cats", categoryRepository.findAll());
					//model.addAttribute("products", productRepository.findAll());//findByName(product.getName()));
					return "/product/listproduct";
			}else{
				if(rch.equals("case3")){
				String searchTerm=product.getName();
				model.addAttribute("products", productRepository.findByCat(searchTerm));
				model.addAttribute("cats", categoryRepository.findAll());
				return "/product/listproduct";
			}
				
			}
		}
		else{
			model.addAttribute("products", productRepository.findAll());
			model.addAttribute("cats", categoryRepository.findAll());
			return "/product/listproduct";
		}
		model.addAttribute("products", productRepository.findAll());
		model.addAttribute("cats", categoryRepository.findAll());
		return "/product/listproduct";
	
		
	}
	
	/*@RequestMapping(value = "/product", method = RequestMethod.POST)
	public String productsListBis(Model model,@ModelAttribute Product product) {
		model.addAttribute("product", new Product());
		productRepository.save(product);
		
		return "redirect:/product";
	}*/
	
	// Submit create / edit product form - Ajax 
			@RequestMapping(value = "/product", method = RequestMethod.POST, produces={"application/json"})
			@ResponseBody
			public JsonResponse ajaxSubmitCustomerForm(Model model, @ModelAttribute(value = "product") @Valid Product product, BindingResult result) {
				JsonResponse res = new JsonResponse();
				if (!result.hasErrors()) {
					/*List<Product> temp= productRepository.findAll();
					int i=0;
					
					while(i<temp.size()){
					if ((product.name==temp.get(i).getName())){
						break;
					}
					i++;
					}
					if(i==temp.size())*/
					//if(productRepository.findOne(product.name)==null)
					try {
						productRepository.save(product);
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
	/*
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

	
	*/
	@RequestMapping(value = "/product/edit", method = RequestMethod.GET, produces={"application/json"})
	public @ResponseBody Product ajaxEditCustomerForm(@RequestParam("id") Long id) {
	
		
		//model.addAttribute("product", productRepository.findOne(id));
		//model.addAttribute("cats",categoryRepository.findAll());
		
		
	
		return productRepository.findOne(id);
	}
	/*
	// Submit create / edit product form - Ajax 
		@RequestMapping(value = "/edit", method = RequestMethod.POST, produces={"application/json"})
		@ResponseBody
		public JsonResponse ajaxSubmitCustomerForm(Model model, @ModelAttribute(value = "product") @Valid Product product, BindingResult result) {
			JsonResponse res = new JsonResponse();
			if (!result.hasErrors()) {
				productRepository.save(product);
				res.setStatus("SUCCESS");
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
		}*/
	
/*	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String checkProductInfo(@Valid Product product,BindingResult bindingResult, Model model) {
		 if (bindingResult.hasErrors()) {
	            return "redirect:/product/create";
	        }
	        
		productRepository.save(product);
		return "redirect:/product";
	}
	*/
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
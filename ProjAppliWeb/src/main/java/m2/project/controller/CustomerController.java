package m2.project.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import m2.project.model.Customer;
import m2.project.model.ErrorMessage;
import m2.project.model.JsonResponse;
import m2.project.repository.CustomerGroupRepository;
import m2.project.service.CustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CustomerController {
	@Autowired
	private CustomerService customerService;
	@Autowired
	private CustomerGroupRepository customerGroupRepository;

	/*
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
       binder.registerCustomEditor(List.class, "customerGroups", new CustomCollectionEditor(List.class) {
    	   @Override
           protected Object convertElement(Object element) {
    		   Long id = null;

               if(element instanceof String && !((String)element).equals("")){
            	   // from the html, 'element' will be a String
            	   try {
            		   id = Long.parseLong((String) element);
                   }
                   catch (NumberFormatException e) {
                	   System.out.println("Element was " + ((String) element));
                	   e.printStackTrace();
                   }
               }
               else if(element instanceof Long) {
            	   // from the database, 'element' will be a Long
            	   id = (Long) element;
               }

               return id != null ? customerGroupRepository.findOne(id) : null;
    	   }
       });
	}*/
	
	@RequestMapping(value = "/customer", method = RequestMethod.GET)
	public String customersList(Model model) {
		model.addAttribute("customers", customerService.getAll());
		model.addAttribute("customer", new Customer());
		model.addAttribute("custGroups", customerGroupRepository.findAll());
		return "/customer/customerslist";
	}
	
	@RequestMapping(value = "/customer", method = RequestMethod.POST, produces={"application/json"})
	@ResponseBody
	public JsonResponse ajaxSubmitCustomerForm(Model model, @ModelAttribute(value = "customer") @Valid Customer customer, BindingResult result) {
		JsonResponse res = new JsonResponse();
		if (!result.hasErrors()) {
			customerService.save(customer);
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
	}

	/*
	 * Bootstrap modal data-toggle TEST
	@RequestMapping(value = "/customer/edit/{id}", method = RequestMethod.GET, produces={"application/json"})
	public String ajaxEditCustomer(Model model, @PathVariable("id") Long id) {
		model.addAttribute("customer", customerRepository.findOne(id));
		return "/customer/customerform";
	}
	*/
	
	@RequestMapping(value = "/customer/edit", method = RequestMethod.GET, produces={"application/json"})
	public @ResponseBody Customer ajaxEditCustomerForm(@RequestParam("id") Long id) {
		//return new ResponseEntity<Customer>(customer, HttpStatus.OK);
		
		// Can't return a ModelAndView with ajax
		//ModelAndView mav = new ModelAndView("/customer/customerform", "customer", customerRepository.findOne(id));
		
		
		// Can't get the view resolver
		//Map<String, Customer> m = new HashMap<String, Customer>();
		//m.put("customer", customerRepository.findOne(id));
		//try {
		//	InternalResourceViewResolver resolver = new InternalResourceViewResolver();
	    //    resolver.setPrefix("/templates/customer/");
	    //    resolver.setSuffix(".html");
		//	View resolvedView = resolver.resolveViewName("customerform", request.getLocale());
		//	MockHttpServletResponse mockResp = new MockHttpServletResponse();
		//	resolvedView.render(m, request, mockResp);
		//	System.out.println("rendered html : " + mockResp.getContentAsString());
			
		//    return mockResp.getContentAsString();
		//}
		//catch(Exception e) {
		//	System.out.println(e.getMessage());
		//}
				
		return customerService.get(id);
	}
	
	// No ajax method
	/*
	@RequestMapping(value = "/customer/create", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public String createCustomerForm(Model model) {
		model.addAttribute("customer", new Customer());
		return "/customer/create";
	}

	@RequestMapping(value = "/customer/create", method = RequestMethod.POST)
	public String submitCreateCustomerForm(@Valid @ModelAttribute Customer customer, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "/customer/create";
		}

		customerRepository.save(customer);
		return "redirect:/customer";
	}

	@RequestMapping(value = "/customer/edit/{id}", method = RequestMethod.GET)
	public String editCustomerForm(@PathVariable("id") Long id, Model model) {

		model.addAttribute("customer", customerRepository.findOne(id));
		return "/customer/create";
	}
	
	@RequestMapping(value = "/customer/edit/{id}", method = RequestMethod.POST)
	public String submitEditCustomerForm(@PathVariable("id") Long id, @Valid @ModelAttribute Customer customer, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("customer", customer);
			return "/customer/create";
		}

		customerRepository.save(customer);
		return "redirect:/customer";
	}
	 */

	@RequestMapping(value = "/customer/delete/{id}", method = RequestMethod.GET)
	public String deleteCustomer(@PathVariable("id") Long id) {
		customerService.delete(id);
		return "redirect:/customer";
	}
}

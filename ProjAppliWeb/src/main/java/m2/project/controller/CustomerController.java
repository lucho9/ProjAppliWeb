package m2.project.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import m2.project.model.Customer;
import m2.project.model.ErrorMessage;
import m2.project.model.JsonResponse;
import m2.project.service.CustomerGroupService;
import m2.project.service.CustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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
	private CustomerGroupService customerGroupService;

	// Customers list / Create customer form - not Ajax
	@RequestMapping(value = "/customer", method = RequestMethod.GET)
	public String customersList(Model model, Pageable pageable) {

		//Page<Customer> curPage = customerService.findAll(pageRequest);
		//PageWrapper<Customer> page = new PageWrapper<Customer>(curPage, "/customer");
		//model.addAttribute("page", page);
		model.addAttribute("customers", customerService.findAll());
		
		// for the customer form create
		model.addAttribute("customer", new Customer());
		model.addAttribute("custGroups", customerGroupService.findAll());
		
		return "/customer/customerslist";
	}
	
	// Edit customer form - Ajax
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
				
		return customerService.findOne(id);
	}
	
	// Submit create / edit customer form - Ajax 
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
	
	// Delete customer - not Ajax
	@RequestMapping(value = "/customer/delete/{id}", method = RequestMethod.GET)
	public String deleteCustomer(@PathVariable("id") Long id) {
		customerService.delete(id);
		return "redirect:/customer";
	}
}

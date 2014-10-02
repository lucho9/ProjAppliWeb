package m2.project.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import m2.project.model.Customer;
import m2.project.repository.CustomerRepository;
import m2.project.model.ErrorMessage;
import m2.project.model.ValidationResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


//https://github.com/michaelisvy/ajax-samples/tree/master/src/main


@Controller
public class CustomerAjaxController {
	@RequestMapping(value = "/userAjaxBootstrap.json", method = RequestMethod.POST)
	public @ResponseBody ValidationResponse processFormAjaxJson(Model model, @ModelAttribute(value = "customer") @Valid Customer customer, BindingResult result) {
		ValidationResponse res = new ValidationResponse();
		if (!result.hasErrors()) {
			res.setStatus("SUCCESS");
		} else {
			res.setStatus("FAIL");
			List<FieldError> allErrors = result.getFieldErrors();
			List<ErrorMessage> errorMesages = new ArrayList<ErrorMessage>();
			for (FieldError objectError : allErrors) {
				errorMesages.add(new ErrorMessage(objectError.getField(),
						objectError.getField() + " "
								+ objectError.getDefaultMessage()));
			}
			res.setErrorMessageList(errorMesages);
		}
		return res;
	}

	@RequestMapping(value = "/userAjaxBootstrap", method = RequestMethod.GET)
	public String showFormBootstrap(Model model) {
		model.addAttribute("customer", new Customer());
		return "/customer/formcustomer";
	}

	/*
	@RequestMapping(value = "/userAjaxBootstrap.htm", method = RequestMethod.POST)
	public String processFormBootstrap(
			@ModelAttribute(value = "user") @Valid User user,
			BindingResult result) {
		if (result.hasErrors()) {
			return "03-bootstrap/userForm";
		} else {
			return "success";
		}
	}

	@RequestMapping(value = "/userAjaxCustomTag", method = RequestMethod.GET)
	public String showFormCustomTag(Model model) {
		model.addAttribute("user", new User());
		return "04-custom-tag/userForm";
	}

	@RequestMapping(value = "/userAjaxCustomTag.htm", method = RequestMethod.POST)
	public String processFormAjax(
			@ModelAttribute(value = "user") @Valid User user,
			BindingResult result) {
		if (result.hasErrors()) {
			return "04-custom-tag/userForm";
		} else {
			return "success";
		}
	}
	*/
}

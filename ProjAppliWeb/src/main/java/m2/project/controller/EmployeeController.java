package m2.project.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import m2.project.model.Employee;
import m2.project.model.ErrorMessage;
import m2.project.model.JsonResponse;
import m2.project.service.EmployeeService;
import m2.project.service.RoleService;
import m2.project.utils.PageWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
//@RequestMapping("/employee")
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private RoleService roleService;
	
	// Employees list / Create employee form - not Ajax
	@RequestMapping(value = "/employee", method = RequestMethod.GET)
	public String employeesList(Model model, Pageable pageable) {
		
		Page<Employee> curPage = employeeService.findAll(pageable);
		PageWrapper<Employee> page = new PageWrapper<Employee>(curPage, "/employee");
		model.addAttribute("page", page);
		//model.addAttribute("employees", employeeService.findAll());
		
		// for the employee form create
		model.addAttribute("employee", new Employee());
		model.addAttribute("roles", roleService.findAll());
		
		return "/employee/employeeslist";
	}
	
	// Edit employee form - Ajax
	@RequestMapping(value = "/employee/edit", method = RequestMethod.GET, produces={"application/json"})
	public @ResponseBody Employee ajaxEditEmployeeForm(@RequestParam("id") Long id) {
		return employeeService.findOne(id);
	}
	
	// Submit create / edit employee form - Ajax 
	@RequestMapping(value = "/employee", method = RequestMethod.POST, produces={"application/json"})
	@ResponseBody
	public JsonResponse ajaxSubmitEmployeeForm(@ModelAttribute(value = "employee") @Valid Employee employee, BindingResult result) {
		JsonResponse res = new JsonResponse();
		if (!result.hasErrors()) {
			employee.hashPassword(employee.getPassword());
			employeeService.save(employee);
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

	// Delete employee - not Ajax
	@RequestMapping(value = "/employee/delete/{id}", method = RequestMethod.GET)
	public String deleteEmployee(@PathVariable("id") Long id) {
		employeeService.delete(id);
		return "redirect:/employee";
	}
}

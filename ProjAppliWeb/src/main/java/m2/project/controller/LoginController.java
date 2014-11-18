package m2.project.controller;

import java.util.ArrayList;
import java.util.List;

import m2.project.model.Employee;
import m2.project.model.ErrorMessage;
import m2.project.model.JsonResponse;
import m2.project.service.EmployeeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
//@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	EmployeeService employeeService;
	
    @RequestMapping(value={"/login"}, method=RequestMethod.GET)
    public ModelAndView showLoginPage() {
        return new ModelAndView("login");
    }
    
    @RequestMapping(value={"/callConfig"}, method=RequestMethod.POST, produces={"application/json"})
    public @ResponseBody JsonResponse callConfig(@RequestParam(value="lastName", required = false) String lastName, @RequestParam(value="firstName", required = false) String firstName) {
    	JsonResponse res = new JsonResponse();

    	Employee employee = employeeService.findByLastNameAndFirstNameAllIgnoreCase(lastName, firstName);
    	if (employee != null) {
			res.setStatus("SUCCESS");
			res.setObj(employee);
		}
		else {
			res.setStatus("FAIL");
			List<ErrorMessage> errorMesages = new ArrayList<ErrorMessage>();
			errorMesages.add(new ErrorMessage("lastName", "Non inconnu"));
			errorMesages.add(new ErrorMessage("firstName", "Prénom inconnu"));
			res.setErrorMessageList(errorMesages);
		}
		
		return res;
    }
    
    @RequestMapping(value={"/config"}, method=RequestMethod.POST, produces={"application/json"})
    public @ResponseBody JsonResponse submitConfig(
    		@RequestParam(value="id", required = true) int id,
    		@RequestParam(value="lastName", required = true) String lastName,
    		@RequestParam(value="firstName", required = true) String firstName,
    		@RequestParam(value="loginType", required = true) int loginType,
    		@RequestParam(value="login", required = true) String login,
    		@RequestParam(value="pwd0", required = false) String pwd0,
    		@RequestParam(value="pwd", required = false) String pwd,
    		@RequestParam(value="pwd2", required = false) String pwd2
    ) {
    	JsonResponse res = new JsonResponse();
    	
    	Employee employee = employeeService.findOne(id);
    	if (employee != null) {
    		List<ErrorMessage> errorMesages = new ArrayList<ErrorMessage>();
    		if (login == null || login.length() == 0) {
    			errorMesages.add(new ErrorMessage("login", "Login obligatoire"));
    		}
    		else if (loginType == 0) {
	    		if (employee.getPassword() != null && employee.getPassword().length() > 0) {
	    			if (pwd0 == null || pwd0.length() == 0) {
						errorMesages.add(new ErrorMessage("pwd0", "Ancien mot de obligatoire"));
					}
	    		}
	    		else if (pwd == null || pwd.length() == 0) {
					errorMesages.add(new ErrorMessage("pwd", "Mot de passe obligatoire"));
				}
	    		else if (pwd2 == null || pwd2.length() == 0) {
					errorMesages.add(new ErrorMessage("pwd2", "Vérification obligatoire"));
				}
	    		else if (!pwd.equals(pwd2)) {
	    			errorMesages.add(new ErrorMessage("pwd2", "Vérification ne correspond pas au mot de passe"));
	    		}
    		}
    		
    		if (errorMesages.size() > 0) {
    			res.setStatus("FAIL");
    			res.setErrorMessageList(errorMesages);
    			return res;
    		}
    		
    		employee.setLastName(lastName);
			employee.setFirstName(firstName);
			employee.setLoginType(loginType);
			employee.setLogin(login);
			employee.hashPassword(pwd);
			employeeService.save(employee);
			res.setStatus("SUCCESS");
		}
		else {
			res.setStatus("FAIL");
			List<ErrorMessage> errorMesages = new ArrayList<ErrorMessage>();
			errorMesages.add(new ErrorMessage("lastName", "Non inconnu"));
			errorMesages.add(new ErrorMessage("firstName", "Prénom inconnu"));
			res.setErrorMessageList(errorMesages);
		}
		
		return res;
    	
    	
    }
}
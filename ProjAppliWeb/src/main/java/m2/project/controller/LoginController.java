package m2.project.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import m2.project.model.Employee;
import m2.project.model.ErrorMessage;
import m2.project.model.JsonResponse;
import m2.project.service.EmployeeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
			errorMesages.add(new ErrorMessage("name", "Cet employé n'existe pas"));
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
						errorMesages.add(new ErrorMessage("pwd0", "Mot de passe obligatoire"));
					}
	    			else  {
	    				PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	    				if (!passwordEncoder.matches(pwd0, employee.getPassword())) {
	    					errorMesages.add(new ErrorMessage("pwd0", "Mot de passe ne correspond pas"));
	    				}
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
			if (loginType == 0 && pwd != null && !pwd.isEmpty())
				employee.hashPassword(pwd);
			else if (loginType == 1)
				employee.setPassword(null);
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
    
    // for 403 access denied page
 	@RequestMapping(value = "/errors/403", method = RequestMethod.GET)
 	public ModelAndView accesssDenied(HttpServletRequest req, Principal user) {
  
 		ModelAndView model = new ModelAndView();
  
 		if (user != null)
 			model.addObject("message", user.getName() + ", vous n'avez pas les droits");
 		else
 			model.addObject("message", "Vous n'avez pas les droits");
 		
 		model.addObject("retUrl", req.getHeader("referer"));
 		
 		model.setViewName("/errors/403");
 		return model;
 	}
}
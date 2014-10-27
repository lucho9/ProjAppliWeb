package m2.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
//@RequestMapping("/login")
public class LoginController {

    @RequestMapping(value={"/login"}, method=RequestMethod.GET)
    public ModelAndView showLoginPage() {
        return new ModelAndView("login");
    }

    /*
    @RequestMapping(value="/success-login", method=RequestMethod.GET)  
    public String successLogin() {  
        return "forward:/";
    }
    */

    /*
    @RequestMapping(value="/error-login", method=RequestMethod.GET)  
    public ModelAndView invalidLogin() {  
        ModelAndView modelAndView = new ModelAndView("login");  
        modelAndView.addObject("param.error", true);  
        return modelAndView;  
    }  
*/
    /*
    @RequestMapping(value="/logout")  
    public ModelAndView logout() { 
        return new ModelAndView("home");
    } 
     */
}
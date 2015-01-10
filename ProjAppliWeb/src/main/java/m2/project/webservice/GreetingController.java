package m2.project.webservice;

import java.util.concurrent.atomic.AtomicLong;

import m2.project.model.Customer;
import m2.project.service.CustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class GreetingController {

	@Autowired
    CustomerService customerService;
    
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    
    
    // WS Server
    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }
    
    @RequestMapping(value = "/cust/{id}", method=RequestMethod.GET)
    public Customer customer(@PathVariable(value="id") Long id) {
        return customerService.findOne(id);
    }
    
    // WS Client
    @RequestMapping("/greeting2")
    public Page greeting2() {
		RestTemplate restTemplate = new RestTemplate();
	    Page page = restTemplate.getForObject("http://graph.facebook.com/pivotalsoftware", Page.class);
	    return page;
    }
    //@RequestMapping("/cust")
    //public Customer cust() {
	//	RestTemplate restTemplate = new RestTemplate();
	//    Customer page = restTemplate.getForObject("http://localhost:8080/customer/edit/1", Customer.class);
	//    return page;
    //}
    

}
package m2.project.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {
		"m2.project.test.controller"
})
public class MvcConfig extends WebMvcConfigurerAdapter {


    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
    	registry.addViewController("/home").setViewName("index");
    	registry.addViewController("/help").setViewName("help");
    
    }


	/*
	 * @Override public void
	 * addArgumentResolvers(List<HandlerMethodArgumentResolver>
	 * argumentResolvers) { PageableHandlerMethodArgumentResolver resolver = new
	 * PageableHandlerMethodArgumentResolver(); resolver.setFallbackPageable(new
	 * PageRequest(0, 3)); argumentResolvers.add(resolver); }
	 */
}

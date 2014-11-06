package m2.project.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {


    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
    	registry.addViewController("/home").setViewName("index");
    
    }


	/*
	 * @Override public void
	 * addArgumentResolvers(List<HandlerMethodArgumentResolver>
	 * argumentResolvers) { PageableHandlerMethodArgumentResolver resolver = new
	 * PageableHandlerMethodArgumentResolver(); resolver.setFallbackPageable(new
	 * PageRequest(0, 3)); argumentResolvers.add(resolver); }
	 */
}

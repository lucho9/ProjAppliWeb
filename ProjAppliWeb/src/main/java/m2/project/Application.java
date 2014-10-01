package m2.project;

import m2.project.model.Product;
import m2.project.repository.ProductRepository;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@EnableAutoConfiguration
@Configuration
@ComponentScan
public class Application {

    public static void main(String[] args) throws Throwable {
        
        ConfigurableApplicationContext context = SpringApplication.run(Application.class);
ProductRepository repository = context.getBean(ProductRepository.class);

        
        // save a couple of customers
        repository.save(new Product("pomme",1, ""));
        repository.save(new Product("poire",1, "fruit"));
        repository.save(new Product("banane",1, "fruit"));
        repository.save(new Product("kiwi",1, "fruit"));
        repository.save(new Product("courgette",1, "legume"));
    }

}
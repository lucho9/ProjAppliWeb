package m2.project;

import java.util.HashSet;
import java.util.Set;

import m2.project.model.Category;
import m2.project.model.Gamme;
import m2.project.model.Product;
import m2.project.model.Stock;
import m2.project.repository.CategoryRepository;
import m2.project.repository.GammeRepository;
import m2.project.repository.ProductRepository;
import m2.project.repository.StockRepository;

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

       CategoryRepository repository2 = context.getBean(CategoryRepository.class);
       GammeRepository gammeRepository = context.getBean(GammeRepository.class);
       StockRepository repository3 = context.getBean(StockRepository.class);
   
        // save a couple of customers
       	
       
        //création des produits
        Product p1= new Product("pomme golden",1);
        Product p2= new Product("pomme golden",1);
        Product p3= new Product("pomme golden",1);
        Product p4= new Product("pomme golden",1);
        Product p5= new Product("pomme golden",1);
        Product p6= new Product("poire williams",2);
        Product p7= new Product("poire williams",2);
        Product p8= new Product("TV Samsung",250);
        Product p9= new Product("TV Samsung",250);
        Product p10= new Product("TV Sony",200);
        
        
        
        Category c1=new Category("fruit");
        Category c2=new Category("TV");
        
        Stock s1=new Stock(120);
        Stock s2=new Stock(30);
        Stock s3=new Stock(10);
        Stock s4=new Stock(14);
        
        
        Gamme g1=new Gamme("pomme");
        Gamme g2=new Gamme("poire");
        Gamme g3=new Gamme("Televiseur");
        
        
        //g.setId((long) 111);
        
        repository2.save(c1);
        repository2.save(c2);
        
        
        repository3.save(s1);
        repository3.save(s2);
        repository3.save(s3);
        repository3.save(s4);
        //g.setStock(s);
        
        
        g1.setStock(s1);
        g2.setStock(s1);
        g3.setStock(s1);
       
       
        
        
        gammeRepository.save(g1);
        gammeRepository.save(g2);
        gammeRepository.save(g3);
        
        //Set collec = new HashSet();
        
        p1.setCategory(c1);
        p2.setCategory(c1);
        p3.setCategory(c1);
        p4.setCategory(c1);
        p5.setCategory(c1);
        p6.setCategory(c1);
        p7.setCategory(c1);
        p8.setCategory(c2);
        p9.setCategory(c2);
        p10.setCategory(c2);
        
        
        
        p1.setGamme(g1);
        p2.setGamme(g1);
        p3.setGamme(g1);
        p4.setGamme(g1);
        p5.setGamme(g1);
        p6.setGamme(g2);
        p7.setGamme(g2);
        p8.setGamme(g3);
        p9.setGamme(g3);
        p10.setGamme(g3);
       
        /*
        
       p1.setStock(s1);
       p2.setStock(s1);
       p3.setStock(s1);
       p4.setStock(s1);
       p5.setStock(s1);
       p6.setStock(s2);
       p7.setStock(s2);
       p8.setStock(s3);
       p9.setStock(s3);
       p10.setStock(s4);
       
        */
       
        repository.save(p1);
        repository.save(p2);
        repository.save(p3);
        repository.save(p4);
        repository.save(p5);
        repository.save(p6);
        repository.save(p7);
        repository.save(p8);
        repository.save(p9);
        repository.save(p10);
        
    }

}
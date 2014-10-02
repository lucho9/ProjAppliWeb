package m2.project;

import m2.project.model.Customer;
import m2.project.model.CustomerGroup;
import m2.project.repository.CustomerRepository;
import m2.project.repository.CustomerGroupRepository;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@EnableAutoConfiguration
@Configuration
@ComponentScan
public class Application {

    public static void main(String[] args) throws Throwable {
		ConfigurableApplicationContext context = SpringApplication.run(Application.class);
		
		CustomerGroupRepository customerGroupRepository = context.getBean(CustomerGroupRepository.class);		
		CustomerRepository customerRepository = context.getBean(CustomerRepository.class);
		
		CustomerGroup g = new CustomerGroup("Big Company");
		customerGroupRepository.save(g);
		
		// save a couple of customers
		customerRepository.save(new Customer("Jack", "Bauer", g));
		customerRepository.save(new Customer("Chloe", "O'Brian", g));
		customerRepository.save(new Customer("Kim", "Bauer", g));
		customerRepository.save(new Customer("David", "Palmer", g));
		customerRepository.save(new Customer("Michelle", "Dessler", g));
		
		
		/*
		SessionFactory sessionFactory = new org.hibernate.cfg.Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();

		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Student student1 = new Student("Eswar", address);
			session.save(student1);
			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}*/
    }

}
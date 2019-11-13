package com.example.demo.bootstrap;

import com.example.demo.domain.Customer;
import com.example.demo.domain.Product;
import com.example.demo.services.CustomerService;
import com.example.demo.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class SpringJPABootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private ProductService productService;
    private CustomerService customerService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Autowired
    public void setCustomerService(CustomerService customerService) { this.customerService = customerService; }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        loadProducts();
        loadCustomers();
    }

    public void loadProducts() {
        Product product1 = new Product();
        product1.setDescription("Product 1");
        product1.setPrice(new BigDecimal("12.99"));
        product1.setImageUrl("http://exmple.com/product1");
        productService.saveOrUpdate(product1);

        Product product2 = new Product();
        product2.setDescription("Product 2");
        product2.setPrice(new BigDecimal("14.99"));
        product2.setImageUrl("http://exmple.com/product2");
        productService.saveOrUpdate(product2);

        Product product3 = new Product();
        product3.setDescription("Product 3");
        product3.setPrice(new BigDecimal("34.99"));
        product3.setImageUrl("http://exmple.com/product3");
        productService.saveOrUpdate(product3);

        Product product4 = new Product();
        product4.setDescription("Product 4");
        product4.setPrice(new BigDecimal("14.99"));
        product4.setImageUrl("http://exmple.com/product4");
        productService.saveOrUpdate(product4);

        Product product5 = new Product();
        product5.setDescription("Product 5");
        product5.setPrice(new BigDecimal("21.99"));
        product5.setImageUrl("http://exmple.com/product5");
        productService.saveOrUpdate(product5);
    }

    public void loadCustomers() {
        Customer customer1 = new Customer();
        customer1.setFirstName("Julian");
        customer1.setLastName("Murillo");
        customer1.setAddressLine1("123 Easy St");
        customer1.setAddressLine2("");
        customer1.setCity("Atlanta");
        customer1.setState("Georgia");
        customer1.setZipCode("30046");
        customer1.setEmail("julian.murillo@pyramidci.com");
        customer1.setPhoneNumber("555.555.5555");
        customerService.saveOrUpdate(customer1);

        Customer customer2 = new Customer();
        customer2.setFirstName("Andrew");
        customer2.setLastName("Patrick");
        customer2.setAddressLine1("456 Medium Ave");
        customer2.setAddressLine2("");
        customer2.setCity("Denver");
        customer2.setState("Colorado");
        customer2.setZipCode("54003");
        customer2.setEmail("andrew.patrick@pyramidci.com.com");
        customer2.setPhoneNumber("123.555.0001");
        customerService.saveOrUpdate(customer2);

        Customer customer3 = new Customer();
        customer3.setFirstName("Nikaylah");
        customer3.setLastName("Woody");
        customer3.setAddressLine1("789 Hard Blvd");
        customer3.setAddressLine2("");
        customer3.setCity("Miami");
        customer3.setState("Florida");
        customer3.setZipCode("33101");
        customer3.setEmail("nikaylah.woody@burnnotice.com");
        customer3.setPhoneNumber("305.323.0233");
        customerService.saveOrUpdate(customer3);

        Customer customer4 = new Customer();
        customer4.setFirstName("Mark");
        customer4.setLastName("Amechi");
        customer4.setAddressLine1("369 Insane Ln");
        customer4.setAddressLine2("");
        customer4.setCity("Columbus");
        customer4.setState("Ohio");
        customer4.setZipCode("80079");
        customer4.setEmail("mark.amechi@lafitness.com");
        customer4.setPhoneNumber("456.555.0002");
        customerService.saveOrUpdate(customer4);

        Customer customer5 = new Customer();
        customer5.setFirstName("Guram");
        customer5.setLastName("Razmadze");
        customer5.setAddressLine1("147 Casual Rd");
        customer5.setAddressLine2("");
        customer5.setCity("Duluth");
        customer5.setState("Georgia");
        customer5.setZipCode("30043");
        customer5.setEmail("guram@darkweb.com");
        customer5.setPhoneNumber("789.555.0003");
        customerService.saveOrUpdate(customer5);
    }
}

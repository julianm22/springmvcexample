package com.example.demo.bootstrap;

import com.example.demo.domain.*;
import com.example.demo.domain.security.Role;
import com.example.demo.enums.OrderStatus;
import com.example.demo.services.CustomerService;
import com.example.demo.services.ProductService;
import com.example.demo.services.RoleService;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class SpringJPABootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private ProductService productService;
    private UserService userService;
    private RoleService roleService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setRoleService(RoleService roleService) { this.roleService = roleService; }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        loadProducts();
        loadUsersAndCustomers();
        loadCarts();
        loadOrderHistory();
        loadRoles();
        assignUsersToDefaultRole();
        assignUsersToAdminRole();
    }

    private void assignUsersToDefaultRole() {
        List<Role> roles = (List<Role>) roleService.listAll();
        List<User> users = (List<User>) userService.listAll();

        roles.forEach(role ->{
            if(role.getRole().equalsIgnoreCase("CUSTOMER")){
                users.forEach(user -> {
                    user.addRole(role);
                    userService.saveOrUpdate(user);
                });
            }

//            if(role.getRole().equalsIgnoreCase("ADMIN")){
//                users.forEach(user -> {
//                    if(user.getUsername().equals("fglenanne")){
//                        user.addRole(role);
//                        userService.saveOrUpdate(user);
//                    }
//                });
//            }
        });
    }

    private void assignUsersToAdminRole() {
        List<Role> roles = (List<Role>) roleService.listAll();
        List<User> users = (List<User>) userService.listAll();

        roles.forEach(role -> {
            if (role.getRole().equalsIgnoreCase("ADMIN")) {
                users.forEach(user -> {
                    if (user.getUsername().equals("fglenanne")) {
                        user.addRole(role);
                        userService.saveOrUpdate(user);
                    }
                });
            }
        });
    }


    private void loadRoles() {
        Role role = new Role();
        role.setRole("CUSTOMER");
        roleService.saveOrUpdate(role);

        Role adminRole = new Role();
        adminRole.setRole("ADMIN");
        roleService.saveOrUpdate(adminRole);
    }

    private void loadOrderHistory() {
        List<User> users = (List<User>) userService.listAll();
        List<Product> products = (List<Product>) productService.listAll();

        users.forEach(user ->{
            Order order = new Order();
            order.setCustomer(user.getCustomer());
            order.setOrderStatus(OrderStatus.SHIPPED);

            products.forEach(product -> {
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setProduct(product);
                orderDetail.setQuantity(1);
                order.addToOrderDetails(orderDetail);
            });
        });
    }

    private void loadCarts() {
        List<User> users = (List<User>) userService.listAll();
        List<Product> products = (List<Product>) productService.listAll();

        users.forEach(user -> {
            user.setCart(new Cart());
            CartDetail cartDetail = new CartDetail();
            cartDetail.setProduct(products.get(0));
            cartDetail.setQuantity(2);
            user.getCart().addCartDetail(cartDetail);
            userService.saveOrUpdate(user);
        });
    }

    public void loadUsersAndCustomers() {
        User user1 = new User();
        user1.setUsername("jmurillo");
        user1.setPassword("password");

        Customer customer1 = new Customer();
        customer1.setFirstName("Julian");
        customer1.setLastName("Murillo");
        customer1.setBillingAddress(new Address());
        customer1.getBillingAddress().setAddressLine1("123 Easy St");
        customer1.getBillingAddress().setCity("Atlanta");
        customer1.getBillingAddress().setState("Georgia");
        customer1.getBillingAddress().setZipCode("30046");
        customer1.setEmail("julian.murillo@pyramidci.com");
        customer1.setPhoneNumber("555.555.5555");
        user1.setCustomer(customer1);
        userService.saveOrUpdate(user1);

        User user2 = new User();
        user2.setUsername("apatrick");
        user2.setPassword("password");

        Customer customer2 = new Customer();
        customer2.setFirstName("Andrew");
        customer2.setLastName("Patrick");
        customer2.setShippingAddress(new Address());
        customer2.getShippingAddress().setAddressLine1("456 Medium Ave");
        customer2.getShippingAddress().setCity("Denver");
        customer2.getShippingAddress().setState("Colorado");
        customer2.getShippingAddress().setZipCode("54003");
        customer2.setEmail("andrew.patrick@pyramidci.com.com");
        customer2.setPhoneNumber("123.555.0001");
        user2.setCustomer(customer2);
        userService.saveOrUpdate(user2);

        User user3 = new User();
        user3.setUsername("nwoody");
        user3.setPassword("password");

        Customer customer3 = new Customer();
        customer3.setFirstName("Nikaylah");
        customer3.setLastName("Woody");
        customer3.setShippingAddress(new Address());
        customer3.getShippingAddress().setAddressLine1("789 Hard Blvd");
        customer3.getShippingAddress().setCity("Miami");
        customer3.getShippingAddress().setState("Florida");
        customer3.getShippingAddress().setZipCode("33101");
        customer3.setEmail("nikaylah.woody@burnnotice.com");
        customer3.setPhoneNumber("305.323.0233");
        user3.setCustomer(customer3);
        userService.saveOrUpdate(user3);
    }

    public void loadProducts(){

        Product product1 = new Product();
        product1.setDescription("Product 1");
        product1.setPrice(new BigDecimal("12.99"));
        product1.setImageUrl("http://example.com/product1");
        productService.saveOrUpdate(product1);

        Product product2 = new Product();
        product2.setDescription("Product 2");
        product2.setPrice(new BigDecimal("14.99"));
        product2.setImageUrl("http://example.com/product2");
        productService.saveOrUpdate(product2);

        Product product3 = new Product();
        product3.setDescription("Product 3");
        product3.setPrice(new BigDecimal("34.99"));
        product3.setImageUrl("http://example.com/product3");
        productService.saveOrUpdate(product3);

        Product product4 = new Product();
        product4.setDescription("Product 4");
        product4.setPrice(new BigDecimal("44.99"));
        product4.setImageUrl("http://example.com/product4");
        productService.saveOrUpdate(product4);

        Product product5 = new Product();
        product5.setDescription("Product 5");
        product5.setPrice(new BigDecimal("25.99"));
        product5.setImageUrl("http://example.com/product5");
        productService.saveOrUpdate(product5);

    }
}

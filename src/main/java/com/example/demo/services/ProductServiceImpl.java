package com.example.demo.services;

import com.example.demo.domain.DomainObject;
import com.example.demo.domain.Product;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class ProductServiceImpl extends AbstractMapService implements ProductService {

    @Override
    public List<DomainObject> listAll() { return super.listAll(); }

    @Override
    public Product getById(Integer id) { return (Product) super.getById(id); }

    @Override
    public Product saveOrUpdate(Product domainObject) { return (Product) super.saveOrUpdate(domainObject); }

    @Override
    public void delete(Integer id) { super.delete(id); }

    @Override
    protected void loadDomainObjects() {
        domainMap = new HashMap<>();

        Product product1 = new Product();
        product1.setId(1);
        product1.setDescription("Product 1");
        product1.setPrice(new BigDecimal("12.99"));
        product1.setImageUrl("http://exmple.com/product1");

        domainMap.put(1, product1);

        Product product2 = new Product();
        product2.setId(2);
        product2.setDescription("Product 2");
        product2.setPrice(new BigDecimal("14.99"));
        product2.setImageUrl("http://exmple.com/product2");

        domainMap.put(2, product2);

        Product product3 = new Product();
        product3.setId(3);
        product3.setDescription("Product 3");
        product3.setPrice(new BigDecimal("34.99"));
        product3.setImageUrl("http://exmple.com/product3");

        domainMap.put(3, product3);
    }
}

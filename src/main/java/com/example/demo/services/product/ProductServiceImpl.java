package com.example.demo.services.product;

import com.example.demo.domain.DomainObject;
import com.example.demo.domain.Product;
import com.example.demo.services.AbstractMapService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
@Profile("map")
public class ProductServiceImpl extends AbstractMapService implements ProductService {

    @Override
    public List<DomainObject> listAll() { return super.listAll(); }

    @Override
    public Product getById(Integer id) { return (Product) super.getById(id); }

    @Override
    public Product saveOrUpdate(Product domainObject) { return (Product) super.saveOrUpdate(domainObject); }

    @Override
    public void delete(Integer id) { super.delete(id); }

}

package com.angstore.models;

import com.angstore.models.Category;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-09-07T19:59:06")
@StaticMetamodel(Product.class)
public class Product_ { 

    public static volatile CollectionAttribute<Product, Category> categorys;
    public static volatile SingularAttribute<Product, Long> quantity;
    public static volatile SingularAttribute<Product, Double> price;
    public static volatile SingularAttribute<Product, String> name;
    public static volatile SingularAttribute<Product, String> description;
    public static volatile SingularAttribute<Product, Long> id;
    public static volatile SingularAttribute<Product, String> sku;

}
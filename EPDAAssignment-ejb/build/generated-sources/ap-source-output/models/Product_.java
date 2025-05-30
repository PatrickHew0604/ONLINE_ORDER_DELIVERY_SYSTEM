package models;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2024-11-22T01:05:16")
@StaticMetamodel(Product.class)
public class Product_ { 

    public static volatile SingularAttribute<Product, Integer> quantity;
    public static volatile SingularAttribute<Product, String> productID;
    public static volatile SingularAttribute<Product, Double> price;
    public static volatile SingularAttribute<Product, Boolean> isForSold;
    public static volatile SingularAttribute<Product, String> productName;

}
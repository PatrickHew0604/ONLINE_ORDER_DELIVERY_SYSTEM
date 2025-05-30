package models;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import models.Product;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2024-11-22T01:05:16")
@StaticMetamodel(PurchaseProduct.class)
public class PurchaseProduct_ { 

    public static volatile SingularAttribute<PurchaseProduct, Integer> quantity;
    public static volatile SingularAttribute<PurchaseProduct, Product> matchProduct;
    public static volatile SingularAttribute<PurchaseProduct, String> purchaseProductID;

}
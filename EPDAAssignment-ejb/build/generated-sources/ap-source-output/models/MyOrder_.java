package models;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import models.MyUser;
import models.OrderStatus;
import models.PurchaseProduct;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2024-11-22T01:05:16")
@StaticMetamodel(MyOrder.class)
public class MyOrder_ { 

    public static volatile SingularAttribute<MyOrder, String> orderID;
    public static volatile SingularAttribute<MyOrder, OrderStatus> orderStatus;
    public static volatile SingularAttribute<MyOrder, Date> orderDate;
    public static volatile SingularAttribute<MyOrder, MyUser> customer;
    public static volatile ListAttribute<MyOrder, PurchaseProduct> products;

}
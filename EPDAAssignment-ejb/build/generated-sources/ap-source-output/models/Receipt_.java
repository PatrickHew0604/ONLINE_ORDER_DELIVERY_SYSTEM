package models;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import models.MyOrder;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2024-11-22T01:05:16")
@StaticMetamodel(Receipt.class)
public class Receipt_ { 

    public static volatile SingularAttribute<Receipt, Date> paymentDate;
    public static volatile SingularAttribute<Receipt, String> receiptID;
    public static volatile SingularAttribute<Receipt, Double> paymentAmount;
    public static volatile SingularAttribute<Receipt, MyOrder> order;

}
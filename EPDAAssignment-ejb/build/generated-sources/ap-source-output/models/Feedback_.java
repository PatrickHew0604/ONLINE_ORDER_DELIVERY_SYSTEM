package models;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import models.MyOrder;
import models.MyUser;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2024-11-22T01:05:16")
@StaticMetamodel(Feedback.class)
public class Feedback_ { 

    public static volatile SingularAttribute<Feedback, Integer> rating;
    public static volatile SingularAttribute<Feedback, Date> feedbackDate;
    public static volatile SingularAttribute<Feedback, String> feedbackID;
    public static volatile SingularAttribute<Feedback, String> comment;
    public static volatile SingularAttribute<Feedback, MyOrder> order;
    public static volatile SingularAttribute<Feedback, MyUser> customer;

}
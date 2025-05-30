package models;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import models.MyOrder;
import models.MyUser;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2024-11-22T01:05:16")
@StaticMetamodel(Assignment.class)
public class Assignment_ { 

    public static volatile SingularAttribute<Assignment, MyUser> deliveryStaff;
    public static volatile SingularAttribute<Assignment, Date> assignmentDate;
    public static volatile SingularAttribute<Assignment, String> assignmentID;
    public static volatile SingularAttribute<Assignment, MyOrder> order;

}
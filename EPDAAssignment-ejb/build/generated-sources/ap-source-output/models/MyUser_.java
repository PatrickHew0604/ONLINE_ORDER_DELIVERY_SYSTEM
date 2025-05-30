package models;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import models.Gender;
import models.Role;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2024-11-22T01:05:16")
@StaticMetamodel(MyUser.class)
public class MyUser_ { 

    public static volatile SingularAttribute<MyUser, Boolean> isAvailable;
    public static volatile SingularAttribute<MyUser, String> password;
    public static volatile SingularAttribute<MyUser, String> phoneNumber;
    public static volatile SingularAttribute<MyUser, String> address;
    public static volatile SingularAttribute<MyUser, Role> role;
    public static volatile SingularAttribute<MyUser, Gender> gender;
    public static volatile SingularAttribute<MyUser, String> identityCardNumber;
    public static volatile SingularAttribute<MyUser, String> name;
    public static volatile SingularAttribute<MyUser, String> email;
    public static volatile SingularAttribute<MyUser, String> username;

}
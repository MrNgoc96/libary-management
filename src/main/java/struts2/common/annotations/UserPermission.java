package struts2.common.annotations;

import struts2.common.users.UserRole;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface UserPermission {

    UserRole[] value() default UserRole.UNKNOWN;

}

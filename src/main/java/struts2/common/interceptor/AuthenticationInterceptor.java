package struts2.common.interceptor;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.opensymphony.xwork2.util.ValueStack;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import struts2.common.annotations.UserPermission;
import struts2.common.entity.User;
import struts2.common.users.UserRole;

import java.lang.reflect.Method;
import java.util.Map;

public class AuthenticationInterceptor implements Interceptor {

    Logger logger = LogManager.getLogger(AuthenticationInterceptor.class);
    private final String ACCESS_DENNIED = "access-denny";

    @Override
    public void destroy() {

    }

    @Override
    public void init() {

    }

    @Override
    public String intercept(ActionInvocation actionInvocation) throws Exception {
        logger.info("========= Begin Authentication Interceptor=======");

        Map<String, Object> sessionAttributes = actionInvocation.getInvocationContext().getSession();
        User user = (User) sessionAttributes.get("currentUser");
        UserRole userRole;

        if (user == null) {
            userRole = UserRole.UNKNOWN;
        } else {
            userRole = user.getRole();
        }

        Action action = (Action) actionInvocation.getAction();

        String methodName = actionInvocation.getProxy().getMethod();
        Method method = action.getClass().getMethod(methodName);
        logger.info("======== Current User is " + userRole.getValue());

        UserPermission userPermission = method.getAnnotation(UserPermission.class);
        if (userPermission == null) return ACCESS_DENNIED;
        for (UserRole typeUser : userPermission.value()) {
            if (userRole == typeUser) {
                logger.info("======= End Authentication Interceptor=======");
                return actionInvocation.invoke();
            }
        }
        logger.info("======= Permission Deny =======");
        return ACCESS_DENNIED;

    }
}

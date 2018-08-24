package struts2.common.basic;


import com.opensymphony.xwork2.ActionSupport;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Map;



@InterceptorRef(value = "authStack")
@ParentPackage(value = "default")
public abstract class BaseAction extends ActionSupport implements SessionAware, RequestAware {

    protected static final long serialVersionUID = 1L;
    protected Logger logger = LogManager.getLogger(this.getClass());
    protected Map<String, Object> request;
    protected Map<String, Object> session;
    protected HttpServletRequest servletRequest;
    protected final File IMAGE_FORDER = new File("./resources/images");

    @Override
    public abstract String execute() throws Exception;

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    @Override
    public void setRequest(Map<String, Object> request) {
        this.request = request;
    }

    public HttpServletRequest getServletRequest() {
        return servletRequest;
    }

}

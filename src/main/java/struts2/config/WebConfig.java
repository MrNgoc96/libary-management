package struts2.config;

import org.apache.struts2.dispatcher.filter.StrutsPrepareAndExecuteFilter;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

public class WebConfig implements WebApplicationInitializer {


    @Override
    public void onStartup(ServletContext container) throws ServletException {

        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.setServletContext(container);
        FilterRegistration.Dynamic filter = container.addFilter("struts2",new StrutsPrepareAndExecuteFilter());
        filter.setInitParameter("actionPackages","struts2.book.action,struts2.reader.action,struts2.staff.action");
        filter.addMappingForUrlPatterns(null,false,"/*");
        container.setInitParameter("contextConfigLocation","struts2.config.AppConfig");
        container.setInitParameter("contextClass","org.springframework.web.context.support.AnnotationConfigWebApplicationContext");
        container.addListener(new ContextLoaderListener());

    }
}

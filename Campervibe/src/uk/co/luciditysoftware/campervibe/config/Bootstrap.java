package uk.co.luciditysoftware.campervibe.config;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;

import uk.co.luciditysoftware.campervibe.site.filters.SessionPerRequestFilter;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

//@SuppressWarnings("unused")
public class Bootstrap implements WebApplicationInitializer
{
	//public static SessionFactory sessionFactory;
	
    @Override
    public void onStartup(ServletContext container) throws ServletException
    {
    	/*sessionFactory = new Configuration()
				.configure("/uk/co/luciditysoftware/campervibe/config/hibernate.cfg.xml")
				.buildSessionFactory();*/
    	
        container.getServletRegistration("default").addMapping("/resource/*");

        AnnotationConfigWebApplicationContext rootContext =
                new AnnotationConfigWebApplicationContext();
        rootContext.register(RootContextConfiguration.class);
        container.addListener(new ContextLoaderListener(rootContext));

        AnnotationConfigWebApplicationContext servletContext =
                new AnnotationConfigWebApplicationContext();
        servletContext.register(ServletContextConfiguration.class);
        ServletRegistration.Dynamic dispatcher = container.addServlet(
                "springDispatcher", new DispatcherServlet(servletContext)
        );
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");
       
        //container.addFilter("sessionPerRequestFilter", arg1)
        FilterRegistration.Dynamic filter = container.addFilter("sessionPerRequestFilter", new DelegatingFilterProxy());
        filter.addMappingForUrlPatterns(null, true, "/*");
    }
}
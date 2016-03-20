package uk.co.luciditysoftware.campervibe.site.filters;

import java.io.IOException;
import java.util.UUID;

import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.hibernate.Session;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;

import uk.co.luciditysoftware.campervibe.config.Bootstrap;

@Component("sessionPerRequestFilter")
//@WebFilter("/booking/index")
public class SessionPerRequestFilter implements Filter {

	private UUID id;

    public SessionPerRequestFilter() {
    		    
        // TODO Auto-generated constructor stub
		this.id = UUID.randomUUID();
    }

	public void destroy() {
		// TODO Auto-generated method stub
	}

	//Possible solutions:
	//http://stackoverflow.com/questions/32367595/how-to-maintain-hibernate-session-opened-per-request
		
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		try {
			//Bootstrap.sessionFactory.openSession();
			chain.doFilter(request, response);
		} finally {

			//Session session = Bootstrap.sessionFactory.getCurrentSession();
			//session.close();
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}
}
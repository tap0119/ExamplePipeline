package web;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class FilterUser implements Filter {
	private ServletContext context;

	public void init(FilterConfig fConfig) throws ServletException {
		this.context = fConfig.getServletContext();
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
	    HttpServletResponse res = (HttpServletResponse) response;
	        
		HttpSession session = req.getSession();
	
		Integer i = 0;
		
		try {
			 i = (Integer) session.getAttribute("role");
	    	if (i.equals(1)) {
	    		chain.doFilter(request, response);
	    	
	        }else {
	        
	        	res.sendRedirect("http://localhost:8080/Project1/");
	        }
		}catch(NullPointerException e) {
        	res.sendRedirect("http://localhost:8080/Project1/");
		}
		
		

	}

	@Override
	public void destroy() {	
	}

}

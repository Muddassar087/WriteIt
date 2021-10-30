package Net.writeit.main.POJO;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Filters implements Filter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        String url = request.getRequestURI();

        response.setHeader("pragma", "no-cache");              
        response.setHeader("Cache-control", "no-cache, no-store, must-revalidate");             
        response.setHeader("Expires", "0"); 
        HttpSession session = request.getSession(false);
		
        if(session==null && !url.contains("login")) {
            response.sendRedirect(request.getContextPath() + "/login"); // here goto http://yourdoamin/login.html
            response.setHeader("message", "Session Timeout."); // you can set your preffered message.
            return; //break filter chain, requested JSP/servlet will not be executed
        }

        chain.doFilter(req, res);
		
	}

}

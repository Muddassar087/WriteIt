package Net.writeit.main.controller;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import Net.writeit.main.POJO.Filters;

@Controller
public class loginController {
	
	
	@GetMapping("/login")
	public String login(@CurrentSecurityContext(expression = "authentication.name") String username) throws IOException {

		if(username == null || username == "" || username == "anonymousUser") {
			return "login";
		}
		
		return "redirect:/User/profile";
	}
	@RequestMapping("/logout")
	String logout(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		Filters fi = new Filters();
		fi.doFilter(req, res, chain);
		return "/login";
		
	}
}

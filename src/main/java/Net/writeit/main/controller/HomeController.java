package Net.writeit.main.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import Net.writeit.main.model.Article;
import Net.writeit.main.service.ArticleService;
import Net.writeit.main.service.HomePageService;

@Controller
public class HomeController {
	@Autowired
	HomePageService homeService;
	@Autowired
	ArticleService as;
	
	@RequestMapping("/")
	public String homeMapping(Model model) {
		ArrayList<Article> article = homeService.getPublishedArticle();
		model.addAttribute("articleList", article);
		model.addAttribute("topUsers", homeService.getTop());
		model.addAttribute("recent", as.getRecentArticles());
		return "home"; 
	}
	
	@RequestMapping("/User/profile/home")
	public String UserHome(Model model, @CurrentSecurityContext(expression = "authentication.name") String username) {
		
		if(username.equals("anonymousUser") || username == null) {
			return "redirect:/logout";
		}
		
		ArrayList<Article> article = homeService.getPublishedArticle();
		model.addAttribute("articleList", article);
		model.addAttribute("topUsers", homeService.getTop());
		model.addAttribute("recent", as.getRecentArticles());
		
		return "user/home";
	}
	
	@RequestMapping("/home/All-Articles")
	public String allArticles(Model model) {
		ArrayList<Article> article = homeService.getAllPublishedArticle();
		model.addAttribute("articleList", article);
		return "allArticles";
	}
	
	@RequestMapping("/User/home/All-Articles")
	public String getallArticles(Model model) {
		ArrayList<Article> article = homeService.getAllPublishedArticle();
		model.addAttribute("articleList", article);
		return "user/allArticles";
	}
}

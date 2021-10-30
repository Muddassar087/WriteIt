package Net.writeit.main.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import Net.writeit.main.model.Article;
import Net.writeit.main.model.ArticleComment;
import Net.writeit.main.model.Profile;
import Net.writeit.main.service.ArticleService;
import Net.writeit.main.service.UserProfileService;

@Controller
public class ArticleController {
	@Autowired
	ArticleService as;
	@Autowired
	UserProfileService ups;
	
	@RequestMapping("/home/{title}")
	public String view_Article(Model model, @PathVariable("title") String title) throws IOException {
		Article article = as.getData(title);
		
		Profile user = ups.findByEmail(article.getEmail());
		article.setContent(article.getContent().replace(",", ""));
		
		model.addAttribute("user", user.getName());
		model.addAttribute("userpic", as.getBase4StringOfImage(user.getPic()));
		model.addAttribute("title", title);
		model.addAttribute("article", article);
		model.addAttribute("articleImage", as.getBase4StringOfImage(article.getTitleImage()));
		model.addAttribute("images", as.getListOfBase64Images(title));
		model.addAttribute("comments", as.getAllCommentsByTitle(title));
		model.addAttribute("recent", as.getRecentArticles());
		
		return "article";
	}
	
	@RequestMapping("user/home/{title}")
	public String viewArticle(Model model, @PathVariable("title") String title) throws IOException {
		
		Article article = as.getData(title);
		Profile user = ups.findByEmail(article.getEmail());
		article.setContent(article.getContent().replace(",", ""));
		
		model.addAttribute("user", user.getName());
		model.addAttribute("userpic", as.getBase4StringOfImage(user.getPic()));
		model.addAttribute("title", title);
		model.addAttribute("article", article);
		model.addAttribute("articleImage", as.getBase4StringOfImage(article.getTitleImage()));
		model.addAttribute("images", as.getListOfBase64Images(title));
		model.addAttribute("comments", as.getAllCommentsByTitle(title));
		model.addAttribute("recent", as.getRecentArticles());
		
		return "user/article";
	}
	
	@RequestMapping("/delete/{title}")
	public String deleteArticle(@PathVariable("title") String title) {
		as.deleteArticleByTitle(title);
		return "redirect:/User/profile/articles";
	}
	
	@RequestMapping(value = "/saveComment", method = RequestMethod.POST)
	@ResponseBody
	public String saveComent(@RequestBody ArticleComment articleComment) {
		as.saveComment(articleComment);
		return "saved";
	}
	
	@RequestMapping("/getTitleNames")
	@ResponseBody
	public List<String> getTitle(@RequestParam("term") String term) {
		return as.findMatchingArticle(term);
	}

	@GetMapping("/SearchReasult")
	public String getArticle(@RequestParam("articleToSearch") String title) {
		return "redirect:/home/"+title;
	}
	
	@GetMapping("/user/SearchResult")
	public String getAllArticle(@RequestParam("articleToSearch") String title) {
		return "redirect:/user/home/"+title;
	}
}

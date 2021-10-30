package Net.writeit.main.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import Net.writeit.main.model.AdminData;
import Net.writeit.main.model.Article;
import Net.writeit.main.repository.articleImageRepo;
import Net.writeit.main.service.AdminArticleService;
import Net.writeit.main.service.ArticleService;

@Controller
public class WriteArticleController {
	
	@Autowired
	ArticleService articleSevice;
	@Autowired
	AdminArticleService adminservice;
	@Autowired
	articleImageRepo repo;
	
	@RequestMapping("User/profile/write")
	String toWritepage() {
		return "user/writeArticle";
	}
	
	@PostMapping("/createArticle")
	String postArticle(Model m, @CurrentSecurityContext(expression="authentication.name")
    String username, @RequestParam("text") String[] arr, @RequestParam("image") MultipartFile otherImages[], @RequestParam("titleImage") MultipartFile tI, @RequestParam("title") String title,
			@RequestParam("description") String desc, @RequestParam("content") String Content, @RequestParam("lid") String id) {
		if(username.equals("anonymousUser") || username=="" || username == null) {
			return "redirect:/logout";
		}
		
		try {
			articleSevice.saveArticle(title, desc, tI, arr ,otherImages, username, Content, id);
		
			Article article = articleSevice.getData(title);
			article.setContent(article.getContent().replace(",",""));
			
			m.addAttribute("title", title);
			m.addAttribute("article", article);
			m.addAttribute("articleImage", articleSevice.getBase4StringOfImage(article.getTitleImage()));
			m.addAttribute("images", articleSevice.getListOfBase64Images(otherImages));
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "user/preview";
	}
	
	@PostMapping("/createArticle/update")
	String updateArticle(Model m, @CurrentSecurityContext(expression="authentication.name")
    String username, @RequestParam("text") String[] arr, @RequestParam("image") MultipartFile otherImages[], @RequestParam("titleImage") MultipartFile tI, @RequestParam("title") String title,
			@RequestParam("description") String desc, @RequestParam("content") String Content,@RequestParam("img") String[] imgs ,@RequestParam("pt") String pt, @RequestParam("lid") String lid) throws IOException {
		try {
			
			Article article = articleSevice.update(pt, title,desc, tI, arr ,otherImages, username, Content, imgs, lid);
			article.setContent(article.getContent().replace(",",""));
			m.addAttribute("title", title);
			m.addAttribute("article", article);
			m.addAttribute("articleImage", articleSevice.getBase4StringOfImage(article.getTitleImage()));
			m.addAttribute("images", articleSevice.getListOfBase64Images(title));
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "user/preview";
	}
	
	@RequestMapping("/publish/{title}")
	String publishArticle(@PathVariable("title") String title, @CurrentSecurityContext(expression = "authentication.name") String username) {
		adminservice.addArticletoPending(new AdminData(title, username, "published"));
		return "redirect:/User/profile/articles";
	}
	
	@RequestMapping("/edit/{title}")
	String EditArticle(Model model, @PathVariable("title") String title) throws IOException {
		Article a = articleSevice.getData(title);
		
		model.addAttribute("content", articleSevice.ArticleContentData(title));
		model.addAttribute("desc", a.getDescription());
		model.addAttribute("title", title);
		model.addAttribute("articleImage", articleSevice.getBase4StringOfImage(a.getTitleImage()));
		model.addAttribute("images", articleSevice.getListOfBase64Images(title));
		model.addAttribute("textData", articleSevice.getArticleTextDataToEdit(title));
		model.addAttribute("update", true);
		model.addAttribute("lid", a.getLid());
		
		
		return "user/writeArticle";
	}
}

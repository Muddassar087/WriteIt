package Net.writeit.main.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import Net.writeit.main.model.Article;
import Net.writeit.main.model.Profile;
import Net.writeit.main.service.ArticleService;
import Net.writeit.main.service.UserProfileService;

@Controller
public class UserProfileController {
	
	@Autowired
	UserProfileService ups;
	@Autowired
	ArticleService articleSevice;
	
	@RequestMapping("/profile/{user}")
	public String authurProfile(Model model, @PathVariable("user") String user) {
		String authur = null;
		authur = user;
		
		Profile p = ups.getByName(authur);
		int num = ups.findArticleByEmail(p.getEmail()).size();
		
		model.addAttribute("profile", p);
		model.addAttribute("totalArticles", num);
		model.addAttribute("source", articleSevice.getBase4StringOfImage(p.getPic()));
		model.addAttribute("articles", ups.findArticleByEmail(p.getEmail()));
		return "profile";
	}
	@RequestMapping("user/authurs/profile/{user}")
	public String authur_Profile(Model model, @PathVariable("user") String user) {
		String authur = null;
		authur = user;
		
		Profile p = ups.getByName(authur);
		int num = ups.findArticleByEmail(p.getEmail()).size();
		
		model.addAttribute("profile", p);
		model.addAttribute("totalArticles", num);
		model.addAttribute("source", articleSevice.getBase4StringOfImage(p.getPic()));
		model.addAttribute("articles", ups.findArticleByEmail(p.getEmail()));
		
		return "user/authurprofile";
	}

	@RequestMapping(path = "/User/profile")
	ModelAndView profile( @CurrentSecurityContext(expression="authentication.name")
    String username, HttpServletResponse res) throws IOException {
		
		if(ups.findByEmail(username) == null) {
			return new ModelAndView("user/edit")
						.addObject("newUser", true);
		}
		ArrayList<Article> a = ups.findArticleByEmail(username);
		Profile user = ups.findByEmail(username);
		return new ModelAndView("user/profile")
					.addObject("newUser", false)
					.addObject("profile", user)
					.addObject("totalArticles", a.size())
					.addObject("source", ups.getBase4StringOfImage(user.getPic()));	
	}
	
	@RequestMapping(path = "/User/profile/edit")
	ModelAndView editUser( @CurrentSecurityContext(expression="authentication.name")
    String username, HttpServletResponse res) throws IOException {
		Profile user = ups.findByEmail(username);
		
		if(user==null) return new ModelAndView("user/profile");
		
		return new ModelAndView("user/edit")
					.addObject("newUser", false)
					.addObject("profile", user)
					.addObject("source", ups.getBase4StringOfImage(user.getPic()));
	}
	
	@RequestMapping(path = "/User/profile/articles")
	ModelAndView viewArticles( @CurrentSecurityContext(expression="authentication.name")
    	String username,HttpServletResponse res) throws IOException {
		
		if(ups.findByEmail(username) == null) {
			return new ModelAndView("user/edit")
					.addObject("newUser", true);
		}
		
		ArrayList<Article> a = ups.findArticleByEmail(username);
		return new ModelAndView("user/UserPage")
				.addObject("ArticleList", a);
	}
	
	@PostMapping(path = "/updateProfile")
	void GetUpdate(@RequestParam("pic") MultipartFile multipart, @RequestParam("name") String name,
			@RequestParam("country") String country,@RequestParam("age") String age,
			 @RequestParam("description") String desc, @RequestParam("DateOfJoin") String date,@CurrentSecurityContext(expression="authentication.name")
    String username, HttpServletResponse res) throws MultipartException, IOException {
		
		Profile profile = new Profile();
		
		profile.setCountry(country);
		profile.setDateOfJoin(date);
		profile.setEmail(username);
		profile.setAge(age);
		profile.setName(name.replace(" ", "_"));
		profile.setDescription(desc);
		profile.setPic(multipart.getBytes());
		
		ups.saveUserProfile(profile);
		
		res.sendRedirect("/User/profile");
		
	}
	
	@RequestMapping("/User/preview/{title}")
	String previewArticle(Model m, @PathVariable("title") String title, @CurrentSecurityContext(expression = "authentication.name") String username) throws IOException {
		
		Article a = articleSevice.getData(title);
		a.setContent(a.getContent().replace(",", ""));
		m.addAttribute("title", title);
		m.addAttribute("article", a);
		m.addAttribute("articleImage", articleSevice.getBase4StringOfImage(a.getTitleImage()));
		m.addAttribute("images", articleSevice.getListOfBase64Images(title));
		return "user/preview";
	}
	
}

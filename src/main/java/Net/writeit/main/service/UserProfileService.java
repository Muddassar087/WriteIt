package Net.writeit.main.service;

import java.util.ArrayList;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.stereotype.Service;

import Net.writeit.main.model.Article;
import Net.writeit.main.model.Profile;
import Net.writeit.main.repository.UserProfileRepository;
import Net.writeit.main.repository.articleRepository;

@Service
public class UserProfileService {
	private String username = "";
	@Autowired
	private UserProfileRepository uRepo;
	@Autowired
	private ProfileUpadetService update;
	@Autowired
	private articleRepository art;
	
	
	public Profile saveUserProfile(Profile p) {
		if(this.findByEmail(p.getEmail()) != null)
			if(p.getEmail().equals(this.findByEmail(p.getEmail()).getEmail())) {
				if(p.getPic().length <= 0) {
					/**adding previous image bytes if there is not new image of type jpg*/
					p.setPic(this.findByEmail(p.getEmail()).getPic());
					
				}update.SaveUpdatedUser(p);
				return p;
			}
		return uRepo.save(p);
	}
	
	public ArrayList<Article> findArticleByEmail(String email){
		return art.findbyemail(email);
	}
	
	public String getBase4StringOfImage(byte[] arr) {
		return Base64.encodeBase64String(arr);
	}
	
	public Profile findByEmail(String email) {
		return uRepo.findByEmail(email);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(@CurrentSecurityContext(expression = "authentication.name") String username) {
		this.username = username;
	}
	
	public Profile getByName(String name) {
		return uRepo.findByName(name);
	}
}

package Net.writeit.main.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Net.writeit.main.model.AdminData;
import Net.writeit.main.repository.AdminArticleDataRepo;
import Net.writeit.main.repository.articleRepository;

@Service
public class AdminArticleService {
	@Autowired
	AdminArticleDataRepo adr;
	@Autowired
	articleRepository ar;
	
	public void addArticletoPending(AdminData adminData) {
		ar.updateArticle(adminData.getTitle());
		adr.save(adminData);
	}
	public ArrayList<Object[]> getTop(){
		return adr.getAuthurAndArticle();
	}
}

package Net.writeit.main.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Net.writeit.main.POJO.TopArticleData;
import Net.writeit.main.model.Article;
import Net.writeit.main.model.Profile;

@Service
public class HomePageService {
	@Autowired
	ArticleService articleService;
	@Autowired
	AdminArticleService ads;
	@Autowired
	UserProfileService ups;
	
	public ArrayList<Article> getPublishedArticle(){
		ArrayList<Article> articles = articleService.getAllArticleByStatus("published");
		for(Article article: articles)
		{
			if(article.getDescription().length() >= 200) {
				article.setDescription(article.getDescription().substring(0, 200));
			}
		}
		return articles;
	}
	
	public ArrayList<Article> getAllPublishedArticle(){
		ArrayList<Article> articles = articleService.getAllArticleByStatus();
		for(Article article: articles)
		{
			if(article.getDescription().length() >= 200) {
				article.setDescription(article.getDescription().substring(0, 200));
			}
		}
		return articles;
	}
	
	public ArrayList<String> getListOfImages(ArrayList<Article> article){
		ArrayList<String> list = new ArrayList<String>();
		for(Article a: article) {
			list.add(articleService.getBase4StringOfImage(a.getTitleImage()));
		}
		return list;
	}
	
	public ArrayList<TopArticleData> getTop(){
		ArrayList<TopArticleData> t = new ArrayList<>();
		for(Object[] obj: ads.getTop()) {
			Profile p=ups.findByEmail(obj[0].toString());
			t.add(new TopArticleData(p.getName(), articleService.getBase4StringOfImage(p.getPic())));
		}
		return t;
	}
}

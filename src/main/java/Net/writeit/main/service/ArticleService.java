package Net.writeit.main.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import Net.writeit.main.POJO.Recent;
import Net.writeit.main.model.AdminData;
import Net.writeit.main.model.Article;
import Net.writeit.main.model.ArticleComment;
import Net.writeit.main.model.ArticleData;
import Net.writeit.main.model.Article_image;
import Net.writeit.main.repository.AdminArticleDataRepo;
import Net.writeit.main.repository.ArticleDataRepo;
import Net.writeit.main.repository.articleImageRepo;
import Net.writeit.main.repository.articleRepository;
import Net.writeit.main.repository.commentRepo;

@Service
public class ArticleService {
	@Autowired
	articleRepository ar;
	@Autowired
	articleImageRepo air;
	@Autowired
	ArticleDataRepo adr;
	@Autowired
	commentRepo cr;
	@Autowired
	AdminArticleDataRepo admin;

	public void saveArticle(String title, String desc, MultipartFile tI, String[] arr, MultipartFile[] otherImages, String email, String content, String id) throws IOException {

		String articleContent = "";
		for(String con: arr) {
			articleContent += con+",";
		}
		articleContent = articleContent.substring(0, articleContent.length()-1);
		
		String out = articleContent.replace("\n","").replace("\r", "");
		
		adr.save(new ArticleData(content, title));

		ar.save(new Article(title, desc, email, tI.getBytes(), out, id));
	
		for(MultipartFile m: otherImages)
			air.save(new Article_image(title, m.getBytes()));
	}
	
	public void saveComment(ArticleComment ac) {
		cr.save(ac);
	}
	
	public Article getData(String title) {
		return ar.getBytitle(title);
	}
	
	public ArrayList<Article> getAllArticleByStatus(String status){
		ArrayList<Article> art = new ArrayList<Article>();
		int ind = 0;
		for(Article a: ar.findByStatus(status)) {
			if(ind==9) break;
			art.add(a);
			ind++;
		}
		return art;
	}
	
	public ArrayList<Article> getAllArticleByStatus(){
		return ar.findByStatus("published");
	}
	
	public ArrayList<String> getArticleTextDataToEdit(String title) {
		ArrayList<String> ss = new ArrayList<String>();
		Article a = ar.getBytitle(title);
		String ma = "<img class='imgA'></img>";
		for(String s: Arrays.asList(a.getContent().split(",")))
			if(!s.matches(ma)) ss.add(s);
		
		return ss;
	}
	
	public void updateSatus(String title) {
		ar.updateArticle(title);
	}
	
	public ArrayList<ArticleComment> getAllCommentsByTitle(String title){
		
		return cr.findAllByTitle(title);
	}
	
	public ArrayList<Recent> getRecentArticles(){
		ArrayList<Recent> art = new ArrayList<Recent>();
		for (Object[] a: ar.findRecentArticle()) {
			art.add(new Recent(a[0].toString(), getBase4StringOfImage((byte[]) a[1])));
		}
		return art;
	}
	
	public ArrayList<String> getListOfBase64Images(String title) throws IOException {
		ArrayList<String> list = new ArrayList<String>();
		
		ArrayList<Article_image> a = air.findByArticleName(title);
		for(Article_image aa: a) {
			list.add(getBase4StringOfImage(aa.getImage()));
		}
		
		return list;
	}
	
	public String ArticleContentData(String articleName) {
		return adr.findArticleDataByArticleName(articleName)
				.getArticleData();
	}
	
	public ArrayList<String> getListOfBase64Images(MultipartFile[] ml) throws IOException {
		ArrayList<String> list = new ArrayList<String>();
		for(MultipartFile m: ml) {
			list.add(getBase4StringOfImage(m.getBytes()));
		}
		return list;
	}
	
	public String getBase4StringOfImage(byte[] arr) {
		return Base64.encodeBase64String(arr);
	}

	public Article update(String pt, String title, String desc, MultipartFile tI, String[] arr, MultipartFile[] otherImages,
			String username, String content, String img[], String id) throws IOException {
		
		String ma = "<img class='imgA'></img>";
		String articleContent = "";
		for(String con: arr) {
			if(con.length() <= 0) {
				con = ma;
			}
			articleContent += con+",";
		}
		articleContent = articleContent.substring(0, articleContent.length()-1);
		
		String out = articleContent.replace("\n","").replace("\r", "");
		
		Article pA = ar.getBytitle(pt);
		
		Article article = null;
		ArticleData adata = null;

		if(tI.getBytes().length <= 0)
			article = new Article(title, desc, username, pA.getTitleImage(), out, id);
		else
			article = new Article(title, desc, username, tI.getBytes(), out, id);
		
		adata = new ArticleData(content, title);
				
		adr.updateArticleData(pt, adata.getArticleName(), adata.getArticleData());
		ar.updateArticleContent(article.getEmail(), pt, article.getDescription(), article.getTitleImage(), article.getContent(), title, article.getLid());

		ArrayList<Article_image> im = air.findByArticleName(pt);
		
		// deleting al the previous images
		for(Article_image imm: im) {
			air.deleteById(imm.getId());
		}
		
		int i = 0;
		for(MultipartFile m: otherImages) {
			if(m.getBytes().length <= 0) {
				air.save(new Article_image(title,im.get(i++).getImage()));
				continue;
			}air.save(new Article_image(title,m.getBytes()));
		}
		return article;
	}
	
	public List<String> findMatchingArticle(String term){

		return admin.findByMatingtitle(term);
	}
	
	public void deleteArticleByTitle(String title) {
		Article a = this.getData(title);
		AdminData Admin = admin.findByTitle(title);
		ArticleData ard = adr.findArticleDataByArticleName(title);
		ArrayList<Article_image> arimage = air.findByArticleName(title);
		ArrayList<ArticleComment> artc = cr.findAllByTitle(title);
		
		ar.deleteById(a.getId());
		admin.deleteById(Admin.getId());
		adr.delete(ard);
		
		for (ArticleComment articleComment : artc) {
			cr.delete(articleComment);
		}
		for(Article_image aim: arimage) {
			air.delete(aim);
		}
	
	}
}

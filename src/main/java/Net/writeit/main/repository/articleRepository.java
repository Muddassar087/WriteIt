package Net.writeit.main.repository;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import Net.writeit.main.model.Article;

public interface articleRepository extends JpaRepository<Article, Integer> {
	Article getBytitle(String title);
	
	@Query("select a from Article a where a.email=?1")
	ArrayList<Article> findbyemail(String email);
	
	@Query(value="select title, title_image from article_content order by id desc limit 4", nativeQuery = true)
	ArrayList<Object[]> findRecentArticle();
	
	ArrayList<Article> findByStatus(String status);
	
	@Transactional
	@Modifying
	@Query("update Article set status='published' where title=?1")
	void updateArticle(String title);
	

	@Transactional
	@Modifying
	@Query("update Article set email=?1, title=?6, description=?3, titleImage=?4, content=?5, lid=?7 where title=?2")
	void updateArticleContent(String email, String title, String description, byte[] titleImage, String content,String ut, String lid);
}

package Net.writeit.main.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import Net.writeit.main.model.Article_image;

public interface articleImageRepo extends JpaRepository<Article_image, Integer> {
	
	@Query("select a from Article_image a where a.articleName=?1")
	ArrayList<Article_image> findByArticleName(String title); 
}

package Net.writeit.main.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import Net.writeit.main.model.ArticleData;

public interface ArticleDataRepo extends JpaRepository<ArticleData, Integer> {
	ArticleData findArticleDataByArticleName(String articleName);

	@Transactional
	@Modifying
	@Query("Update ArticleData set articleName=?2, articleData=?3 where articleName=?1")
	void updateArticleData(String articleName, String upAn, String ad);
}

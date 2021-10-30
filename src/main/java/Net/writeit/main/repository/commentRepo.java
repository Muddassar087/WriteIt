package Net.writeit.main.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import Net.writeit.main.model.ArticleComment;

public interface commentRepo extends JpaRepositoryImplementation<ArticleComment, Integer> {
	ArrayList<ArticleComment> findAllByTitle(String title);
}

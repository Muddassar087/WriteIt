package Net.writeit.main.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "articleData")
public class ArticleData {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;
	@Column(nullable = false, columnDefinition = "text")
	String articleData;
	
	@Column(nullable = false)
	String articleName;
	
	public String getArticleName() {
		return articleName;
	}
	
	public ArticleData(String articleData, String articleName) {
		super();
		this.articleData = articleData;
		this.articleName = articleName;
	}

	public String getArticleData() {
		return articleData;
	}
	public void setArticleData(String articleData) {
		this.articleData = articleData;
	}
	public ArticleData(String articleData) {
		
		this.articleData = articleData;
	}
	public ArticleData() {
		
	}
	
}

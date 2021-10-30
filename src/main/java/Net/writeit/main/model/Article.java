package Net.writeit.main.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Table(name = "article_content")
@Entity
public class Article {
	/**********************************************************
	 * create the model class for article *
	 * the article contains title description title image content
	 * content includes 
	 *	- text
	 *		- heading
	 *		- paragraph
	 *		- break
	 *		- strong
	 *		- italics
	 *
	 *	- images
	 *	- code
	 *	- block quote 
	 **********************************************************/
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;
	@Column(nullable = false)
	String title;
	@Column(nullable = false)
	String description;
	@Column(nullable = false)
	String email; // authors email
	@Lob
	byte[] titleImage;
	@Column(nullable=false)
	String content;
	@Column(nullable=false)
	String status = "draft";
	@Column(nullable=false)
	String lid;

	public String getLid() {
		return lid;
	}

	public void setLid(String lid) {
		this.lid = lid;
	}

	public Article(String lid) {
		super();
		this.lid = lid;
	}

	public Article() {
	}
	
	public Article(String title, String description, String email, byte[] titleImage, String content,
			String lid) {
		super();
		this.title = title;
		this.description = description;
		this.email = email;
		this.titleImage = titleImage;
		this.content = content;
		this.lid = lid;
	}

	public Article(String title, String description, String email, byte[] titleImage, String content) {
		super();
		this.title = title;
		this.description = description;
		this.email = email;
		this.titleImage = titleImage;
		this.content = content;
	}
	

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public byte[] getTitleImage() {
		return titleImage;
	}
	public void setTitleImage(byte[] titleImage) {
		this.titleImage = titleImage;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}

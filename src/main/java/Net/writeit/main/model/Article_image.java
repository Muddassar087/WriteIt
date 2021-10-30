package Net.writeit.main.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "article_image")
public class Article_image {
	/****************************
	 * article images contains the images of particular article
	 * images contains
	 * 	- id
	 * 	- article_name/id
	 * 	- array of images bytes[]
	 *	As an article can have multiple images so we need to add each images in a separate table
	 *	an idea is to store them in increasing order and retrieve them in same order and post them in article 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;
	@Column(nullable = false)
	String articleName;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Lob
	byte[] image;
	public Article_image() {
	}
	public Article_image(String articleName, byte[] image) {
		super();
		this.articleName = articleName;
		this.image = image;
	}
	public String getArticleName() {
		return articleName;
	}
	public void setArticleName(String articleName) {
		this.articleName = articleName;
	}
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	
	
}

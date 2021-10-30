package Net.writeit.main.model;

import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Table(name = "admin_data")
public class AdminData {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;
	@Column(nullable = false)
	String title;
	@Column(nullable = false)
	String auther;
	@Column(nullable = false)
	String status;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public AdminData() {
		super();
	}
	public AdminData(String title, String auther, String status) {
		this.title = title;
		this.auther = auther;
		this.status = status;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuther() {
		return auther;
	}
	public void setAuther(String auther) {
		this.auther = auther;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}

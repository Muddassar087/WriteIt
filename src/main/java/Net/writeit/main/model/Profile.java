package Net.writeit.main.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "profile")
public class Profile {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(nullable = false)
	private String DateOfJoin;
	
	@Column(nullable = false)
	private String country;
	
	@Column(nullable = false)
	private String description;
	
	@Column(nullable = false)
	private String age;
	
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	@Column(nullable = false)
	private String email;
	
	@Column(nullable = false)
	private String name;
	@Lob
	@Column(nullable = false)
	private byte[] pic;

	public String getDateOfJoin() {
		return DateOfJoin;
	}
	public void setDateOfJoin(String dateOfJoin) {
		DateOfJoin = dateOfJoin;
	}
	public String getName() {
		return name.replace(' ', '_');
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCountry() {
		return country;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public byte[] getPic() {
		return pic;
	}
	public void setPic(byte[] pic) {
		this.pic = pic;
	}
	public int getId() {return this.id;}
}

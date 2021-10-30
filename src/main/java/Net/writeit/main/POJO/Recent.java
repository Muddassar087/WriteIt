package Net.writeit.main.POJO;

public class Recent {
	String name;
	String pic;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public Recent(String name, String pic) {
		super();
		this.name = name;
		this.pic = pic;
	}
	
}

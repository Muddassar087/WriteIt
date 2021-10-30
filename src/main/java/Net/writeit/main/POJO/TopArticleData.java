package Net.writeit.main.POJO;

public class TopArticleData {
	private String name;
	private String pic;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public TopArticleData(String name, String pic) {
		super();
		this.name = name;
		this.pic = pic;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
}

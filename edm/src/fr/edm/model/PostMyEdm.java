package fr.edm.model;

public class PostMyEdm {
	
	String post;
	String auteurPost, datePost, HeurePost;
	
	public PostMyEdm() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PostMyEdm(String post, String auteurPost, String datePost,
			String heurePost) {
		super();
		this.post = post;
		this.auteurPost = auteurPost;
		this.datePost = datePost;
		HeurePost = heurePost;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public String getAuteurPost() {
		return auteurPost;
	}

	public void setAuteurPost(String auteurPost) {
		this.auteurPost = auteurPost;
	}

	public String getDatePost() {
		return datePost;
	}

	public void setDatePost(String datePost) {
		this.datePost = datePost;
	}

	public String getHeurePost() {
		return HeurePost;
	}

	public void setHeurePost(String heurePost) {
		HeurePost = heurePost;
	}
	
	
	

}

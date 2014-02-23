package fr.edm.model;

public class PostEdm {
	String numEdm;
	String post;
	String auteurPost, datePost, HeurePost, nbLikesEdm;
	
	public PostEdm() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PostEdm(String numEdm,String post, String auteurPost, String datePost,
			String heurePost, String nbLikesEdm) {
		super();
		this.numEdm = numEdm;
		this.post = post;
		this.auteurPost = auteurPost;
		this.datePost = datePost;
		this.HeurePost = heurePost;
		this.nbLikesEdm = nbLikesEdm;
	}
	

	public String getNumEdm() {
		return numEdm;
	}

	public void setNumEdm(String numEdm) {
		this.numEdm = numEdm;
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

	public String getNbLikesEdm() {
		return nbLikesEdm;
	}

	public void setNbLikesEdm(String nbLikesEdm) {
		this.nbLikesEdm = nbLikesEdm;
	}
	
	
	

}

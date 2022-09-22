package article.model;

import article.model.ArticleDTO;

public class WritingRequest {

	private String writerName;
	private String password;
	private String title;
	private String content;

	

	public String getWriterName() {
		return writerName;
	}

	public void setWriterName(String writerName) {
		this.writerName = writerName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public ArticleDTO toArticle() {
		ArticleDTO article = new ArticleDTO();
		article.setWriterName(writerName);
		article.setPassword(password);;
		article.setTitle(title);
		article.setContent(content);
		
		return article;
	}

}

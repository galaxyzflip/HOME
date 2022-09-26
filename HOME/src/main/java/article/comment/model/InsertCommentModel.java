package article.comment.model;

public class InsertCommentModel {

	private String commentContent;
	private String password;
	private String commenter;
	private String ip;


	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCommenter() {
		return commenter;
	}

	public void setCommenter(String commenter) {
		this.commenter = commenter;
	}
	
	public CommentDTO toComment() {
		CommentDTO comment = new CommentDTO();
		comment.setCommentContent(this.commentContent);
		comment.setPassword(this.password);
		comment.setCommenter(this.commenter);
		comment.setIp(this.ip);
		
		return comment;
		
	}

}

package article.comment.model;

import java.util.ArrayList;
import java.util.List;

public class CommentListModel {

	public List<CommentDTO> commentLists;
	private int requestPage;
	private int totalPageCount;
	private int startRow;
	private int endRow;
	
	
	public CommentListModel() {
		this(new ArrayList<CommentDTO>(), 0,0,0,0);
		
	}
	
	public CommentListModel(List<CommentDTO> commentLists, int requestPage, int totalPageCount, int startRow,
			int endRow) {
		super();
		this.commentLists = commentLists;
		this.requestPage = requestPage;
		this.totalPageCount = totalPageCount;
		this.startRow = startRow;
		this.endRow = endRow;
	}
	
	public boolean isHasComment() {
		return !commentLists.isEmpty();
	}

	public int getRequestPage() {
		return requestPage;
	}

	public int getTotalPageCount() {
		return totalPageCount;
	}

	public int getStartRow() {
		return startRow;
	}

	public int getEndRow() {
		return endRow;
	}
	
	
}

package article.model;

import java.util.List;
import java.util.ArrayList;
import article.model.ArticleDTO;

public class ArticleListRequest {

	private List<ArticleDTO> articleList;
	private int requestPage;
	private int totalPageCount;
	private int startRow;
	private int endRow;
	
	
	public ArticleListRequest() {
		this(new ArrayList<ArticleDTO>(), 0,0,0,0);
	}

	public ArticleListRequest(List<ArticleDTO> articleList, int requestPage, int totalPageCount, int startRow,
			int endRow) {
		this.articleList = articleList;
		this.requestPage = requestPage;
		this.totalPageCount = totalPageCount;
		this.startRow = startRow;
		this.endRow = endRow;
	}
	
	

	public List<ArticleDTO> getArticleList() {
		return articleList;
	}
	
	public boolean isHasArticle() {
		return !articleList.isEmpty();
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

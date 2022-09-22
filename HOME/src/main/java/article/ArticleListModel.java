package article;
import java.util.List;
import java.util.ArrayList;

public class ArticleListModel {

	private List<ArticleDTO> articleList;
	private int requestPage;
	private int totalPageCount;
	private int startRow;
	private int endrow;
	
	public ArticleListModel() {
		this(new ArrayList<ArticleDTO>(), 0,0,0,0);
	}

	public ArticleListModel(List<ArticleDTO> articleList, int requestPage, int totalPageCount, int startRow,
			int endrow) {
		this.articleList = articleList;
		this.requestPage = requestPage;
		this.totalPageCount = totalPageCount;
		this.startRow = startRow;
		this.endrow = endrow;
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

	public int getEndrow() {
		return endrow;
	}
	
	
}

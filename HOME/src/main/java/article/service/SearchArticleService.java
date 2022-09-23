package article.service;

public class SearchArticleService {

	private SearchArticleService(){}
	private static SearchArticleService instance = new SearchArticleService();
	
	public static SearchArticleService getInstance() {
		return instance;
	}
	
	
	
	
}

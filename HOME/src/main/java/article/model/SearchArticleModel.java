package article.model;

import java.util.ArrayList;
import java.util.List;

public class SearchArticleModel {

	private static ArrayList<String> targetList = new ArrayList<>() {{
		add("writer_name");
		add("title");
		add("content");
		
	}};
	
	public static String getTargetList(int target) {
		if(target == -1) {
			return new String();
		} else
		return targetList.get(target);
	}
	
	
}

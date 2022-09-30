package tiles;

import java.util.ArrayList;
import java.util.List;

import org.apache.tiles.Attribute;
import org.apache.tiles.AttributeContext;
import org.apache.tiles.context.TilesRequestContext;
import org.apache.tiles.preparer.ViewPreparer;

public class MenuViewPreparer implements ViewPreparer{

	@Override
	public void execute(TilesRequestContext tilesContext, AttributeContext attrContext) {

		List<MenuItem> userMenus = new ArrayList<>();
		
		userMenus.add(new MenuItem("메뉴1", "link1"));
		userMenus.add(new MenuItem("메뉴2", "link2"));
		userMenus.add(new MenuItem("메뉴3", "link3"));
		
		tilesContext.getRequestScope().put("userMenus", userMenus);
		
		List<MenuItem> adminMenus = new ArrayList<>();
		
		adminMenus.add(new MenuItem("관리메뉴1", "link1"));
		adminMenus.add(new MenuItem("관리메뉴2", "link2"));
		adminMenus.add(new MenuItem("관리메뉴3", "link3"));
		attrContext.putAttribute("adminMunew", new Attribute(adminMenus), true);
		
	}

	
}

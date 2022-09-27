package pds.service;

import java.sql.*;
import java.util.*;
import pds.dao.PdsItemDAO;
import pds.model.PdsItem;
import pds.model.PdsItemListModel;

import logon.jdbcUtil;
import messagebook.ConnectionProvider;


public class ListPdsItemService {

	private static ListPdsItemService instance = new ListPdsItemService();
	private ListPdsItemService() {}
	public static ListPdsItemService getInstance() {
		return instance;
	}
	
	public static final int COUNT_PER_PAGE = 5;
	
	public PdsItemListModel getPdsItemList(int pageNumber, String searchKeyword) {
		if(pageNumber < 0) {
			throw new IllegalArgumentException("Page number < 0 :" + pageNumber);
		}
		
		PdsItemDAO pdsItemDao = PdsItemDAO.getInstance();
		Connection conn = null;
		PdsItemListModel PdsItemListview = null;
		
		try {
			
			conn = ConnectionProvider.getConnection();
			int totalArticleCount = pdsItemDao.selectCount(conn);
			
			if(totalArticleCount == 0) {
				return new PdsItemListModel();
				
			}
			
			int totalPageCount = calculateTotalPageCount(totalArticleCount);
			int firstRow = (pageNumber - 1) * COUNT_PER_PAGE + 1;
			int endRow = firstRow + COUNT_PER_PAGE - 1;
			
			if(endRow > totalArticleCount) {
				endRow = totalArticleCount;
			}
			
			List<PdsItem> PdsItemLists = pdsItemDao.select(conn, firstRow, endRow, searchKeyword);
			PdsItemListview = new PdsItemListModel(PdsItemLists, pageNumber, totalPageCount, firstRow, endRow);
			
			
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}finally {
			jdbcUtil.close(conn);
		}
		
		return PdsItemListview;
		
		
	}
	private int calculateTotalPageCount(int totalArticleCount) {

		if(totalArticleCount == 0) {
			return 0;
		}
		
		int pageCount = totalArticleCount / COUNT_PER_PAGE;
	
		if(totalArticleCount % COUNT_PER_PAGE > 0) {
			pageCount++;
		}
		
		return pageCount;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

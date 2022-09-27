package pds.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PdsItemListModel {

	private List<PdsItem> pdsItemLists;
	private int requestPage;
	private int totalPageCount;
	private int startRow;
	private int endRow;

	public PdsItemListModel() {
		this(new ArrayList<PdsItem>(), 0, 0, 0, 0);
	}

	public PdsItemListModel(List<PdsItem> pdsItemLists, int requestPage, int totalPageCount, int startRow, int endRow) {
		super();
		this.pdsItemLists = pdsItemLists;
		this.requestPage = requestPage;
		this.totalPageCount = totalPageCount;
		this.startRow = startRow;
		this.endRow = endRow;
	}
	
	public boolean isHasPdsItem() {
		return !pdsItemLists.isEmpty();
	}

	public List<PdsItem> getPdsItemLists() {
		return pdsItemLists;
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

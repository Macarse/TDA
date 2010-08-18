package com.tda.persistence.paginator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Paginator {
	private int resultsPerPage;
	private int pageIndex = 1;
	private int totalResultsCount;
	private Boolean orderAscending;
	private String orderField;
	private Map<String, String> params;

	public Paginator(int resultsPerPage) {
		this.params = new HashMap<String, String>();
		this.resultsPerPage = resultsPerPage;
	}

	public int getResultsPerPage() {
		return resultsPerPage;
	}

	public void setResultsPerPage(int resultsPerPage) {
		this.resultsPerPage = resultsPerPage;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getTotalResultsCount() {
		return totalResultsCount;
	}

	public void setTotalResultsCount(int totalResultsCount) {
		this.totalResultsCount = totalResultsCount;
	}

	public Boolean getOrderAscending() {
		return orderAscending;
	}

	public void setOrderAscending(Boolean orderAscending) {
		this.orderAscending = orderAscending;
	}

	public String getOrderField() {
		return orderField;
	}

	public void setOrderField(String orderField) {
		this.orderField = orderField;
	}

	public int getPageCount() {
		if (this.totalResultsCount == 0)
			return 0;

		/* Get how many pages depending on the results */
		return (int) Math.ceil(this.totalResultsCount
				/ (double) this.resultsPerPage);
	}

	public boolean isLastPage() {
		if (getPageCount() == 1)
			return true;

		return getPageCount() == pageIndex;
	}

	public boolean isFirstPage() {
		return 1 == getPageIndex();
	}

	public List<Integer> getPages() {
		ArrayList<Integer> pages = new ArrayList<Integer>();
		for (int i = 0; i < getPageCount(); i++)
			pages.add(i + 1);

		return pages;
	}

	public Integer getNextPage() {
		return pageIndex + 1;
	}

	public Integer getPreviousPage() {
		return pageIndex - 1;
	}

	public void setParam(String key, String value) {
		this.params.put(key, value);
	}
	
	public String getParam(String key){
		return this.params.get(key);
	}

	public String getParams() {
		String ret = "";
		
		for (String e : this.params.keySet()) {
			ret += e + "=" + this.params.get(e) + "&";
		}
		
		return ret;
	}
}

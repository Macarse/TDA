package com.tda.model.paginator;

import java.util.ArrayList;
import java.util.List;

public class Paginator {
	private int pageSize;
	private int pageIndex;
	private int rowsCount;
	private Order order;
	private String orderField;

	public Paginator(int pageSize, int pageIndex, Order order) {
		this.pageSize = pageSize;
		this.pageIndex = pageIndex;
		this.order = order;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getRowsCount() {
		return rowsCount;
	}

	public void setRowsCount(int rowsCount) {
		this.rowsCount = rowsCount;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public String getOrderField() {
		return orderField;
	}

	public void setOrderField(String orderField) {
		this.orderField = orderField;
	}

	public int getPageCount() {
		if (this.rowsCount == 0)
			return 0;

		return (int) Math.ceil((this.rowsCount + this.pageSize)
				/ (double) this.rowsCount);
	}

	public boolean isLastPage() {
		if (getPageCount() == 0)
			return true;
		return getPageCount() == (getPageIndex() + 1);
	}

	public boolean isFirstPage() {
		return 0 == getPageIndex();
	}

	public List<Integer> getPages() {
		ArrayList<Integer> pages = new ArrayList<Integer>();
		for (int i = 0; i < getPageCount(); i++)
			pages.add(i + 1);

		return pages;
	}

	public int getActualPage() {
		return this.pageIndex;
	}
}

package com.tda.model.paginator;

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
}

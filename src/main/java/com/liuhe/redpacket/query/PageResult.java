package com.liuhe.redpacket.query;

import java.io.Serializable;
import java.util.List;

public class PageResult<T> implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7065048367863170801L;
	private List<T> rows;// 当前页数据
	private int size = 10;// 每页条数
	private int page = 1;// 当前页
	private int total;// 总数

	

	public PageResult(List<T> rows,int size,int page,int total) {
		super();
		this.rows = rows;
		this.size = size;
		this.page = page;
		this.total = total;
	}
	public PageResult(){
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	

	public int getTotalPage() {
		return (int)Math.ceil((double)this.total/this.size);
	}
	
}

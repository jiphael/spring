package com.my.model;

import java.util.List;

public class PageBean {
	public static final int CNT_PER_PAGE =3; //페이지당 보여줄 목록수
	public static final int CNT_PER_PAGEGROUP = 4; //페이지당 보여줄 페이지그룹수
	private String url ; //링크클릭시 이동할 URL
	private int currentPage; // 현재페이지
	private int startRow;
	private int endRow;
	private int totalPage; // 현재페이지
	private List list;
	private int startPage; //페이지 그룹의 시작페이지
	private int endPage; //페이지 그룹의 끝페이지
	public String getUrl() {
		return url;
	}

	public PageBean() {}
	public PageBean(int currentPage) {
		this.currentPage = currentPage;
	    this. endRow = currentPage * CNT_PER_PAGE; 
	    this. startRow = endRow - CNT_PER_PAGE +1;
	}
	
	


	public void setUrl(String url) {
		this.url = url;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getStartRow() {
		return startRow;
	}
	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}
	public int getEndRow() {
		return endRow;
	}
	public void setEndRow(int endRow) {
		this.endRow = endRow;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public List getList() {
		return list;
	}
	public void setList(List list) {
		this.list = list;
	}
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public static int getCntPerPage() {
		return CNT_PER_PAGE;
	}
	public static int getCntPerPagegroup() {
		return CNT_PER_PAGEGROUP;
	}
	@Override
	public String toString() {
		return "PageBean [url=" + url + ", currentPage=" + currentPage + ", startRow=" + startRow + ", endRow=" + endRow
				+ ", totalPage=" + totalPage + ", list=" + list + ", startPage=" + startPage + ", endPage=" + endPage
				+ "]";
	}
}

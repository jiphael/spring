package com.my.model;

import java.util.List;

public class PageBean {
	public static final int CNT_PER_PAGE = 3; //페이지당 보여줄 목록수
	public static final int CNT_PER_PAGEGROUP = 4; //페이지당 보여줄 페이지그룹수
	private String url; //링크클릭시 이동할URL
	private int currentPage; //현재페이지
	private int startRow; //페이지당 보여줄 시작행
	private int endRow;   //페이지당 보여줄 끝행
	private int totalPage; //총페이지
	private List list;
	private int startPage; //페이지그룹의 시작페이지
	private int endPage;  //페이지그룹의 끝페이지
	public PageBean() {}
	public PageBean(int currentPage) {
		this.currentPage = currentPage;
		this.endRow = currentPage * CNT_PER_PAGE;
		this.startRow = endRow - CNT_PER_PAGE + 1;	
		
	}
	
	
	public PageBean(int currentPage, int rowCnt) {
		this(currentPage);
		int totalPage = (int) Math.ceil((double)rowCnt/PageBean.CNT_PER_PAGE);//총페이지수: 게시판 총행수와 페이지당 보여줄 목록수로 계산
		setTotalPage(totalPage);
		
		int startPage = (currentPage-1)/PageBean.CNT_PER_PAGEGROUP * PageBean.CNT_PER_PAGEGROUP + 1;
		int endPage = startPage + PageBean.CNT_PER_PAGEGROUP - 1;
		if(endPage > totalPage) {
			endPage = totalPage;
		}		
		setStartPage(startPage);
		setEndPage(endPage);
	}
	@Override
	public String toString() {
		return "PageBean [url=" + url + ", currentPage=" + currentPage + ", startRow=" + startRow + ", endRow=" + endRow
				+ ", totalPage=" + totalPage + ", list=" + list + ", startPage=" + startPage + ", endPage=" + endPage
				+ "]";
	}
	public String getUrl() {
		return url;
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
	
	
}

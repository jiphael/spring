package com.my.service;

import java.util.List;

import com.my.dao.BoardDAO;
import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.model.PageBean;
import com.my.vo.Board;

public class BoardService {
	private BoardDAO dao;
	public BoardService() {
		dao = new BoardDAO();
	}
	public void write(Board board) throws AddException{
		if(board.getParent_no() != 0) {
			throw new AddException("부모글번호가 필요없습니다");
		}
		dao.insert(board);
	}
	public void reply(Board board) throws AddException{
		if(board.getParent_no() == 0) {
			throw new AddException("부모글번호가 없습니다");
		}
		dao.insert(board);
	}
	//public List<Board> findAll(int page) throws FindException{
	public PageBean findAll(int page) throws FindException{
		if(page < 1) {
			throw new FindException(page+" 페이지가  존재하지 않습니다");
		}
		//PageBean pb = new PageBean(page);//현재페이지, 시작행, 끝행
//		List<Board> list =  dao.selectAll(pb.getStartRow(), pb.getEndRow());
//		pb.setList(list);
//		
//		int rowCnt = dao.selectCount();//게시판 총 행수
//		int totalPage = (int) Math.ceil((double)rowCnt/PageBean.CNT_PER_PAGE);//총페이지수: 게시판 총행수와 페이지당 보여줄 목록수로 계산
//		pb.setTotalPage(totalPage);
//		
//		int startPage = (page-1)/PageBean.CNT_PER_PAGEGROUP * PageBean.CNT_PER_PAGEGROUP + 1;
//		int endPage = startPage + PageBean.CNT_PER_PAGEGROUP - 1;
//		if(endPage > totalPage) {
//			endPage = totalPage;
//		}		
//		pb.setStartPage(startPage);
//		pb.setEndPage(endPage);
		
		int rowCnt = dao.selectCount();//게시판 총 행수
		PageBean pb = new PageBean(page, rowCnt);//현재페이지, 시작행, 끝행, 총페이지, 시작페이지, 끝페이지
		List<Board> list =  dao.selectAll(pb.getStartRow(), pb.getEndRow());
		pb.setList(list);
		return pb;
	}
	public Board findByNo(int board_no) throws FindException {
		return dao.selectByNo(board_no);
	}	
}

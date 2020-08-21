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
         throw new AddException("부모글 번호가 없습니다");
      }
      dao.insert(board);
   }
  // public List<Board> findAll(int page) throws FindException{
      public PageBean findAll(int page) throws FindException{
    
      
	   if(page <1) {
		   throw new FindException(page +"페이지가 존재하지 않습니다.");
	   }
	   PageBean pb = new PageBean(page); //현재페이지, 시작행,끝행 
	   List<Board> list = dao.selectAll(pb.getStartRow(), pb.getEndRow());
	   pb.setList(list);
	   int rowCnt = dao.selectCount(); //게시판 총행수
	   int totalPage =(rowCnt % pb.CNT_PER_PAGE == 0) ? rowCnt / pb.CNT_PER_PAGE : rowCnt / pb.CNT_PER_PAGE +1;
			  //총페이지수 : 게시판총행수와 페이지당 보여줄 페이지그룹화
	   pb.setTotalPage(totalPage);
	   float midVal = (float)page / pb.CNT_PER_PAGEGROUP;
	   int endPage =(int)(Math.ceil(midVal)*pb.CNT_PER_PAGEGROUP);
	   int startPage = endPage - pb.CNT_PER_PAGEGROUP +1;
	   if(endPage > totalPage) {
		   endPage = totalPage;
	   }
	   //~
	   pb.setStartPage(startPage);

	   pb.setEndPage(endPage);
	   
	   return pb;
   }
}
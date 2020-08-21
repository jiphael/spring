package com.my.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.sql.MyConnection;
import com.my.vo.Board;

public class BoardDAO {
   
   public static final int CNT_PER_PAGE=3;
   
   public void insert(Board board) throws AddException{
      Connection con = null;
      PreparedStatement pstmt = null;
      try {
         String insertSQL = "";
//         if(board.getParent_no() == 0) {//글쓰기
//            insertSQL ="INSERT INTO board (board_no, parent_no, board_title, board_writer, board_dt, board_content)\r\n" + 
//                  "VALUES (board_seq.NEXTVAL, 0, ?, ?, SYSTIMESTAMP, ?)";
//         }else {//답글쓰기
//            insertSQL ="INSERT INTO board (board_no, parent_no, board_title, board_writer, board_dt, board_content)\r\n" + 
//                  "VALUES (board_seq.NEXTVAL, ?, ?, ?, SYSTIMESTAMP, ?)";
//         }
         insertSQL ="INSERT INTO board (board_no, parent_no, board_title, board_writer, board_dt, board_content)\r\n" + 
               "VALUES (board_seq.NEXTVAL, ?, ?, ?, SYSTIMESTAMP, ?)";
         con = MyConnection.getConnection();
         pstmt = con.prepareStatement(insertSQL);
         pstmt.setInt(1, board.getParent_no());
         pstmt.setString(2, board.getBoard_title());
         pstmt.setString(3, board.getBoard_writer());
         pstmt.setString(4, board.getBoard_content());
         pstmt.executeUpdate();
      } catch (ClassNotFoundException | SQLException e) {
         e.printStackTrace();
         throw new AddException(e.getMessage());
      } finally {
         MyConnection.close(pstmt, con);
      }
   }

   public List<Board> selectAll(int startRow, int endRow) throws FindException{
      Connection con = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      try {
         con = MyConnection.getConnection();
         String selectAllSQL = "SELECT *\r\n" + 
               "FROM (SELECT ROWNUM r, a.*\r\n" + 
               "        FROM (SELECT level, board.*\r\n" + 
               "            FROM board\r\n" + 
               "            START WITH parent_no = 0\r\n" + 
               "            CONNECT BY PRIOR board_no = parent_no\r\n" + 
               "            ORDER SIBLINGS BY board_no DESC) a\r\n" + 
               ")\r\n" + 
               "WHERE r BETWEEN ? AND ?";
         //page가 1페이지면 startRow는 1 endRow 3
         //page가 2               4      6
         //      3               7      9
         pstmt = con.prepareStatement(selectAllSQL);
         pstmt.setInt(1, startRow);
         pstmt.setInt(2, endRow);
         rs = pstmt.executeQuery();
         List<Board> list = new ArrayList<>();
         while(rs.next()) {
            int level = rs.getInt("LEVEL");
            int board_no = rs.getInt("board_no");
            int parent_no = rs.getInt("parent_no");
            String board_title = rs.getString("board_title");
            String board_writer = rs.getString("board_writer");
            Date board_dt = rs.getDate("board_dt");
            String board_content = rs.getString("board_content");
            Board b = new Board(level, board_no, parent_no, board_title,board_writer, board_dt, board_content);
            list.add(b);
         }
         if(list.size() == 0) {
            throw new FindException("게시글이 없습니다");
         }return list;
         
      } catch (ClassNotFoundException | SQLException e) {
         throw new FindException("찾지못함");
      } finally {
         MyConnection.close(rs, pstmt, con);
      }
   }

   public int selectCount() {
      Connection con = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      int rowCnt = 0;
      try {
         con = MyConnection.getConnection();
         String selectCountSQL = "select COUNT(*) FROM board";
         pstmt = con.prepareStatement(selectCountSQL);
         rs = pstmt.executeQuery();
         if(rs.next()) {
            rowCnt=rs.getInt("COUNT(*)");
         }
      } catch (ClassNotFoundException | SQLException e) {
         e.printStackTrace();
      } finally {
         MyConnection.close(rs, pstmt, con);
      }
      return rowCnt;
   }
}
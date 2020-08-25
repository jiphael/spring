package com.my.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.sql.MyConnection;
import com.my.vo.OrderInfo;
import com.my.vo.OrderLine;
import com.my.vo.Product;

public class OrderDAO {
	public void insert(OrderInfo info) throws AddException{
		Connection con = null;
		try {
			con = MyConnection.getConnection();
			con.setAutoCommit(false); //자동커밋 해제
			insertInfo(con, info);             //주문기본 추가
			insertLines(con, info.getLines()); //주문상세들 추가
			con.commit();
		}catch(Exception e) {
			e.printStackTrace();
			if(con != null)
				try {
					con.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			throw new AddException(e.getMessage());
		}finally {
			MyConnection.close(con);
		}
	}
	private void insertInfo(Connection con, OrderInfo info)  throws AddException{
		//주문기본정보 추가
		PreparedStatement pstmt = null;
		String insertInfoSQL = 
				"INSERT INTO order_info(order_no, order_id, order_dt)"
				+ " VALUES(order_seq.NEXTVAL,  ?, SYSDATE)";
		
		try {
			pstmt = con.prepareStatement(insertInfoSQL);
			pstmt.setString(1, info.getOrder_c().getId());
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AddException(e.getMessage());
		}finally {
			MyConnection.close(pstmt, null);
		}
		
	}
	private void insertLines(Connection con, List<OrderLine> lines)  throws AddException{
		//주문상세정보들 추가
			
		PreparedStatement pstmt = null;
		String insertLineSQL = 
			  "INSERT INTO order_line(order_no, order_prod_no, order_quantity)"
			+ " VALUES (order_seq.CURRVAL,       ?,            ?)";
		try {
			pstmt = con.prepareStatement(insertLineSQL);
			for(OrderLine line: lines) {
				pstmt.setString(1, line.getOrder_p().getProd_no());
				pstmt.setInt(2, line.getOrder_quantity());
				//pstmt.executeUpdate();
				pstmt.addBatch(); //일괄처리에 추가
			}
			pstmt.executeBatch(); //일괄처리작업 수행
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AddException(e.getMessage());
		}finally {
			MyConnection.close(pstmt, null);
		}
	}
	
	public List<OrderInfo> selectById(String id) throws FindException{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = MyConnection.getConnection();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new FindException(e.getMessage());
		}
		String selectByIdSQL = 
			"SELECT info.order_no,  order_id, order_dt\r\n" + 
			"      ,order_prod_no, prod_name, prod_price\r\n" + 
			"      , order_quantity     \r\n" + 
			"FROM order_info info \r\n" + 
			"JOIN order_line line ON (info.order_no = line.order_no)\r\n" + 
			"JOIN product p ON (line.order_prod_no = p.prod_no)\r\n" + 
			"WHERE order_id = ? "+
			"ORDER BY info.order_no DESC";
		
		try {
			pstmt = con.prepareStatement(selectByIdSQL);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			List<OrderInfo> infos = new ArrayList<>(); 			
			List<OrderLine> lines = null; //주문상세들
			int oldOrder_no = 0;//이전주문번호
			while(rs.next()) {
				int order_no = rs.getInt("order_no"); //행의 주문번호 얻기				
				if(oldOrder_no != order_no) {
					OrderInfo info=null; //주문기본
					info = new OrderInfo();   //주문기본객체 새로 생성
					info.setOrder_no(order_no); 
					info.setOrder_dt(rs.getDate("order_dt"));
					lines = new ArrayList<>(); //주문상세들lines 새로 생성
					info.setLines(lines); 
					infos.add(info);	
					oldOrder_no = order_no;
				}
				Product order_p = new Product();
				order_p.setProd_no(rs.getString("order_prod_no"));
				order_p.setProd_name(rs.getString("prod_name"));
				order_p.setProd_price(rs.getInt("prod_price"));
				//order_p.setAmount(rs.getInt("금액"));//???
				
				OrderLine line = new OrderLine(
						 order_no
						,order_p
						,rs.getInt("order_quantity")
						);
				//line.setAmount(rs.getInt("금액"));
				lines.add(line);
			}
			if(infos.size() == 0) {
				throw new FindException("주문이 없습니다");
			}
			return infos;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new FindException(e.getMessage());
		}finally {
			MyConnection.close(rs, pstmt, con);
		}
		
	}
	
	
	
}

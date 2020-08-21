package com.my.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.my.exception.AddException;
import com.my.exception.DuplicatedException;
import com.my.exception.FindException;
import com.my.sql.MyConnection;
import com.my.vo.Product;

import oracle.net.aso.e;

@Repository(value = "productDAO")
@Qualifier(value="myds")
public class ProductDAO {
   @Autowired
   private DataSource ds;
   public void insert(Product product) throws AddException, DuplicatedException{
   }

   public Product selectByNo(String no) throws FindException {
      Connection con = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      
//      try {
//         con= MyConnection.getConnection();
//      } catch (ClassNotFoundException | SQLException e) {
//         e.printStackTrace();
//         throw new FindException(e.getMessage());
//      }
      try {
    		con = ds.getConnection();
    	} catch (SQLException e) {
    		e.printStackTrace();
    		throw new FindException(e.getMessage());
    	}

      String selectByNoSQL ="SELECT prod_no,prod_name,prod_price\r\n" + 
            "FROM product\r\n"+
            "WHERE prod_no = ?";
   
               try {
                  pstmt = con.prepareStatement(selectByNoSQL);
                  pstmt.setString(1, no);   
                  rs = pstmt.executeQuery();
                  if(rs.next()) {
                     Product p = new Product();
                     p.setProd_no(rs.getString("prod_no"));
                     p.setProd_name(rs.getString("prod_name"));
                     p.setProd_price(rs.getInt("prod_price"));
                     return p;
                  }
                  throw new FindException("�긽�뭹�씠 �뾾�뒿�땲�떎.");
                  
               } catch (SQLException e) {
                  e.printStackTrace();
                  throw new FindException("�엯�젰�쓣 �옒紐삵뻽�뼱�슂");
                  
               }finally {
                  MyConnection.close(pstmt, con);
               }
   }
   public List<Product> selectByName(String word) throws FindException{
      return null;
   }
   public List<Product> selectAll(int page) throws FindException{
      return null;
   }
   
   public List<Product> selectAll() throws FindException{
      Connection con = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      List<Product> list = new ArrayList<>();   
      try {
         con= MyConnection.getConnection();
      } catch (ClassNotFoundException | SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
         throw new FindException(e.getMessage());
      }
      
      String selectAllSQL = "SELECT prod_no,prod_name,prod_price\r\n" + 
                        "FROM product";
      try {
         pstmt = con.prepareStatement(selectAllSQL);
         rs = pstmt.executeQuery();
            while(rs.next()) {
               Product p = new Product(
               rs.getString("prod_no")   
               ,rs.getString("prod_name")   
               ,rs.getInt("prod_price"));
            list.add(p);
         }
         if(list.size() ==0) {
            throw new FindException("�긽�뭹�씠 �뾾�뒿�땲�떎.");
         }
         return list;
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }finally {
         MyConnection.close(rs, pstmt, con);
      }
      
      
      return null;
   }
}
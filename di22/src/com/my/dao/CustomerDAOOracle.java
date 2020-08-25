package com.my.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.my.exception.AddException;
import com.my.exception.DuplicatedException;
import com.my.exception.FindException;
import com.my.exception.ModifyException;
import com.my.exception.RemoveException;
import com.my.sql.MyConnection;
import com.my.vo.Customer;
import com.my.vo.Postal;

@Repository
@Primary
public class CustomerDAOOracle implements CustomerDAO2 {
	@Autowired
//	@Qualifier(value = "tutords")
    private SqlSessionFactory sqlSessionFactory;
	private DataSource ds;
	@Override
	public void insert(Customer c) throws AddException, DuplicatedException {
		Connection con = null;
		PreparedStatement pstmt = null;
//		try {
//			con = MyConnection.getConnection();
//		} catch (ClassNotFoundException | SQLException e) {
//			e.printStackTrace();
//			throw new AddException(e.getMessage());
//		}		
		try {
		con = ds.getConnection();
		} catch (SQLException e) {
		e.printStackTrace();
		throw new AddException(e.getMessage());
		}	
		
		String insertSQL = 
			"INSERT INTO customer(id, pwd, name, buildingno, addr) VALUES (?,?,?,?,?)";
		try {
			pstmt = con.prepareStatement(insertSQL);
			pstmt.setString(1, c.getId());	
			pstmt.setString(2, c.getPwd());
			pstmt.setString(3, c.getName());
			pstmt.setString(4, c.getPostal().getBuildingno());
			pstmt.setString(5, c.getAddr());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			if(e.getErrorCode() == 1) { //�̹������ϴ� ID�� �߰��Ϸ��� ���
			  throw new DuplicatedException("�̹� �����ϴ� ID�Դϴ�");
			}else {
				throw new AddException(e.getMessage());
			}
		} finally {
			MyConnection.close(pstmt, con);
		}
		
	}

	@Override
	public List<Customer> selectAll() throws FindException {
		return null;
	}

	@Override
	public Customer selectById(String id) throws FindException {
		Connection con = null; //DB�� ����
		PreparedStatement pstmt = null;
		ResultSet rs = null;
//		try {
//			con = com.my.sql.MyConnection.getConnection();
//		}catch(ClassNotFoundException | SQLException e) {
//			throw new FindException("selectById:"+e.getMessage());
//		}
		try {
		con = ds.getConnection();
		}catch(SQLException e1) {
		throw new FindException("selectById:"+e1.getMessage());
		}
		//String selectByIdSQL = "SELECT * FROM customer WHERE id=?";
		String selectByIdSQL = 
	"SELECT id, pwd, name\r\n" + 
	"      ,zipcode\r\n" + 
	"      ,p.buildingno\r\n" + 
	"      ,sido ||' ' || NVL(sigungu, ' ') ||' ' || NVL(eupmyun, ' ')  city    \r\n" + 
	"      ,doro || ' ' || DECODE(building2, '0' , building1, building1 ||'-' || building2) doro\r\n" + 
	"      ,building\r\n" +
	"      ,addr\r\n"+
	"FROM customer c LEFT JOIN postal p ON (c.buildingno = p.buildingno)\r\n" + 
	"WHERE id=?";
		try {
			pstmt = con.prepareStatement(selectByIdSQL);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) { //���� �����Ѵ�
				Customer c = new Customer();
				c.setId(id);
				c.setPwd(rs.getString("pwd"));
				c.setName(rs.getString("name"));
				Postal p = new Postal();
				p.setZipcode(rs.getString("zipcode"));
				p.setBuildingno(rs.getString("buildingno"));
				p.setCity(rs.getString("city"));
				p.setDoro(rs.getString("doro"));
				p.setBuilding(rs.getString("building"));
				c.setPostal(p);
				
				c.setAddr(rs.getString("addr"));
				return c;
			}
			throw new  FindException("selectById: ���̵� �ش��ϴ� �������ϴ�");		
		} catch (SQLException e) {
			throw new FindException("selectById:" + e.getMessage());
		} finally {
			MyConnection.close(rs, pstmt, con);
		}
	}
	@Override
	public List<Customer> selectByName(String word) throws FindException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Customer c) throws ModifyException {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(String id) throws RemoveException {
		// TODO Auto-generated method stub

	}
	
}

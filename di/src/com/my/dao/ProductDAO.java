package com.my.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLSyntaxErrorException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.my.exception.AddException;
import com.my.exception.DuplicatedException;
import com.my.exception.FindException;
import com.my.vo.Product;
@Repository(value = "productDAO")
public class ProductDAO {
//	@Autowired
//	@Qualifier(value="myds")
//	private DataSource ds;
	
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	
	public void insert(Product product) throws AddException, DuplicatedException{
	}
	public Product selectByNo(String no) throws FindException{
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			Product p = session.selectOne("ProductMapper.selectByNo"
	                , "C0001");
			if(p == null) {
				throw new FindException("상품이 없습니다");
			}
			return p;
		}catch(DataAccessException e) {
			throw new FindException(e.getMessage());
		}finally {
			session.close();
		}
	}
	public List<Product> selectByName(String word) throws FindException{
		return null;
	}
	public List<Product> selectAll(int page) throws FindException{
		
		return null;
	}
	public List<Product> selectAll() throws FindException{	
		List<Product> list = new ArrayList<>();
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			list = session.selectList("ProductMapper.selectAll");
			if(list.size() == 0) {
				throw new FindException("상품이 하나도 없습니다");
			}
			return list;
		}catch(DataAccessException e) {
			throw new FindException(e.getMessage());
		}finally {
			session.close();
		}
	}
	
}

package com.my.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
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
@Qualifier(value = "customerDAOOracle")
public class CustomerDAOOracle implements CustomerDAO2 {
	@Autowired
	@Qualifier("myds")
	private DataSource ds;
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	
	@Override
	public void insert(Customer c) throws AddException, DuplicatedException {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			session.insert("CustomerMapper.insert", c);
		}catch(Exception e) {
			if(e.getCause() instanceof SQLIntegrityConstraintViolationException) {
				throw new DuplicatedException("이미 존재하는 ID입니다");
			}else {
				throw new AddException("insert:"+e.getMessage());
			}
		} finally {
			session.close();
		}
	}

	@Override
	public List<Customer> selectAll() throws FindException {
		return null;
	}

	@Override
	public Customer selectById(String id) throws FindException {
		SqlSession session = sqlSessionFactory.openSession();
		try {
		Customer c = session.selectOne("CustomerMapper.selectById", id);
		if(c == null) {
			throw new  FindException("selectById: 아이디에 해당하는 고객없습니다");		
		}
		return c;
		}catch(Exception e) {
			throw new FindException("selectById:" + e.getMessage());
		} finally {
			session.close();
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

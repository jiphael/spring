package test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.my.dao.CustomerDAO2;
import com.my.dao.OrderDAO;
import com.my.dao.ProductDAO;
import com.my.exception.FindException;
import com.my.vo.Customer;
import com.my.vo.OrderInfo;
import com.my.vo.OrderLine;
import com.my.vo.Postal;
import com.my.vo.Product;

import lombok.extern.log4j.Log4j;
//@RunWith(SpringJUnit4ClassRunner.class)
@ExtendWith(SpringExtension.class)
//@ContextConfiguration(locations = "file:C:\\수업SPRING\\di\\src\\configuration.xml")
@ContextConfiguration(locations = "classpath:configuration.xml")
//@ContextConfiguration(classes = com.my.config.Configuration.class)
@Log4j
class StartContainerTest {
//	@Autowired
	private Customer c;
	
	@Autowired
	@Qualifier("customerDAOOracle")
	private CustomerDAO2 dao;
	
	@Autowired
	@Qualifier("orderDAO")
	private OrderDAO dao2;
		
//	@Test
//	@DisplayName("Customer 자동주입")
	void testCustomer() {
		assertNotNull(c);
	}
	
//	@Test
//	@DisplayName("CustomerDAO2 자동주입")
	void testDAO() {
		assertNotNull(dao);
	}
	
	@Autowired
	@Qualifier(value = "hikarids")
	DataSource ds;
	
//	@Test
//	@DisplayName("HikariDBCP")
	void testHikari() {
		assertNotNull(ds);	
		try {
			Connection con = ds.getConnection();
		} catch (SQLException e) {
//			e.printStackTrace();
			fail(e.getMessage());
		}
	}		
	
	@Autowired
	SqlSessionFactory sqlSessionFactory;
//	
//	@Test
	void testMyBatis() {
		SqlSession session = sqlSessionFactory.openSession();
		assertNotNull(session);
		try {
			Product p = session.selectOne("ProductMapper.selectByNo"
					                    , "C0001");
			assertNotNull(p);
			assertEquals("아메리카노", p.getProd_name());
			log.info(p.toString());
		}catch(DataAccessException e) {
			
		}
//		}catch(DataAccessException e) {
//			fail(e.getMessage());
//		}
		
		List<Product> list = session.selectList("ProductMapper.selectAll");
		assertNotNull(list);
		assertTrue(list.size() > 0);
	}
	
	@Autowired
	ProductDAO productDAO;
	
//	@Test
//	@DisplayName("ProductDAO Mybatis")
	void productDAOTest() {
		try {
			Product p = productDAO.selectByNo("C0001");
			assertNotNull(p);
		} catch (FindException e) {
			fail(e.getMessage());
		}
		
		try {
			List<Product>list = productDAO.selectAll();
			assertTrue(list.size() == 43);
		} catch (FindException e) {
			fail(e.getMessage());
		}
	}
	
	//@Test
	@DisplayName("CustomerDAO insert Mybatis")
	void testCustomerDAOInsert() {
		
		Postal postal = new Postal();
		postal.setBuildingno("4473037024103620003000001");
		Customer c = new Customer("id99", "p99", "n99", postal, "addr99");
		SqlSession session = sqlSessionFactory.openSession();
		try {
			session.insert("CustomerMapper.insert", c);
		}catch(Exception e) {
			if(e.getCause() instanceof SQLIntegrityConstraintViolationException) {
				fail("이미 존재하는 ID입니다");
			}
		}
	}
	
	@Test
	@DisplayName("CustomerDAO selectById Mybatis")
	void testCustomerDAOSelectById() {
//		SqlSession session = sqlSessionFactory.openSession();
//		Customer c = session.selectOne("CustomerMapper.selectById", "id1");
//		assertNotNull(c);
		try {
			Customer c = dao.selectById("id5");
			assertNotNull(c);
//			System.out.println(c.getName());
//			System.out.println(c.getPostal());
			log.info(c.getName()+":"+c.getPostal());
		} catch (FindException e) {
			fail(e.getMessage());
		}
	}
	
	//@Test
	@DisplayName("OrderDAO insertLine Mybatis")
	void testOrderLine(){
		Product p = new Product();
		p.setProd_no("C0001");
		OrderLine orderline = new OrderLine(0,p,2);
		SqlSession session = sqlSessionFactory.openSession();
		try {
			session.insert("OrderMapper.insertLine",orderline);
		}catch(Exception e) {
			fail(e.getMessage());
		}
	}
	//@Test
	@DisplayName("OrderDAO insertInfo Mybatis")
	void testOrderInfo(){
		
		Customer c = new Customer();
		c.setId("id5");
		Date d =new Date();
		OrderInfo orderinfo = new OrderInfo(109,c,d);
		SqlSession session = sqlSessionFactory.openSession();
		try {
			session.insert("OrderMapper.insertInfo",orderinfo);
		}catch(Exception e) {
			fail(e.getMessage());
			
		}
	}

}

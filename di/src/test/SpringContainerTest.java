package test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.my.dao.CustomerDAO2;
import com.my.dao.ProductDAO;
import com.my.exception.FindException;
import com.my.vo.Customer;
import com.my.vo.Postal;
import com.my.vo.Product;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "classpath:configuration.xml")
class SpringContainerTest {
	@Autowired
	private Customer c;
	
	@Autowired
	@Qualifier("customerDAOOracle")
	private CustomerDAO2 dao;
	
	@Test
	@DisplayName("Customer 자동주입")
	void testCustomer(){
		assertNotNull(c);
	}
	@Test
	@DisplayName("CustomerDAO2 자동주입")
	void testDAO(){
		assertNotNull(dao);
	}
	
	@Autowired
	@Qualifier(value = "hikarids")
	DataSource ds;
	
	@Test
	@DisplayName("HikariDBCP")
	void testHikari() {
		assertNotNull(ds);
		try {
			Connection con = ds.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			fail(e.getMessage());
		}
	
	}
	@Autowired
	SqlSessionFactory sqlSessionFactory;
	@Test
	void testMyBatis() {
		SqlSession session = sqlSessionFactory.openSession();
		assertNotNull(session);
		Product p = session.selectOne("ProductMapper.selectByNo","C0001");
		assertNotNull(p);
		
//		assertEquals("아메리카노", p.getProd_name());
//		log.info(p.toString());
		
	}
	@Autowired
	ProductDAO productDAO;
	@Test
	void productDAOTest() {
		try {
			Product p = productDAO.selectByNo("C0001");
			assertNotNull(p);
		}catch(FindException e) {
			fail(e.getMessage());
		}
		try {
			List<Product>list = productDAO.selectAll();
			assertTrue(list.size() ==6);
		}catch(FindException e) {
			fail(e.getMessage());
		}
		}
	
	@Test
	@DisplayName("CustomerDAO Mybatis")
	void testCustomerDAO() {
		Postal postal = new Postal();
		postal.getBuildingno();
		Customer c = new Customer ("id11", "p11","n11","1동110호");
		SqlSession session = sqlSessionFactory.openSession();
	}
	@Test
	@DisplayName("CustomerDAO selectById")

}
	




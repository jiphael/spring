package test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.my.dao.CustomerDAO2;
import com.my.vo.Customer;

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
	

}

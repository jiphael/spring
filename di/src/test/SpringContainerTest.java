package test;

import static org.junit.Assert.assertNotNull;

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
}

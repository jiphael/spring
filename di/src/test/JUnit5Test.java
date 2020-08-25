package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeTrue;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assumptions.assumingThat;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.my.vo.Customer;

public class JUnit5Test {
	String str;
	int num;
	@BeforeAll
	static void ba() {
		System.out.println("before all");
	}

	@BeforeEach
	void be() {
		System.out.println("before each");
		str = "hello";
		num = 1;
	}
	@Test
	void testAssert() {
		assertEquals(str, new String("hello"));//equals()와 같음
		assertTrue(num==1);		
		assertNull(str);//test fail 
		assertSame(str, new String("hello"));//test fail : ==와 같음
	}
	@Test
	void testAssertAll() {
		assertAll(
		 ()->assertEquals(str, "hello")
		,()->assertTrue(num==1)
		,()->assertNull(str)//test fail
		,()->assertSame(str, new String("hello"))//test fail
		);
	}
	@Test
	void testAssume() {
		assumeTrue(num%2==1);
		assumingThat(num%2==1, ()->assertEquals(str, "hello"));//test fail		
	}
	
	@Test
	@DisplayName("Customer의 equals, id로 재정의")
	void testEqauls() {		
		Customer c1, c2;
		c1 = new Customer(); c1.setId("id1");
		c2 = new Customer(); c2.setId("id1");
		assertTrue(c1.equals(c2));
	}

	@AfterEach
	void ae() {
		System.out.println("after each");
	}

	@AfterAll
	static void aa() {
		System.out.println("after all");
	}
}
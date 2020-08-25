package test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assumptions.assumingThat;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.my.vo.Customer;
import com.my.vo.Person;

class JUnitTest {
	private static Person p1, p2;
	private static Customer c1, c2;
	@BeforeAll
	static void ba() {
		System.out.println("@BeforeAll : 모든 테스트메서드 이전에 1회 호출됨");
//		p1 = new Person("n1", "a1");
		p2 = new Person();
		
		c1 = new Customer("id1", "p1");
		c2 = new Customer("id1", "p2");
	}
	
	private int num;
	@BeforeEach 
	void be(){
		System.out.println("@BeforeEach : 각 테스트메서드 이전에 호출됨");
		num = 10;
	}
	
	@Test
	@DisplayName(value = "롬복라이브러리: 단정문(assert)")
	void testLombok() {
		//fail("Not yet implemented");
		assertEquals( "n1", p1.getName());
		//assertFalse(c1 == c2);
		assertNotSame(c1, c2); //위와 같음
		assertNotNull(c1);
		assertEquals(c1, c2);
		
//		assertNotNull(c1.getName());//테스트 실패
//		assertNotNull(c1.getAddr());//테스트 실패
		
		assertAll(
				()->assertNotNull(c1.getName())//테스트 실패
				,()->assertNotNull(c1.getAddr())//테스트 실패
		);
	}
	
	@Test
	@DisplayName(value="가정문(assuming)")
	void testAssume() {
		assumingThat(num%2==0, ()->assertEquals(num, 5));//test fail		
	}
	
	@AfterEach
	void ae() {
		System.out.println("@AfterEach : 각 테스트메서드 이후에 호출됨");
	}

	@AfterAll
	static void aa() {
		System.out.println("@AfterAll : 모든 테스트메서드 이후에 1회 호출됨");
	
	}
}

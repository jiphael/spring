package com.my.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.my.vo.Product;

@Controller
public class TestController {
	public TestController() {
		System.out.println("TestController객체생성됨");
	}
	@RequestMapping(value ="/a.do")
	public void a() {
		System.out.println("a()호출됨");
	}
	
	@GetMapping(value="/b.do")
	public void b() {
		System.out.println("b()호출됨");
	}
	@GetMapping(value="/c.do")
//http://localhost/mvc/c.do?t=hello&p=pa&c=2
//	public void c(String t, String p, int c) {
//		System.out.println("c()호출됨.t="+t+",p="+p+",c="+c);
//		
//	}
	public void c(String t, String p, int[] c) {
//http://localhost/mvc/c.do?t=hello&p=pa&c=2&c=3
		System.out.println("c()호출됨.t="+t+",p="+p+",c="+c);
		for(int e:c) {
			System.out.println("c="+e);
		}
	}
	//http://localhost/mvc/c.do?&p=pa&c=2&c=3	//t=null
	//http://localhost/mvc/c.do?t=hello&p=pa //NullPointerException
	//http://localhost/mvc/c.do?r=99&p=pa&c=2&c=3
	
	@GetMapping("/d.do")
	//http://localhost/mvc/d.do?first=hello&second=5
	//http://localhost/mvc/d.do?first=hello
	public void d(@RequestParam(name="first")String a, @RequestParam(name="second", required=false, defaultValue ="0")int b) {
		System.out.println("d()호출됨 a="+a+", b="+b);
	}
	
	@GetMapping("/e.do")
	//http://localhost/mvc/e.do?prod_no=C&prod_name=COFFEE&prod_price=1000
	public void e(Product p) {
		System.out.println("e()호출됨 p" + p);
	}
	
}

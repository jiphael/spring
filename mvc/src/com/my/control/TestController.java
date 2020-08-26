package com.my.control;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.my.vo.Product;

@Controller
//@RestController //view리저브를 사용하는 매서드가 혼합되어 있으면 restController를 붙이면 안됨. ViewResolver, View를 사용안함.
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
	
	@PostMapping("/f.do")
	public void f(@RequestBody String data) {
		 ObjectMapper mapper = new ObjectMapper();
		 
		try {
			List<Product> list = mapper.readValue(data, new TypeReference<List<Product>>(){ });
			for(Product p:list) {
				 System.out.println(p);
			 }
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		System.out.println("f()호출됨");
		System.out.println(data);
	}
	@GetMapping("/g.do")
	public ModelAndView g() {
		ModelAndView mnv = new ModelAndView();
		mnv.addObject("errorMsg", "테스트");
		mnv.setViewName("/fail"); //기본방식 : foward redirect로가고싶으면 rediret:/fail.jsp로 입력
		return mnv;
	}
	
	@GetMapping("/h.do")
	public String h() {
		return "/fail";
	}
	@GetMapping("/i.do")
	public void i() {//viewname은 /i.jsp가 됨
		
	}
	
	@GetMapping("/j.do")
	@ResponseBody
	public Product j() {
		return new Product("C0001", "아메리카노", 1000);
	}
	
	@GetMapping("/k.do")
	@ResponseBody
	public String k() {
		return"{\"id\":\"id1\"}";
	}
	
	@GetMapping("/l.do")
	public void l(HttpServletRequest request, HttpServletResponse response,HttpSession session) {
		
	}
	
	@GetMapping("/m.do")
	public Map m(@ModelAttribute(name="product")Product p){
		Map<String, Object> attrs = new HashMap();
		//attrs.put("product",p);
		attrs.put("test","hello");
		return attrs;
	}
	
}

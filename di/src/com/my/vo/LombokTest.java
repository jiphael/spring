package com.my.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
class Parent{
	String p;
}

@AllArgsConstructor
@NoArgsConstructor
class Child1 extends Parent{
	int c;
	@NonNull
	Date dt;
}
@RequiredArgsConstructor
class Child2 extends Parent{
	int c;
	@NonNull
	Date dt;
}

public class LombokTest {
	public static void main(String[] args) {
		Parent p1 = new Parent();
		p1.setP("부모");
		System.out.println(p1.getP());
		Parent p2 = p1;
		System.out.println(p1.canEqual(p2));
		System.out.println(p1.canEqual(new Customer()));
		
		Child1 c1 = new Child1(5, new Date());
		System.out.println(c1.canEqual(p1));
		c1.setP("자식");
		System.out.println(c1.getP());
		
		Child2 c2 = new Child2(new Date());
		System.out.println(c2.canEqual(p1));
		System.out.println(c2.canEqual(c1));
	}
}
